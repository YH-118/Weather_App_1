package blog;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;
import java.util.List;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.*;

public class PostServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		ObjectifyService.register(BlogEntry.class);
		List<BlogEntry> blogEntries = ObjectifyService.ofy().load().type(BlogEntry.class).list();   
		Collections.sort(blogEntries, Collections.reverseOrder()); 
		req.setAttribute("blogEntries", blogEntries);
		req.getRequestDispatcher("/posts.jsp").forward(req, resp);

	}
	
	
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        
        if(user == null) {
        	resp.sendRedirect("/landing.jsp");
        	return;
        }
        
        ObjectifyService.register(BlogEntry.class);
        String blogCollection = req.getParameter("blogCollection");
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        BlogEntry be = new BlogEntry(title, content, user);
        // Save Object/Entity to datastore
        ofy().save().entity(be).now(); 
        
        resp.sendRedirect("/");
       
    }
}
