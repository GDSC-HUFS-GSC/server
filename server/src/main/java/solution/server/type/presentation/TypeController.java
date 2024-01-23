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

@RestController
@RequestMapping(value = "/v1/type")
@RequiredArgsConstructor
public class TypeController {

    private final TypeService typeService;
    private final ImageFileService imageFileService;

    @GetMapping(value = "", produces = "application/json;charset=UTF-8")
    public ApiResponse<TypeResponseDto> getTypeDetailInfo(@RequestParam Long id) {
        var type = new TypeResponseDto(typeService.getTypeById(id));
        return ApiResponse.success(type);
    }
    @GetMapping(value = "/name", produces = "application/json;charset=UTF-8")
    public ApiResponse<TypeResponseDto> getTypeDetailInfoByName(@RequestParam String name) {
        var type = new TypeResponseDto(typeService.getTypeByName(name));
        return ApiResponse.success(type);
    }

    @PostMapping(value = "", produces = "application/json;charset=UTF-8")
    public ApiResponse<TypeResponseDto> addNewType(@RequestBody TypeRequestDto request) {
        return ApiResponse.success(new TypeResponseDto(typeService.addNewType(request.toEntity())));
    }

    @PutMapping(value = "/image", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ApiResponse<TypeResponseDto> updateTypeImageUrl(@RequestParam("typeId") Long typeId, @RequestParam("file") MultipartFile file) {
        String imageUrl = imageFileService.uploadImageFile(file, "Type");
        var type = typeService.updateImageUrl(typeId, imageUrl);
        return ApiResponse.success(new TypeResponseDto(type));
    }

    @PutMapping(value = "/name")
    public ApiResponse<TypeResponseDto> updateTypeName(@RequestParam("typeId") Long typeId,
                                                       @RequestBody TypeUpdateNameRequestDto request) {
        var type = typeService.updateTypeName(typeId, request.getName());
        return ApiResponse.success(new TypeResponseDto(type));
    }

    @GetMapping(value = "/all", produces = "application/json;charset=UTF-8")
    public ApiResponse<List<TypeResponseDto>> getAllTypes() {
        List<TypeResponseDto> itemDetailResponse = typeService.getAllTypes().stream().map(TypeResponseDto::new).toList();
        return ApiResponse.success(itemDetailResponse);
    }
}