/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AutorizacaoDeAcesso;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelos.PerfilDeAcesso;
import modelos.Usuario;

/**
 *
 * @author alunocmc
 */
public class AcessoFuncionarioComum implements Filter { 
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                    FilterChain chain) throws IOException, ServletException {
        //recuperar a sessão
        HttpSession sessaoUsuario = ((HttpServletRequest)request).getSession();
        Usuario usuario = (Usuario) sessaoUsuario.getAttribute("usuarioAutenticado");
        
        if(usuario!=null && usuario.getPerfil().equals(PerfilDeAcesso.FUNCIONARIOCOMUM)){
            chain.doFilter(request, response);
        }else{
            ((HttpServletResponse)response).sendRedirect("/AgendamentoDigital/acessoNegado.jsp");
        }
    }

    @Override
    public void destroy() {

    }
}
