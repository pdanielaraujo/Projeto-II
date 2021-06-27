<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

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
            .box{
                display: flex;
                width: 30%;
                margin-left: 13%;
            }
            .container{
                margin-left: 10%;
                margin-top: 10%;
                width: 25%;
                background: cornflowerblue;
                border-radius: 20px;
                padding: 30px;
            }
            .button{
                display: flex;
                flex-direction: row;
                padding: 50px;
            }
            .welcome_title{
                padding-left: 10px;
                font-family: Arial;
                font-weight: lighter;
                color: white;
            }
            .input_btn{
                border-radius: 5px;
                margin-top: 20px;
                height: 30px;
                align-self: center;
                font-size: 18px;
                font-weight: lighter;
                font-family: Arial;
                text-align: center;
                border-style: none;
                width: 100%;
                cursor: pointer;
            }
            .container_form{
                display: flex;
            }
        </style>
        <title>Biblioteca - Inicie Sessão ou Registe-se</title>
    </head>

    <body>
        <div class="container">
            <div class="welcome_title">
                <h1>Bem vindo à nossa Biblioteca</h1>
            </div>
            <div class="container_form">
                <form class="box" method="post" action="index.htm">
                    <input class="input_btn" type="submit" name="submitGoToLogin" value="Entrar">
                </form>
                <form class="box" method="post" action="index.htm">
                    <input class="input_btn" type="submit" name="submitGoToRegister" value="Criar Conta">
                </form>
            </div>
            
        </div>
    </body>
</html>
