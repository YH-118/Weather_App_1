package blog;

import java.util.Date;

import com.google.appengine.api.users.User;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

@Entity
public class Subscriber {
	@Parent Key<SubscriberCollection> subscriber;
//	@Id Long id;
	@Id String userId;
	@Index String email;
	@Index String nickname;
	
	private Subscriber() {}
	
	public Subscriber(String userId, String email, String nickname) {
			this.userId = userId;
			this.email = email;
	        this.nickname = nickname;
	        this.subscriber = Key.create(SubscriberCollection.class, "subscriber");
	    }
	
		public String getUserId() {
			return userId;
		}
	    public String getEmail() {
	        return email;
	    }
	    public String getNickname() {
	        return nickname;
	    }
}
