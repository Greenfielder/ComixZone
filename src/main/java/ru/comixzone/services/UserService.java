package ru.comixzone.services;

/**
 * Created by Mickey on 14.06.2019.
 */

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.comixzone.entities.SystemUser;
import ru.comixzone.entities.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    SystemUser findById(Long id);

    User findByUserName(String username);

    boolean save(SystemUser systemUser);

    List<SystemUser> findAll();
}