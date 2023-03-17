package au.com.brisbane;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/todoSpring")
public class TodoSpringResource {

    @GetMapping
    public List<Todo> todos() {
        return Todo.listAll();
    }

    @Secured("admin")
    @PutMapping
    @Transactional
    public void addTodo(Todo todo) {
        todo.persistAndFlush();
    }
}
