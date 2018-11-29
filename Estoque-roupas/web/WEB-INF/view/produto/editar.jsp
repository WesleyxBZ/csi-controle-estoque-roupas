<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

        <title>Editar - Produto | Estoque</title>
    </head>    

    <body id="page-top">

        <!-- Navbar -->        
        <jsp:include page="../template/navbar.jsp" />

        <!-- Todo conteúdo abaixo da navbar -->
        <div id="wrapper">

            <jsp:include page="../template/sidebar.jsp" />

            <!-- Conteúdo princípal -->
            <div id="content-wrapper">

                <div class="container-fluid">

                    <!-- Informação de localização -->
                    <ol class="breadcrumb justify-content-end">
                        <li class="breadcrumb-item">
                            <a href="dashboard">Início</a>
                        </li>
                        <li class="breadcrumb-item active">Produto - Editar produto</li>
                    </ol>

                    <section>
                        <!-- Card de registro de conta -->
                        <div class="row mb-4">
                            <div class="col-md-12">
                                <div class="card mx-auto mt-1" style="background-color: #e9ecef">
                                    <div class="card-header"><h4 class="mb-0">Editar produto</h4></div>
                                    <div class="card-body">

                                        <form class="needs-validation" method="POST" action="atualizar-produto" novalidate>
                                            <div class="form-group">
                                                <div class="form-row">                                
                                                    <div class="col-md-6">
                                                        <div class="form-label-group">
                                                            <input type="text" name="marca" id="marca" class="form-control" placeholder="marca" autocomplete="off" maxlength="30" value="${marca}" required>
                                                            <label for="marca">Marca</label>
                                                            <div class="valid-feedback">Ok</div>
                                                            <div class="invalid-feedback">Campo vazio</div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <div class="form-label-group">
                                                            <input type="text" name="descricao" id="descricao" class="form-control" placeholder="Descrição" autocomplete="off" maxlength="30" value="${descricao}">
                                                            <label for="descricao">Descrição</label>
                                                            <div class="valid-feedback">Ok</div>
                                                            <div class="invalid-feedback">Campo vazio</div>
                                                        </div>
                                                    </div>                                             
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="form-row">
                                                    <div class="col-md-4">
                                                        <div class="form-label-group">
                                                            <input type="text" name="categoria" id="categoria" class="form-control" placeholder="categoria" value="${categoria}" readonly>
                                                            <label for="categoria">Categoria</label>
                                                        </div>
                                                    </div> 
                                                    <div class="col-md-4">
                                                        <div class="form-label-group">
                                                            <input type="text" name="cor" id="cor" class="form-control" placeholder="cor" value="${cor}" readonly>
                                                            <label for="cor">Cor</label>
                                                        </div>
                                                    </div>     
                                                    <div class="col-md-4">
                                                        <div class="form-label-group">
                                                            <input type="text" name="tamanho" id="tamanho" class="form-control" placeholder="tamanho" value="${tamanho}" readonly>
                                                            <label for="tamanho">Tamanho</label>
                                                        </div>
                                                    </div> 
                                                </div>
                                            </div>                                            
                                            <div class="form-group">
                                                <div class="form-row">
                                                    <div class="col-md-6">
                                                        <div class="input-group">
                                                            <div class="input-group-prepend">
                                                                <span class="input-group-text" id="inputGroupFileAddon01">Upload</span>
                                                            </div>
                                                            <div class="custom-file">
                                                                <input type="file" class="custom-file-input" id="inputGroupFile01" aria-describedby="inputGroupFileAddon01">
                                                                <label class="custom-file-label" for="inputGroupFile01">Escolher arquivo</label>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <div class="form-label-group">
                                                            <div class="input-group">
                                                                <div class="input-group-prepend">
                                                                    <span class="input-group-text" id="basic-addon3">Vísivel ao cliente</span>
                                                                </div>
                                                                <select class="custom-select" name="visivelCliente" id="visivelCliente">                                                                    
                                                                    <c:if test="${visivelCliente eq true}">
                                                                        <option value="0" selected>Sim</option>
                                                                        <option value="1">Não</option>
                                                                    </c:if>   

                                                                    <c:if test="${visivelCliente eq false}">
                                                                        <option value="1" selected>Não</option>
                                                                        <option value="0">Sim</option>                                                                        
                                                                    </c:if>                                                                                                                                        
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group mb-4">
                                                <div class="form-row">
                                                    <div class="col-md-8">
                                                        <div class="form-label-group">
                                                            <input type="text" name="codigoBarras" id="codigoBarras" class="form-control" placeholder="First name" value="${codigoBarras}" readonly>
                                                            <label for="codigoBarras">Código de barras</label>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <div class="form-label-group">                                                            
                                                            <input type="text" name="preco" id="preco" name="preco" class="form-control money" placeholder="Digite o preço" autocomplete="off" maxlength="6" value="${preco}" required>
                                                            <label for="preco">Preço</label>
                                                            <div class="valid-feedback">Ok</div>
                                                            <div class="invalid-feedback">Campo vazio</div>
                                                        </div>
                                                    </div>
                                                    <input type="hidden" name="id_produto" value="${id_produto}">
                                                </div>                                  
                                            </div>
                                            <div class="form-group">
                                                <div class="form-row">
                                                    <div class="col-md-6">
                                                        <a href="produto-visualizarEditar" class="btn btn-secondary btn-block">Cancelar</a>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <button class="btn btn-primary btn-block" type="submit">Editar</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>

                </div>
                <!-- /.container-fluid -->

                <!-- Footer -->
                <jsp:include page="../template/footer.jsp" />

            </div>
            <!-- /.content-wrapper -->

        </div>
        <!-- /#wrapper -->

        <!-- Scroll to Top Button-->
        <a class="scroll-to-top rounded" href="#page-top">
            <i class="fas fa-angle-up"></i>
        </a>

        <!-- Modals -->
        <jsp:include page="../template/modal-sair.jsp" />

        <!-- Bootstrap core JavaScript-->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Core plugin JavaScript-->
        <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

        <!-- Custom scripts for all pages-->
        <script src="js/sb-admin.min.js"></script>

        <!-- Marcara para formulário -->
        <script src="vendor/jquery/jquery.maskMoney.js"></script>
        <script>
            $(function () {
                $('#preco').maskMoney();
            })
        </script>        

        <script src="js/functions.js"></script>

    </body>

</html>
