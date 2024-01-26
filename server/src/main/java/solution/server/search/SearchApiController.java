package solution.server.search;

import java.util.List;
import javax.swing.event.ListDataEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import solution.server.global.common.dto.ApiResponse;
import solution.server.search.SearchDtos.SearchRequestDto;
import solution.server.type.dto.TypeDtos.TypeResponseDto;


@RestController
@RequestMapping(value = "/v1/search")
@RequiredArgsConstructor
public class SearchApiController {
    private final SearchService searchService;

    @GetMapping(value = "", produces = "application/json;charset=UTF-8")
    public ApiResponse<List<TypeResponseDto>> getTypeDetailInfo(@RequestBody SearchRequestDto requestDto) {
        return ApiResponse.success(searchService.getSearchResult(requestDto));
    }
}
