package solution.server.global.file.domain.application;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import solution.server.global.file.domain.model.ImageFile;
import solution.server.global.file.dto.ImageDtos.ImageDto;
import solution.server.global.file.infrastructure.GlobalImageRepository;


@Transactional
@Service
@RequiredArgsConstructor
public class GlobalImageFileService {

    private final GlobalImageRepository globalImageRepository;

    private final ImageFileService imageFileService;

    @Transactional(readOnly = true)
    public ImageDto getGlobalImageFileById(Long id) {
        ImageFile imageFile = globalImageRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Image not found for id: " + id));
        return new ImageDto(imageFile);
    }

    public ImageDto uploadGlobalImageFile(MultipartFile file, String folderName) {
        String fileName = imageFileService.uploadImageFile(file,folderName);
        ImageFile imageFile = new ImageFile(file,fileName,folderName);
        globalImageRepository.save(imageFile);
        return new ImageDto(imageFile);
    }

    public List<ImageDto> uploadGlobalImageFiles(MultipartFile[] files, String folderName) {
        List<ImageDto> uploadImageFiles= imageFileService.uploadImageFiles(files,folderName);
        List<ImageFile> uploadedFiles = uploadImageFiles
                    .stream()
                    .map(ImageFile::new)
                    .toList();
        globalImageRepository.saveAll(uploadedFiles);
        return uploadImageFiles;
    }
}
