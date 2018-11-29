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

        <title>Carinho | Estoque</title>
    </head>    

    <body id="page-top">

        <!-- Navbar -->        
        <jsp:include page="template/navbar.jsp" />

        <!-- Todo conteúdo abaixo da navbar -->
        <div id="wrapper">

            <jsp:include page="template/sidebar.jsp" />

            <!-- Conteúdo princípal -->
            <div id="content-wrapper">
                <div class="container-fluid">

                    <!-- Breadcrumbs-->
                    <ol class="breadcrumb justify-content-end">
                        <li class="breadcrumb-item">
                            <a href="dashboard">Início</a>
                        </li>
                        <li class="breadcrumb-item active">Carinho de vendas</li>
                    </ol>

                    <div class="row">

                        <!-- Tabelas a esquerda com informacoes dos produtos selecionados -->
                        <div class="col-md-8 col-sm-12">
                            <!-- DataTables Example -->
                            <div class="card mb-3 bg-light">
                                <div class="card-header">
                                    <h4>Carinho de vendas</h4>
                                </div>
                                <div class="card-body">
                                    <div class="table-responsive">
                                        <table class="table table-bordered" width="100%" cellspacing="0">
                                            <thead>
                                                <tr>
                                                    <th>#</th>
                                                    <th>Imagem</th>
                                                    <th>Produto</th>
                                                    <th>Cód. de barras</th>
                                                    <th>Quantidade</th>
                                                    <th>Preço unitário</th>
                                                    <th>Opção</th>
                                                </tr>
                                            </thead>
                                            <tbody>                                                            
                                                <c:forEach var="item" items="${prodCarinho}" varStatus="status">
                                                    <tr>
                                                        <td>${status.index}</td>
                                                        <td><img src="img/foto-produto.png" alt="fotoProduto" style="max-width: 50px;"></td>
                                                        <td>${item.estoque.produto.categoria.nome} ${item.estoque.produto.marca} ${item.estoque.produto.descricao} ${item.estoque.produto.cor} ${item.estoque.produto.tamanho}</td>
                                                        <td>${item.estoque.produto.codigoBarras}</td>  
                                                        <td>
                                                            <select name="quantidade" class="custom-select custom-select-sm form-control form-control-sm">
                                                                <option value="1" selected>1</option>
                                                                <option value="2">2</option>
                                                                <option value="3">3</option>
                                                                <option value="4">4</option>
                                                                <option value="5">5</option>
                                                            </select>
                                                        </td>
                                                        <td>R$ ${item.estoque.produto.preco}0</td>  
                                                        <td><a class="btn btn-danger text-white idEstoque" href="excluirProdCarinho?idProdVenda=${item.id_produtoVenda}" title="Excluir"><i class="fas fa-times"></i></a></td>
                                                    </tr> 
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Tabelas à direita com valor total dos produtos -->
                        <div class="col-md-4 col-sm-12">
                            <div class="card mb-3 bg-light">
                                <div class="card-header">
                                    <h4>Resumo de vendas</h4>
                                </div>
                                <div class="card-body">
                                    <p>Total de pedidos: ${qtdProdutosCarinho}</p>
                                    <p><strong>VALOR TOTAL: R$ ${valorTotalProdutosCarinho}0</strong></p>
                                    <button class="btn btn-primary btn-block" data-toggle="modal" data-target="#finalizarVendaModal"><i class="fas fa-fw fa-shopping-cart"></i> Finalizar venda</button>
                                </div>
                            </div>
                        </div>

                    </div>

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

        <!-- Modals -->
        <jsp:include page="template/modal-sair.jsp" />
        <jsp:include page="template/modal-finalizarVenda.jsp" />

        <!-- Bootstrap core JavaScript-->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Core plugin JavaScript-->
        <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

        <!-- Page level plugin JavaScript-->
        <script src="vendor/datatables/jquery.dataTables.js"></script>
        <script src="vendor/datatables/dataTables.bootstrap4.js"></script>

        <!-- Custom scripts for all pages-->
        <script src="js/sb-admin.min.js"></script>

        <!-- Demo scripts for this page-->
        <script src="js/demo/datatables-demo.js"></script>

    </body>

</html>
