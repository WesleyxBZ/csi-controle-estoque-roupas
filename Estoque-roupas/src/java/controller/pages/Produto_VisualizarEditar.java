package controller.pages;

import dao.ProdutoDAO;
import dao.VendaDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Estoque;

@WebServlet(urlPatterns = "/produto-visualizarEditar")
public class Produto_VisualizarEditar extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ArrayList<Estoque> produtos = new ProdutoDAO().getProdutoEstoque();
        req.setAttribute("produtos", produtos);
        req.setAttribute("qtdProdutosCarinho", new VendaDAO().getQtdProdCarinho());
        RequestDispatcher disp = req.getRequestDispatcher("WEB-INF/view/produto/visualizarEditar.jsp");
        disp.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ArrayList<Estoque> produtos = new ProdutoDAO().getProdutoEstoque();
        req.setAttribute("produtos", produtos);
        req.setAttribute("qtdProdutosCarinho", new VendaDAO().getQtdProdCarinho());
        RequestDispatcher disp = req.getRequestDispatcher("WEB-INF/view/produto/visualizarEditar.jsp");
        disp.forward(req, resp);
    }

}
