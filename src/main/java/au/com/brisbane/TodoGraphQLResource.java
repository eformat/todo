package au.com.brisbane;

import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Query;

import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import java.util.List;

@GraphQLApi
public class TodoGraphQLResource {

    @Query
    public List<Todo> todos() {
        return Todo.listAll();
    }

    @Mutation
    @Transactional
    @RolesAllowed("admin")
    public Todo addTodo(Todo todo) {
        todo.persistAndFlush();
        return todo;
    }
}
