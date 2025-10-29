package br.edu.utfpr.sistema_monitoramento.exception;


/**
 * Classe para representar uma resposta de erro.
 * Ela contém uma mensagem descritiva do erro ocorrido.
 */
public record ErrorResponse(String message) {
}