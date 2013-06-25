package br.com.infoliver.sah.apresentacao.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.print.attribute.standard.Media;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.internal.matchers.Each;

import br.com.infoliver.sah.configuracao.util.BeanFactory;
import br.com.infoliver.sah.negocio.entity.Agendamento;
import br.com.infoliver.sah.negocio.entity.EncaminhamentoExterno;
import br.com.infoliver.sah.negocio.entity.Fornecedor;
import br.com.infoliver.sah.negocio.entity.Medico;
import br.com.infoliver.sah.negocio.entity.Movimentacao;
import br.com.infoliver.sah.negocio.entity.MovimentacaoProcedimento;
import br.com.infoliver.sah.negocio.entity.Paciente;
import br.com.infoliver.sah.negocio.entity.Pessoa;
import br.com.infoliver.sah.negocio.entity.PreAtendimento;
import br.com.infoliver.sah.negocio.entity.Setor;
import br.com.infoliver.sah.negocio.entity.SetorEncaminhamentoExterno;
import br.com.infoliver.sah.negocio.entity.SigtapProcedimento;
import br.com.infoliver.sah.negocio.entity.TipoStatusAtendimento;
import br.com.infoliver.sah.negocio.entity.Usuario;
import br.com.infoliver.sah.negocio.facade.ISistemaFacade;
import br.com.infoliver.sah.negocio.facade.impl.SistemaFacade;
import br.com.infoliver.sah.negocio.vo.PaginacaoVO;

public class TesteFacade extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		import org.springframework.security.core.Authentication; 
		import org.springframework.security.core.context.SecurityContext; 
		import org.springframework.security.core.context.SecurityContextHolder; 

		SecurityContext securityContext = SecurityContextHolder.getContext(); 
		Authentication authentication = securityContext.getAuthentication();
		*/ 

		
		BeanFactory beanFactory=new BeanFactory();
		beanFactory.setPath(getServletContext().getRealPath("/WEB-INF"));
		/*IAutenticacaoFacade autenticacaoFacade = (AutenticacaoFacade) beanFactory.lookupSpringSecurity("autenticacaoFacade");
		LoginVO login=autenticacaoFacade.autenticarUsuario("admin", "CF2D3AF3A668068B4BD763D4CD71E562");
		System.out.println(login.getPessoaUsuario().getNome());
		*/
		//--------------------------------------------------------------------
		ISistemaFacade facade = (SistemaFacade) beanFactory.lookup("facade");
		Movimentacao movimentacao = new Movimentacao();
		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setSequencial(1);
		movimentacao.setFornecedor(fornecedor);
		
		
		
		List<Movimentacao> lista =facade.listarRelatorioMapa(movimentacao);	
		
		for (Movimentacao movimentacao2 : lista) {
			System.out.println("teste facade"+movimentacao2.getPaciente().getNome());
		}
	
			
				
	}

}
