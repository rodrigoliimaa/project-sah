package br.com.infoliver.sah.negocio.bo;

import java.util.List;

import br.com.infoliver.sah.configuracao.exception.BOException;
import br.com.infoliver.sah.negocio.entity.Usuario;
import br.com.infoliver.sah.negocio.vo.LoginVO;
import br.com.infoliver.sah.negocio.vo.PaginacaoVO;
import br.com.infoliver.sah.negocio.vo.RetornoVO;

public interface IUsuarioBO {
	
	LoginVO acessar(Usuario usuario)throws BOException;
	
	RetornoVO inserir(Usuario usuario)throws BOException;

	RetornoVO alterar(Usuario usuario)throws BOException;

	RetornoVO excluir(Usuario usuario)throws BOException;

	PaginacaoVO listarPaginado(PaginacaoVO usuario);

	List<Usuario> listar();

	List<Usuario> listarUsuarioDoGrupo(Integer sequencialGrupo);

	List<Usuario> listarUsuarioDiferenteDoGrupo(Integer sequencialGrupo);
	
	Usuario obter(Usuario usuario);
}