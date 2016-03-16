<%
if(request.getSession().getAttribute("currentUser")==null){
	response.sendRedirect("login.jsp");
}
%>
