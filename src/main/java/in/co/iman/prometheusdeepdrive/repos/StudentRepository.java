package in.co.iman.prometheusdeepdrive.repos;


import in.co.iman.prometheusdeepdrive.models.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository  extends CrudRepository<Student, Long> {
}
