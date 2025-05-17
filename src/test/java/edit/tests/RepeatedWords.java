package edit.tests;

import org.testng.annotations.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class RepeatedWords {
    public CountWordsResult findMoreRepeatedWord(String[] words) {
        if(words == null || words.length == 0) {
            return new CountWordsResult(null, 0);
        }
        Map<String, Integer> wordsFrequency = new HashMap<>();

        String moreRepeatedWord = null;
        int maxWords = 0;

        for(String word: words) {
            if(word == null) {
                continue;
            }
            String lowerCaseWord = word.toLowerCase();
            int cont = wordsFrequency.getOrDefault(lowerCaseWord, 0);
            wordsFrequency.put(lowerCaseWord, cont+1);

            if(cont + 1 > maxWords) {
                maxWords = cont + 1;
                moreRepeatedWord = lowerCaseWord;
            }
        }
        return new CountWordsResult(moreRepeatedWord, maxWords);
    }
    @Test
    void testRepeatedWords() {
// --- Ejemplos de uso ---

        String[] arreglo1 = {"manzana", "pera", "manzana", "uva", "manzana", "pera", "naranja"};
        CountWordsResult resultado1 = findMoreRepeatedWord(arreglo1);
        System.out.println("En el arreglo: " + java.util.Arrays.toString(arreglo1));
        System.out.println("La palabra más repetida es \"" + resultado1.getWord() + "\" y se repitió " + resultado1.getTotal() + " veces.");
        // Salida esperada: La palabra más repetida es "manzana" y se repitió 3 veces.

        System.out.println("-------------------------------------------------");

        String[] arreglo2 = {"hola", "mundo", "hola", "adiós", "Mundo", "hola"};
        CountWordsResult resultado2 = findMoreRepeatedWord(arreglo2);
        System.out.println("En el arreglo: " + java.util.Arrays.toString(arreglo2));
        System.out.println("La palabra más repetida es \"" + resultado2.getWord() + "\" y se repitió " + resultado2.getTotal() + " veces.");
        // Salida esperada: La palabra más repetida es "hola" y se repitió 3 veces.
        // Nota: "Mundo" y "mundo" se cuentan como la misma palabra debido a la normalización.

        System.out.println("-------------------------------------------------");

        String[] arreglo3 = {"uno", "dos", "tres", "cuatro"};
        CountWordsResult resultado3 = findMoreRepeatedWord(arreglo3);
        System.out.println("En el arreglo: " + java.util.Arrays.toString(arreglo3));
        System.out.println("La palabra más repetida es \"" + resultado3.getWord() + "\" y se repitió " + resultado3.getTotal() + " veces.");
        // Salida esperada: La palabra más repetida es "uno" (o cualquiera de ellas si hay empate) y se repitió 1 vez.

        System.out.println("-------------------------------------------------");

        String[] arreglo4 = {}; // Arreglo vacío
        CountWordsResult resultado4 = findMoreRepeatedWord(arreglo4);
        System.out.println("En el arreglo: " + java.util.Arrays.toString(arreglo4));
        System.out.println("La palabra más repetida es \"" + resultado4.getWord() + "\" y se repitió " + resultado4.getTotal() + " veces.");
        // Salida esperada: La palabra más repetida es "null" y se repitió 0 veces.

        System.out.println("-------------------------------------------------");

        String[] arreglo5 = {"test", "test", "Test"};
        CountWordsResult resultado5 = findMoreRepeatedWord(arreglo5);
        System.out.println("En el arreglo: " + java.util.Arrays.toString(arreglo5));
        System.out.println("La palabra más repetida es \"" + resultado5.getWord() + "\" y se repitió " + resultado5.getTotal() + " veces.");
        // Salida esperada: La palabra más repetida es "test" y se repitió 3 veces.

        System.out.println("-------------------------------------------------");

        String[] arreglo6 = {"solo"};
        CountWordsResult resultado6 = findMoreRepeatedWord(arreglo6);
        System.out.println("En el arreglo: " + java.util.Arrays.toString(arreglo6));
        System.out.println("La palabra más repetida es \"" + resultado6.getWord() + "\" y se repitió " + resultado6.getTotal() + " veces.");
        // Salida esperada: La palabra más repetida es "solo" y se repitió 1 vez.

        System.out.println("-------------------------------------------------");

        String[] arreglo7 = null; // Arreglo nulo
        CountWordsResult resultado7 = findMoreRepeatedWord(arreglo7);
        System.out.println("En el arreglo: null");
        System.out.println("La palabra más repetida es \"" + resultado7.getWord() + "\" y se repitió " + resultado7.getTotal() + " veces.");
        // Salida esperada: La palabra más repetida es "null" y se repitió 0 veces.
    }

    public class CountWordsResult {
        private String word;
        private int total;

        public CountWordsResult(String word, int total) {
            this.word = word;
            this.total = total;
        }
        String getWord() {
            return word;
        }
        int getTotal() {
            return total;
        }
    }
}
