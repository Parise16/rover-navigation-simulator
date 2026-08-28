# Rover Navigation Simulator

Aplicação Java executada via terminal que simula a navegação de um rover em diferentes condições de terreno e diante de obstáculos.

O sistema analisa fatores como tipo de obstáculo, terreno, inclinação e distância para decidir se o rover pode atravessar o obstáculo com segurança ou se precisa desviar a rota. Durante a missão, a aplicação também monitora o consumo de bateria e registra o histórico de navegação.

## Funcionalidades

- Configuração do rover com inclinação máxima suportada
- Validação das entradas do usuário
- Simulação de diferentes condições de terreno
- Detecção de pedras e buracos
- Limitações de navegação de acordo com o terreno
- Simulação de distância até obstáculos
- Decisão automática entre atravessar ou desviar
- Cálculo do ângulo de desvio
- Consumo de bateria baseado no terreno e no movimento
- Registro do histórico da missão
- Relatório final da missão

## Como funciona

Durante a simulação, o rover analisa o ambiente antes de decidir como prosseguir.

1. O usuário informa o modelo do rover e sua inclinação máxima suportada.
2. O sistema verifica se existe um obstáculo no caminho.
3. O terreno é classificado como:
   - Plano
   - Rochoso
   - Areia fofa
4. A inclinação do obstáculo é comparada com a capacidade do rover e com as condições do terreno.
5. Se o obstáculo puder ser atravessado com segurança, o rover segue em frente.
6. Caso a inclinação seja muito alta, o sistema analisa o espaço disponível nos dois lados.
7. O rover calcula um ângulo de desvio e seleciona a menor rota ao redor do obstáculo.
8. Cada movimento afeta o consumo de bateria.
9. Ao final, o sistema gera um relatório da missão.

## Lógica de navegação

As condições do terreno alteram a inclinação máxima efetiva suportada pelo rover:

- **Terreno plano:** 100% da capacidade configurada
- **Terreno rochoso:** 90% da capacidade configurada
- **Areia fofa:** 80% da capacidade configurada

Quando é necessário desviar de um obstáculo, o rover calcula o ângulo de mudança de direção utilizando a largura do obstáculo e sua distância simulada:

```java
Math.toDegrees(Math.atan(largura / distancia));
```

Esse cálculo permite estimar o ângulo necessário para contornar o obstáculo.

## Estrutura do projeto

```text
src/
├── aplicacao/
│   └── NavegacaoRover.java
│
└── modelos/
    ├── Imagem.java
    ├── Obstaculo.java
    └── Rover.java
```

### `NavegacaoRover`

Ponto de entrada da aplicação. Responsável pela interação com o usuário, validação das entradas e controle do fluxo da simulação.

### `Rover`

Representa o rover e concentra a lógica de navegação, gerenciamento de bateria, análise de obstáculos e histórico da missão.

### `Obstaculo`

Representa um obstáculo e armazena informações como tipo, dimensões, inclinação e condições do terreno.

### `Imagem`

Simula o processo de captura do ambiente pelo rover, associando os obstáculos detectados a informações de distância.

## Tecnologias e conceitos utilizados

- Java
- Programação Orientada a Objetos
- Classes e objetos
- Construtores
- Métodos
- Collections
- Validação de entrada
- Estruturas condicionais
- Cálculos matemáticos
- Organização de código
- Resolução de problemas

## Como executar

### Requisitos

- Java JDK instalado

Clone o repositório:

```bash
git clone https://github.com/Parise16/rover-navigation-simulator.git
```

Entre na pasta do projeto:

```bash
cd rover-navigation-simulator
```

Compile:

```bash
javac -d out src/modelos/Imagem.java src/modelos/Obstaculo.java src/modelos/Rover.java src/aplicacao/NavegacaoRover.java
```

Execute:

```bash
java -cp out aplicacao.NavegacaoRover
```

Também é possível abrir e executar o projeto em uma IDE como IntelliJ IDEA.

## Possíveis melhorias

- Testes unitários
- Melhor encapsulamento dos atributos do rover
- Separação entre lógica de simulação e interface com o usuário
- Visualização gráfica da rota
- Novos tipos de terreno
- Novos tipos de obstáculos
- Persistência do histórico das missões

## Contexto acadêmico

Projeto acadêmico desenvolvido durante a graduação em Engenharia da Computação na FIAP, com foco em Programação Orientada a Objetos, modelagem de classes, lógica de decisão e simulação de navegação.
