package solution.server.category.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @NotEmpty
    @Column(name = "category_name", unique = true)
    private String name;

    @Column(name = "category_image_url")
    private String imageUrl;

    public Category(String name, String imageUrl){
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public void updateName(String newName) { this.name = newName; }

    public void updateImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
