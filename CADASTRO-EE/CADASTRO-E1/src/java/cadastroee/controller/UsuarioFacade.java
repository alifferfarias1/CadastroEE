/*
 * Your comment here
 */
package cadastroee.controller;

import cadastroee.model.User; // Changed Usuario to User for better naming convention
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


@Stateless
public class UserHandler extends AbstractHandler<User> implements UserHandlerLocal { // Changed UsuarioFacade to UserHandler for better naming convention

    @PersistenceContext(unitName = "CadastroEE-ejbPU")
    private EntityManager entityManager; // Changed em to entityManager for better naming convention

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    public UserHandler() {
        super(User.class);
    }
    
}
