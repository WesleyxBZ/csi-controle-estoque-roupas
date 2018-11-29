package controller.servlets;

import dao.ProdutoDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Estoque;

@WebServlet(urlPatterns = "auditar_produto")
public class Produto_Auditar extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id_estoque = Integer.parseInt(req.getParameter("id_estoque"));
        int quantidade = Integer.parseInt(req.getParameter("quantidade"));

        req.setAttribute("chamou_auditarProduto", true);

        if (id_estoque > 0 && quantidade >= 0) {

            Estoque e = new Estoque();
            e.setId_estoque(id_estoque);
            e.setQtdProd(quantidade);

            if (new ProdutoDAO().updateAuditarEstoque(e)) {

                // Seta mensagens a ser exibidas no modal sobre o status do cadastro
                req.setAttribute("status", "<i class=\"fas fa-check text-success\"></i> Sucesso");
                req.setAttribute("detalhes", "Produto auditado com sucesso.");

            } else {

                // Seta mensagens a ser exibidas no modal sobre o status do cadastro
                req.setAttribute("status", "<i class=\"fas fa-times text-danger\"></i> Falha");
                req.setAttribute("detalhes", "Produto não pode ser auditado, erro interno.");

            }

        } else {

            // Seta mensagens a ser exibidas no modal sobre o status do cadastro
            req.setAttribute("status", "<i class=\"fas fa-times text-danger\"></i> Falha");
            req.setAttribute("detalhes", "Quantidade e id não podem ser menor que zero.");

        }

        // Redireciona para uma página
        RequestDispatcher disp = req.getRequestDispatcher("produto-auditarEstoque");
        disp.forward(req, resp);

    }
}
