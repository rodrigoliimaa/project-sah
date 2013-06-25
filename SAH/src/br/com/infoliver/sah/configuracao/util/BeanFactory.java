package br.com.infoliver.sah.configuracao.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class BeanFactory {
	private String path;
		
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Object lookup(String bean) {
		ApplicationContext context = new FileSystemXmlApplicationContext(
				getPath()+"\\config\\data-source-config.xml",
				getPath()+"\\config\\integracao-config.xml",
				getPath()+"\\config\\web-application-config.xml");
		return context.getBean(bean);
	}
}