<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="pt-br">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="Trabalho feito na disiciplina POO Web I">
        <meta name="author" content="WesleyxBZ">

        <!-- Bootstrap core CSS -->
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- Fontes customizáveis para esse template -->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">

        <!-- Page level plugin CSS -->
        <link href="vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">

        <!-- Estilos customizáveis para esse template -->
        <link href="css/sb-admin.css" rel="stylesheet">

        <title>Página não encontrada | Estoque</title>
    </head>

    <body id="page-top">

        <!-- Navbar -->
        <jsp:include page="template/navbar.jsp" />

        <!-- Todo conteúdo abaixo da navbar -->
        <div id="wrapper">

            <!-- Sidebar da esquerda -->  
            <jsp:include page="template/sidebar.jsp" />

            <!-- Conteúdo princípal -->
            <div id="content-wrapper">

                <div class="container-fluid">

                    <!-- Breadcrumbs-->
                    <ol class="breadcrumb justify-content-end">
                        <li class="breadcrumb-item">
                            <a href="dashboard">Início</a>
                        </li>
                        <li class="breadcrumb-item active">Erro 404</li>
                    </ol>

                    <!-- Page Content -->
                    <h1 class="display-1">404</h1>
                    <p class="lead">Página não encontrada. Volte para a página inicial
                        <a href="dashboard">clicando aqui</a>.</p>
                </div>
                <!-- /.container-fluid -->

                <!-- Footer -->
                <jsp:include page="template/footer.jsp" />

            </div>
            <!-- /.content-wrapper -->

        </div>
        <!-- /#wrapper -->

        <!-- Scroll to Top Button-->
        <a class="scroll-to-top rounded" href="#page-top">
            <i class="fas fa-angle-up"></i>
        </a>

        <!-- Modal confimação sair -->
        <jsp:include page="template/modal-sair.jsp" />

        <!-- Bootstrap core JavaScript-->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Core plugin JavaScript-->
        <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

        <!-- Custom scripts for all pages-->
        <script src="js/sb-admin.min.js"></script>

    </body>

</html>
