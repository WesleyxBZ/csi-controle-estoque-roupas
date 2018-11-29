<%@page import="java.util.List"%>
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

        <title>Cadastrar - Produto | Estoque</title>
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
                        <li class="breadcrumb-item active">Cadastrar produto</li>
                    </ol>

                    <section>
                        <!-- Card de registro de conta -->
                        <div class="row mb-4">
                            <div class="col-md-12">
                                <div class="card mx-auto mt-1" style="background-color: #e9ecef">
                                    <div class="card-header"><h4 class="mb-0">Cadastrar produto</h4></div>
                                    <div class="card-body">

                                        <form class="needs-validation" method="POST" action="cadastrar_produto" novalidate>
                                            <div class="form-group">
                                                <div class="form-row">                                
                                                    <div class="col-md-6">
                                                        <div class="form-label-group">
                                                            <input type="text" name="marca" id="marca" class="form-control" placeholder="marca" autocomplete="off" maxlength="30" required>
                                                            <label for="marca">Marca</label>
                                                            <div class="valid-feedback">Ok</div>
                                                            <div class="invalid-feedback">Campo vazio</div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <div class="form-label-group">
                                                            <input type="text" name="descricao" id="descricao" class="form-control" placeholder="Descrição" autocomplete="off" maxlength="30">
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
                                                        <div class="form-label-group py-1">
                                                            <div class="input-group">
                                                                <select class="custom-select" name="categoria" id="categoria">  
                                                                    <option value="select">Categoria</option>                                                                                                                                                            
                                                                    <c:forEach var="categoria" items="${categorias}" varStatus="id">
                                                                        <option value="${categoria.id_categoria}">${categoria.nome}</option>
                                                                    </c:forEach>                                                
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div> 
                                                    <div class="col-md-4">
                                                        <div class="form-label-group py-1">
                                                            <div class="input-group">
                                                                <select class="custom-select" name="tamanho" id="tamanho">
                                                                    <option selected>Tamanho</option>
                                                                    <option value="P">P</option>
                                                                    <option value="M">M</option>
                                                                    <option value="G">G</option>
                                                                    <option value="GG">GG</option>
                                                                    <option value="34">34</option>
                                                                    <option value="36">36</option>
                                                                    <option value="38">38</option>
                                                                    <option value="40">40</option>
                                                                    <option value="42">42</option>
                                                                    <option value="44">44</option>
                                                                    <option value="46">46</option>
                                                                    <option value="48">48</option>
                                                                    <option value="50">50</option>
                                                                    <option value="53">52</option>
                                                                    <option value="54">54</option>
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <div class="form-label-group py-1">
                                                            <div class="input-group">
                                                                <select class="custom-select" name="cor" id="cor">
                                                                    <option selected>Cor</option>
                                                                    <option value="Branco">Branco</option>
                                                                    <option value="Preto">Preto</option>
                                                                    <option value="Amarelo">Amarelo</option>
                                                                    <option value="Roxo">Roxo</option>
                                                                    <option value="Rosa">Rosa</option>
                                                                    <option value="Vermelho">Vermelho</option>
                                                                    <option value="Cinza">Cinza</option>
                                                                    <option value="Laranja">Laranja</option>
                                                                    <option value="Azul">Azul</option>
                                                                    <option value="Azul">Azul Turquesa</option>
                                                                    <option value="Verde">Verde</option>
                                                                    <option value="Dourado">Dourado</option>
                                                                    <option value="Marrom">Marrom</option>
                                                                </select>
                                                            </div>
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
                                                                    <option value="0">Sim</option>
                                                                    <option value="1">Não</option>
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group mb-4">
                                                <div class="form-row">
                                                    <div class="col-md-2">
                                                        <div class="form-label-group">
                                                            <input type="text" name="quantidade" id="quantidade" class="form-control quantidade" placeholder="Email address" autocomplete="off" maxlength="2" required>
                                                            <label for="quantidade">Quantidade</label>
                                                            <div class="valid-feedback">Ok</div>
                                                            <div class="invalid-feedback">Campo vazio</div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-5">
                                                        <div class="form-label-group">
                                                            <input type="text" name="codigoBarras" id="codigoBarras" class="form-control" placeholder="First name" autocomplete="off" maxlength="15" required>
                                                            <label for="codigoBarras">Código de barras</label>
                                                            <div class="valid-feedback">Ok</div>
                                                            <div class="invalid-feedback">Campo vazio</div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-5">
                                                        <div class="form-label-group">
                                                            <input type="text" name="preco" id="preco" name="preco" class="form-control preco" placeholder="Digite o preço" autocomplete="off" maxlength="5" required>
                                                            <label for="preco">Preço</label>
                                                            <div class="valid-feedback">Ok</div>
                                                            <div class="invalid-feedback">Campo vazio</div>
                                                        </div>
                                                    </div>
                                                </div>                                  
                                            </div>
                                            <div class="form-group mb-4">
                                                <div class="form-row">
                                                    <div class="col-md-6">
                                                        <a class="btn btn-secondary btn-block" href="dashboard">Cancelar</a>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <button class="btn btn-primary btn-block" type="submit">Cadastrar</button>
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
        <jsp:include page="../template/modal-mensagemStatus.jsp" />

        <!-- Bootstrap core JavaScript-->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Core plugin JavaScript-->
        <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

        <!-- Custom scripts for all pages-->
        <script src="js/sb-admin.min.js"></script>

        <!-- Marcara para formulÃ¡rio -->
        <script src="vendor/jquery/jquery.maskedinputs.js"></script>
        <script src="vendor/jquery/mask.js"></script>

        <script src="js/functions.js"></script>

        <c:if test="${chamou_cadastro eq true}">
            <script>
                $(document).ready(function () {
                    $('#mensagemStatusModal').modal('show');
                });
            </script>
        </c:if>

    </body>

</html>
