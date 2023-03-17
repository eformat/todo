package au.com.brisbane;

import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import java.util.List;

@Path("/todo")
public class TodoResource {

    @GET
    public List<Todo> todos() {
        return Todo.listAll();
    }

    @PUT
    @Transactional
    @RolesAllowed("admin")
    public void addTodo(Todo todo) {
        todo.persistAndFlush();
    }
}
