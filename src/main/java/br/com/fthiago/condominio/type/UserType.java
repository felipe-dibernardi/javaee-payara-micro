/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.fthiago.condominio.type;

/**
 * Enum UserType
 *
 * Esse Enum é reponsável por representar os tipos de Usuário no sistema.
 *
 * @author Felipe Di Bernardi S Thiago
 */
public enum UserType {

    ADMINISTRATOR("ADMINISTRATOR"),
    BASIC("BASIC");
    
    private final String userType;

    /**
     * Construtor padrão do Enum.
     * @param userType String que representa o valor do Enum.
     */
    UserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return userType;
    }
    
}
