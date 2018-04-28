/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.fthiago.condominio.entity;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Classe Resident
 *
 * Essa classe é responsável por representar a entidade Morador no sistema.
 *
 * @author Felipe Di Bernardi S Thiago
 */
@Entity
@Table(name = "resident")
public class Resident implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String name;
    
    private String email;
    
    private Integer apartment;
    
    private String block;
    
    private String document;
    
    private String phone;

    public Resident() {
    }

    public Resident(final String name, final String email, final Integer apartment, final String block, 
            final String document, final String phone) {
        this.name = name;
        this.email = email;
        this.apartment = apartment;
        this.block = block;
        this.document = document;
        this.phone = phone;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(final Integer id) {
        this.id = id;
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

    public void setApartment(final Integer apartment) {
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.id);
        hash = 13 * hash + Objects.hashCode(this.name);
        hash = 13 * hash + Objects.hashCode(this.email);
        hash = 13 * hash + Objects.hashCode(this.apartment);
        hash = 13 * hash + Objects.hashCode(this.block);
        hash = 13 * hash + Objects.hashCode(this.document);
        hash = 13 * hash + Objects.hashCode(this.phone);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Resident other = (Resident) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.apartment, other.apartment)) {
            return false;
        }
        if (!Objects.equals(this.block, other.block)) {
            return false;
        }
        if (!Objects.equals(this.document, other.document)) {
            return false;
        }
        if (!Objects.equals(this.phone, other.phone)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}
