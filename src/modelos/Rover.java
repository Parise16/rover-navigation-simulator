package modelos;

import java.util.ArrayList;
import java.util.List;

public class Rover {
    public String modelo;
    public int inclinacaoMaxima;
    public double bateria;
    public int obstaculosTranspostos;
    public int obstaculosDesviados;
    public List<String> historicoObstaculos;

    public Rover(String modelo, int inclinacaoMaxima) {
        this.modelo = modelo;
        this.inclinacaoMaxima = inclinacaoMaxima;
        this.bateria = 100.0;
        this.obstaculosTranspostos = 0;
        this.obstaculosDesviados = 0;
        this.historicoObstaculos = new ArrayList<>();
    }

    public void capturarImagemCamera(Imagem img, Obstaculo obs) {
        img.getImagem(obs);
    }

    public double calcularAnguloDesvio(double largura, double distancia) {
        return Math.toDegrees(Math.atan(largura / distancia));
    }

    public void analisarObstaculoParaMovimento(Imagem img) {
        Obstaculo obs = img.obstaculo;
        double inclinacaoEfetiva = this.inclinacaoMaxima;

        if (obs.tipoSolo.equals("Rochoso")) {
            inclinacaoEfetiva = this.inclinacaoMaxima * 0.90;
        } else if (obs.tipoSolo.equals("Areia Fofa")) {
            inclinacaoEfetiva = this.inclinacaoMaxima * 0.80;
        }

        if (obs.inclinacao <= inclinacaoEfetiva) {
            System.out.println("Rover transpõe obstáculo com inclinação de " + obs.inclinacao + " graus.");

            double gastoTranspor = 3.0 + (3.0 * (obs.custoBateriaEstimado / 100.0));
            this.bateria -= gastoTranspor;

            this.obstaculosTranspostos++;
            this.historicoObstaculos.add("Transposto: " + obs.tipoObstaculo + " em solo " + obs.tipoSolo + " (Inclinação: " + obs.inclinacao + "°)");
        } else {
            double angulo;
            String direcao;
            if (obs.larguraDireita < obs.larguraEsquerda) {
                System.out.println("Rover precisa desviar para direita");
                angulo = calcularAnguloDesvio(obs.larguraDireita, img.distancia);
                System.out.printf("Rover desviou %.1f graus para a direita\n", angulo);
                direcao = "Direita";
            } else {
                System.out.println("Rover precisa desviar para esquerda");
                angulo = calcularAnguloDesvio(obs.larguraEsquerda, img.distancia);
                System.out.printf("Rover desviou %.1f graus para a esquerda\n", angulo);
                direcao = "Esquerda";
            }

            double gastoDesvio = (angulo * 0.05) + (2.0 * (obs.custoBateriaEstimado / 100.0));
            this.bateria -= gastoDesvio;

            this.obstaculosDesviados++;
            this.historicoObstaculos.add("Desviado (" + direcao + "): " + obs.tipoObstaculo + " em solo " + obs.tipoSolo + " (Inclinação: " + obs.inclinacao + "°, Giro: " + String.format("%.1f", angulo) + "°)");
        }
    }

    public void exibirRelatorioMissao() {
        System.out.println("\n=============================================");
        System.out.println("           RELATÓRIO DA MISSÃO               ");
        System.out.println("=============================================");
        System.out.printf("Modelo do Rover: %s\n", this.modelo);
        System.out.printf("Bateria Restante Final: %.1f%%\n", this.bateria);
        System.out.printf("Energia Total Consumida: %.1f%%\n", (100.0 - this.bateria));
        System.out.println("---------------------------------------------");
        System.out.println("Total de Obstáculos Encontrados: " + (this.obstaculosTranspostos + this.obstaculosDesviados));
        System.out.println("Obstáculos Transpostos: " + this.obstaculosTranspostos);
        System.out.println("Obstáculos Desviados:   " + this.obstaculosDesviados );

        if (!this.historicoObstaculos.isEmpty()) {
            System.out.println("---------------------------------------------");
            System.out.println("Histórico de Encontros:");
            for (int i = 0; i < this.historicoObstaculos.size(); i++) {
                System.out.println("  " + (i + 1) + ". " + this.historicoObstaculos.get(i));
            }
        }
        System.out.print("=============================================\n");
    }

    @Override
    public String toString() {
        return "Rover{" +
                "\nModelo= " + modelo + "\nInclinacaoMaxima= " + inclinacaoMaxima + "\nBateria= " + bateria + "%\n" + "}\n";
    }
}