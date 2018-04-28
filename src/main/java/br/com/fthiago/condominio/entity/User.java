/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.fthiago.condominio.entity;

import br.com.fthiago.condominio.type.UserType;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Classe User
 *
 * Essa classe é responsável por representar os Usuários que utilizarão o sistema.
 *
 * @author Felipe Di Bernardi S Thiago
 */
@Entity
@Table(name = "user")
public class User implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String username;
    
    private String password;

    @Enumerated(EnumType.STRING)
    private UserType type;

    public User() {
    }

    public User(String username, String password, UserType type) {
        this.username = username;
        this.password = password;
        this.type = type;
    }
    
    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(final Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public UserType getType() {
        return type;
    }

    public void setType(final UserType type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.username);
        hash = 79 * hash + Objects.hashCode(this.password);
        hash = 79 * hash + Objects.hashCode(this.type);
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
        final User other = (User) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        return true;
    }

}
