package solution.server.infra.gcp.vision;

import java.io.IOException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import solution.server.global.common.dto.ApiResponse;
import solution.server.infra.gcp.vision.VisionDtos.VisionResponseDto;
import solution.server.infra.gcp.vision.application.VisionService;

@RestController
@RequestMapping("/v1/vision/test")
@AllArgsConstructor
public class VisionApi {

        private VisionService visionService;
    @PostMapping(value = "/image", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ApiResponse<VisionResponseDto> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        return ApiResponse.success(HttpStatus.CREATED, visionService.detect(file));
    }
}
