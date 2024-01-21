package solution.server.recycle.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solution.server.recycle.model.Recycle;
import solution.server.recycle.repository.RecycleRepository;

import java.util.List;

@Service
@Transactional
public class RecycleService {


    private RecycleRepository recycleRepository;

    //분류 등록
    public Long addNewRecycle(String name){
        Recycle recycle = new Recycle();
        recycle.setName(name);
        recycleRepository.save(recycle);
        return recycle.getId();
    }

    // 모든 분류 조회
    public List<Recycle> getAllRecycles(){ return recycleRepository.findAll();}

    // 분류 수정
    public Long changeRecycle(String nameOriginal, String nameNew){
        Recycle recycle = recycleRepository.findByName(nameOriginal);
        recycle.setName(nameNew);
        recycleRepository.save(recycle);
        return recycle.getId();
    }

    // 분류 삭제
    public Long deleteRecycle(String name){
        Recycle recycle = recycleRepository.findByName(name);
        recycleRepository.delete(recycle);
        return recycle.getId();
    }
}
