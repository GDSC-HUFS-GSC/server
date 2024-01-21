package solution.server.recycle.dto;

import lombok.Getter;
import solution.server.recycle.model.Recycle;

public class RecycleDtos {

    @Getter
    public static class RecycleResponseDto {
        private final Long id;
        private final String name;
        private final String imageUrl;
        public RecycleResponseDto(Recycle recycle) {
            this.id = recycle.getId();
            this.name = recycle.getName();
            this.imageUrl = recycle.getImageUrl();
        }
    }
    @Getter
    public static class RecycleRequestDto {
        private String name;
        private String imageUrl;
        public Recycle toEntity() {
            return new Recycle(name,imageUrl);
        }
    }
    @Getter
    public static class RecycleUpdateNameRequestDto {
        private String name;
    }
}
