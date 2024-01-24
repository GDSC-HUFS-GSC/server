package solution.server.typetag.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import solution.server.tag.model.Tag;
import solution.server.type.model.Type;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    public TypeTag updateType(Type type){
        this.type = type;
        return this;
    }

    public TypeTag updateTag(Tag tag){
        this.tag = tag;
        return this;
    }

    public String getTypeName(){return type.getName();}
    public String getTagName(){return tag.getName();}

    public TypeTag addInfo(Type type, Tag tag){
        this.type = type;
        this.tag = tag;
        return this;
    }
}
