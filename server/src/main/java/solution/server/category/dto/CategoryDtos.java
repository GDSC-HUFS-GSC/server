package solution.server.category.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import solution.server.category.model.Category;

public class CategoryDtos {

    @Getter
    public static class CategoryResponseDto {
        private String CategoryName;
        private String CategoryImageUrl;
        public CategoryResponseDto(Category category){
            this.CategoryName = category.getName();
            this.CategoryImageUrl = category.getImg();
        }
    }
}
