package br.com.infoliver.sah.configuracao.validacao;

import java.util.List;

import br.com.infoliver.sah.configuracao.exception.BOException;
import br.com.infoliver.sah.configuracao.resource.ResourceConstant;
import br.com.infoliver.sah.configuracao.resource.ResourceUtils;

/**
 * 
 * Classe utilizada para realizar validações
 * 
 * @author Igo Cavalcanti
 * @since 17/11/2009
 * @version 1.0
 */
public class Validador {

    /**
     *
     * Verifica se a lista não está vazia.
     *
     * @param List
     *            <object> Lista
     * @param obj
     *            nome do objeto (O nome será utilizado na mensagem de erro
     *            lançada pela exceção)
     * @throws Exception
     *             (Ocorrera uma exceção caso a lista esteja vazia)
     */
    public static <E> void assertNotNullListObject(List<E> objectList,
            Object... obj) throws BOException {
        if (objectList.isEmpty()) {
            throw new BOException(ResourceUtils.getResourceFromKey(ResourceConstant.mensagemAvisoEntidadeNaoEncontrada, obj));
        }
    }

    /**
     *
     * Verifica se a lista não está vazia.
     *
     * @param List
     *            <object> Lista
     * @param mensagem
     *            (A mensagem será utilizada para descrever o motivo da exceção)
     * @throws Exception
     *             (Ocorrera uma exceção caso a lista esteja vazia)
     */
    public static <E> void assertNotNullListObject(Class<?> clazz, List<E> objectList) throws BOException {
        if (objectList.isEmpty()) {
            mensagemValidacao(clazz, ResourceConstant.mensagemAvisoEntidadeNaoEncontrada);
        }
    }

    /**
     *
     * Verifica se a lista não está vazia.
     *
     * @param List
     *            <object> Lista
     * @param mensagem
     *            (A mensagem será utilizada para descrever o motivo da exceção)
     * @throws Exception
     *             (Ocorrera uma exceção caso a lista esteja vazia)
     */
    public static <E> void assertNotNullListObject(List<E> objectList,
            String mensagem) throws BOException {
        if (objectList.isEmpty()) {
            throw new BOException(mensagem);
        }
    }

    /**
     *
     * Verifica se objeto não está vazio.
     *
     * @param object
     * @param obj
     *            nome do objeto
     * @throws Exception
     *             (Ocorrera uma exceção caso o objeto esteja vazio)
     */
    public static void assertNotNullObject(Class<?> clazz, Object object)
            throws BOException {
        if (object == null) {
            mensagemValidacao(clazz, ResourceConstant.mensagemAvisoEntidadeNaoEncontrada);
        }
    }

    public static void mensagemValidacao(Class<?> clazz, String mensagem) throws BOException {
        SearchValidador searchValidador = new SearchValidador(clazz);
        throw new BOException(ResourceUtils.getResourceFromKey(mensagem, searchValidador.getEntidadeNome(), searchValidador.getGenero()));
    }

    /**
     *
     * Verifica se objeto não está vazio.
     *
     * @param object
     * @param nome
     *            do objeto
     * @throws Exception
     *             (Ocorrera uma exceção caso o objeto esteja vazio)
     */
    public static void assertNotNullObject(Object object, Object... obj)
            throws BOException {
        if (object == null) {
            throw new BOException(ResourceUtils.getResourceFromKey(ResourceConstant.mensagemAvisoEntidadeNaoEncontrada, obj));
        }
    }

    /**
     *
     * Verifica se houve uma atualização no banco de dados.
     *
     * @param número
     *            de linhas atualizadas no banco de dados
     * @param nome
     *            do objeto que foi inserido no banco
     * @throws Exception
     *             (Ocorrera uma exceção caso o número de linhas atualizadas no
     *             banco de dados é igual a 0)
     */
    public static void assertNotUpdateObject(Class<?> clazz, Integer numero)
            throws BOException {
        if (numero == 0) {
            mensagemValidacao(clazz, ResourceConstant.mensagemAvisoEntidadeNaoAlterada);
        }
    }

    /**
     *
     * Verifica se houve uma inserção no bando de dados.
     *
     * @param número
     *            de linhas inseridas no banco de dados
     * @param nome
     *            do objeto que foi inserido no banco
     * @throws Exception
     *             (Ocorrera uma exceção caso o número de linhas inseridas no
     *             banco de dados é igual a 0)
     */
    public static void assertNotSaverObject(Class<?> clazz, Integer numero)
            throws BOException {
        if (numero == 0) {
            mensagemValidacao(clazz, ResourceConstant.mensagemAvisoEntidadeNaoSalva);
        }
    }

    /**
     *
     * Verifica se houve uma exclusão no bando de dados.
     *
     * @param número
     *            de linhas inseridas no banco de dados
     * @param nome
     *            do objeto que foi inserido no banco
     * @throws Exception
     *             (Ocorrera uma exceção caso o número de linhas excluidas no
     *             banco de dados é igual a 0)
     */
    public static void assertNotDeleteObject(Class<?> clazz, Integer numero)
            throws BOException {
        if (numero == 0) {
            mensagemValidacao(clazz, ResourceConstant.mensagemAvisoEntidadeNaoExcluida);
        }
    }
}
