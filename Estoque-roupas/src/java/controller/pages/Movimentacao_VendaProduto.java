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

@WebServlet(urlPatterns = "/movimentacao-vendaProduto")
public class Movimentacao_VendaProduto extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ArrayList<ProdutoVenda> vendas = new VendaDAO().getProdutosVendidos();
        req.setAttribute("vendas", vendas);
        req.setAttribute("qtdProdutosCarinho", new VendaDAO().getQtdProdCarinho());
        RequestDispatcher disp = req.getRequestDispatcher("WEB-INF/view/movimentacao/vendaProduto.jsp");
        disp.forward(req, resp);
    }

}
