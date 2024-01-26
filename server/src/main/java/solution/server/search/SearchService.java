package solution.server.search;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solution.server.search.SearchDtos.SearchRequestDto;
import solution.server.type.dto.TypeDtos.TypeResponseDto;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class SearchService {

    public List<TypeResponseDto> getSearchResult(SearchRequestDto requestDto) {
        return null;
    }
}
