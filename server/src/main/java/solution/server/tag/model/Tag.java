package solution.server.tag.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import solution.server.typetag.model.TypeTag;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long id;

    @NotEmpty
    @Column(name = "tag_name", unique = true)
    private String name;

    @OneToMany(mappedBy = "tag", cascade = CascadeType.PERSIST)
    private List<TypeTag> typeTag = new ArrayList<>();

    public Tag(String name){ this.name = name;}

    public void updateName(String newName) { this.name = newName;}
}