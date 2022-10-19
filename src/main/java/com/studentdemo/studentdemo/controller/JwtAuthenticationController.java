package com.studentdemo.studentdemo.controller;

import com.studentdemo.studentdemo.config.JwtTokenUtil;
import com.studentdemo.studentdemo.dao.UserRepository;
import com.studentdemo.studentdemo.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@RestController
//@CrossOrigin(origins = { "${origins_url}" })
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    @Qualifier("theJwtTokenUtil")
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService jwtInMemoryUserDetailsService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView viewLoginPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return new ModelAndView("login");
        }
        return null;
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST,
        consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<Map<String, Object>> createAuthenticationToken(@RequestParam String username, @RequestParam String password)
        throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> mapResponse = new HashMap<String, Object>();
        Map<String, Object> mapResponseUserData = new HashMap<String, Object>();
        Optional<UserEntity> userEntity1 = userRepository.findOneByEmailIgnoreCase(username);
        try {

            if (userEntity1.isPresent() && userEntity1.get().getStatus().equalsIgnoreCase("INACTIVE")) {
                map.put("message", "Account has been locked");
                map.put("status", "400");
                return ResponseEntity.ok(map);
            }
            authenticate(username, password);

            final UserDetails userDetails = jwtInMemoryUserDetailsService
                    .loadUserByUsername(username);
            final String token = jwtTokenUtil.generateToken(userDetails);
            mapResponse.put("token", token);

            Optional<UserEntity> userEntity = userRepository.findOneByEmailIgnoreCase(userDetails.getUsername());

            if (userEntity.isPresent()) {
                UserEntity userData = userEntity.get();

                mapResponseUserData.put("id", userData.getUserName().toString());
                mapResponseUserData.put("name", userData.getPassword());
                mapResponseUserData.put("token", token);
                mapResponse.put("userData", mapResponseUserData);

            }
            return ResponseEntity.ok(mapResponse);
        } catch (Exception e) {

        }

        if (userEntity1.isPresent()) {
            UserEntity userEntity = userEntity1.get();
            if (userEntity.getAttempts() < 3) {
                userEntity.setAttempts(userEntity.getAttempts() + 1);
            } else if (userEntity.getAttempts() == 3) {
                userEntity.setStatus("INACTIVE");
            }
            userEntity.setLastLoginDate(new Date());
            userRepository.save(userEntity);
            map.put("status", "400");
            map.put("message", "Login Invalid  Credentials");

            return ResponseEntity.ok(map);
        }
        return ResponseEntity.ok(map);
    }


    private void authenticate(String name, String password) throws Exception {
        Objects.requireNonNull(name);
        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(name, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("login invalid credentials", e);
        }
    }


    @RequestMapping(value = "/loginCheck", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView checkLogin(@RequestParam String username, @RequestParam String password, Model model) {

        Optional<UserEntity> userEntity1 = userRepository.findOneByEmailIgnoreCase(username);

        if (userEntity1.isPresent() && userEntity1.get().getStatus().equalsIgnoreCase("INACTIVE")) {
            model.addAttribute("lockError","Inactive");
            return new ModelAndView("login");
        }

        if (userEntity1.isPresent()) {
            UserEntity userEntity = userEntity1.get();
            if (userEntity.getAttempts() < 3) {
                userEntity.setAttempts(userEntity.getAttempts() + 1);
            } else if (userEntity.getAttempts() == 3) {
                userEntity.setStatus("INACTIVE");
            }
            userEntity.setLastLoginDate(new Date());
            userRepository.save(userEntity);

            model.addAttribute("logError","Invalid");
            return new ModelAndView("login");
        }

        return new ModelAndView("welcome");

    }

}
