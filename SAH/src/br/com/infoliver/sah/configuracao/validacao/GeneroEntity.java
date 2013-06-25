package br.com.infoliver.sah.configuracao.validacao;

public enum GeneroEntity {
    Masculino("o"),
    Feminino("a");

    private String genero;

    private GeneroEntity(String genero) {
        this.genero = genero;
    }

    public String getGenero() {
        return genero;
    }
}
