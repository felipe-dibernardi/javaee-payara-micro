/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.fthiago.condominio.service;

import br.com.fthiago.condominio.entity.Resident;
import br.com.fthiago.condominio.filter.ResidentFilter;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 * Classe ResidentService
 *
 * Essa classe é responsável por implementar as regras de negócio para a entidade Morador.
 *
 * @author Felipe Di Bernardi S Thiago
 */
@Stateless
public class ResidentService extends BaseService<Resident> {

    /**
     * Busca Residentes de acordo com os parâmetros básicos do filtro de Residentes.
     * @param filter Filtro para Residentes.
     * @return Lista de Residentes.
     */
    public List<Resident> listByParams(final ResidentFilter filter) {
        
        StringBuilder strQuery = new StringBuilder();
        strQuery.append("SELECT r FROM Resident r ");
        strQuery.append("WHERE r.id IS NOT NULL ");
        
        if (filter.getName() != null && !filter.getName().equals("")) {
            strQuery.append("AND r.name LIKE '%").append(filter.getName()).append("%' ");
        }
        
        if (filter.getEmail() != null && !filter.getEmail().equals("")) {
            strQuery.append("AND r.email LIKE '%").append(filter.getEmail()).append("%' ");
        }
        
        if (filter.getBlock() != null && !filter.getBlock().equals("")) {
            strQuery.append("AND r.block LIKE '%").append(filter.getBlock()).append("%' ");
        }
        
        if (filter.getApartment()!= null) {
            strQuery.append("AND r.apartment = ").append(filter.getApartment());
        }
        
        if (filter.getDocument() != null && !filter.getDocument().equals("")) {
            strQuery.append("AND r.document LIKE '%").append(filter.getDocument()).append("%' ");
        }
        
        if (filter.getPhone() != null && !filter.getPhone().equals("")) {
            strQuery.append("AND r.phone LIKE '%").append(filter.getPhone()).append("%' ");
        }
        
        Query query = super.createQuery(strQuery.toString());
        
        return query.getResultList();
    }
    
}
