package solution.server.infra.gcp.storage.domain.application;

import com.google.cloud.storage.HttpMethod;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import solution.server.global.file.domain.application.ImageFileHandle;
import solution.server.global.file.domain.application.ImageFileNameUtil;
import solution.server.global.file.domain.model.ImageFile;
import solution.server.infra.gcp.storage.domain.model.GCPStorageFile;

@Service
public class GCPStorageService implements ImageFileHandle {
    private final static int EMPTY_FILE_SIZE = 0;
    private final Storage gcpStorage;
    private final ImageFileNameUtil imageFileNameUtil;
    private final String bucketName;

    public GCPStorageService(Storage gcpStorage,@Value("${cloud.gcp.storage.bucket}") String bucketId) {
        this.gcpStorage = gcpStorage;
        this.imageFileNameUtil = new ImageFileNameUtil();
        this.bucketName = bucketId;
    }

    @Override
    public void deleteImage(String fileName, String folderName) {
        String key = folderName + "/" + fileName;
        gcpStorage.delete(BlobId.of(bucketName, key));
    }

    @Override
    public ImageFile uploadImageFile(MultipartFile file, String folderName) {
        String fileUrl = sendImageFileToGCP(file, folderName).toString();
        String fileName = imageFileNameUtil.cutFullFileUrlIntoNameOnlyGCP(fileUrl);
        return new GCPStorageFile(file, fileName, folderName);
    }

    @Override
    public List<ImageFile> uploadImageFiles(MultipartFile[] files, String folderName) {
        return Arrays.stream(files)
                .map(file -> uploadImageFile(file, folderName))
                .collect(Collectors.toList());
    }

    private URL sendImageFileToGCP(MultipartFile file, String folderName) {
        if (!checkFolderExistence(folderName)) {
            makeFolder(folderName);
        }
        String key = constructObjectForGCS(file, folderName);
        return gcpStorage.signUrl(BlobInfo.newBuilder(bucketName, key).build(), 365, TimeUnit.DAYS,Storage.SignUrlOption.httpMethod(HttpMethod.PUT)
        );
    }

    private boolean checkFolderExistence(String folderName) {
        return gcpStorage.list(bucketName, Storage.BlobListOption.prefix(folderName + "/"))
                .iterateAll()
                .iterator()
                .hasNext();
    }

    private void makeFolder(String folderName) {
        InputStream inputStream = new ByteArrayInputStream(new byte[EMPTY_FILE_SIZE]);
        String key = folderName + "/";
        uploadObjectToGCS(key, inputStream, EMPTY_FILE_SIZE, "application/x-directory");
    }

    private String constructObjectForGCS(MultipartFile file, String folderName) {
        String fileName = file.getOriginalFilename();
        String key = folderName + "/" + fileName + imageFileNameUtil.getLocalDateTimeMilliseconds();
        try {
            InputStream inputStream = file.getInputStream();
            return uploadObjectToGCS(key, inputStream, file.getSize(), file.getContentType());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String uploadObjectToGCS(String key, InputStream inputStream, long size,
                                     String contentType) {
        BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, key)
                .setContentType(contentType)
                .build();

        gcpStorage.create(blobInfo, inputStream);
        return key;
    }

}