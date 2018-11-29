package controller.pages;

import dao.ProdutoDAO;
import dao.VendaDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/dashboard")
public class Dashboard extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("qtdProdutosCarinho", new VendaDAO().getQtdProdCarinho());
        req.setAttribute("qtdProdutos", new ProdutoDAO().getQtdProdutos());
        req.setAttribute("qtdProdutosComEstoque", new ProdutoDAO().getQtdProdutoComEstoque());
        req.setAttribute("qtdProdutosSemEstoque", new ProdutoDAO().getQtdProdutoSemEstoque());

        RequestDispatcher disp = req.getRequestDispatcher("WEB-INF/view/dashboard.jsp");
        disp.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("qtdProdutosCarinho", new VendaDAO().getQtdProdCarinho());
        req.setAttribute("qtdProdutos", new ProdutoDAO().getQtdProdutos());
        req.setAttribute("qtdProdutosComEstoque", new ProdutoDAO().getQtdProdutoComEstoque());
        req.setAttribute("qtdProdutosSemEstoque", new ProdutoDAO().getQtdProdutoSemEstoque());

        RequestDispatcher disp = req.getRequestDispatcher("WEB-INF/view/dashboard.jsp");
        disp.forward(req, resp);
    }

}
