package br.com.infoliver.sah.configuracao.validacao;

import java.lang.annotation.Annotation;

public class SearchValidador {
    private String entidadeNome;
    private String genero;

    public SearchValidador(Class<?> clazz) {
        search(clazz);
    }

    public void search(Class<?> clazz) {
        for (Annotation annotation : clazz.getAnnotations()) {
            if (annotation instanceof EntityValidador) {
                entidadeNome = ((EntityValidador) annotation).entidade();
                genero = ((EntityValidador) annotation).genero().getGenero();
                break;
            }
        }
    }

    public String getEntidadeNome() {
        return entidadeNome;
    }

    public String getGenero() {
        return genero;
    }
}