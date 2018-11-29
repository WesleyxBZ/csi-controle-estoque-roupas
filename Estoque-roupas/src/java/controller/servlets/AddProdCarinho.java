package controller.servlets;

import dao.VendaDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Usuario;

@WebServlet(urlPatterns = "add_prod_carinho")
public class AddProdCarinho extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("chamou_produtoVenda", true);

        // Verifica se as strings enviadas por GET não são vazias
        if (!req.getParameter("id_estoque").isEmpty()) {

            // Pega objeto usuário da sessão
            HttpSession sessao = req.getSession();
            Usuario usuario = (Usuario) sessao.getAttribute("usuario");

            int id_estoque = Integer.parseInt(req.getParameter("id_estoque"));

            // Verifica se id's são maiores que zero
            if ((usuario != null) && usuario.getId_usuario() > 0 && id_estoque > 0) {

                if (new VendaDAO().insert(id_estoque, usuario.getId_usuario())) {

                    req.setAttribute("status", "<i class=\"fas fa-check text-success\"></i> Sucesso");
                    req.setAttribute("detalhes", "Produto adicionado ao carinho.");

                } else {

                    req.setAttribute("status", "<i class=\"fas fa-times text-danger\"></i> Falha");
                    req.setAttribute("detalhes", "Produto já está no carinho ou não existe.");
                }

            } else {

                req.setAttribute("status", "<i class=\"fas fa-times text-danger\"></i> Falha");
                req.setAttribute("detalhes", "Parâmetros GET incorretos.");
            }

        } else {

            req.setAttribute("status", "<i class=\"fas fa-times text-danger\"></i> Falha");
            req.setAttribute("detalhes", "Parâmetros GET não podem ser vázios.");
        }

        RequestDispatcher disp = req.getRequestDispatcher("vender");
        disp.forward(req, resp);
    }

}
