package ohtu.services;

import ohtu.domain.User;
import java.util.ArrayList;
import java.util.List;
import ohtu.data_access.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationService {

    private UserDao userDao;

    @Autowired
    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public boolean createUser(String username, String password) {
        if (userDao.findByName(username) != null) {
            return false;
        }

        if (invalid(username, password)) {
            return false;
        }

        userDao.add(new User(username, password));

        return true;
    }

    private boolean invalid(String username, String password) {
        return !isUsernameAcceptable(username) || !isPasswordAcceptable(password);
    }
    
    private boolean isUsernameAcceptable(String username) {
        boolean acceptable = true;
        
        if (!username.matches("[a-z]+")) {
            acceptable = false;
        }
        
        if (username.length() < 3) {
            acceptable = false;
        }
        
//        if (userDao.findByName(username) != null) {
//            acceptable = false;
//        }
        
        return acceptable;
    }
    
    private boolean isPasswordAcceptable(String password) {
        boolean acceptable = false;
        
        //Password contains at least one character that is something else than alphabet
        for(int i = 0 ; i < password.length() ; i++){
            if(!Character.isAlphabetic( password.charAt(i) )){
                acceptable = true;
            }
        }
        
        if (password.length() < 8) {
            acceptable = false;
        }
        
        return acceptable;
    }
}
