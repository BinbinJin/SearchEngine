<%@ page import = "org.jbb.se.Searcher, java.util.Map, java.util.ArrayList, kevin.zhang.NLPIR, orz.TestNLPIR" %>
<%@ page language="java" contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head><title>result</title></head>
	<body>
	<form method = "POST" action = "result.jsp">
	<%! String searchText = "NBA";%>
	<%
		request.setCharacterEncoding("utf-8");
		searchText = request.getParameter("User");
	%>
	<p align = "left">
	<font size = "3"></font>
	<input type = "text" name = "User" size = "40" value=<%= searchText %>>
	<input type = "submit" value = "Search"><br><br>
	</form>
	<%! Searcher searcher = new Searcher();
	//TestNLPIR tt=new TestNLPIR();	
	//NLPIR nl=new NLPIR();
		%>
	<% 
		System.out.println(searchText);
		if(searchText != null){
		    searcher.searchIndex(searchText);
			if(searcher.hits > 0){
				for(int i=0;i<searcher.hits;++i){
					out.println("<font color = \"red\" size = \"3\">");
					out.println(searcher.title[i]);
					out.println("</font>");
					out.println("<br>");
					out.println(searcher.content[i]+"<br>");
					out.println("<br>");
				}
			}
		}
	%>
	</body>
</html>