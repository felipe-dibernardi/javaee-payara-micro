/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fthiago.condominio.exception;

/**
 * Classe ErrorMessage
 *
 * Essa classe é responsável por armazenas as mensagens customizadas de erro.
 *
 * @author Felipe Di Bernardi S Thiago
 */
public class ErrorMessage {

    private String key;
    private String[] args;

    /**
     * Construtor padrao.
     */
    public ErrorMessage() {
    }

    /**
     * Construtor com parametros.
     *
     * @param key Chave da mensagem internacionalizada.
     * @param args Parametros da chave (se necessario).
     */
    public ErrorMessage(final String key, final String[] args) {
        this.key = key;
        this.args = args;
    }

    /**
     * Construtor com parametro.
     *
     * @param key Chave da mensagem internacionalizada.
     */
    public ErrorMessage(final String key) {
        this.key = key;
    }

    public final String getKey() {
        return key;
    }

    public final String[] getArgs() {
        return args;
    }

}
