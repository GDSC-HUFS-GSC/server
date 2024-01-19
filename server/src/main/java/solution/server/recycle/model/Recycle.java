package solution.server.recycle.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Recycle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recycle_id")
    private Long id;

    @NotEmpty
    @Column(name = "recycle_name", unique = true)
    private String name;

    @Column(name = "recycle_image_url")
    private String img;
}
