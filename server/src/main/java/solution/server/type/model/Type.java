package solution.server.type.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import solution.server.category.model.Category;
import solution.server.recycle.model.Recycle;
import solution.server.typetag.model.TypeTag;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@Setter
@Entity
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private Long id;

    @NotEmpty
    @Column(name = "type_name", unique = true)
    private String name;

    @Column(name = "type_image_url")
    private String img;

    @Column(name = "how")
    private String how;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "recycle_id")
    private Recycle recycle;

    @OneToMany(mappedBy = "type", cascade = CascadeType.PERSIST)
    private List<TypeTag> typeTag = new ArrayList<>();

}
