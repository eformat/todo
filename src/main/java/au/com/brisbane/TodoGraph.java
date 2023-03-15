package au.com.brisbane;

import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Query;

@GraphQLApi
public class TodoGraph {

    @Query
    public List<Todo> todos() {
//        Todo persistTodo = new Todo();
//        persistTodo.description = "Persist the todo in a DB";
//        persistTodo.title = "Persist";
//        persistTodo.isDone = false;
//        return List.of(persistTodo);

        return Todo.listAll();
    }
    
    @Mutation
    @RolesAllowed("admin")
    @Transactional
    public Todo addTodo(Todo todo){
        todo.persistAndFlush();
        return todo;
    }
    
}