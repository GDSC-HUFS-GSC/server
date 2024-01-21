package solution.server.infra.gcp.vision.application;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import solution.server.infra.gcp.vision.VisionDtos.VisionResponseDto;

@Service
public class VisionService {

    public VisionResponseDto detect(MultipartFile file) {
        // add code of object detections
        return null;
    }

}
