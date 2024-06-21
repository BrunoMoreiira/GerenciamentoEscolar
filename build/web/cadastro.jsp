<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Usuário</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <div class="container">
        <h2>Cadastrar Login</h2>
        <form name="frmCadastrarLogin" action="CadastroLogin" method="post">
            <div class="form-group">
                <label for="nome">Nome:</label>
                <input type="text" id="nome" name="nome">
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="text" id="email" name="email">
            </div>
            <div class="form-group">
                <label for="senha">Senha:</label>
                <input type="password" id="senha" name="senha">
            </div>
            <div class="form-group">
                <label for="tipo">Tipo de Usuário:</label>
                <select id="tipo" name="tipo">
                    <option value="">Selecione</option>
                    <option value="aluno">Aluno</option>
                    <option value="professor">Professor</option>
                    <option value="admin">Admin</option>
                </select>
            </div>
            <div class="form-group">
                <input type="submit" value="Cadastrar">
            </div>
        </form>
    </div>
</body>
</html>
