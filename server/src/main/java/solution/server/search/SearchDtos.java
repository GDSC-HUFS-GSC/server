package solution.server.search;

import lombok.Getter;

public class SearchDtos {

    @Getter
    public static class SearchRequestDto {
        private String word;
    }
}
