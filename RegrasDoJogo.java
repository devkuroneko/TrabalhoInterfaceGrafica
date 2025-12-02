package com.mycompany.usandoguibuilder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RegrasDoJogo{
    public enum Posicao{
        SUPERIOR_ESQUERDA,
        SUPERIOR_DIREITA,
        SUPERIOR_CENTRAL,
        CENTRAL,
        INFERIOR_ESQUERDA,
        INFERIOR_DIREITA;
    }


    //Map que define o grafo
    private final Map<Posicao, List<Posicao>> mapaTabuleiro;

    //Estado do jogo
    private Posicao posicaoCabrito;
    private Posicao posicaoCarcara;
    private boolean turnoDoCabrito;
    private boolean superPuloDisponivel;
    private boolean jogoFinalizado;
    private int TotalJogadas;

    public RegrasDoJogo(){
        this.mapaTabuleiro = new HashMap<>();
        inicializarTabuleiro();
        reiniciarJogo();
    }

     //Começo da correçao gerado por IA
        private void inicializarTabuleiro() {
            mapaTabuleiro.put(Posicao.CENTRAL, Arrays.asList(
                    Posicao.SUPERIOR_CENTRAL, Posicao.SUPERIOR_ESQUERDA, Posicao.SUPERIOR_DIREITA,
                    Posicao.INFERIOR_ESQUERDA, Posicao.INFERIOR_DIREITA
            ));

            mapaTabuleiro.put(Posicao.SUPERIOR_CENTRAL, Arrays.asList(Posicao.CENTRAL, Posicao.SUPERIOR_ESQUERDA, Posicao.SUPERIOR_DIREITA));
            mapaTabuleiro.put(Posicao.SUPERIOR_ESQUERDA, Arrays.asList(Posicao.CENTRAL, Posicao.SUPERIOR_CENTRAL, Posicao.INFERIOR_ESQUERDA));
            mapaTabuleiro.put(Posicao.SUPERIOR_DIREITA, Arrays.asList(Posicao.CENTRAL, Posicao.SUPERIOR_CENTRAL, Posicao.INFERIOR_DIREITA));
            mapaTabuleiro.put(Posicao.INFERIOR_ESQUERDA, Arrays.asList(Posicao.CENTRAL, Posicao.SUPERIOR_ESQUERDA, Posicao.INFERIOR_DIREITA));
            mapaTabuleiro.put(Posicao.INFERIOR_DIREITA, Arrays.asList(Posicao.CENTRAL, Posicao.SUPERIOR_DIREITA, Posicao.INFERIOR_ESQUERDA));
        }
      //Fim gerado por IA


    public void reiniciarJogo(){
        this.posicaoCabrito = Posicao.INFERIOR_ESQUERDA;
        this.posicaoCarcara = Posicao.SUPERIOR_CENTRAL;

        this.turnoDoCabrito = true;
        this.superPuloDisponivel= true;
        this.jogoFinalizado = false;
        this.TotalJogadas = 0;
    }
    public void processarJogada (Posicao destino) throws MovimentoInvalidoException{
        //validação de estado
        if (jogoFinalizado){
            throw new MovimentoInvalidoException("O jogo Terminou. Reinicie para jogar novamente");
        }

//indetifica quem está jogando no turno
        Posicao origem;
        if(turnoDoCabrito) {
            origem = posicaoCabrito;
        } else {
            origem = posicaoCarcara;
        }

        //Regra 1 - Não pode ficar parado
        if(origem == destino){
            throw new MovimentoInvalidoException("Tente mover para uma posição diferente da atual");
        }

        //Regra 2 - verifica colisão
        if (turnoDoCabrito && destino == posicaoCarcara){
            throw new MovimentoInvalidoException("Movimento Suicida! o cabrito não pode pular no carcara");
        }

        //Regra 3 - Validar conexões e super Pulo
        boolean ehVizinho = mapaTabuleiro.get(origem).contains(destino);
        boolean gastouSuperPulo = false;

        if(!ehVizinho){
            if(turnoDoCabrito && superPuloDisponivel){
                gastouSuperPulo = true;
            }else {
                throw new MovimentoInvalidoException("Caminho bloqueado: As posições não estão conectadas");
            }
        }

        //aplicar Movimento
        if (turnoDoCabrito){
            moverCabrito(destino, gastouSuperPulo);
        }else{
            moverCarcara(destino);
        }
        //atualizar contadores e troca a vez
        if(!jogoFinalizado){
            TotalJogadas++;
            turnoDoCabrito = !turnoDoCabrito;
        }

    }

    private void moverCabrito(Posicao destino, boolean usarPoder){
        this.posicaoCabrito = destino;
        if(usarPoder){
            this.superPuloDisponivel = false;
            System.out.println("O cabrito utlizou o super pulo");
        }
    }
    private void moverCarcara(Posicao destino) {
        this.posicaoCarcara = destino;
        if (this.posicaoCarcara == this.posicaoCabrito) {
            this.jogoFinalizado = true;
            this.TotalJogadas++;
        }
    }

// Getters com TelaJogo
    public Posicao getPosicaoCabrito(){
        return posicaoCabrito;
    }

    public Posicao getPosicaoCarcara(){
        return posicaoCarcara;
    }

    public boolean isturnoDoCabrito(){
        return turnoDoCabrito;
    }

    public boolean isjogoFinalizado(){
        return jogoFinalizado;
    }

    public int getTotalJogadas(){
        return TotalJogadas;
    }

    public String getMensagemVitoria(){
        if(posicaoCarcara == posicaoCabrito){
            return "Fim de jogo! O Carcará jantou o Cabrito em" + TotalJogadas + "  jogadas.";
        }
        return "Jogo Encerrado";
    }

}


