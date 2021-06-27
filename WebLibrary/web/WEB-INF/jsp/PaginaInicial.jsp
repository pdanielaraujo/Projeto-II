<%-- 
    Document   : PaginaInicial
    Created on : 7/jun/2021, 23:51:09
    Author     : Pedro
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Biblioteca - Página Inicial</title>
        <style>
            input,
        textarea,
        button {
            height: 25px;
            margin: 0;
            font-weight: normal;
            font-size: 12pt;
            outline: none;
            border-radius: 0;
            background: none;
        }
        
        body{
            margin: 0;
            font-family: Arial;
        }

        button,
        select {
            height: 45px;
            padding: 0 15px;
            cursor: pointer;
        }

        button {
            background: none;
            border: 1px solid black;
            margin: 25px 0;
        }

        button:hover {
            background-color: #282B33;
            color: white;
        }


        .tools {
            overflow: auto;
            zoom: 1;
            display: contents;
        }

        .search-area {
            float: left;
            width: 40%;
            margin-left: 10%;
            margin-right: 10%;
        }

        #searchbutton {
            width: 60px;
            height: 47px;
        }

        input#search {
            width: calc(100% - 90px);
            padding: 10px;
            border: 1px solid #282B33;
        }

        @media screen and (max-width:400px) {
            .search-area {
                width: 100%;
            }
        }

        .products {
            width: 100%;
            margin-top: 5%;
            margin-left: 2%;
        }

        .product img {
            display: block;
            margin: 0 auto;
            box-shadow: 0px 0px 7px 0px rgba(0, 0, 0, 0.8);
            border-radius: 2px;
        }

        .product h3 {
            font-size: 20px;
            font-weight: 600;
            margin: 10px 0 0 0;
        }

        .product h3 small {
            display: block;
            font-size: 16px;
            font-weight: 400;
            font-style: italic;
            margin: 7px 0 0 0;
        }

        .product .product-text {
            margin: 7px 0 0 0;
            color: #777;
            font-size: 14px;
        }
        
        /* TABLE VIEW */

            .products-table .product {
                display: block;
                width: auto;
                margin: 10px 10px 30px 10px;
            }
            .products-table .product .product-img {
                display: inline-block;
                margin: 0;
                width: 120px;
                height: 120px;
                vertical-align: middle;
            }
            .products-table .product img {
                width: auto;
                height: 120px;
                max-width: 120px;
            }
            .products-table .product-content {
                text-align: left;
                display: inline-block;
                margin-left: 20px;
                vertical-align: middle;
                width: 20%;
            }
            .product-update{
                display: inline-block;
                vertical-align: middle;
            }
            .products-table .product h3 {
                margin: 0;
            }
            .inputReservar{
                cursor: pointer;
                border-radius: 5px;
                width: 6em;
                height: 30px;
                font-size: 18px;
                font-weight: lighter;
                font-family: Arial;
                text-align: center;
                border-style: none;
                background: gainsboro;
                margin-left: 1%;
            }
            
            .inputLogout{
                cursor: pointer;
                border-radius: 5px;
                height: 30px;
                font-size: 18px;
                font-weight: lighter;
                font-family: Arial;
                text-align: center;
                border-style: none;
                background: gainsboro;
                width: 100%;
            }
            .header{
                display: flex;
                flex-flow: row-reverse;
                width: 100%;
            }
            .siteTitle{
                margin-left: 2%;
                margin-right: auto;
            }
            #logout{
                width: 5%;
                padding: 1%;
            }
        </style>
    </head>
    <!--width: calc(100% - 145px);-->
    <body>
        <!--<form method="post" action="paginainicial.htm">
            <input class="input_btn" type="submit" name="submitver" value="Ver">
        </form>-->
    <div class="header">
        <form id="logout" method="post" action="paginainicial.htm">
            <input class="inputLogout" type="submit" name="submitLogout" value="Sair">
        </form>
        
        <h3 class="loggedTitle"> Sessão iniciada em, ${leitor.username}!</h3>
        <div class="tools">
            <div class="search-area">
            <input type="text" id="search" placeholder="Search" />
            <button id="searchbutton">Go</button>
            </div>
        </div>
        <h1 class="siteTitle">Biblioteca</h1>
    </div>
    
    <div class="products products-table">
        <c:forEach items="${lista}" var="item">
        <div class="product">
            <div class="product-img">
                <img src="https://upload.wikimedia.org/wikipedia/commons/6/62/1edicao_Mensagem_1934.jpg">
            </div>
            <div class="product-content">
                <h3>${item.livroId}</h3>
                <p class="product-text">Páginas: ${item.numPaginas}</p>
                <p class="product-text">Idioma: ${item.linguaId}</p>
                <p class="product-text">Edição: ${item.edicaoId}</p>
                <p class="product-text">Disponibilidade: ${item.estadoId}</p>
            </div>
            <div class="product-update">
                <form method="get" action="paginainicial.htm">
                    <input class="product-text" type="hidden" name="idExemplar" value="${item.idExemplar}">
                    <label class="label" for="tempoRequisicao"><b>Tempo de requisição</b></label>
                    <input class="product-text" type="hidden" name="leitor" value="${leitor}">
                    <input type="text" placeholder="Tempo de requisição" name="tempoRequisicao" required>
                    <input class="inputReservar" type="submit" name="submitReservar" value="Reservar">
                </form>
            </div>
        </div>
        </c:forEach>
    </div>  
    </body>
</html>