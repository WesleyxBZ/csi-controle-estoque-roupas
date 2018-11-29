package controller.pages;

import dao.EntradaDAO;
import dao.VendaDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Entrada;

@WebServlet(urlPatterns = "/movimentacao-entradaProduto")
public class Movimentacao_EntradaProduto extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ArrayList<Entrada> entradas = new EntradaDAO().getEntradas();
        req.setAttribute("entradas", entradas);
        req.setAttribute("qtdProdutosCarinho", new VendaDAO().getQtdProdCarinho());
        RequestDispatcher disp = req.getRequestDispatcher("WEB-INF/view/movimentacao/entradaProduto.jsp");
        disp.forward(req, resp);
    }
}
