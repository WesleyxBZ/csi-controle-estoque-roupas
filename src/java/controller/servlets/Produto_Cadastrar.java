package controller.servlets;

import com.sun.tools.corba.se.idl.Util;
import dao.CategoriaDAO;
import dao.EntradaDAO;
import dao.ProdutoDAO;
import model.Estoque;
import model.Produto;
import model.Categoria;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Entrada;

@WebServlet(urlPatterns = "cadastrar_produto")
public class Produto_Cadastrar extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Recebe todos os dados inseridos no formulário 
        int id_categoria = Integer.parseInt(req.getParameter("categoria"));
        String marca = Util.capitalize(req.getParameter("marca").toLowerCase());
        String cor = Util.capitalize(req.getParameter("cor").toLowerCase());
        String tamanho = req.getParameter("tamanho");
        String descricao = Util.capitalize(req.getParameter("descricao").toLowerCase());
        String caminhoImg = "sem"/*req.getParameter("caminhoImg")*/;
        int visivelCliente = Integer.parseInt(req.getParameter("visivelCliente"));
        String codigoBarras = req.getParameter("codigoBarras");
        double preco = Double.parseDouble(req.getParameter("preco"));
        int quantidade = Integer.parseInt(req.getParameter("quantidade"));

        // Criar um objeto categoria e seta informações
        Categoria c = new Categoria(id_categoria, new CategoriaDAO().getNome(id_categoria));

        // Verifica se visivelCliente == 1 (falso)
        boolean visivelC = true;
        if (visivelCliente == 1) {
            visivelC = false;
        }

        // Criar um objeto produto e seta informações
        Produto p = new Produto(0, c, marca, cor, tamanho, descricao, codigoBarras, preco, caminhoImg, visivelC);

        // Cria um objeto estoque e seta informações
        Estoque est = new Estoque(0, p, quantidade);
        req.setAttribute("chamou_cadastro", true);

        // Chama método para inserir o produto no BD
        int cod_estoque = new ProdutoDAO().insert(est);

        if (cod_estoque > 0) { // Se 'cod_produto' > 1 inseriu tudo no BD sem erros

            boolean entrada = false;

            if (quantidade > 0) { // Se quantidade maior que 0, registrar entrada de produtos

                est.setId_estoque(cod_estoque); // Seta 'id_estoque' com chave do produto do BD

                // Criar um objeto entrada e seta informações
                Entrada ent = new Entrada(est, quantidade);
                entrada = new EntradaDAO().insert(ent);
            }

            if (entrada || quantidade == 0) { // Se a entrada for true
                // Seta mensagens a ser exibidas no modal sobre o status do cadastro
                req.setAttribute("status", "<i class=\"fas fa-check text-success\"></i> Sucesso");
                req.setAttribute("detalhes", "Produto cadastrado com sucesso.");

            } else {
                // Seta mensagens a ser exibidas no modal sobre o status do cadastro
                req.setAttribute("status", "<i class=\"fas fa-check text-success\"></i> Sucesso");
                req.setAttribute("detalhes", "Produto cadastrado com sucesso, porém a sua entrada não foi registrada com sucesso.");
            }

        } else {

            // Seta mensagens a ser exibidas no modal sobre o status do cadastro
            req.setAttribute("status", "<i class=\"fas fa-times text-danger\"></i> Falha");
            req.setAttribute("detalhes", "Produto não cadastrado, erro interno.");

        }

        RequestDispatcher disp = req.getRequestDispatcher("produto-cadastrar");
        disp.forward(req, resp);
    }

}
