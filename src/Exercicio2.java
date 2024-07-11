//ENUNCIADO:
//Um Condomínio é composto por diversos blocos de apartamentos e que cada bloco gerencia suas próprias receitas e
// despesas independentemente. Considerando que existem apartamentos de diversos tamanhos e que o valor do condomínio
// depende da fração ideal de cada um, calcule o valor do condomínio de cada apartamento. O valor do condomínio é
// calculado de acordo com a somatória das despesas do bloco. Desenvolva uma classe em Java que armazene os apartamentos
// e suas respectivas áreas e que seja capaz de calcular o valor do condomínio de cada apartamento de acordo com o total
// das despesas informadas.


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Exercicio2 {
    public static void main(String[] args) {
        Bloco bloco = new Bloco();


        Scanner scanner = new Scanner(System.in);

        // Entrada de despesas do bloco
        System.out.print("Digite o valor das despesas do bloco: ");
        double despesas = scanner.nextDouble();
        scanner.nextLine();
        bloco.adicionarDespesas(despesas);

        // Entrada da quantidade de apartamentos do bloco
        System.out.print("Digite a quantidade de apartamentos do bloco: ");
        int quantidadeApartamentos = scanner.nextInt();
        scanner.nextLine();

        // Entrada dos detalhes de cada apartamento
        System.out.println("Digite os detalhes de cada apartamento:");
        System.out.println("Formato de entrada: area_construida area_total numero_apartamento");

        for (int i = 0; i < quantidadeApartamentos; i++) {
            System.out.print("Apartamento " + (i + 1) + ": ");
            String input = scanner.nextLine().trim();

            String[] dados = input.split(" ");
            if (dados.length != 3) {
                System.out.println("Entrada inválida. Tente novamente.");
                i--;
                continue;
            }

            try {
                double areaConstruida = Double.parseDouble(dados[0]);
                double areaTotal = Double.parseDouble(dados[1]);
                int numeroApartamento = Integer.parseInt(dados[2]);

                // Calcular fração ideal
                double fracaoIdeal = areaConstruida / areaTotal;

                // Adicionar apartamento ao bloco
                bloco.adicionarApartamento(new Apartamento(areaConstruida, fracaoIdeal, numeroApartamento));
            } catch (NumberFormatException e) {
                System.out.println("Erro ao processar entrada. Tente novamente.");
                i--;
            }
        }

        scanner.close();

        bloco.imprimirValoresCondominio();
    }
}

class Apartamento {
    private double areaConstruida;
    private double fracaoIdeal;
    private int numeroApartamento;

    public Apartamento(double areaConstruida, double fracaoIdeal, int numeroApartamento) {
        this.areaConstruida = areaConstruida;
        this.fracaoIdeal = fracaoIdeal;
        this.numeroApartamento = numeroApartamento;
    }

    public double getAreaConstruida() {
        return areaConstruida;
    }

    public double getFracaoIdeal() {
        return fracaoIdeal;
    }

    public int getNumeroApartamento() {
        return numeroApartamento;
    }
}

class Bloco {
    private List<Apartamento> apartamentos;
    private double despesas;
    private double somaFracaoIdeal;

    public Bloco() {
        this.apartamentos = new ArrayList<>();
        this.despesas = 0;
        this.somaFracaoIdeal = 0;
    }

    public void adicionarApartamento(Apartamento apartamento) {
        apartamentos.add(apartamento);
        somaFracaoIdeal += apartamento.getFracaoIdeal();
    }

    public void adicionarDespesas(double valor) {
        this.despesas += valor;
    }

    public double calcularValorCondominio(Apartamento apartamento) {

        double valorCondominio = (apartamento.getFracaoIdeal() / somaFracaoIdeal) * despesas;
        return valorCondominio;
    }

    public void imprimirValoresCondominio() {
        DecimalFormat df = new DecimalFormat("#.##");
        for (Apartamento apartamento : apartamentos) {
            double valorCondominio = calcularValorCondominio(apartamento);
            System.out.println("Apartamento número " + apartamento.getNumeroApartamento() +
                    " com área construída " + apartamento.getAreaConstruida() +
                    " paga condomínio de: " + df.format(valorCondominio));
        }
    }
}