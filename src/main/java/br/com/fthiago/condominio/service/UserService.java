/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.fthiago.condominio.service;

import br.com.fthiago.condominio.entity.User;
import br.com.fthiago.condominio.exception.BusinessException;
import br.com.fthiago.condominio.util.MD5HashUtil;
import javax.ejb.Stateless;

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
        t.setPassword(MD5HashUtil.encript(t.getPassword()));
        return super.insert(t);
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
    
}
