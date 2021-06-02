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
                width: 40em;
                margin-left: 15em;
            }
        </style>
        <title>Biblioteca</title>
    </head>

    <body>
        <p>Hello! This is the default welcome page for a Spring Web MVC project.</p>
        <div style="margin-left: 15em; margin-top: 15em; color: ">
            <h1>Bem vindo à nossa Biblioteca</h1>
        </div>
        <div class="box">
            <form class="button" method="post" action="paginalogin.htm">
                <input type="submit" name="submitGoToLogin" value="Entrar">
            </form>
            <form class="button" method="post" action="paginaregistar.htm">
                <input type="submit" name="submitGoToRegister" value="Criar Conta">
            </form>
        </div>
    </body>
</html>
