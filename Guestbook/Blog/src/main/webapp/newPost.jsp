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
  	<title>Create A New Post</title>
  </head>
  
  <body>
  	<div>
  	<div>
	<%

	UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();

    if (user == null) {
      pageContext.setAttribute("user", user);
	
	%>
		<p>
			Please <a href="<%= userService.createLoginURL("/") %>" class = 'button'>Sign in</a> to post!
		</p>
	<%
	    } else {
	%>
	
	<h2>Create a New Post</h2>

	<div>
		<form action="/posts" method="post">
			<div>
				<label>Title</label>
				<input type="text" name="title" class ='input_Title' required/>
			</div>
	   		<div>
	   			<label>Content</label>
	   			<textarea name="content" rows="10" cols="60" required></textarea>
	   		</div>
	   		<input type="hidden" name="blogCollection" value="blogCollection"/>
	   		<div>
	   			<input type="submit" class = 'button' value="Post">
	   			<a href="/" class = 'button'>Cancel</a>	
	   		</div>
	 	</form>
 	</div>
	
	<%
	 }
	%>
	

	   	
		</div>
	</div>

  </body>

</html>