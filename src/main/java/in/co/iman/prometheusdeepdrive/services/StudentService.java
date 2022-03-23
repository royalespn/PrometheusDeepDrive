package in.co.iman.prometheusdeepdrive.services;

import in.co.iman.prometheusdeepdrive.models.Student;
import in.co.iman.prometheusdeepdrive.repos.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {

    private static final String REDIS_CACHE_VALUE = "student";

    private final StudentRepository studentRepository;

    @CachePut(value = REDIS_CACHE_VALUE, key = "#student.id")
    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        List<Student> listOfStudents = new ArrayList<>();
        studentRepository.findAll().forEach(listOfStudents::add);
        return listOfStudents;
    }

    @Cacheable(value = REDIS_CACHE_VALUE, key = "#id")
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public void updateStudent(Long id, Student student) {
        studentRepository.save(student);
    }

    @CacheEvict(value = REDIS_CACHE_VALUE, key = "#id")
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
