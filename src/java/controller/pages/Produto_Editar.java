package controller.pages;

import dao.ProdutoDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Produto;

@WebServlet(urlPatterns = "/produto-editar")
public class Produto_Editar extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher disp;

        if (!req.getParameter("idProduto").isEmpty()) {

            int id_produto = Integer.parseInt(req.getParameter("idProduto"));
            Produto p = new ProdutoDAO().read(id_produto); // Cria objeto passando id e recebe produto do BD

            if ((id_produto > 0) && p != null) { // Se o BD retornar um objeto diferente de nulo, este produto está cadastrado no BD

                req.setAttribute("id_produto", id_produto);
                req.setAttribute("categoria", p.getCategoria().getNome());
                req.setAttribute("marca", p.getMarca());
                req.setAttribute("descricao", p.getDescricao());
                req.setAttribute("cor", p.getCor());
                req.setAttribute("tamanho", p.getTamanho());
                req.setAttribute("visivelCliente", p.isVisivelCliente());
                req.setAttribute("codigoBarras", p.getCodigoBarras());
                req.setAttribute("preco", p.getPreco());

                disp = req.getRequestDispatcher("WEB-INF/view/produto/editar.jsp");
                disp.forward(req, resp);

            } else {

                // Seta mensagens a ser exibidas no modal sobre o status do cadastro
                req.setAttribute("status", "<i class=\"fas fa-times text-danger\"></i> Falha");
                req.setAttribute("detalhes", "Produto não encontrado");
                req.setAttribute("chamou_editar", true);

                disp = req.getRequestDispatcher("produto-visualizarEditar");
                disp.forward(req, resp);
            }

        } else {

            req.setAttribute("status", "<i class=\"fas fa-times text-danger\"></i> Falha");
            req.setAttribute("detalhes", "Parâmetros GET incorretos.");
            req.setAttribute("chamou_editar", true);

            disp = req.getRequestDispatcher("produto-visualizarEditar");
            disp.forward(req, resp);
        }

    }

}
