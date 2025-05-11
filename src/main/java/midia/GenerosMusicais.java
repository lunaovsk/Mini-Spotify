package midia;

public enum GenerosMusicais {
    ROCK(1, "Rock"),
    POP(2, "Pop"),
    MPB(3, "MPB"),
    JAZZ(4, "Jazz"),
    CLASSICA(5, "Clássica");

    private final int indice;
    private final String descricao;

    GenerosMusicais(int indice, String descricao) {
        this.indice = indice;
        this.descricao = descricao;

    }

    public String getDescricao() {
        return descricao;
    }
    public int getIndice() {
        return indice;
    }
    public static GenerosMusicais porIndice(int indice) {
        for (GenerosMusicais c: GenerosMusicais.values()) {
            if(c.getIndice() == indice) {
                return c;
            }
        }
        return null;
    }
}
