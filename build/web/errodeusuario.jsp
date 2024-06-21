<%@page isErrorPage="true" %>
<%@page import="java.util.Enumeration" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <div class="container">
        <div align="center">
            <div id="cabecalho"></div>
            <div id="corpo">
                <h3> Email e Senha incorretos! </h3><br/>
                <svg xmlns="http://www.w3.org/2000/svg" height="50" width="50" viewBox="0 0 512 512"><!--!Font Awesome Free 6.5.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.--><path fill="#ffffff" d="M256 512A256 256 0 1 0 256 0a256 256 0 1 0 0 512zm0-384c13.3 0 24 10.7 24 24V264c0 13.3-10.7 24-24 24s-24-10.7-24-24V152c0-13.3 10.7-24 24-24zM224 352a32 32 0 1 1 64 0 32 32 0 1 1 -64 0z"/></svg>
                <br/>     
                <div class="container3">
                    <a class="link-btn" href="login.jsp"><div class="botao">Tentar Novamente</div></a> 
                    <a class="link-btn" href="index.jsp"><div class="botao">cadastre-se</div></a>
                </div>
            </div>
            <div id="rodape"></div>
        </div>
    </div>
</body>
</html>
