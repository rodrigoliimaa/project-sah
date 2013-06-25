package br.com.infoliver.sah.negocio.vo;

import java.io.Serializable;
import org.springframework.stereotype.Component;

@Component("retornoVO")
public class RetornoVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String msg;
	private Object entidade;
	
	public RetornoVO retornar(String msg) {
		this.msg = msg;
		return this;
	}

	public RetornoVO retornar(Object entidade,String msg) {
		this.entidade = entidade;
		this.msg = msg;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getEntidade() {
		return entidade;
	}

	public void setEntidade(Object entidade) {
		this.entidade = entidade;
	}
}