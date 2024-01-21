package solution.server.infra.gcp.vision;

import lombok.Getter;
import lombok.NoArgsConstructor;

public class VisionDtos {

    @Getter
    @NoArgsConstructor
    public static class VisionResponseDto {
        private String objectName;
        public VisionResponseDto(String objectName)  {
            this.objectName = objectName;
        }
    }
}
