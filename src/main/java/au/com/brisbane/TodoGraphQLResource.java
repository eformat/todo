package au.com.brisbane;

import jakarta.annotation.security.RolesAllowed;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.graphql.GraphQLApi;

import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Query;

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
