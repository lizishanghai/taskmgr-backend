package api;

//import api.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "tasks", path = "tasks")
public interface TaskRepo extends MongoRepository<Task, String> {
    public Task findByDesc(String desc);
    public List<Task> findByCompleted(Boolean completed);

}
