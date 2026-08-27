package aplicacao;

import modelos.Imagem;
import modelos.Obstaculo;
import modelos.Rover;

import java.util.Scanner;

public class NavegacaoRover {
    public static Scanner le = new Scanner(System.in);

    private static int lerInteiroSeguro(String mensagem, int min, int max) {
        int valor = 0;
        boolean valido = false;
        while (!valido) {
            System.out.print(mensagem);
            if (le.hasNextInt()) {
                valor = le.nextInt();
                if (valor >= min && valor <= max) {
                    valido = true;
                } else {
                    System.out.printf("-> Erro: Opção inválida. Digite um valor entre %d e %d.\n", min, max);
                }
            } else {
                System.out.println("-> Erro: Entrada inválida. Digite apenas números inteiros.");
                le.next();
            }
        }
        return valor;
    }

    private static double lerDoubleSeguro(String mensagem) {
        double valor = 0;
        boolean valido = false;
        while (!valido) {
            System.out.print(mensagem);
            if (le.hasNextDouble()) {
                valor = le.nextDouble();
                if (valor > 0) {
                    valido = true;
                } else {
                    System.out.println("-> Erro: O valor precisa ser maior que zero.");
                }
            } else {
                System.out.println("-> Erro: Entrada inválida. Digite um número válido.");
                le.next();
            }
        }
        return valor;
    }

    public static void main(String[] args) {
        String modelo = "";

        while (modelo.trim().isEmpty()) {
            System.out.print("Digite o modelo do Rover: ");
            modelo = le.nextLine();
            if (modelo.trim().isEmpty()) {
                System.out.println("-> Erro: O modelo não pode ficar vazio.");
            }
        }

        int inclinacao = lerInteiroSeguro("\nQual inclinação máxima para subir ou descer obstáculos? (graus): ", 1, 90);

        Rover rover = new Rover(modelo, inclinacao);

        System.out.println("Rover está pronto para iniciar testes!!!");
        System.out.println(rover.toString());

        Imagem img = new Imagem();

        int continua;
        do {
            Obstaculo obstaculo = lerDadosObstaculo(rover);

            if (obstaculo != null) {
                rover.capturarImagemCamera(img, obstaculo);
                img.exibirImagem();
                rover.analisarObstaculoParaMovimento(img);
            }

            System.out.println("\nRover está se movimentando para frente...");
            rover.bateria -= 1.5;
            System.out.printf("Bateria restante: %.1f%%\n", rover.bateria);

            if (rover.bateria <= 0) {
                System.out.println("Alerta: Bateria esgotada. Missão encerrada por falha de energia.");
                break;
            }

            continua = lerInteiroSeguro("\nDigite 1 para continuar a movimentar rover (ou 0 para sair): ", 0, 1);
        } while (continua == 1);

        rover.exibirRelatorioMissao();

        System.out.println("Process finished with exit code 0");
    }

    public static Obstaculo lerDadosObstaculo(Rover rover) {
        int opcao = lerInteiroSeguro("\nExiste objeto a frente? (1-sim, 2-não): ", 1, 2);

        if (opcao == 1) {
            int tipo = lerInteiroSeguro("Qual tipo de obstáculo 1-Rocha ou 2-Buraco: ", 1, 2);
            String tipoObstaculo;
            if (tipo == 1) {
                tipoObstaculo = "Rocha";
            } else {
                tipoObstaculo = "Buraco";
            }

            double probSolo = Math.random();
            String tipoSolo;
            if (probSolo < 0.50) {
                tipoSolo = "Plano";
            } else if (probSolo < 0.80) {
                tipoSolo = "Rochoso";
            } else {
                tipoSolo = "Areia Fofa";
            }
            System.out.println("-> Sensor identificou o solo como: " + tipoSolo);

            double inclinacaoEfetiva = rover.inclinacaoMaxima;
            if (tipoSolo.equals("Rochoso")) {
                inclinacaoEfetiva = rover.inclinacaoMaxima * 0.90;
            } else if (tipoSolo.equals("Areia Fofa")) {
                inclinacaoEfetiva = rover.inclinacaoMaxima * 0.80;
            }

            int inclinacaoObs = lerInteiroSeguro("\nQual a inclinação para transpor? (graus): ", 0, 90);

            double larguraDireita = 0.0;
            double larguraEsquerda = 0.0;

            if (inclinacaoObs > inclinacaoEfetiva) {
                System.out.println("-> Obstáculo muito íngreme! Solicitando leitura periférica...");
                larguraDireita = lerDoubleSeguro("\nQual a largura do obstáculo para direita? (cm): ");
                larguraEsquerda = lerDoubleSeguro("Qual a largura do obstáculo para esquerda? (cm): ");
            } else {
                System.out.println("-> Inclinação segura. Ignorando leitura lateral.");
            }

            return new Obstaculo(tipoObstaculo, larguraEsquerda, larguraDireita, inclinacaoObs, tipoSolo);
        }

        return null;
    }
}