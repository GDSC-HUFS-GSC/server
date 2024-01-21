package solution.server.infra.gcp.storage.domain.model;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;
import solution.server.global.file.domain.model.ImageFile;

@Getter
public class GCPStorageFile extends ImageFile {

    public GCPStorageFile(MultipartFile file, String fileName, String folderName) {
        this.file = file;
        this.fileName = fileName;
        this.fileType = file.getContentType();
        this.fileSize = file.getSize();
        this.folderName = folderName;
    }
}
