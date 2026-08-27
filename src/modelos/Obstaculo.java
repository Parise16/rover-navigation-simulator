package modelos;

public class Obstaculo {
    public String tipoObstaculo;
    public double larguraEsquerda;
    public double larguraDireita;
    public int inclinacao;
    public String tipoSolo;
    public double custoBateriaEstimado;

    public Obstaculo(String tipoObstaculo, double larguraEsquerda, double larguraDireita, int inclinacao, String tipoSolo) {
        this.tipoObstaculo = tipoObstaculo;
        this.larguraEsquerda = larguraEsquerda;
        this.larguraDireita = larguraDireita;
        this.inclinacao = inclinacao;
        this.tipoSolo = tipoSolo;

        if (tipoSolo.equals("Rochoso")) {
            this.custoBateriaEstimado = 15.0;
        } else if (tipoSolo.equals("Areia Fofa")) {
            this.custoBateriaEstimado = 30.0;
        } else {
            this.custoBateriaEstimado = 0.0;
        }
    }

    public void exibirObstaculo() {
        System.out.println("\n\nObstáculo encontrado");
        System.out.println("Tipo = " + tipoObstaculo);
        System.out.println("Solo = " + tipoSolo);
        System.out.printf("largura para direita = %.2f (cm)\n", larguraDireita);
        System.out.printf("largura para esquerda = %.2f (cm)\n", larguraEsquerda);
        System.out.println("inclinação = " + inclinacao + " graus");
    }
}