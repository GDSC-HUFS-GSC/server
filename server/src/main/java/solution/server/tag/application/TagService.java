package solution.server.tag.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solution.server.tag.model.Tag;
import solution.server.tag.repository.TagRepository;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    public Tag addNewTag(Tag tag){
        tagRepository.save(tag);
        return tag;
    }

    public List<Tag> getAllTags() { return tagRepository.findAll();}

    public Tag getTagByName(String name){ return tagRepository.findByName(name);}

    public Tag getTagById(Long id){
        return tagRepository.findById(id).orElseThrow(()->new IllegalArgumentException("[Error]"));
    }

    public Tag updateTagName(Long tagId, String newName) {
        Tag tag = getTagById(tagId);
        tag.updateName(newName);
        return tag;
    }

    public void deleteTag(String name){
        Tag tag = tagRepository.findByName(name);
        tagRepository.delete(tag);
    }
}