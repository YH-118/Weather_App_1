<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="java.util.Collections" %>
<%@ page import="com.googlecode.objectify.*" %>
<%@ page import="blog.BlogEntry" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>



<html>
  <head>
  	<link type="text/css" rel="stylesheet" href="/stylesheets/main.css" />
  </head>
  
  <body>
	<div>
	

		<h1>
		  <div>
			Posts
		  </div>
		</h1>

	<%
	
	//ObjectifyService.register(BlogEntry.class);
	//List<BlogEntry> blogEntries = ObjectifyService.ofy().load().type(BlogEntry.class).list();   
	//Collections.sort(blogEntries, Collections.reverseOrder()); 

	List<BlogEntry> blogEntries = (List<BlogEntry>) request.getAttribute("blogEntries");  
	
    if (blogEntries.isEmpty()) {
        %>
        <p>No Blog yet.</p>
        <%
    } else {
    	%>
    	<p>
    		<div>

    	<% 
        for (BlogEntry blogentry : blogEntries) {
        	pageContext.setAttribute("id", blogentry.getId());
            pageContext.setAttribute("content", blogentry.getContent().substring(0, Math.min(50, blogentry.getContent().length())));
            pageContext.setAttribute("title", blogentry.getTitle().substring(0, Math.min(10, blogentry.getTitle().length())));
            pageContext.setAttribute("user", blogentry.getUser());
            pageContext.setAttribute("date", blogentry.getDate());
		%>
				
		<div>
		  <div>
		    <div>
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
		        <span> ${fn:escapeXml(user)}</span>
		      </div>
		      <div>
		        <span>${fn:escapeXml(date)}</span>
		      </div>
		      <a href="/posts/${id}" class = 'button'>More</a>
		      
			   <div></div>	
		    </div>
		  </div>
		  </div>

         <%
        }
    }
%>
	<a href="/" class = 'button'> Go Back</a>
	</div>
	</p>

  </body>
</html>