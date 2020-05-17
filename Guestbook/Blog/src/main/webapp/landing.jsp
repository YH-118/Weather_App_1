<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="java.util.Collections" %>
<%@ page import="com.googlecode.objectify.*" %>
<%@ page import="blog.BlogEntry" %>
<%@ page import="blog.Subscriber" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<html>
  <head>
  	<!-- <link type="text/css" rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.min.css" /> -->
  	<link type="text/css" rel="stylesheet" href="/stylesheets/main.css" />
  </head>
  
  <body>
	<div>
	  <!-- https://steamcdn-a.akamaihd.net/steam/apps/348250/ss_e2cdfe41ef48fa2ee173b4206a835a403a917ac4.1920x1080.jpg?t=1508799201 -->
	  <img src="http://twist.elearningguild.net/wp-content/uploads/2014/02/Blog-graphic-from-Istock.jpg" class = 'image'>
	  <h2>
		  <div>
		    EE 461L BLOG
		  </div>
	  </h2>
	  <h4>
	  <div>Welcome to the BLOG! </div>
	  <div>1. Log in with your google account</div>
	  <div>2. Post a blog</div>
	  <div>3. Subscribe to get daily email</div>
	  </h4>
	 </div>

	<%
	UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();

    boolean subscribed = false;
    ObjectifyService.register(Subscriber.class);
    List<Subscriber> subscribers = ObjectifyService.ofy().load().type(Subscriber.class).list();   
	%>
	
	<% 
    if (user != null) {
        for(Subscriber ss: subscribers) {
        	if(ss.getUserId().equals(user.getUserId())) {
        		subscribed = true;
        	}	
        }
      	pageContext.setAttribute("user", user);
	%>
	
		
			
			    
				<a href="<%= userService.createLogoutURL(request.getRequestURI()) %>" class="button">Sign Out</a>
				
				
					<a href="/posts/new" class="button"> New Post</a>
					
					<%
						if(subscribed){
					%>
						<a href="/unsubscribe"  class="button"> Unsubscribe </a>	
					<% 	
						} else {
					%>	
						<a href="/subscribe" class="button"> Subscribe </a>
					<%	
					}
					%>
					
				
				
			
			
		<%
		    } else {
		%>
		
				
				<a href="<%= userService.createLoginURL(request.getRequestURI()) %>" class="button">Sigh In</a>
			
		
		<%
		 }
		%>
			
			
			<a href="/posts" class="button"> All Posts</a>
			</p>
			
		<%
		ObjectifyService.register(BlogEntry.class);
		List<BlogEntry> blogEntries = ObjectifyService.ofy().load().type(BlogEntry.class).list();   
		Collections.sort(blogEntries, Collections.reverseOrder()); 
		
	    if (blogEntries.isEmpty()) {
	        %>
	        <p>No Blog yet.</p>
	        <%
	    } else {
	    	int size = blogEntries.size();
	    	%>
	    	<p>
	    	<div>
	    	<% 
	        for (int i=0; i<Math.min(size,3); i++) {
	        	BlogEntry blogentry = blogEntries.get(i);
	        	pageContext.setAttribute("id", blogentry.getId());
	            pageContext.setAttribute("content", blogentry.getContent().substring(0, Math.min(50, blogentry.getContent().length())));
	            pageContext.setAttribute("title", blogentry.getTitle().substring(0, Math.min(10, blogentry.getTitle().length())));
	            pageContext.setAttribute("user", blogentry.getUser());
	            pageContext.setAttribute("date", blogentry.getDate());
			%>
				<%if(blogentry.getTitle().length()<=10) {%>
					  <div>
				        ${fn:escapeXml(title)}
				      </div>
				      <%} else {%>
				      <div>
				        ${fn:escapeXml(title)}...
				      </div>
				      <%} %>
				<%if(blogentry.getContent().length()<=10) {%>
					  <div>
				        ${fn:escapeXml(content)}
				      </div>
				      <%} else {%>
				      <div>
				        ${fn:escapeXml(content)}...
				      </div>
				      <%} %>
				      <div>
				        ${fn:escapeXml(user)}
				      </div>
				    </div>
				    <div>
				      ${fn:escapeXml(date)}
					  <a href="/posts/${id}"  data-tooltip="View the Full" class="button">More</a>
				   
	            <%
	        }
	    	%>
	    	</div>
	    	</p>
	    	<% 
	    }
	%>
		</div>


		
  </body>
</html>