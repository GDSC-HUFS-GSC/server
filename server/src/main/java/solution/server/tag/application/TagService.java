package solution.server.tag.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solution.server.tag.model.Tag;
import solution.server.tag.repository.TagRepository;

import java.util.List;

@Service
@Transactional
public class TagService {

    private TagRepository tagRepository;

    // 태그 등록
    public Long addNewTag(String name){
        Tag tag = new Tag();
        tag.setName(name);
        tagRepository.save(tag);
        return tag.getId();
    }

    // 모든 태그 조회
    public List<Tag> getAllTags() { return tagRepository.findAll();}

    // 태그 수정
    public Long ChangeTag(String nameOriginal, String nameNew) {
        Tag tag = tagRepository.findByName(nameOriginal);
        tag.setName(nameNew);
        tagRepository.save(tag);
        return tag.getId();
    }

    // 태그 삭제
    public Long deleteTag(String name){
        Tag tag = tagRepository.findByName(name);
        tagRepository.delete(tag);
        return tag.getId();
    }
}