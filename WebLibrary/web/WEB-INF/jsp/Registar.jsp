<%-- 
    Document   : Registar
    Created on : 27/mai/2021, 0:39:15
    Author     : Pedro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            .container{
                margin-left: 10%;
                margin-top: 5%;
                width: 35%;
                background: cornflowerblue;
                border-radius: 20px;
            }
            #form{
                display: flex;
                flex-direction: column;
                padding: 50px;
            }
            #login_title{
                padding-left: 10px;
                font-family: Arial;
                font-weight: lighter;
                color: white;
            }
            .label{
                margin: 5px;
                padding-left: 10px;
                font-size: 18px;
                font-family: Arial;
                font-weight: lighter;
                color: white;
            }
            .input_txt{
                border-radius: 5px;
                border-style: ridge;
                margin-bottom: 10px;
                padding-left: 15px;
                height: 30px;
                font-family: Arial;
                border-style: none;
            }
            .input_btn{
                border-radius: 5px;
                margin-top: 20px;
                width: 20%;
                height: 30px;
                align-self: center;
                font-size: 18px;
                font-weight: lighter;
                font-family: Arial;
                text-align: center;
                border-style: none;
            }
        </style>
        <title>Biblioteca - Registe-se</title>
    </head>
    <body>
        <div class="container">
            <form id="form" method="post" action="paginaregistar.htm">
                <h1 id="login_title">Registo</h1>
                <label class="label" for="username"><b>Username</b></label>
                <input class="input_txt" type="text" placeholder="Enter Username" name="username" required>
                
                <label class="label" for="email"><b>Email</b></label>
                <input class="input_txt" type="email" placeholder="Enter Email" name="email" required>

                <label class="label" for="password"><b>Password</b></label>
                <input class="input_txt" type="password" placeholder="Enter Password" name="password" required>
                
                <label class="label" for="nome"><b>Nome</b></label>
                <input class="input_txt" type="text" placeholder="Enter your name" name="nome" required>
                
                <label class="label" for="morada"><b>Morada</b></label>
                <input class="input_txt" type="text" placeholder="Enter your address" name="morada" required>
                
                <label class="label" for="dataNascimento"><b>Data Nascimento</b></label>
                <input class="input_txt" type="date" placeholder="Birth date" name="dataNascimento" required>

                <input class="input_btn" type="submit" name="submitRegister" value="Criar Conta">
            </form>
        </div>
    </body>
</html>
