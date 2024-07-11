//ENUNCIADO:
// Considerando os tipos de figuras geométricas como quadrado, triângulo, retângulo e círculo. Desenvolva uma
// classe em Java que armazene calcule a área total das figuras, essa classe deve ser capaz de imprimir a somatória das
// áreas no console. Cada figura é representada por uma classe e suas propriedades devem ser armazenadas de acordo com o
// tipo da figura. Utilize os conceitos de orientação a objetos como herança, polimorfismo e encapsulamento para
// resolver o problema.


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Exercicio1 {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);

        List<FiguraGeometrica> figuras = new ArrayList<>();

        // Entrada de dados:
        System.out.print("Digite o lado do Quadrado: ");
        double ladoQuadrado = scanner.nextDouble();

        System.out.print("Digite a base do Triângulo: ");
        double baseTriangulo = scanner.nextDouble();
        System.out.print("Digite a altura do Triângulo: ");
        double alturaTriangulo = scanner.nextDouble();

        System.out.print("Digite a largura do Retângulo: ");
        double larguraRetangulo = scanner.nextDouble();
        System.out.print("Digite a altura do Retângulo: ");
        double alturaRetangulo = scanner.nextDouble();

        System.out.print("Digite o raio do Círculo: ");
        double raioCirculo = scanner.nextDouble();

        // Adicionando figuras à lista
        figuras.add(new Quadrado(ladoQuadrado));
        figuras.add(new Triangulo(baseTriangulo, alturaTriangulo));
        figuras.add(new Retangulo(larguraRetangulo, alturaRetangulo));
        figuras.add(new Circulo(raioCirculo));

        // Área Total
        double areaTotal = figuras.stream()
                .mapToDouble(FiguraGeometrica::calcularArea)
                .sum();

        // Imprime valor da soma total
        System.out.println("A soma da área das figuras geométricas é: " + areaTotal);

        // Imprime valor de cada figura
        for (FiguraGeometrica figura : figuras) {
            System.out.println("A área da figura: " + figura.getNome() + " é igual a: " + figura.calcularArea());
        }
        scanner.close();
    }
}

interface FiguraGeometrica {
    double calcularArea();
    String getNome();
}

// Quadrado
class Quadrado implements FiguraGeometrica {
    private double lado;

    public Quadrado(double lado) {
        this.lado = lado;
    }

    @Override
    public double calcularArea() {
        return lado * lado;
    }

    public String getNome() {
        return "Quadrado";
    }
}

// Triângulo
class Triangulo implements FiguraGeometrica {
    private double base;
    private double altura;

    public Triangulo(double base, double altura) {
        this.base = base;
        this.altura = altura;
    }

    @Override
    public double calcularArea() {
        return (base * altura) / 2;
    }

    public String getNome() {
        return "Triângulo";
    }
}

// Retângulo
class Retangulo implements FiguraGeometrica {
    private double largura;
    private double altura;

    public Retangulo(double largura, double altura) {
        this.largura = largura;
        this.altura = altura;
    }

    @Override
    public double calcularArea() {
        return largura * altura;
    }

    public String getNome() {
        return "Retângulo";
    }
}

// Círculo
class Circulo implements FiguraGeometrica {
    private double raio;

    public Circulo(double raio) {
        this.raio = raio;
    }

    @Override
    public double calcularArea() {
        return Math.PI * raio * raio;
    }

    public String getNome() {
        return "Círculo";
    }
}