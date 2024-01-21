package solution.server.recycle.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Recycle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recycle_id")
    private Long id;

    @NotEmpty
    @Column(name = "recycle_name", unique = true)
    private String name;

    @Column(name = "recycle_image_url")
    private String imageUrl;

    public Recycle(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public void updateName(String newName) {
        this.name = newName;
    }

    public void updateImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
