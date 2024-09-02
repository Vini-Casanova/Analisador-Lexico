package br.com.cesupa;


public class Simbolos {
    Token tipo;
    String lexema;

    public Simbolos(Token tipo, String value) {
        this.tipo = tipo;
        this.lexema = value;
    }

    public Simbolos(Token tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {

        return lexema == null ? String.format("<%s>", tipo) : String.format("<%s, %s>", tipo, lexema);
    }
}
