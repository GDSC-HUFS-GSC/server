package solution.server.recycle.dto;

import lombok.Getter;
import solution.server.recycle.model.Recycle;

public class RecycleDtos {

    @Getter
    public static class RecycleResponseDto {
        private final Long recycleId;
        private final String recycleName;
        private final String recycleImageUrl;
        public RecycleResponseDto(Recycle recycle) {
            this.recycleId = recycle.getId();
            this.recycleName = recycle.getName();
            this.recycleImageUrl = recycle.getImageUrl();
        }
    }
    @Getter
    public static class RecycleRequestDto {
        private String recycleName;
        private String recycleImageUrl;
        public Recycle toEntity() {
            return new Recycle(recycleName,recycleImageUrl);
        }
    }
    @Getter
    public static class RecycleUpdateNameRequestDto {
        private String recycleName;
    }
}
