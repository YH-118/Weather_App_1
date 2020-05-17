//My URL: https://ee461l-231521.appspot.com
// https://ee461l-231521.appspot.com/ofyguestbook.jsp
package guestbook;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.ObjectifyService;
import static com.googlecode.objectify.ObjectifyService.ofy;

public class OfySignGuestbookServlet extends HttpServlet {
	
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
                throws IOException {
    	
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();

        ObjectifyService.register(Greeting.class);

        String guestbookName = req.getParameter("guestbookName");
        String content = req.getParameter("content");
        
        
        Greeting greeting = new Greeting(user, content, guestbookName);
      
        ofy().save().entity(greeting).now();

        resp.sendRedirect("/ofyguestbook.jsp?guestbookName=" + guestbookName);
    }
}