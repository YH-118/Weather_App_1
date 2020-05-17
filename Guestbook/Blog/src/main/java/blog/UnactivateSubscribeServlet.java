package blog;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Result;



public class UnactivateSubscribeServlet extends HttpServlet{
	
	private static final Logger log = Logger.getLogger(UnactivateSubscribeServlet.class.getName());
	
	// Detele Current User From Subscriber Collections	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        
        if(user == null) {
        	resp.sendRedirect(userService.createLoginURL("/"));
        	return;
        }

        ObjectifyService.register(Subscriber.class);
        List<Subscriber> subscribers = ObjectifyService.ofy().load().type(Subscriber.class).list();   
        for(Subscriber ss: subscribers) {
        	if(ss.getUserId().equals(user.getUserId())) {
        		ofy().delete().entity(ss).now();
        	}	
        }

        req.setAttribute("message", "You have successfully Unsubscribe!");
		req.getRequestDispatcher("/subInfo.jsp").forward(req, resp);
	}
}