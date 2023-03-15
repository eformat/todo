package au.com.brisbane;

import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/todo")
public class TodoResource {

    @GET
    public List<Todo> todos() {
//        Todo persistTodo = new Todo();
//        persistTodo.description = "Persist the todo in a DB";
//        persistTodo.title = "Persist";
//        persistTodo.isDone = false;
//        return List.of(persistTodo);

        return Todo.listAll();
    }
    
    @POST
    @RolesAllowed("admin")
    @Transactional
    public void addTodo(Todo todo){
        todo.persistAndFlush();
    }
    
}