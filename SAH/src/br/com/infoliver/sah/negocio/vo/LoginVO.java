package br.com.infoliver.sah.negocio.vo;

import java.util.List;

import br.com.infoliver.sah.negocio.entity.Empresa;
import br.com.infoliver.sah.negocio.entity.Usuario;

public class LoginVO {
	private Empresa empresa;
	private Usuario usuario;
	private List parametro;
	private List permissao;

	public LoginVO() {
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List getParametro() {
		return parametro;
	}

	public void setParametro(List parametro) {
		this.parametro = parametro;
	}

	public List getPermissao() {
		return permissao;
	}

	public void setPermissao(List permissao) {
		this.permissao = permissao;
	}
}