<%-- 
    Document   : PaginaInicial
    Created on : 7/jun/2021, 23:51:09
    Author     : Pedro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            padding: 10px;
            font-family: Raleway, sans-serif;
            font-weight: normal;
            font-size: 12pt;
            outline: none;
            border-radius: 0;
            background: none;
            border: 1px solid #282B33;
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
        }

        .search-area {
            float: left;
            width: 60%;
        }

        .settings {
            display: none;
            float: right;
            width: 40%;
            text-align: right;
        }

        #view {
            display: none;
            width: auto;
            height: 47px;
        }

        #searchbutton {
            width: 60px;
            height: 47px;
        }

        input#search {
            width: 30%;
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
            font-family: Raleway;
        }

        .product {
            display: inline-block;
            width: calc(24% - 13px);
            margin: 10px 10px 30px 10px;
            vertical-align: top;
        }

        .product img {
            display: block;
            margin: 0 auto;
            width: auto;
            height: 200px;
            max-width: calc(100% - 20px);
            background-cover: fit;
            box-shadow: 0px 0px 7px 0px rgba(0, 0, 0, 0.8);
            border-radius: 2px;
        }

        .product-content {
            text-align: center;
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
        }

        .product .price {
            font-family: sans-serif;
            font-size: 16px;
            font-weight: 700;
        }

        .product .genre {
            font-size: 14px;
        }


        @media screen and (max-width:1150px) {
            .product {
                width: calc(33% - 23px);
            }
        }

        @media screen and (max-width:700px) {
            .product {
                width: calc(50% - 43px);
            }
        }

        @media screen and (max-width:400px) {
            .product {
                width: 100%;
            }
        }

        /* TABLE VIEW */

        @media screen and (min-width:401px) {
            .settings {
                display: block;
            }
            #view {
                display: inline;
            }
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
                width: calc(100% - 145px);
            }
            .products-table .product h3 {
                margin: 0;
            }
        }
    </style>
    </head>
    <body>
        <div class="tools">
        <div class="search-area">
            <input type="text" id="search" placeholder="Search" />
            <button id="searchbutton">Go</button>
        </div>
        <div class="settings">
            <button id="view">Switch View</button>
        </div>
    </div>
    <div class="products products-table">
        <div class="product">
            <div class="product-img">
                <img src="http://placehold.it/400x650">
            </div>
            <div class="product-content">
                <h3>
                    Lorem ipsum dolor sit amet, consectetur adipisicing elit
                    <small>Consectetur Adipisicing</small>
                </h3>
                <p class="product-text price">$9.99</p>
                <p class="product-text genre">DVD Rental</p>
            </div>
        </div>
        <div class="product">
            <div class="product-img">
                <img src="http://placehold.it/400x650">
            </div>
            <div class="product-content">
                <h3>
                    Lorem ipsum dolor sit amet, consectetur adipisicing elit. Corrupti maxime doloribus sint, repudiandae.
                    <small>Consectetur Adipisicing</small>
                </h3>
                <p class="product-text price">$9.99</p>
                <p class="product-text genre">DVD Rental</p>
            </div>
        </div>
        <div class="product">
            <div class="product-img">
                <img src="http://placehold.it/400x650">
            </div>
            <div class="product-content">
                <h3>
                    Lorem ipsum dolor sit amet, consectetur adipisicing elit.
                    <small>Consectetur Adipisicing</small>
                </h3>
                <p class="product-text price">$9.99</p>
                <p class="product-text genre">DVD Rental</p>
            </div>
        </div>
        <div class="product">
            <div class="product-img">
                <img src="http://placehold.it/400x650">
            </div>
            <div class="product-content">
                <h3>
                    Lorem ipsum dolor sit amet, consectetur adipisicing elit
                    <small>Consectetur Adipisicing</small>
                </h3>
                <p class="product-text price">$9.99</p>
                <p class="product-text genre">DVD Rental</p>
            </div>
        </div>
    </div>
    </body>
</html>