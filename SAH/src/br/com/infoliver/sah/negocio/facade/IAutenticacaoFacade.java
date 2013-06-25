package br.com.infoliver.sah.negocio.facade;

import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.flex.remoting.RemotingInclude;

import br.com.infoliver.sah.negocio.vo.LoginVO;

@RemotingDestination
public interface IAutenticacaoFacade {

	/*@RemotingInclude
	LoginVO login(String login, String senha);

	void sair();

	boolean isAutenticado();
	*/
}
