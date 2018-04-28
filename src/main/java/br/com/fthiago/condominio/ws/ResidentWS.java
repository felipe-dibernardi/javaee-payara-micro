/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fthiago.condominio.ws;

import br.com.fthiago.condominio.entity.Resident;
import br.com.fthiago.condominio.exception.BusinessException;
import br.com.fthiago.condominio.filter.ResidentFilter;
import br.com.fthiago.condominio.service.ResidentService;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

/**
 * Classe ResidentWS
 *
 * Essa classe é responsável por implementar o EndPoint para a entidade Resident.
 *
 * @author Felipe Di Bernardi S Thiago
 */
@Stateless
@Path("/residents/")
public class ResidentWS {

    @EJB
    private ResidentService residentService;

    /**
     * Busca todos os Moradores.
     *
     * @return Resposta a Requisição.
     */
    @GET
    @Produces("application/json")
    public Response listAll() {
        return Response.ok(new GenericEntity<List<Resident>>(this.residentService.listAll(Resident.class)) {}).build();
    }

    /**
     * Busca um Morador específico pelo identicador.
     * @param id Id do Morador.
     * @return Resposta a Requisição.
     */
    @GET
    @Path("{id}")
    @Produces("application/json")
    public Response find(@PathParam("id") final Integer id) {
        return Response.ok().entity(this.residentService.find(Resident.class, id)).build();
    }
    
    /**
     * Busca os Moradores de acordo com parâmetros.
     *
     * @param name Nome do morador.
     * @param email Email do morador.
     * @param block Bloco do apartemnto do morador.
     * @param apartment Apartamento do morador.
     * @param document Documento do morador.
     * @param phone Telefone do morador.
     * @return Resposta a Requisição.
     */
    @GET
    @Path("/filter")
    @Produces("application/json")
    public Response listByParams(@QueryParam("name") final String name, @QueryParam("email") final String email,
            @QueryParam("block") final String block, @QueryParam("apartment") final Integer apartment,
            @QueryParam("document") final String document, @QueryParam("phone") final String phone) {
        ResidentFilter filter = new ResidentFilter(name, email, apartment, block, document, phone);
        return Response.ok(new GenericEntity<List<Resident>>(this.residentService.listByParams(filter)) {}).build();
    }

    /**
     * Insere um Morador no sistema.
     *
     * @param resident Morador a ser inserido.
     * @return Resposta a Requisição.
     */
    @POST
    @Consumes("application/json")
    public Response insert(final Resident resident) {
        try {
            this.residentService.insert(resident);
            return Response.status(Response.Status.CREATED).entity(resident).build();
        } catch (BusinessException ex) {
            return Response.serverError().entity(ex.getErrorMessages().get(0).getKey()).build();

        }
    }

    /**
     * Atualiza um Morador no sistema.
     *
     * @param resident Morador a ser atualizado.
     * @return Resposta a Requisição.
     */
    @PUT
    @Consumes("application/json")
    public Response update(final Resident resident) {
        try {
            if (resident.getId() != null) {
                this.residentService.update(resident);
                return Response.status(Response.Status.OK).entity(resident).build();
            } else {
                this.residentService.insert(resident);
                return Response.status(Response.Status.CREATED).entity(resident).build();
            }

        } catch (BusinessException ex) {
            return Response.serverError().entity(ex.getErrorMessages().get(0).getKey()).build();

        }
    }

    /**
     * Remove um Morador do sistema.
     *
     * @param id Id do Morador
     * @return Resposta a Requisição.
     */
    @DELETE
    @Path("/remove/{id}")
    public Response remove(@PathParam("id") final Integer id) {
        try {
            this.residentService.remove(Resident.class, id);
            return Response.noContent().build();
        } catch (BusinessException ex) {
            return Response.serverError().entity(ex.getErrorMessages().get(0).getKey()).build();
        }
    }

}
