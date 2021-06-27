<%-- 
    Document   : Login
    Created on : 25/mai/2021, 10:38:23
    Author     : Pedro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            body{
                background-image: url("resources/bibliotecaBK.png");
                background-size: cover;
                background-repeat: no-repeat;
                margin: 0px;
            }
            .container{
                margin-left: 10%;
                margin-top: 10%;
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
                cursor: pointer;
            }
            a{
                margin-left: 2%;
                text-decoration: none;
                color: aliceblue;
                font-family: Arial;
                font-size: 14px;
            }
        </style>
        <title>Biblioteca - Inicie Sessão</title>
    </head>
    <body>
        <div class="container">
            <form id="form" method="post" action="paginalogin.htm">
                <h1 id="login_title">Login</h1>
                <label class="label" for="username"><b>Username</b></label>
                <input class="input_txt" type="text" placeholder="Enter Username" name="username" required>

                <label class="label" for="password"><b>Password</b></label>
                <input class="input_txt" type="password" placeholder="Enter Password" name="password" required>
                
                <a href="paginaregistar.htm">Ainda não tenho uma conta criada.</a>

                <input class="input_btn" type="submit" name="submitLogin" value="Entrar">
            </form>
        </div>
    </body>
</html>
