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

    // 태그 등록
    public Tag addNewTag(Tag tag){
        tagRepository.save(tag);
        return tag;
    }

    // 모든 태그 조회
    public List<Tag> getAllTags() { return tagRepository.findAll();}

    // 이름으로 태그 단건 조회
    public Tag getTagByName(String name){ return tagRepository.findByName(name);}

    // 아이디로 태그 단건 조회
    public Tag getTagById(Long id){
        return tagRepository.findById(id).orElseThrow(()->new IllegalArgumentException("[Error]"));
    }

    // 태그 수정
    public Tag updateTagName(Long tagId, String newName) {
        Tag tag = getTagById(tagId);
        tag.updateName(newName);
        return tag;
    }

    // 태그 삭제
    public void deleteTag(String name){
        Tag tag = tagRepository.findByName(name);
        tagRepository.delete(tag);
    }
}