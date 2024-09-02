package br.com.cesupa;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LexicalAnalyzer {
    public static void main(String[] args) {
        try {
            String codigo = readFile("src/main/java/br/com/cesupa/code/codigo.txt");
            Regras regras = new Regras();
           // System.out.println(codigo);
            List<Simbolos> tokens = tokenize(regras.getTabela(),codigo);

            for (Simbolos token : tokens) {
                System.out.println(token);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String readFile(String path) throws IOException{
        String readLine;
        StringBuilder text = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(path));

        while ((readLine = reader.readLine()) != null) {
            text.append(readLine).append(" ");
        }
        reader.close();

        return text.toString();
    }


    public static List<Simbolos> tokenize(Map<Character, Token> regras, String input) {
        int position = 0;

        List<Simbolos> tokens = new ArrayList<>();
        while (position < input.length()) {
            char currentChar = currentChar(position,input);

            if (regras.containsKey(currentChar)) {
                tokens.add(new Simbolos(regras.get(currentChar)));
                position++;
            } else if ('"' == currentChar) {
                StringBuilder result = new StringBuilder();
                position++;
                while ('"' != currentChar(position,input)) {
                    result.append(currentChar(position,input));
                    position++;
                }
                position++;
                tokens.add(new Simbolos(Token.LITERAL, result.toString()));
            } else if (Character.isLetter(currentChar)) {
                StringBuilder result = new StringBuilder();
                while (Character.isLetterOrDigit(currentChar(position,input))) {
                    result.append(currentChar(position,input));
                    position++;
                }
                tokens.add(new Simbolos(Token.IDENT, result.toString()));
            } else if (Character.isDigit(currentChar)) {
                StringBuilder result = new StringBuilder();
                Token numberType = Token.NUM;
                while (Character.isDigit(currentChar(position,input)) || '.' == currentChar(position,input)) {
                    if ('.' == currentChar(position,input)) numberType = Token.FLOAT;
                    result.append(currentChar(position,input));
                    position++;
                }
                tokens.add(new Simbolos(numberType, result.toString()));
            }  else {
                System.out.println("Unexpected character: " + currentChar);
            }
        }
        return tokens;
    }

    private static char currentChar(int offset, String input) {
        return offset >= input.length() ? '\0' : input.charAt(offset);
    }

}





