// VERIFICA CAMPOS VAZIOS NO FORMULÁRIO
(function () {
    'use strict';
    window.addEventListener('load', function () {
        // Fetch all the forms we want to apply custom Bootstrap validation styles to
        var forms = document.getElementsByClassName('needs-validation');
        // Loop over them and prevent submission
        var validation = Array.prototype.filter.call(forms, function (form) {
            form.addEventListener('submit', function (event) {
                if (form.checkValidity() === false) {
                    event.preventDefault();
                    event.stopPropagation();
                }
                form.classList.add('was-validated');
            }, false);
        });
    }, false);
})();

// PEGA ID DO PRODUTO E JOGA PARA CAMPO NO MODAL

$(document).on("click", ".idEstoque", function () {
    var myId = $(this).data('id');
    var myQtd = $(this).data('qtd');
    var myProd = $(this).data('prod');
    $(".modal-body #id_estoque").val(myId);
    $(".modal-body #produto_qtd").val(myQtd);
    $(".modal-body #produto_nome").val(myProd);
});

// MOSTRAR SOMENTE ITENS (QUANTIDADE) SELECIONADOS NO SELECT
document.querySelector('#opcoesQuantidade').addEventListener('change', // quando o select for alterado, chamar a função abaixo
        function () {
            // opção selecionada
            let opcao = this.options[this.selectedIndex];
            // opção selecionada é "Escolher...", mostrar todas as linhas
            let mostrarTodos = this.selectedIndex == 0;
            // verificar as células da tabela que possuem o valor selecionado
            let tabela = document.querySelector('#dataTable');
            
            // começar em 1 para ignorar a primeira linha (cabeçalhos estão na linha zero)
            for (let i = 1; row = tabela.rows[i]; i++) {
                let valor = row.cells[5].innerHTML; // neste exemplo, valor é a quantidade

                if (valor > 0) {
                    valor = 1;
                } else {
                    valor = 2;
                }

                if (opcao.value == valor || mostrarTodos) {
                    // opção selecionada é igual a linha, ou é a opção "Escolher..."
                    row.style.display = 'table-row';
                } else {
                    row.style.display = 'none';
                }
            }
        }
);