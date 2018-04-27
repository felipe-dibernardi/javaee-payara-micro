/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.fthiago.condominio.entity;

import java.io.Serializable;

/**
 * Interface BaseEntity
 *
 * Essa Interface é responsável por representar o comportamento de uma entidade no sistema.
 *
 * @author Felipe Di Bernardi S Thiago
 */
public interface BaseEntity extends Serializable {

    Integer getId();

    void setId(final Integer id);

}
