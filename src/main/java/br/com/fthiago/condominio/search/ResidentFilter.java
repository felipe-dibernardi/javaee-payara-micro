/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.fthiago.condominio.search;

/**
 * Classe ResidentFilter
 *
 * Essa classe é responsável por representar um filtro padrão para busca relacionado a entidade Morador.
 *
 * @author Felipe Di Bernardi S Thiago
 */
public class ResidentFilter {

    private String name;
    
    private String email;
    
    private Integer apartment;
    
    private String block;
    
    private String document;
    
    private String phone;

    /**
     * Construtor com parâmetros.
     * @param name Nome.
     * @param email Email.
     * @param apartment Apartamento do morador.
     * @param block Bloco do apartamento.
     * @param document Documento do morador.
     * @param phone Telefone.
     */
    public ResidentFilter(final String name, final String email, final Integer apartment, final String block, 
            final String document, final String phone) {
        this.name = name;
        this.email = email;
        this.apartment = apartment;
        this.block = block;
        this.document = document;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public Integer getApartment() {
        return apartment;
    }

    public void setApartment(Integer apartment) {
        this.apartment = apartment;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(final String block) {
        this.block = block;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(final String document) {
        this.document = document;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    
}
