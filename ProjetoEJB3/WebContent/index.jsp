<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cadastro de Pessoas</title>
<link rel ="stylesheet" href="css/index.css">
</head>
<body>
 <h1>Register Person</h1>
 
 <div class="container">
  <form  action="InserePessoa" method="POST">
    Nome: <input type="text" name="name"/>
   <br/>
  	CPF: <input type="text" name="cpf"/>
   <br/> 
    RG: <input type="text" name="rg"/>
   <br/>
  	E-mail: <input type="text" name="email"/>
  	<br/>
    Telefone: <input type="text" name="telefone"/>
   <br/>
  	Data Nascimento: <input type="text" name="dbd"/>
   <input  type="submit" value="Save"/>
  </form>
 </div>
 
</body>
</html>