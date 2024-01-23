package solution.server.tag.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import solution.server.global.common.dto.ApiResponse;
import solution.server.recycle.dto.RecycleDtos;
import solution.server.tag.application.TagService;
import solution.server.tag.dto.TagDtos;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/tag")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @GetMapping(value = "", produces = "application/json;charset=UTF-8")
    public ApiResponse<TagDtos.TagResponseDto> getTagDetailInfo(@RequestParam String name){
        var tag = new TagDtos.TagResponseDto(tagService.getTagByName(name));
        return ApiResponse.success(tag);
    }

    @PostMapping(value = "", produces = "application/json;charset=UTF-8")
    public ApiResponse<TagDtos.TagResponseDto> addNewTag(@RequestBody TagDtos.TagRequestDto request){
        return ApiResponse.success(new TagDtos.TagResponseDto(tagService.addNewTag(request.toEntity())));
    }

    @PutMapping(value = "/name")
    public ApiResponse<TagDtos.TagResponseDto> updateTagName(@RequestParam("tagId") Long tagId,
                                                                     @RequestBody TagDtos.TagUpdateNameRequestDto request) {
        var tag = tagService.updateTagName(tagId, request.getName());
        return ApiResponse.success(new TagDtos.TagResponseDto(tag));
    }

    @GetMapping(value = "/all", produces = "application/json;charset=UTF-8")
    public ApiResponse<List<TagDtos.TagResponseDto>> getAllTag() {
        List<TagDtos.TagResponseDto> ItemDetailResponse = tagService.getAllTags().stream().map(TagDtos.TagResponseDto::new).toList();
        return ApiResponse.success(ItemDetailResponse);
    }
}
