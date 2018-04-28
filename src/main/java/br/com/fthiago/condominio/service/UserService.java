/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.fthiago.condominio.service;

import br.com.fthiago.condominio.entity.User;
import br.com.fthiago.condominio.exception.BusinessException;
import br.com.fthiago.condominio.exception.ErrorMessage;
import br.com.fthiago.condominio.util.MD5HashUtil;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 * Classe UserService
 *
 * Essa classe é responsável por implementar as regras de negócio para a entidade Usuário
 *
 * @author Felipe Di Bernardi S Thiago
 */
@Stateless
public class UserService extends BaseService<User> {

    @Override
    public User insert(User t) throws BusinessException {
        if (this.findByUsername(t.getUsername()) != null) {
            throw new BusinessException(null, new ErrorMessage("Username already exists"));
        }
        t.setPassword(MD5HashUtil.encript(t.getPassword()));
        return super.insert(t);
    }

    @Override
    public User update(User t) throws BusinessException {
        User user = this.findByUsername(t.getUsername());
        if (user != null && !user.getId().equals(t.getId())) {
            throw new BusinessException(null, new ErrorMessage("Username already exists"));
        }
        return super.update(t);
    }
    
    /**
     * Checa se houve atualização na senha do Usuário. Caso haja, encripta a senha.
     * @param user Usuário a ter sua senha checada.
     * @return Usuário.
     */
    public User checkPasswordUpdate(final User user) {
        User userDB = this.find(User.class, user.getId());
        if (!userDB.getPassword().equals(MD5HashUtil.encript(user.getPassword()))) {
            user.setPassword(MD5HashUtil.encript(user.getPassword()));
        }
        return user;
    }
    
    /**
     * Busca um Usuário pelo seu Username.
     * @param username Username do Usuário que se quer buscar.
     * @return Usuário desejado ou nulo se não houver Usuário com esse username.
     */
    public User findByUsername(final String username) {
        Query query = super.createNamedQuery("User.findByUsername");
        query.setParameter("username", username);
        if (query.getResultList().isEmpty()) {
            return null;
        } else {
            return (User) query.getSingleResult();
        }
    }
    
}
