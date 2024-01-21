package solution.server.typetag.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import solution.server.tag.model.Tag;
import solution.server.type.model.Type;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@Setter
@Entity
public class TypeTag {
    @Id
    @GeneratedValue
    @Column(name = "type_tag_id")
    private Long id;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "type_id")
    private Type type;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "tag_id")
    private Tag tag;
}
