package com.xwaves.repository;

import com.xwaves.domain.User;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    User findById(long id);
    
    User findByUsername(String username);
    
    User findByEmail(String email);
    
    //boolean insertUser(User user);
    
    List<User> findAll();
}
