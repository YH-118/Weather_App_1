package blog;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.ObjectifyService;


public class ActivateSubscribeServlet extends HttpServlet{
	// Add Current User to Subscriber Collection
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        
        if(user == null) {
        	resp.sendRedirect(userService.createLoginURL("/"));
        	return;
        }
        
        ObjectifyService.register(Subscriber.class);
        String email = user.getEmail();
        String nickname = user.getNickname();
        Subscriber ss = new Subscriber(user.getUserId(),email, nickname);
        // Save Object/Entity to datastore
        ofy().save().entity(ss).now();
        
        req.setAttribute("message", "You have successfully Subscribe!");
		req.getRequestDispatcher("/subInfo.jsp").forward(req, resp);
	}
}
