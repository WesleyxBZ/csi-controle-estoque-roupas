package controller.servlets;

import com.sun.tools.corba.se.idl.Util;
import dao.ProdutoDAO;
import dao.VendaDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Produto;

@WebServlet(urlPatterns = "/atualizar-produto")
public class Produto_Atualizar extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Recebe todos os dados inserido no formulário 
        int id_produto = Integer.parseInt(req.getParameter("id_produto"));
        String marca = Util.capitalize(req.getParameter("marca").toLowerCase());
        String descricao = Util.capitalize(req.getParameter("descricao").toLowerCase());
        String caminhoImg = "sem"/*req.getParameter("caminhoImg")*/;
        int visivelCliente = Integer.parseInt(req.getParameter("visivelCliente"));
        double preco = Double.parseDouble(req.getParameter("preco"));

        // Verifica se visivelCliente == 1 (falso)
        boolean visivelC = true;
        if (visivelCliente == 1) {
            visivelC = false;
        }

        // Criar objeto produto e seta informações
        Produto p = new Produto();
        p.setId_produto(id_produto);
        p.setMarca(marca);
        p.setDescricao(descricao);
        p.setCaminhoImg(caminhoImg);
        p.setVisivelCliente(visivelC);
        p.setPreco(preco);

        req.setAttribute("chamou_editar", true);
        req.setAttribute("qtdProdutosCarinho", new VendaDAO().getQtdProdCarinho());

        if (new ProdutoDAO().updateProduto(p)) {

            req.setAttribute("status", "<i class=\"fas fa-check text-success\"></i> Sucesso");
            req.setAttribute("detalhes", "Informações do produto atualizadas com sucesso.");

        } else {

            req.setAttribute("status", "<i class=\"fas fa-times text-danger\"></i> Falha");
            req.setAttribute("detalhes", "Produto não atualizado, erro interno.");
        }

        RequestDispatcher disp = req.getRequestDispatcher("produto-visualizarEditar");
        disp.forward(req, resp);
    }

}
