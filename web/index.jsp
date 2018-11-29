<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="pt-br">
    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="Trabalho feito na disiciplina POO Web I">
        <meta name="author" content="WesleyxBZ">

        <title>Login - Estoque</title>

        <!-- Bootstrap core CSS-->
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- Fontes customizáveis para esse template -->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

        <!-- Estilos customizáveis para esse template -->
        <link href="css/sb-admin.css" rel="stylesheet">

    </head>

    <body>

        <!-- Conteúdo principal -->
        <div class="container">
            <!-- Logo -->
            <div class="col-md-12 mt-5">
                <p class="text-center"><img src="img/seu_logo.png" alt="seulogo" class="img-fluid border border-secondary rounded" style="max-width: 100px;"></p>
            </div>
            <!-- Card de login -->
            <div class="card card-login mx-auto mt-1">
                <div class="card-header text-center">Preencha os campos para entrar no seu estoque</div>
                <div class="card-body">
                    <form method="POST" action="login">
                        <div class="form-group">
                            <div class="form-label-group">
                                <input type="email" id="inputEmail" name="email" class="form-control" placeholder="Email" required="required" autofocus="autofocus">
                                <label for="inputEmail">Email</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="form-label-group">
                                <input type="password" id="inputPassword" name="senha" class="form-control" placeholder="Senha" required="required">
                                <label for="inputPassword">Senha</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <c:if test="${not empty mensagem_erro}">
                                <div class="form-label-group text-danger">
                                    <p>${mensagem_erro}</p>
                                </div>
                            </c:if> 
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" value="remember-me">
                                    Lembrar senha
                                </label>
                            </div>
                        </div>

                        <button class="btn btn-primary btn-block" type="submit">Entrar</button>
                    </form>
                    <!--
                    <div class="text-center">
                        <a class="d-block small mt-3" href="register.html">Register an Account</a>
                        <a class="d-block small" href="forgot-password.html">Forgot Password?</a>
                    </div>
                    -->
                </div>
            </div>
        </div>


        <!-- Bootstrap core JavaScript-->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Core plugin JavaScript-->
        <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

    </body>
</html>
