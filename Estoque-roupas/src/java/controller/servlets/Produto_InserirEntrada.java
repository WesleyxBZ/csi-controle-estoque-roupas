package controller.servlets;

import dao.EntradaDAO;
import dao.ProdutoDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Entrada;
import model.Estoque;

@WebServlet(urlPatterns = "inserir_entrada")
public class Produto_InserirEntrada extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id_estoque = Integer.parseInt(req.getParameter("id_estoque"));
        int quantidade = Integer.parseInt(req.getParameter("quantidade"));

        if (id_estoque > 0 && quantidade > 0) {

            Estoque est = new Estoque();
            est.setId_estoque(id_estoque);
            est.setQtdProd(quantidade);

            req.setAttribute("chamou_inserirEntrada", true);

            if (new ProdutoDAO().updateEntradaEstoque(est)) {

                // Criar um objeto entrada e seta informações
                Entrada ent = new Entrada(est, quantidade);

                if (new EntradaDAO().insert(ent)) { // Se a entrada for inserida corretamente

                    // Seta mensagens a ser exibidas no modal sobre o status do cadastro
                    req.setAttribute("status", "<i class=\"fas fa-check text-success\"></i> Sucesso");
                    req.setAttribute("detalhes", "Entrada inserida com sucesso.");

                } else {

                    // Seta mensagens a ser exibidas no modal sobre o status do cadastro
                    req.setAttribute("status", "<i class=\"fas fa-check text-success\"></i> Sucesso");
                    req.setAttribute("detalhes", "Entrada inserida com sucesso, porém a sua entrada não foi registrada com sucesso.");
                }

            } else {

                req.setAttribute("status", "<i class=\"fas fa-times text-danger\"></i> Falha");
                req.setAttribute("detalhes", "Entrada naõ inserida, erro interno.");
            }

        } else {

            // Seta mensagens a ser exibidas no modal sobre o status do cadastro
            req.setAttribute("status", "<i class=\"fas fa-times text-danger\"></i> Falha");
            req.setAttribute("detalhes", "Quantidade e id não podem ser menor que zero.");

        }

        // Redireciona para a página
        RequestDispatcher disp = req.getRequestDispatcher("produto-inserirEntrada");
        disp.forward(req, resp);
    }

}
