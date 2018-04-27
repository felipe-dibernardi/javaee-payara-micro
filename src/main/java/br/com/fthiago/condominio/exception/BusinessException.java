/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fthiago.condominio.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe BusinessException
 *
 * Essa classe é responsável por representar uma Exceção de regra de negócio no sistema.
 *
 * @author Felipe Di Bernardi S Thiago
 */
public class BusinessException extends Exception {

    private List<ErrorMessage> errorMessages;

    /**
     * Construtor da classe.
     *
     * @param message Recebe a mensagem de erro.
     * @param cause Causa raiz do problema.
     */
    public BusinessException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Construtor da classe.
     *
     * @param message Recebe a mensagem de erro.
     * @param errorMessages Lista com objetos contendo mensagem i18n.
     * @param cause Causa raiz do problema.
     */
    public BusinessException(final String message, final List<ErrorMessage> errorMessages, final Throwable cause) {
        super(message, cause);
        this.errorMessages = errorMessages;
    }

    /**
     * Construtor da classe.
     *
     * @param message Recebe a mensagem de erro.
     * @param errorMessage Objeto contendo mensagem i18n.
     * @param cause Causa raiz do problema.
     */
    public BusinessException(final String message, final ErrorMessage errorMessage, final Throwable cause) {
        super(message, cause);
        addErrorMessage(errorMessage);
    }

    /**
     * Construtor da classe.
     *
     * @param message Recebe a mensagem de erro.
     * @param errorMessages Lista com objetos contendo mensagem i18n.
     */
    public BusinessException(final String message, final List<ErrorMessage> errorMessages) {
        super(message);
        this.errorMessages = errorMessages;
    }

    /**
     * Construtor da classe.
     *
     * @param message Recebe a mensagem de erro.
     * @param errorMessage Objeto contendo mensagem i18n.
     */
    public BusinessException(final String message, final ErrorMessage errorMessage) {
        super(message);
        addErrorMessage(errorMessage);
    }

    /**
     * Adiciona uma nova mensagem de erro i18n.
     *
     * @param errorMessage Contém a mensagem internacionalizada.
     */
    public final void addErrorMessage(final ErrorMessage errorMessage) {
        if (errorMessages == null) {
            errorMessages = new ArrayList<>();
        }
        errorMessages.add(errorMessage);
    }

    /**
     * Recupera as mensagens i18n.
     *
     * @return Lista com a mensagens de negócio.
     */
    public final List<ErrorMessage> getErrorMessages() {
        return errorMessages;
    }

}
