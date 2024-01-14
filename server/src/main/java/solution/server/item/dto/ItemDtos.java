package solution.server.item.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import solution.server.item.model.Item;

public class ItemDtos {
    @Getter
    @NoArgsConstructor
    public static class ItemDetailDto {
        public ItemDetailDto(Item item) {
        }
    }

    @Getter
    @NoArgsConstructor
    public static class ItemPostRequestDto {
        private String barCode;
        private String name;
        public Item toEntity() {
            return new Item(name,barCode);
        }
    }

    @Getter
    @NoArgsConstructor
    public static class ItemResponseDetailDto {
        public ItemResponseDetailDto(Item item) {
        }
    }
}
