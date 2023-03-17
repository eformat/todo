# todo

dev mode

```bash
mvn quarkus:dev
```

live updates

```java
@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{name}")
    public String hello(@PathParam("name") String name) {
        return "Hello " + name;
    }
}
```

native build

```bash
mvn compile -Pnative
```

'r' real time tests, update test

```java
@Test
public void testHelloEndpoint() {
    given()
      .when().get("/hello/Mike")
      .then()
         .statusCode(200)
         .body(is("Hello Mike"));
}
```

open api

```xml
        <dependency>
            <groupId>io.quarkus</groupId>
            <artifactId>quarkus-smallrye-openapi</artifactId>
        </dependency>
```

show swagger ui panel from dev ui

add todo

```java
@Path("/todo")
public class TodoResource {

    @GET
    public List<Todo> todos() {
        Todo todo = new Todo();
        todo.name = "Database";
        todo.description = "Add a Database";
        todo.isDone = false;
        return List.of(todo);
    }
}
```

```json
{
  "name": "Database",
  "description": "add a databse",
  "isDone": false
}
```

add postgres 

```xml
        <dependency>
            <groupId>io.quarkus</groupId>
            <artifactId>quarkus-hibernate-orm-panache</artifactId>
        </dependency>

        <dependency>
            <groupId>io.quarkus</groupId>
            <artifactId>quarkus-jdbc-postgresql</artifactId>
        </dependency>
```

show DevServices in Dev UI, postgres config

Add code

```java
@Entity
public class Todo extends PanacheEntity {
```

```java
@Path("/todo")
public class TodoResource {

    @GET
    public List<Todo> todos() {
        return Todo.listAll();
    }

    @PUT
    @Transactional
    public void addTodo(Todo todo) {
        todo.persistAndFlush();
    }
}
```

```json
{
  "name": "Security",
  "description": "add security",
  "isDone": false
}
```

```bash
$ podman ps
CONTAINER ID  IMAGE                          COMMAND               CREATED         STATUS         PORTS                    NAMES
bdb67ce1f619  docker.io/library/postgres:14  postgres -c fsync...  10 minutes ago  Up 10 minutes  0.0.0.0:46581->5432/tcp  affectionate_benz

$ psql -h localhost -p 46581 -U quarkus 
Password for user quarkus: 
psql (14.3, server 14.5 (Debian 14.5-2.pgdg110+2))
Type "help" for help.

quarkus=# \dt+
                                  List of relations
 Schema | Name | Type  |  Owner  | Persistence | Access method | Size  | Description 
--------+------+-------+---------+-------------+---------------+-------+-------------
 public | todo | table | quarkus | permanent   | heap          | 16 kB | 
(1 row)

quarkus=# select * from todo;

 id | description  | isdone |   name   
----+--------------+--------+----------
  1 | add security | f      | Security
(1 row)
```

add oidc keycloak


```java
    @RolesAllowed("admin")
    public void addTodo(Todo todo) {
```

try PUT - 403 Not Allowed
login as bob
try PUT - 401 Not Authorized
login as alice
PUT - 204

```json
{
  "name": "GraphQL",
  "description": "add graphql",
  "isDone": false
}
```

add graphql

```xml
        <dependency>
            <groupId>io.quarkus</groupId>
            <artifactId>quarkus-smallrye-graphql</artifactId>
        </dependency>
```

add TodoGrapQLResource

```java
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
```

```
mutation {
  addTodo(todo: {name: "Spring", description: "What About Spring !?", isDone: false }){
    id
  }
}
```

```
query {
  todos {
    description
  }
}
```
