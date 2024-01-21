package solution.server.item.presentation;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import solution.server.global.common.dto.ApiResponse;
import solution.server.global.file.domain.application.ImageFileService;
import solution.server.item.application.ItemService;
import solution.server.item.dto.ItemDtos.ItemDetailDto;
import solution.server.item.dto.ItemDtos.ItemPostRequestDto;
import solution.server.item.dto.ItemDtos.ItemResponseDetailDto;
import solution.server.item.model.Item;

@RestController
@RequestMapping(value = "/v1/item")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final ImageFileService imageFileService;

    @PostMapping(produces = "application/json;charset=UTF-8")
    public ApiResponse<ItemDetailDto> addNewItem(@RequestBody ItemPostRequestDto request) {
        return ApiResponse.success(new ItemDetailDto(itemService.addNewItem(request.toEntity())));
    }

    @PutMapping(value = "/image", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ApiResponse<ItemDetailDto> updateItemImageUrl(@RequestParam("ItemId") Long ItemId,
                                          @RequestParam("file") MultipartFile file) {
        String imageUrl = imageFileService.uploadImageFile(file, "Item");
        Item Item = itemService.changeImageUrl(ItemId, imageUrl);
        return ApiResponse.success(new ItemDetailDto(Item));
    }

    @GetMapping(value = "/{ItemId}", produces = "application/json;charset=UTF-8")
    public ApiResponse<ItemResponseDetailDto> getItemDetailInfo(@PathVariable Long ItemId,
                                                                @RequestHeader("Authorization") String token) {
        ItemResponseDetailDto ItemDetailDto = new ItemResponseDetailDto(itemService.getItemDetailById(ItemId, token));
        return ApiResponse.success(ItemDetailDto);
    }

    @GetMapping(value = "/all", produces = "application/json;charset=UTF-8")
    public ApiResponse<List<ItemDetailDto>> getAllItems() {
        List<ItemDetailDto> ItemDetailResponse = itemService.getAllItems().stream().map(ItemDetailDto::new).collect(Collectors.toList());
        return ApiResponse.success(ItemDetailResponse);
    }
}
