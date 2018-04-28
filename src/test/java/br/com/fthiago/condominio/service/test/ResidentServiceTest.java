/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.fthiago.condominio.service.test;

import br.com.fthiago.condominio.entity.Resident;
import br.com.fthiago.condominio.exception.BusinessException;
import br.com.fthiago.condominio.filter.ResidentFilter;
import br.com.fthiago.condominio.service.ResidentService;
import javax.ejb.EJB;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Classe ResidentServiceTest
 *
 * Essa classe é responsável por implementar os testes para o CRUD e as regras de negócio da entidade Moradores.
 *
 * @author Felipe Di Bernardi S Thiago
 */
@RunWith(Arquillian.class)
public class ResidentServiceTest {

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap
                .create(WebArchive.class, "condominio-service-test.war")
                .addPackage(ResidentService.class.getPackage())
                .addPackage(Resident.class.getPackage())
                .addAsWebInfResource("test-web.xml", "web.xml")
                .addAsWebInfResource("glassfish-resources.xml", "glassfish-resources.xml")
                .addAsWebInfResource("test-beans.xml", "beans.xml")
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml");
    }
    
    private static Integer residentId;
    
    @EJB
    private ResidentService residentService;
    
    @Test
    @InSequence(1)
    public void shouldInsertResident() throws BusinessException {
        Resident resident = new Resident("Felipe Di Bernardi", "felipe.dibernardi@gmail.com", 107, "B", "000.000.000-00",
                "48996144008");
        
        residentService.insert(resident);
        
        residentId = resident.getId();
        
        resident = residentService.find(Resident.class, residentId);
        
        Assert.assertEquals("Felipe Di Bernardi", resident.getName());
        Assert.assertEquals("felipe.dibernardi@gmail.com", resident.getEmail());
        Assert.assertEquals(Integer.valueOf(107), resident.getApartment());
        Assert.assertEquals("B", resident.getBlock());
        Assert.assertEquals("000.000.000-00", resident.getDocument());
        Assert.assertEquals("48996144008", resident.getPhone());
        
    }
    
    @Test
    @InSequence(2)
    public void shouldUpdateResident() throws BusinessException {
        Resident resident = residentService.find(Resident.class, residentId);
        
        resident.setName("Novo nome");
        
        residentService.update(resident);
        
        resident = residentService.find(Resident.class, residentId);
        
        Assert.assertEquals("Novo nome", resident.getName());
    }
    
    @Test
    @InSequence(3)
    public void shouldListAllResidents() {
        Assert.assertFalse(residentService.listAll(Resident.class).isEmpty());
    }
    
    @Test
    @InSequence(4)
    public void shouldListNoResidents() {
        ResidentFilter filter = new ResidentFilter("", "", 108, "", "", "");
        Assert.assertTrue(residentService.listByParams(filter).isEmpty());
    }
    
    @Test
    @InSequence(5)
    public void shouldListResidentsByParams() {
        ResidentFilter filter = new ResidentFilter("Novo", "felipe", 107, "B", "000.000", "99614");
        Assert.assertFalse(residentService.listByParams(filter).isEmpty());
    }
    
    @Test
    @InSequence(6)
    public void shouldRemoveResident() throws BusinessException {
        residentService.remove(Resident.class, residentId);
        Assert.assertNull(residentService.find(Resident.class, residentId));
    }
}
