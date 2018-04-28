/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.fthiago.condominio.service;

import br.com.fthiago.condominio.entity.BaseEntity;
import br.com.fthiago.condominio.exception.BusinessException;
import br.com.fthiago.condominio.exception.ErrorMessage;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Classe BaseService
 *
 * Essa classe é responsável por implementar um CRUD genérico para BaseEntities.
 *
 * @author Felipe Di Bernardi S Thiago
 * @param <T> Classe genérica que implemente BaseEntity.
 */
public class BaseService<T extends BaseEntity> {

    @PersistenceContext
    private EntityManager em;

    /**
     * Recupera todos os registros de T.
     *
     * @param classType Classe de T.
     * @return Lista de T.
     */
    public List<T> listAll(final Class<T> classType) {
        Query query = this.createQuery("SELECT object(o) FROM " + classType.getSimpleName() + " AS o");
        return query.getResultList();
    }

    /**
     * Busca um registro específico através do id.
     *
     * @param classType Classe de T.
     * @param id Id do registro a ser recuperado.
     * @return Registro recuperado
     */
    public T find(final Class<T> classType, final Integer id) {
        return em.find(classType, id);
    }

    /**
     * Insere um registro no sistema.
     *
     * @param t Registro a ser inserido.
     * @return Registro inserido com seu id.
     * @throws BusinessException Mensagem a ser enviada caso uma exceção seja lançada.
     */
    public T insert(final T t) throws BusinessException {
        try {
            em.persist(t);
            em.flush();
            return t;
        } catch (Exception e) {
            throw new BusinessException(e.getLocalizedMessage(), new ErrorMessage("Failure to insert record."));
        }

    }

    /**
     * Atualiza um registro no sistema.
     *
     * @param t Registro a ser atualizado.
     * @return Registro atualizado.
     * @throws BusinessException Mensagem a ser enviada caso uma exceção seja lançada.
     */
    public T update(final T t) throws BusinessException {
        try {
            em.merge(t);
            em.flush();
            return t;
        } catch (Exception e) {
            throw new BusinessException(e.getLocalizedMessage(), new ErrorMessage("Failure to update record."));
        }

    }

    /**
     * Remove um registro do sistema através do id.
     *
     * @param classType Classe de T.
     * @param id Id do registro.
     * @throws BusinessException Mensagem a ser enviada caso uma exceção seja lançada.
     */
    public void remove(Class<T> classType, Integer id) throws BusinessException {
        T bean = this.find(classType, id);
        if (bean != null) {
            try {
                em.remove(bean);
                em.flush();
            } catch (Exception e) {
                throw new BusinessException(e.getLocalizedMessage(), new ErrorMessage("Failure to remove record."));
            }
        } else {
            throw new BusinessException(null, new ErrorMessage("Failure to remove record. Record not found."));
        }
    }

    /**
     * Cria uma Query SQL a partir de uma String.
     *
     * @param query String representando a Query SQL.
     * @return Objeto Query.
     */
    public Query createQuery(final String query) {
        return em.createQuery(query);
    }

    /**
     * Cria uma Query SQL a partir de uma NamedQuery.
     *
     * @param query Named Query.
     * @return Objeto Query.
     */
    public Query createNamedQuery(final String query) {
        return em.createNamedQuery(query);
    }
}
