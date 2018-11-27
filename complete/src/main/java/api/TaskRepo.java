package api;

//import api.Task;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.repository.query.Param;


@RepositoryRestResource(collectionResourceRel = "tasks", path = "tasks")
public interface TaskRepo extends MongoRepository<Task, String> {
//    public Task findByDesc(String desc);
//    public List<Task> findByCompleted(Boolean completed);

//    public Task findByDesc(@Param("desc") String desc);
//    public List<Task> findByCompleted(@Param("completed") Boolean completed);

    List<Task> findByDesc(@Param("desc") String desc);

}
