<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Logado</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <div class="container">
        <% String email = (String) session.getAttribute("email"); %>
        
        Seja bem Vindo : <%=email %> | <a href="remover.jsp">Sair</a>
        
        <h1>Logado</h1>
        <hr>
    </div>
</body>
</html>
