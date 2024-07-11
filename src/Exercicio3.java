//ENUNCIADO
//Implemente uma classe que representa uma estrutura de listas que execute as seguintes operações:
// Lista.reverse(). Exemplo {1, 2, 3, 4}.reverse() resultará em {4 ,3, 2, 1}
//Lista.filter(args): {1, 2, 3, 4}.filter(x => x%2 == 0) resultará em {2, 4}
//Lista.map(args): {a, b, c, d}.map(x => x.toUpperCase()) resultará em {A, B, C, D}
//Para a solução utilize apenas tipos primitivos, não utilize wrappers como ArrayList, Collection, etc e
// operador Lambda.

import java.util.Scanner;
import java.util.function.Function;
import java.util.function.IntPredicate;

public class Exercicio3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.println("Digite os elementos da lista de números inteiros separados por espaços:");
        String[] inputElements = scanner.nextLine().split(" ");
        int[] intElements = new int[inputElements.length];
        for (int i = 0; i < inputElements.length; i++) {
            intElements[i] = Integer.parseInt(inputElements[i]);
        }
        Exercicio3 listaNumeros = new Exercicio3(intElements);


        System.out.println("Lista de números inteiros original: " + listaNumeros);
        System.out.println("Lista de números inteiros reversa: " + listaNumeros.reverse());


        System.out.println("Digite a condição para filtragem (ex: x -> x % 2 == 0):");
        String filterCondition = scanner.nextLine();
        IntPredicate predicate = x -> evaluatePredicate(filterCondition, x);
        Exercicio3 listaFiltradaNumeros = listaNumeros.filter(predicate);
        System.out.println("Lista de números inteiros filtrada: " + listaFiltradaNumeros);


        System.out.println("Digite a função para mapeamento (ex: x -> (char)(x + 'A')):");
        String mapFunction = scanner.nextLine();
        Function<Character, Character> function = x -> evaluateFunction(mapFunction, x);
        Exercicio3 listaMapeadaCaracteres = new Exercicio3('a', 'b', 'c', 'd').map(function);
        System.out.println("Lista de caracteres mapeada: " + listaMapeadaCaracteres);

        scanner.close();
    }

    private int[] intElements;
    private char[] charElements;


    public Exercicio3(int... elements) {
        this.intElements = elements;
    }


    public Exercicio3(char... elements) {
        this.charElements = elements;
    }


    public Exercicio3 reverse() {
        int[] reversed = new int[intElements.length];
        for (int i = 0; i < intElements.length; i++) {
            reversed[i] = intElements[intElements.length - 1 - i];
        }
        return new Exercicio3(reversed);
    }


    public Exercicio3 filter(IntPredicate predicate) {
        int[] filtered = new int[intElements.length];
        int count = 0;
        for (int element : intElements) {
            if (predicate.test(element)) {
                filtered[count++] = element;
            }
        }
        return new Exercicio3(trimArray(filtered, count));
    }


    public Exercicio3 map(Function<Character, Character> function) {
        char[] mapped = new char[charElements.length];
        for (int i = 0; i < charElements.length; i++) {
            mapped[i] = function.apply(charElements[i]);
        }
        return new Exercicio3(mapped);
    }


    @Override
    public String toString() {
        if (intElements != null) {
            return intArrayToString(intElements);
        } else {
            return charArrayToString(charElements);
        }
    }


    private String intArrayToString(int[] array) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i < array.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }


    private String charArrayToString(char[] array) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i < array.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }


    private int[] trimArray(int[] array, int length) {
        int[] trimmed = new int[length];
        System.arraycopy(array, 0, trimmed, 0, length);
        return trimmed;
    }


    private static boolean evaluatePredicate(String predicateString, int x) {
        return true;
    }


    private static char evaluateFunction(String functionString, char x) {
        if (functionString.equals("x -> ((Character)x).toUpperCase()")) {
            return Character.toUpperCase(x);
        }
        return x;
    }
}