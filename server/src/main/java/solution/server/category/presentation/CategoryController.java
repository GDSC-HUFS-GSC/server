package solution.server.category.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import solution.server.category.application.CategoryService;
import solution.server.category.dto.CategoryDtos.CategoryResponseDto;
import solution.server.category.dto.CategoryDtos.CategoryRequestDto;
import solution.server.category.dto.CategoryDtos.CategoryUpdateNameRequestDto;
import solution.server.global.common.dto.ApiResponse;
import solution.server.global.file.domain.application.ImageFileService;

import java.util.List;
import solution.server.type.application.TypeService;
import solution.server.type.dto.TypeDtos.TypeResponseDto;

@RestController
@RequestMapping(value = "/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final TypeService typeService;
    private final CategoryService categoryService;
    private final ImageFileService imageFileService;
    @GetMapping(value = "", produces = "application/json;charset=UTF-8")
    public ApiResponse<List<TypeResponseDto>> getResultDetailInfo(@RequestParam String name) {
        var category = typeService.getTypeListByCategoryName(name);
        return ApiResponse.success(category);
    }

    @GetMapping(value = "/name", produces = "application/json;charset=UTF-8")
    public ApiResponse<CategoryResponseDto> getCategoryDetailInfo(@RequestParam String name) {
        var category = new CategoryResponseDto(categoryService.getCategoryByName(name));
        return ApiResponse.success(category);
    }

    @PostMapping(value = "",produces = "application/json;charset=UTF-8")
    public ApiResponse<CategoryResponseDto> addNewCategory(@RequestBody CategoryRequestDto request){
        return ApiResponse.success(new CategoryResponseDto(categoryService.addNewCategory(request.toEntity())));
    }

    @PutMapping(value = "/image", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ApiResponse<CategoryResponseDto> updateCategoryImageUrl(@RequestParam("categoryId") Long categoryId, @RequestParam("file")MultipartFile file){
        String imageUrl = imageFileService.uploadImageFile(file, "Recycle");
        var category = categoryService.updateImageUrl(categoryId, imageUrl);
        return ApiResponse.success(new CategoryResponseDto(category));
    }

    @PutMapping(value = "/name")
    public ApiResponse<CategoryResponseDto> updateCategoryName(@RequestParam("categoryId") Long categoryId,
                                                               @RequestBody CategoryUpdateNameRequestDto request){
        var category = categoryService.updateCategoryName(categoryId, request.getName());
        return ApiResponse.success(new CategoryResponseDto(category));
    }

    @GetMapping(value = "/all", produces = "application/json;charset=UTF-8")
    public ApiResponse<List<CategoryResponseDto>> getAllCategory(){
        List<CategoryResponseDto> ItemDetailResponse = categoryService.getAllCategories().stream().map(CategoryResponseDto::new).toList();
        return ApiResponse.success(ItemDetailResponse);
    }
}
