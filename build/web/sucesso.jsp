<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <script language="Javascript">
	var timer = 2;
	function countdown() {
		if (timer > 0) {
			timer -= 1;
			setTimeout("countdown()", 2000);
		} else {
			location.href = 'painelProfessor.jsp';
		}
	}
	countdown();
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Comunicado Enviado</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <div class="container2">
        <h2>Comunicado Enviado  </h2>
        <div class="success-message">
            <p>O seu Comunicado foi enviado com sucesso para todos os alunos!</p>
            <p>Você será redirecionado para o painel novamente em breve...</p>
        </div>
    </div>
</body>
</html>