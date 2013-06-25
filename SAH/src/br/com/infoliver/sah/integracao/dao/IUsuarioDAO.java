package br.com.infoliver.sah.integracao.dao;

import java.util.List;

import br.com.infoliver.sah.configuracao.exception.DAOException;
import br.com.infoliver.sah.negocio.entity.Usuario;
import br.com.infoliver.sah.negocio.vo.PaginacaoVO;

public interface IUsuarioDAO {
	
	Usuario consultarUsuario(Usuario usuario);
	
	void alterarSenha(Usuario usuario)throws DAOException;

	List<Usuario> inserir(Usuario usuario)throws DAOException;

	List<Usuario> alterar(Usuario usuario)throws DAOException;

	List<Usuario> excluir(Usuario usuario)throws DAOException;

	Integer totalRegitrosParaPaginacao(PaginacaoVO usuario);

	List<Usuario> listarPaginado(PaginacaoVO usuario);

	List<Usuario> listar();

	List<Usuario> listarUsuarioDoGrupo(Integer sequencialGrupo);

	List<Usuario> listarUsuarioDiferenteDoGrupo(Integer sequencialGrupo);
	
	Usuario obter(Usuario usuario);
}