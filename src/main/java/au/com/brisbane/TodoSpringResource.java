package au.com.brisbane;

import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spring/todo")
public class TodoSpringResource {

    @GetMapping
    public List<Todo> todos() {
        return Todo.listAll();
    }
    
    @PostMapping
    @RolesAllowed("admin")
    @Transactional
    public void addTodo(Todo todo){
        todo.persistAndFlush();
    }
    
}