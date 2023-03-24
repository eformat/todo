package au.com.brisbane;


import jakarta.annotation.security.RolesAllowed;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;

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
