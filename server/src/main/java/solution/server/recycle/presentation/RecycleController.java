package solution.server.recycle.presentation;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import solution.server.global.common.dto.ApiResponse;
import solution.server.global.file.domain.application.ImageFileService;
import solution.server.recycle.application.RecycleService;
import solution.server.recycle.dto.RecycleDtos.RecycleRequestDto;
import solution.server.recycle.dto.RecycleDtos.RecycleResponseDto;
import solution.server.recycle.dto.RecycleDtos.RecycleUpdateNameRequestDto;

@RestController
@RequestMapping(value = "/v1/recycle")
@RequiredArgsConstructor
public class RecycleController {

    private final RecycleService recycleService;
    private final ImageFileService imageFileService;
    @GetMapping(value = "", produces = "application/json;charset=UTF-8")
    public ApiResponse<RecycleResponseDto> getRecycleDetailInfo(@RequestParam String name) {
        var recycle  = new RecycleResponseDto(recycleService.getRecycleByName(name));
        return ApiResponse.success(recycle);
    }
    @PostMapping(value = "",produces = "application/json;charset=UTF-8")
    public ApiResponse<RecycleResponseDto> addNewRecycle(@RequestBody RecycleRequestDto request) {
        return ApiResponse.success(new RecycleResponseDto(recycleService.addNewRecycle(request.toEntity())));
    }

    @PutMapping(value = "/image", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ApiResponse<RecycleResponseDto> updateRecycleImageUrl(@RequestParam("recycleId") Long recycleId, @RequestParam("file") MultipartFile file) {
        String imageUrl = imageFileService.uploadImageFile(file, "Recycle");
        var recycle = recycleService.updateImageUrl(recycleId, imageUrl);
        return ApiResponse.success(new RecycleResponseDto(recycle));
    }
    @PutMapping(value = "/name")
    public ApiResponse<RecycleResponseDto> updateRecycleName(@RequestParam("recycleId") Long recycleId,
                                                             @RequestBody RecycleUpdateNameRequestDto request) {
        var recycle = recycleService.updateRecycleName(recycleId,request.getRecycleName());
        return ApiResponse.success(new RecycleResponseDto(recycle));
    }

    @GetMapping(value = "/all", produces = "application/json;charset=UTF-8")
    public ApiResponse<List<RecycleResponseDto>> getAllRecycle() {
        List<RecycleResponseDto> ItemDetailResponse = recycleService.getAllRecycles().stream().map(RecycleResponseDto::new).toList();
        return ApiResponse.success(ItemDetailResponse);
    }
}
