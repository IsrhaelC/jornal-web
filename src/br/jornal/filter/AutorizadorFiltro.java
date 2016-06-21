package br.jornal.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class AutorizadorFiltro implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String uri = ((HttpServletRequest)request).getRequestURI();
		
		if(uri.endsWith("loginFormulario") || uri.endsWith("login")|| uri.endsWith("inserirUsuarioForm")|| 
				uri.endsWith("inserirUsuario") || uri.endsWith("/") || uri.endsWith("homePage"))
		{
			chain.doFilter(request, response);
		}
		else if(((HttpServletRequest) request).getSession().getAttribute("usuario_logado")!= null)
		{
			chain.doFilter(request, response);
		}
		else if((uri.startsWith("/Trabalho_Final/resources/")))
		{
			chain.doFilter(request, response);
		}
		else if(uri.endsWith("listarNoticias") || uri.endsWith("lerNoticia"))
		{
			chain.doFilter(request, response);
		}
		else if(uri.endsWith("listarSecoes") || uri.endsWith("noticiaSecao"))
		{
			chain.doFilter(request, response);
		}
		else if(uri.endsWith("listarClassificados") || uri.endsWith("inserirOfertaForm"))
		{
			chain.doFilter(request, response);
		}
		else {
			((HttpServletResponse) response).sendRedirect("homePage");
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
