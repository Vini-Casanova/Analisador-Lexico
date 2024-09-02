package br.com.cesupa;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class Regras {
    private Map<Character, Token> tabela;

    public Regras() {
        this.tabela = new HashMap<>();
        tabela.put('+', Token.OP_ARITME);
        tabela.put('-', Token.OP_ARITME);
        tabela.put('*', Token.OP_ARITME);
        tabela.put('/', Token.OP_ARITME);
        tabela.put('=', Token.OP_ARITME);
        tabela.put('(', Token.PARENTESES);
        tabela.put(')', Token.PARENTESES);
        tabela.put(' ', Token.ESPACO);
    }

}
