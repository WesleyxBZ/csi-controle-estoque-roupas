package controller.servlets;

import dao.VendaDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "excluirProdCarinho")
public class ExcluirProdCarinho extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Se o id não for vázio
        if (!req.getParameter("idProdVenda").isEmpty()) {

            int id_produtoVenda = Integer.parseInt(req.getParameter("idProdVenda"));

            if ((id_produtoVenda > 0) && new VendaDAO().VerifProdVenda(id_produtoVenda)) {
                new VendaDAO().deletarProdCarinho(id_produtoVenda);
            }
        }

        RequestDispatcher disp = req.getRequestDispatcher("carinho");
        disp.forward(req, resp);
    }

}
