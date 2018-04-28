/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.fthiago.condominio.service.test;

import br.com.fthiago.condominio.entity.User;
import br.com.fthiago.condominio.exception.BusinessException;
import br.com.fthiago.condominio.service.UserService;
import br.com.fthiago.condominio.type.UserType;
import br.com.fthiago.condominio.util.MD5HashUtil;
import javax.ejb.EJB;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Classe UserServiceTest

 Essa classe é responsável por implementar os testes para o CRUD de Usuário.
 *
 * @author Felipe Di Bernardi S Thiago
 */
@RunWith(Arquillian.class)
public class UserServiceTest {

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap
                .create(WebArchive.class, "condominio-service-test.war")
                .addPackage(UserService.class.getPackage())
                .addPackage(User.class.getPackage())
                .addAsWebInfResource("test-web.xml", "web.xml")
                .addAsWebInfResource("glassfish-resources.xml", "glassfish-resources.xml")
                .addAsWebInfResource("test-beans.xml", "beans.xml")
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml");
    }
    
    private static Integer userId;
    
    private static Integer anotherUserId;    
    
    @EJB
    private UserService userService;
    
    @Test
    @InSequence(1)
    public void shouldInsertUser() throws BusinessException {
        User user = new User("test", "passwd", UserType.ADMINISTRATOR);
        
        User anotherUser = new User("test2", "other-passwd", UserType.ADMINISTRATOR);
        userService.insert(anotherUser);
        anotherUserId = anotherUser.getId();
        
        userService.insert(user);
        
        userId = user.getId();
        
        user = userService.find(User.class, userId);
        anotherUser = userService.find(User.class, anotherUserId);
        
        Assert.assertEquals(userId, user.getId());
        Assert.assertEquals("test", user.getUsername());
        Assert.assertEquals(MD5HashUtil.encript("passwd"), user.getPassword());
        Assert.assertEquals(anotherUserId, anotherUser.getId());
        Assert.assertEquals("test2", anotherUser.getUsername());
        Assert.assertEquals(MD5HashUtil.encript("other-passwd"), anotherUser.getPassword());
    }
    
    @Test(expected = BusinessException.class)
    @InSequence(2)
    public void shouldNotInsertUserWithSameUsername() throws BusinessException {
        User user = new User("test", "passwd-2", UserType.BASIC);
        
        userService.insert(user);
    }
    
    @Test
    @InSequence(3)
    public void shouldFindUserByUsername() {
        Assert.assertNotNull(userService.findByUsername("test"));
    }
    
    @Test
    @InSequence(4)
    public void shouldUpdateUser() throws BusinessException {
        User user = userService.find(User.class, userId);
        
        user.setUsername("new-username");
        
        userService.update(user);
        
        user = userService.find(User.class, userId);
        
        Assert.assertEquals("new-username", user.getUsername());
        
    }
    
    @Test(expected = BusinessException.class)
    @InSequence(5)
    public void shouldNotUpdateUserWithSameUsernameAsOther() throws BusinessException {
        User user = userService.find(User.class, userId);
        
        user.setUsername("test2");
        
        userService.update(user);
    }
    
    @Test
    @InSequence(6)
    public void shouldChangePassword() {
        User user = userService.find(User.class, userId);
        
        user.setPassword("new-passwd");
        
        userService.checkPasswordUpdate(user);
        
        Assert.assertEquals(MD5HashUtil.encript("new-passwd"), user.getPassword());
    }
    
    @Test
    @InSequence(7)
    public void shouldReturnAllUsers() throws BusinessException {
        Assert.assertFalse(userService.listAll(User.class).isEmpty());
    }
    
    @Test
    @InSequence(8)
    public void shouldRemoveUser() throws BusinessException {
        userService.remove(User.class, userId);
        userService.remove(User.class, anotherUserId);
        Assert.assertNull(userService.find(User.class, userId));
        Assert.assertNull(userService.find(User.class, anotherUserId));
    }
    
}
