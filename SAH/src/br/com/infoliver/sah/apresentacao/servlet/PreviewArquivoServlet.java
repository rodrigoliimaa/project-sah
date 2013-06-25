package br.com.infoliver.sah.apresentacao.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.infoliver.sah.configuracao.util.BeanFactory;
import br.com.infoliver.sah.negocio.entity.Arquivo;
import br.com.infoliver.sah.negocio.facade.ISistemaFacade;
import br.com.infoliver.sah.negocio.facade.impl.SistemaFacade;

public class PreviewArquivoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Integer sequencial= Integer.parseInt(request.getParameter("sequencial"));
			Boolean isDownload = Boolean.parseBoolean(request.getParameter("isDownload"));
			//------------------------------------------------------------------------------------
			BeanFactory beanFactory=new BeanFactory();
			beanFactory.setPath(getServletContext().getRealPath("/WEB-INF"));
			ISistemaFacade facade = (SistemaFacade) beanFactory.lookup("facade");			
			//------------------------------------------------------------------------------------
			String nome;
			byte[] imagemArquivo;		
			Arquivo arquivo=facade.consultarParaVisualizacaoArquivo(sequencial);
			nome=arquivo.getNome();
			imagemArquivo=arquivo.getImagemArquivo();
			//------------------------------------------------------------------------------------			
			if(isDownload){
				response.setContentLength(imagemArquivo.length);
				response.setContentType("application/octet-stream");
			}else{
				String extensao= nome.substring(nome.length()-3, nome.length()).toLowerCase();
				if (extensao.equals("pdf"))
					response.setContentType("application/pdf");
				else if(extensao.equals("txt"))
					response.setContentType("text/plain");
				else if(extensao.equals("exe"))
					response.setContentType("application/octet-stream");
				else if(extensao.equals("zip"))
					response.setContentType("application/zip");
				else if(extensao.equals("doc"))
					response.setContentType("application/msword");
				else if(extensao.equals("xls"))
					response.setContentType("application/vnd.ms-excel");
				else if(extensao.equals("ppt"))
					response.setContentType("application/vnd.ms-powerpoint");
				else if(extensao.equals("gif"))
					response.setContentType("image/gif");
				else if(extensao.equals("png"))
					response.setContentType("image/png");
				else if(extensao.equals("jpeg"))
					response.setContentType("image/jpeg");
				else if(extensao.equals("jpg"))
					response.setContentType("image/jpeg");
				else if(extensao.equals("mp3"))
					response.setContentType("audio/mpeg");
				else if(extensao.equals("wav"))
					response.setContentType("audio/x-wav");
				else if(extensao.equals("mpeg"))
					response.setContentType("video/mpeg");
				else if(extensao.equals("mpg"))
					response.setContentType("video/mpeg");
				else if(extensao.equals("mpe"))
					response.setContentType("video/mpeg");
				else if(extensao.equals("mov"))
					response.setContentType("video/quicktime");
				else if(extensao.equals("avi"))
					response.setContentType("video/x-msvideo");
				else if(extensao.equals("flv"))
					response.setContentType("video/flv");
			}
			//------------------------------------------------------------------------------------			
			response.getOutputStream().write(imagemArquivo);
			response.flushBuffer();
		} catch (Exception e) {
			System.out.println("Erro inesperado: " + e.getMessage());
			e.printStackTrace();
		}
	}
}