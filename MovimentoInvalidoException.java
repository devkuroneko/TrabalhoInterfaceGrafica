package com.mycompany.usandoguibuilder;

/**
 * Exceção personalizada para validar movimentos.
 * Requisito: "o jogo deve usar tratamento de exceções para implementar o sistema de jogadas inválidas"
 */
public class MovimentoInvalidoException extends Exception {
    public MovimentoInvalidoException(String message) {
        super(message);
    }
}