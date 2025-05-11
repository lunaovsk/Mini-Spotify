package midia;

public enum TipoMidia {
    MUSICA(1, "Música"),
    PODCAST(2, "Podcast"),
    AUDIOBOOK(3, "Audiobook");

    private final String descricao;
    private final int indice;

    TipoMidia(int indice, String descricao) {
        this.descricao = descricao;
        this.indice = indice;
    }

    public int getIndice() {
        return indice;
    }
    public String getDescricao() {
        return descricao;
    }
    public static TipoMidia porIndiceTipo(int indice) {
        for (TipoMidia m : TipoMidia.values()) {
            if (m.getIndice() == indice) {
                return m;
            }
        }
        return null;
    }
}
