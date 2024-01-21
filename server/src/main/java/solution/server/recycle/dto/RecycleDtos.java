package solution.server.recycle.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import solution.server.recycle.model.Recycle;

public class RecycleDtos {

    @Getter
    public static class RecycleResponseDto {
        private String RecycleName;
        private String RecycleImageUrl;
        public RecycleResponseDto(Recycle recycle){
            this.RecycleName = recycle.getName();
            this.RecycleImageUrl = recycle.getImg();
        }
    }
}
