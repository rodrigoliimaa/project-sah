package br.com.infoliver.sah.integracao.dao;

import java.util.List;

import br.com.infoliver.sah.negocio.entity.EncaminhamentoExterno;

public interface IEncaminhamentoExternoDAO {
List<EncaminhamentoExterno> listarEncaminhamento();
List<EncaminhamentoExterno> listarEncaminhamentoFiltro(EncaminhamentoExterno encaminhamentoExterno);
void inserir(EncaminhamentoExterno encaminhamentoExterno);
void alterar(EncaminhamentoExterno encaminhamentoExterno);
}
