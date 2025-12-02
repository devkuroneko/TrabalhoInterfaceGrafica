package com.mycompany.usandoguibuilder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegrasJogo {

    // Enum para facilitar a identificação das posições sem usar Strings soltas
    public enum Posicao {
        SUPERIOR_ESQUERDA,
        SUPERIOR_DIREITA,
        SUPERIOR_CENTRAL,
        CENTRAL,
        INFERIOR_ESQUERDA,
        INFERIOR_DIREITA
    }

    // Mapa que define o GRAFO (quem conecta com quem)
    private final Map<Posicao, List<Posicao>> vizinhos;

    // Estado do jogo
    private Posicao posicaoCabrito;
    private Posicao posicaoCarcara;
    private boolean turnoDoCabrito; // true = Cabrito, false = Carcará
    private boolean superPuloDisponivel;
    private boolean jogoFinalizado;
    private int contadorJogadas;

    public RegrasJogo() {
        this.vizinhos = new HashMap<>();
        configurarVizinhos();
        reiniciarJogo();
    }

    private void configurarVizinhos() {
        // Mapeando as conexões conforme o desenho do pentágono [cite: 41]

        // Centro conecta com todos
        vizinhos.put(Posicao.CENTRAL, Arrays.asList(
                Posicao.SUPERIOR_CENTRAL, Posicao.SUPERIOR_ESQUERDA, Posicao.SUPERIOR_DIREITA,
                Posicao.INFERIOR_ESQUERDA, Posicao.INFERIOR_DIREITA
        ));

        // Superior Central conecta com Centro, Sup. Esquerda e Sup. Direita
        vizinhos.put(Posicao.SUPERIOR_CENTRAL, Arrays.asList(
                Posicao.CENTRAL, Posicao.SUPERIOR_ESQUERDA, Posicao.SUPERIOR_DIREITA
        ));

        // Superior Esquerda conecta com Centro, Sup. Central e Inf. Esquerda
        vizinhos.put(Posicao.SUPERIOR_ESQUERDA, Arrays.asList(
                Posicao.CENTRAL, Posicao.SUPERIOR_CENTRAL, Posicao.INFERIOR_ESQUERDA
        ));

        // Superior Direita conecta com Centro, Sup. Central e Inf. Direita
        vizinhos.put(Posicao.SUPERIOR_DIREITA, Arrays.asList(
                Posicao.CENTRAL, Posicao.SUPERIOR_CENTRAL, Posicao.INFERIOR_DIREITA
        ));

        // Inferior Esquerda conecta com Centro, Sup. Esquerda e Inf. Direita
        vizinhos.put(Posicao.INFERIOR_ESQUERDA, Arrays.asList(
                Posicao.CENTRAL, Posicao.SUPERIOR_ESQUERDA, Posicao.INFERIOR_DIREITA
        ));

        // Inferior Direita conecta com Centro, Sup. Direita e Inf. Esquerda
        vizinhos.put(Posicao.INFERIOR_DIREITA, Arrays.asList(
                Posicao.CENTRAL, Posicao.SUPERIOR_DIREITA, Posicao.INFERIOR_ESQUERDA
        ));
    }

    public void reiniciarJogo() {
        // Posições iniciais sugeridas (ajuste conforme imagem do PDF se necessário)
        this.posicaoCabrito = Posicao.INFERIOR_ESQUERDA;
        this.posicaoCarcara = Posicao.SUPERIOR_CENTRAL;

        this.turnoDoCabrito = true; // O Cabrito começa fugindo [cite: 49]
        this.superPuloDisponivel = true;
        this.jogoFinalizado = false;
        this.contadorJogadas = 0;
    }

    public void processarJogada(Posicao destino) throws MovimentoInvalidoException {
        if (jogoFinalizado) {
            throw new MovimentoInvalidoException("O jogo acabou! Reinicie para jogar novamente.");
        }

        Posicao origemAtual = turnoDoCabrito ? posicaoCabrito : posicaoCarcara;

        // 1. Verifica se tentou mover para o próprio lugar
        if (origemAtual == destino) {
            throw new MovimentoInvalidoException("Você já está nesta posição!");
        }

        // 2. Verifica se o destino é vizinho (Regra de adjacência [cite: 42])
        boolean isVizinho = vizinhos.get(origemAtual).contains(destino);
        boolean usouSuperPulo = false;

        if (!isVizinho) {
            // Regra do Super Pulo do Cabrito [cite: 53]
            if (turnoDoCabrito && superPuloDisponivel) {
                // Se não é vizinho, mas é cabrito e tem super pulo, permite (desde que não pule em cima do carcará)
                usouSuperPulo = true;
            } else {
                throw new MovimentoInvalidoException("Movimento inválido! Só pode mover para posições vizinhas.");
            }
        }

        // 3. Verifica colisão
        if (turnoDoCabrito && destino == posicaoCarcara) {
            throw new MovimentoInvalidoException("O Cabrito não pode pular para onde o Carcará está!");
        }

        // --- Efetiva a Jogada ---
        if (turnoDoCabrito) {
            posicaoCabrito = destino;
            if (usouSuperPulo) {
                superPuloDisponivel = false;
                System.out.println("SUPER PULO UTILIZADO!");
            }
        } else {
            posicaoCarcara = destino;
            // Se Carcará moveu para onde o Cabrito está -> Fim de Jogo (Vitória Carcará) [cite: 56]
            if (posicaoCarcara == posicaoCabrito) {
                jogoFinalizado = true;
                contadorJogadas++;
                return; // Encerra aqui, não troca turno
            }
        }

        contadorJogadas++;
        turnoDoCabrito = !turnoDoCabrito; // Troca o turno [cite: 52]
    }

    // Getters para a Tela usar
    public Posicao getPosicaoCabrito() { return posicaoCabrito; }
    public Posicao getPosicaoCarcara() { return posicaoCarcara; }
    public boolean isTurnoDoCabrito() { return turnoDoCabrito; }
    public boolean isJogoFinalizado() { return jogoFinalizado; }
    public int getContadorJogadas() { return contadorJogadas; }
    public String getMensagemVitoria() {
        if (posicaoCarcara == posicaoCabrito) return "O Carcará capturou o Cabrito!";
        return "Fim de Jogo!";
    }
}