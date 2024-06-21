<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
  function validarLogin(){
	if(document.formLogin.email.value==""){
		alert("Campo Usuário Não Informado");  
		return false;
	} 
	if(document.formLogin.senha.value==""){ 
		alert("Campo Senha Não Informado");
		return false;
	} 
	
	document.formLogin.submit();
  }
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Acessar</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <div class="container">
        <form name="formLogin" action="Login" method="post" class="center-form">
            <div class="form-group">
                <span>Usuário:</span>
                <input type="text" name="email">
            </div>
            <div class="form-group">
                <span>Senha:</span>
                <input type="password" name="senha">
            </div>
            <div class="form-group">
                <input type="button" value="Entrar" onclick="validarLogin()">
            </div>
        </form>
    </div>
</body>
</html>
