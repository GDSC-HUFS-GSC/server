package solution.server.tag.dto;

import lombok.Getter;
import solution.server.tag.model.Tag;
import solution.server.typetag.model.TypeTag;

import java.util.List;

public class TagDtos {

    @Getter
    public static class TagResponseDto {
        private final Long id;
        private final String name;
        public TagResponseDto(Tag tag){
            this.id = tag.getId();
            this.name = tag.getName();
        }
    }

    @Getter
    public static class TagRequestDto {
        private String name;
        public Tag toEntity() { return new Tag(name);}
    }

    @Getter
    public static class TagUpdateNameRequestDto{
        private String name;
    }
}
