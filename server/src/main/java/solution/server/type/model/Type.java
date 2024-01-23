package solution.server.type.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import solution.server.category.model.Category;
import solution.server.item.model.Item;
import solution.server.recycle.model.Recycle;
import solution.server.typetag.model.TypeTag;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    private String imageUrl;

    @Column(name = "how")
    private String how;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "recycle_id")
    private Recycle recycle;

    @OneToMany(mappedBy = "type", cascade = CascadeType.PERSIST)
    private List<TypeTag> typeTags = new ArrayList<>();

    @OneToMany(mappedBy = "type", cascade = CascadeType.PERSIST)
    private List<Item> items = new ArrayList<>();

    public Type(String typeName, String typeImageUrl,String how) {
        checkTypeName(typeName);
        this.name = typeName;
        this.imageUrl = typeImageUrl;
        this.how = how;
    }

    private void checkTypeName(String typeName) {
        if(typeName.length() > 100 ) throw new IllegalArgumentException("[Error] Wrong Size");
    }

    public Type updateName(String newName) {
        this.name = newName;
        return this;
    }

    public Type updateHow(String how) {
        this.name = how;
        return this;
    }

    public Type updateImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Type updateCategory(Category category) {
        this.category = category;
        return this;
    }

    public Type updateRecycle(Recycle recycle) {
        this.recycle = recycle;
        return this;
    }

    public List<String> getHashTags() {
        return typeTags.stream().map(i -> i.getTag().getName()).toList();
    }

    public String getCategoryName() {
        return category.getName();
    }
    public String getRecycleName() {
        return recycle.getName();
    }

    public Type addInfo(Recycle recycle, Category category) {
        this.recycle = recycle;
        this.category = category;
        return this;
    }
}
