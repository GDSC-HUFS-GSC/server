package solution.server.tag.dto;

import lombok.Getter;
import solution.server.tag.model.Tag;
import solution.server.typetag.model.TypeTag;

import java.util.List;

public class TagDtos {

    @Getter
    public static class TagResponseDto {
        private final Long tagId;
        private final String tagName;
        public TagResponseDto(Tag tag){
            this.tagId = tag.getId();
            this.tagName = tag.getName();
        }
    }

    @Getter
    public static class TagRequestDto {
        private String tagName;
        public Tag toEntity() { return new Tag(tagName);}
    }

    @Getter
    public static class TagUpdateNameRequestDto{
        private String tagName;
    }
}
