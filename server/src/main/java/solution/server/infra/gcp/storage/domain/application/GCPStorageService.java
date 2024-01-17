package solution.server.infra.gcp.storage.domain.application;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import solution.server.global.file.domain.application.ImageFileHandle;
import solution.server.global.file.domain.model.ImageFile;

public class GCPStorageService implements ImageFileHandle {
    @Override
    public ImageFile uploadImageFile(MultipartFile file, String folderName) {
        return null;
    }

    @Override
    public List<ImageFile> uploadImageFiles(MultipartFile[] file, String folderName) {
        return null;
    }

    @Override
    public void deleteImage(String fileName, String folderName) {

    }
}
