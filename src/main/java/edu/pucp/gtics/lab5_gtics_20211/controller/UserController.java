package edu.pucp.gtics.lab5_gtics_20211.controller;

import edu.pucp.gtics.lab5_gtics_20211.entity.User;
import edu.pucp.gtics.lab5_gtics_20211.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;
@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/loginForm")
    public String signIn(){
       return "user/sigIn";
    }

    @GetMapping("/signInRedirect")
    public String signInRedirect(Authentication auth, HttpSession session) {
        return "juegos/lista";
    }

}
