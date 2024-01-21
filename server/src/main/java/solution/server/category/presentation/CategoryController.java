package solution.server.category.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import solution.server.category.application.CategoryService;
import solution.server.global.common.dto.ApiResponse;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    //@PostMapping(produces = "application/json;charset=UTF-8")
    //public ApiResponse<Boolean> postCategory(@RequestHeader)
}
