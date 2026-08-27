package modelos;

public class Imagem {
    public Obstaculo obstaculo;
    public double distancia;

    public Imagem() {
    }

    public void getImagem(Obstaculo obs) {
        this.obstaculo = obs;
        this.distancia = 100 + (Math.random() * 400);
    }

    public void exibirImagem() {
        if (obstaculo != null) {
            obstaculo.exibirObstaculo();
            System.out.printf("Distância do obstáculo %.2f (cm)\n", distancia);
        }
    }
}