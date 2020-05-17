package blog;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;


//Define the User Object/Entity
@Entity
public class BlogCollection {
    @Id long id;
    String name;

    public BlogCollection(String name) {
        this.name = name;
    }
}



