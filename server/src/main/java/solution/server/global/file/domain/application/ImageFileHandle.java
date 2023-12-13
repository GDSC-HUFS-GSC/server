package solution.server.global.file.domain.application;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import solution.server.global.file.domain.model.ImageFile;
public interface ImageFileHandle {
    ImageFile uploadImageFile(MultipartFile file, String folderName);
    List<ImageFile> uploadImageFiles(MultipartFile[] file, String folderName);
    void deleteImage(String fileName, String folderName);
}
