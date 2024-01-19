package solution.server.category.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @NotEmpty
    @Column(name = "category_name", unique = true)
    private String name;

    @Column(name = "category_image_url")
    private String img;
}
