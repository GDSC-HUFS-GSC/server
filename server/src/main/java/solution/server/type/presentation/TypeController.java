package solution.server.type.presentation;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import solution.server.global.common.dto.ApiResponse;
import solution.server.global.file.domain.application.ImageFileService;
import solution.server.type.application.TypeService;
import solution.server.type.dto.TypeDtos.TypeRequestDto;
import solution.server.type.dto.TypeDtos.TypeResponseDto;
import solution.server.type.dto.TypeDtos.TypeUpdateNameRequestDto;

@RestController
@RequestMapping(value = "/v1/type")
@RequiredArgsConstructor
public class TypeController {

    private final TypeService typeService;
    private final ImageFileService imageFileService;

    @GetMapping(value = "", produces = "application/json;charset=UTF-8")
    public ApiResponse<TypeResponseDto> getTypeDetailInfo(@RequestParam Long id) {
        return ApiResponse.success(typeService.getTypeResponseById(id));
    }
    @GetMapping(value = "/name", produces = "application/json;charset=UTF-8")
    public ApiResponse<TypeResponseDto> getTypeDetailInfoByName(@RequestParam String name) {
        return ApiResponse.success(typeService.getTypeResponseByName(name));
    }

    @PostMapping(value = "", produces = "application/json;charset=UTF-8")
    public ApiResponse<TypeResponseDto> addNewType(@RequestBody TypeRequestDto request) {
        return ApiResponse.success(new TypeResponseDto(typeService.addNewType(request.toEntity(),request.getRecycleName(),request.getCategoryName())));
    }

    @PutMapping(value = "/image", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ApiResponse<TypeResponseDto> updateTypeImageUrl(@RequestParam("typeName") String typeName, @RequestParam("file") MultipartFile file) {
        String imageUrl = imageFileService.uploadImageFile(file, "Type");
        return ApiResponse.success(typeService.updateImageByName(typeName, imageUrl));
    }

    @PutMapping(value = "/name")
    public ApiResponse<TypeResponseDto> updateTypeName(@RequestParam("typeName") String typeName,
                                                       @RequestBody TypeUpdateNameRequestDto request) {
        return ApiResponse.success(typeService.updateTypeName(typeName, request.getTypeName()));
    }

    @GetMapping(value = "/all", produces = "application/json;charset=UTF-8")
    public ApiResponse<List<TypeResponseDto>> getAllTypes() {
        return ApiResponse.success(typeService.getAllTypeResponse());
    }
}