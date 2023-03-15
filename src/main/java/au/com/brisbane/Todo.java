package au.com.brisbane;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import javax.persistence.Entity;

@Entity
public class Todo extends PanacheEntity {
    public String title;
    public String description;
    public boolean isDone = false;
}
