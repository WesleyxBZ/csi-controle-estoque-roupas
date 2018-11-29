package controller.pages;

import dao.CategoriaDAO;
import dao.VendaDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Categoria;

@WebServlet(urlPatterns = "/produto-cadastrar")
public class Produto_Cadastrar extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ArrayList<Categoria> categorias = new CategoriaDAO().getCategorias();
        req.setAttribute("categorias", categorias);
        req.setAttribute("qtdProdutosCarinho", new VendaDAO().getQtdProdCarinho());

        RequestDispatcher disp = req.getRequestDispatcher("WEB-INF/view/produto/cadastrar.jsp");
        disp.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ArrayList<Categoria> categorias = new CategoriaDAO().getCategorias();
        req.setAttribute("categorias", categorias);
        req.setAttribute("qtdProdutosCarinho", new VendaDAO().getQtdProdCarinho());

        RequestDispatcher disp = req.getRequestDispatcher("WEB-INF/view/produto/cadastrar.jsp");
        disp.forward(req, resp);
    }

}
