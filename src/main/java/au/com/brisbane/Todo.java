package au.com.brisbane;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Todo extends PanacheEntity {
    public String name;
    public String description;
    public boolean isDone;
}
