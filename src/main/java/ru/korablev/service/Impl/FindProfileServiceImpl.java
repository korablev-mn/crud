package ru.korablev.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.korablev.dao.UserDAO;
import ru.korablev.model.CurrentProfile;
import ru.korablev.model.User;
import ru.korablev.service.FindProfileService;

@Transactional
@Service
public class FindProfileServiceImpl implements UserDetailsService, FindProfileService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = findProfile(s);
        if (user != null) {
            return new CurrentProfile(user);
        } else {
            throw new UsernameNotFoundException("Profile not found by " + s);
        }
    }

    @Override
    public User findProfile(String login) {
        User user = (User) userDAO.findUserByLogin(login);
        return user;
    }
}
