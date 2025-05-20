package midia;

public enum Generos {
    ROCK(1, "Rock"),
    POP(2, "Pop"),
    MPB(3, "MPB"),
    JAZZ(4, "Jazz"),
    CLASSICA(5, "Clássica"),
    FICCAO(6, "Ficção"),
    BIOGRAFIA(7, "Biografia"),
    TECH(8, "Tecnologia"),
    ENTRETENIMENTO(9, "Entretenimento");

    private final int indice;
    private final String descricao;

    Generos(int indice, String descricao) {
        this.indice = indice;
        this.descricao = descricao;

    }
    public String getDescricao() {
        return descricao;
    }
    public int getIndice () { return indice;}
    public static Generos getDescricaoIndice(int indice) {
        for (Generos g : Generos.values()) {
            if (g.indice == indice) {
                return g;
            }
        }
        return null;
    }
}
