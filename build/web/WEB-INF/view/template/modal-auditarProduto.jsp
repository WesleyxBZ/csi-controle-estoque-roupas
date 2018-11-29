<div class="modal fade" id="auditarProdutoModal" tabindex="-1" role="dialog" aria-labelledby="alterarModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="inserirEntradaModalLabel">Auditar produto</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">

                <form class="needs-validation" method="POST" action="auditar_produto" novalidate>
                    <div class="form-label-group">
                        <input type="text" id="produto_nome" name="produto_nome" class="form-control" placeholder="quantidade address" readonly>
                        <label for="quantidade">Produto</label>
                    </div>

                    <div class="form-label-group my-3">
                        <input type="text" id="produto_qtd" name="produto_qtd" class="form-control" placeholder="quantidade address" readonly>
                        <label for="produto_qtd">Quantidade em estoque</label>
                    </div>

                    <input type="hidden" class="id_estoque" id="id_estoque" name="id_estoque">

                    <div class="form-label-group mb-3">
                        <input type="text" name="quantidade" id="quantidade" class="form-control quantidade" autocomplete="off" placeholder="quantidade address" required>
                        <label for="quantidade">Digite a quantidade total disponível em estoque</label>
                        <div class="valid-feedback">Ok</div>
                        <div class="invalid-feedback">Campo vazio</div>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                        <button type="submit" id="btn-alterar" class="btn btn-primary">Auditar</button>
                    </div>
                </form>

            </div>
        </div>
    </div>
</div>