package blog;

import java.util.Calendar;
import java.util.Date;

import com.google.appengine.api.users.User;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

//Define the User Object/Entity
@Entity
public class BlogEntry implements Comparable<BlogEntry>{	
	@Parent Key<BlogCollection> blogcollection;
	@Id Long id;
	@Index String title;
	@Index String content;
	@Index User user;
	@Index Date date;
	private BlogEntry() {}
	
	public BlogEntry(String title, String content, User user) {
			this.title = title;
	        this.user = user;
	        this.content = content;
	        String blogCollection = "blogCollection";
	        this.blogcollection = Key.create(BlogCollection.class, blogCollection);
	        date = new Date();
	    }
	    public Long getId() {
	        return id;
	    }
	    public String getTitle() {
	        return title;
	    }
	    public User getUser() {
	        return user;
	    }
	    public String getContent() {
	        return content;
	    }
	    public String getDate() {
	        return date.toString();
	    }

	    @Override
	    public int compareTo(BlogEntry other) {
	        if (date.after(other.date)) {
	            return 1;
	        } else if (date.before(other.date)) {
	            return -1;
	        }
	        return 0;
	     }
}

