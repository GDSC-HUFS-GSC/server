package solution.server.category.dto;


import lombok.Getter;
import solution.server.category.model.Category;

public class CategoryDtos {

    @Getter
    public static class CategoryResponseDto {
        private final Long id;
        private final String name;
        private final String imageUrl;
        public CategoryResponseDto(Category category){
            this.id = category.getId();
            this.name = category.getName();
            this.imageUrl = category.getImageUrl();
        }
    }

    @Getter
    public static class CategoryRequestDto {
        private String name;
        private String imageUrl;
        public Category toEntity() { return new Category(name,imageUrl);}
    }

    @Getter
    public static class CategoryUpdateNameRequestDto{
        private String name;
    }
}
