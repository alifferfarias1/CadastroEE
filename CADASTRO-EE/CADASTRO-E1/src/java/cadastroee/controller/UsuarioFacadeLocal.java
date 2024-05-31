package cadastroee.controller;

import cadastroee.model.User; 
import java.util.List;
import jakarta.ejb.Local;

@Local
public interface UserHandlerLocal { 

    void create(User user); 

    void edit(User user); 

    void remove(User user);

    User find(Object id); 

    List<User> findAll(); 

    List<User> findRange(int[] range); 

    int count();
    
}
