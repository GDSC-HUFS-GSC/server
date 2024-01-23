package solution.server.recycle.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solution.server.recycle.model.Recycle;
import solution.server.recycle.repository.RecycleRepository;

import java.util.List;
@Transactional
@Service
@RequiredArgsConstructor
public class RecycleService {
    private final RecycleRepository recycleRepository;
    public Recycle addNewRecycle(Recycle recycle) {
        recycleRepository.save(recycle);
        return recycle;
    }
    public List<Recycle> getAllRecycles() {
        return recycleRepository.findAll();
    }
    public Recycle getRecycleByName(String name) {
        return recycleRepository.findByName(name);
    }
    public Recycle getRecycleById(Long id) {
        return recycleRepository.findById(id).orElseThrow(()->new IllegalArgumentException("[Error]"));
    }
    public Recycle updateImageUrl(Long recycleId, String imageUrl) {
        Recycle recycle = getRecycleById(recycleId);
        recycle.updateImageUrl(imageUrl);
        return recycle;
    }
    public Recycle updateRecycleName(Long recycleId, String newName) {
        Recycle recycle = getRecycleById(recycleId);
        recycle.updateName(newName);
        recycleRepository.save(recycle);
        return recycle;
    }
    public void deleteRecycle(String name) {
        Recycle recycle = getRecycleByName(name);
        recycleRepository.delete(recycle);
    }
}
