/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.fthiago.condominio.ws;

import br.com.fthiago.condominio.entity.User;
import br.com.fthiago.condominio.exception.BusinessException;
import br.com.fthiago.condominio.service.UserService;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

/**
 * Classe UserWS
 *
 * Essa classe é responsável por implementar o EndPoint para a entidade Usuário.
 *
 * @author Felipe Di Bernardi S Thiago
 */
@Stateless
@Path("/users/")
public class UserWS {

    @EJB
    private UserService userService;
    
    /**
     * Lista todos os Usuários do sistema.
     * @return Resposta a Requisição
     */
    @GET
    @Produces("application/json")
    public Response listAll() {
        return Response.ok(new GenericEntity<List<User>>(this.userService.listAll(User.class)) {}).build();
    }
    
    /**
     * Insere um Usuário no sistema.
     * @param user Usuário a ser inserido.
     * @return Resposta a Requisição.
     */
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response insert(final User user) {
        try {
            this.userService.insert(user);
            return Response.status(Response.Status.CREATED).entity(user).build();
        } catch (BusinessException ex) {
            return Response.serverError().entity(ex.getErrorMessages().get(0).getKey()).build();
        }
    }
    
    /**
     * Atualiza um Usuário no sistema.
     * @param user Usuário a ser atualizado.
     * @return Resposta a Requisição.
     */
    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response update(final User user) {
        try {
            if (user.getId() != null) {
                this.userService.checkPasswordUpdate(user);
                this.userService.update(user);
                return Response.ok().entity(user).build();
            } else {
                this.userService.insert(user);
                return Response.status(Response.Status.CREATED).entity(user).build();
            }
        } catch (BusinessException ex) {
            return Response.serverError().entity(ex.getErrorMessages().get(0).getKey()).build();
        }
    }
    
    /**
     * Remove um Usuário do sistema.
     * @param id Identificador único do Usuário.
     * @return Resposta a Requisição.
     */
    @DELETE
    @Path("/remove/{id}")
    @Consumes("application/json")
    public Response remove(@PathParam("id") final Integer id) {
        try {
            this.userService.remove(User.class, id);
            return Response.noContent().build();
        } catch (BusinessException ex) {
            return Response.serverError().entity(ex.getErrorMessages().get(0).getKey()).build();
        }
    }
}
