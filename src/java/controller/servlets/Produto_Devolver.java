package controller.servlets;

import dao.DevolucaoDAO;
import dao.ProdutoDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Devolucao;
import model.Estoque;

@WebServlet(urlPatterns = "devolver_produto")
public class Produto_Devolver extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id_estoque = Integer.parseInt(req.getParameter("id_estoque"));
        int quantidade = Integer.parseInt(req.getParameter("quantidade"));

        req.setAttribute("chamou_devolverProduto", true);

        // Se a quantdade inserida for maior que zero e tiver uma quantidade maior no BD
        if ((quantidade > 0) && (new ProdutoDAO().getQtdEstoque(id_estoque) >= quantidade)) {

            Estoque e = new Estoque();
            e.setQtdProd(quantidade);
            e.setId_estoque(id_estoque);

            if (new ProdutoDAO().updateDevolucaoEstoque(e)) {

                Devolucao d = new Devolucao(e, quantidade);

                if (new DevolucaoDAO().insert(d)) {

                    // Seta mensagens a ser exibidas no modal sobre o status do cadastro
                    req.setAttribute("status", "<i class=\"fas fa-check text-success\"></i> Sucesso");
                    req.setAttribute("detalhes", "Devolução concluida com sucesso.");

                } else {

                    // Seta mensagens a ser exibidas no modal sobre o status do cadastro
                    req.setAttribute("status", "<i class=\"fas fa-check text-success\"></i> Sucesso");
                    req.setAttribute("detalhes", "Devolução concluida com sucesso, porém o seu registro não foi efetuado.");
                }
            }

        } else {

            // Seta mensagens a ser exibidas no modal sobre o status do cadastro
            req.setAttribute("status", "<i class=\"fas fa-times text-danger\"></i> Falha");
            req.setAttribute("detalhes", "Estoque não possui essa quantidade disponível para devolução.");
        }

        // Redireciona para uma página
        RequestDispatcher disp = req.getRequestDispatcher("produto-devolver");
        disp.forward(req, resp);
    }

}
