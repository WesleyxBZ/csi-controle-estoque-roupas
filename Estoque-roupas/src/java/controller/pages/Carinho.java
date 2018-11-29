package controller.pages;

import dao.VendaDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ProdutoVenda;

@WebServlet(urlPatterns = "/carinho")
public class Carinho extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ArrayList<ProdutoVenda> prodCarinho = new VendaDAO().getProdCarinho();
        req.setAttribute("prodCarinho", prodCarinho);
        req.setAttribute("qtdProdutosCarinho", new VendaDAO().getQtdProdCarinho());
        req.setAttribute("valorTotalProdutosCarinho", new VendaDAO().getValorTotalProdCarinho());
        RequestDispatcher disp = req.getRequestDispatcher("WEB-INF/view/carinho.jsp");
        disp.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ArrayList<ProdutoVenda> prodCarinho = new VendaDAO().getProdCarinho();
        req.setAttribute("prodCarinho", prodCarinho);
        req.setAttribute("qtdProdutosCarinho", new VendaDAO().getQtdProdCarinho());
        RequestDispatcher disp = req.getRequestDispatcher("WEB-INF/view/carinho.jsp");
        disp.forward(req, resp);
    }
}
