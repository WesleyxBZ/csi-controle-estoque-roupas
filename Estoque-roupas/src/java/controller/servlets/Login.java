package controller.servlets;

import dao.LoginDAO;
import dao.UsuarioDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = "login")
public class Login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String senha = req.getParameter("senha");

        RequestDispatcher disp;

        if (new LoginDAO().autenticar(email, senha)) {

            // Iniciando sessão
            HttpSession sessao = req.getSession();

            // Atributo da sessão, ficará aberto na sessão enquando ela existir            
            sessao.setAttribute("usuario", new UsuarioDAO().getUsuario(email));

            disp = req.getRequestDispatcher("dashboard");
            disp.forward(req, resp);

        } else {
            req.setAttribute("mensagem_erro", "Senha ou email incorreto!");
            disp = req.getRequestDispatcher("index.jsp");
            disp.forward(req, resp);
        }

    }

}
