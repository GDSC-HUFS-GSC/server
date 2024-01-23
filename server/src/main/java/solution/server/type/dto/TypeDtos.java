package solution.server.type.dto;

import java.util.List;
import lombok.Getter;
import solution.server.type.model.Type;

public class TypeDtos {

 @Getter
    public static class TypeResponseDto {
        private final Long typeId;
        private final String typeName;
        private final String typeHow;
        private final String typeImageUrl;
        private final String categoryName;
        private final String recycleName;
        private final List<String> hashTags;

        public TypeResponseDto(Type type){
            this.typeId = type.getId();
            this.typeName = type.getName();
            this.categoryName = type.getCategoryName();
            this.recycleName = type.getRecycleName();
            this.typeHow = type.getHow();
            this.typeImageUrl = type.getImageUrl();
            this.hashTags = type.getHashTags();
        }
    }
    @Getter
    public static class TypeRequestDto {
        private String name;
        private String imageUrl;

        public Type toEntity() {
            return new Type() ;
        }
    }
}
