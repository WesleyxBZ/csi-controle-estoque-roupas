package controller.pages;

import dao.DevolucaoDAO;
import dao.VendaDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Devolucao;

@WebServlet(urlPatterns = "/movimentacao-devolucaoProduto")
public class Movimentacao_DevolucaoProduto extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ArrayList<Devolucao> devolucoes = new DevolucaoDAO().getDevolucoes();
        req.setAttribute("devolucoes", devolucoes);
        req.setAttribute("qtdProdutosCarinho", new VendaDAO().getQtdProdCarinho());
        RequestDispatcher disp = req.getRequestDispatcher("WEB-INF/view/movimentacao/devolucaoProduto.jsp");
        disp.forward(req, resp);
    }
}
