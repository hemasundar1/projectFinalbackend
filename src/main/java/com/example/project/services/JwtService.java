package com.example.project.services;

import org.apache.log4j.*;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.project.exception.CustomException;
import com.example.project.models.UserModel;
import com.example.project.modelsdto.JwtRequest;
import com.example.project.modelsdto.JwtResponse;
import com.example.project.repository.UserRepository;
import com.example.project.util.JwtUtil;



@Service
public class JwtService implements UserDetailsService {
	private static org.apache.log4j.Logger log=Logger.getLogger(JwtService.class);

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    public JwtResponse createJwtToken(JwtRequest jwtRequest){
        String email = jwtRequest.getEmail();
        String password = jwtRequest.getPassword();
        authenticate(email, password);
        UserDetails userDetails = loadUserByUsername(email);
       log.info(userDetails);
        String newGeneratedToken = jwtUtil.generateToken(userDetails);
   
        UserModel user = userRepo.findByEmail(email);
        return new JwtResponse(user, newGeneratedToken);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserModel user = userRepo.findByEmail(email);

        if(user != null ) {
        	log.info("User is valid");
			return new org.springframework.security.core.userdetails.User(
						user.getEmail(),
						user.getPassword(),
						getAuthorities(user)	
					);
					
		}else {
			throw new UsernameNotFoundException("Username is not valid");
		}
    }
    
    private Set<SimpleGrantedAuthority> getAuthorities(UserModel user) {
    	Set<SimpleGrantedAuthority> authorities = new HashSet<>();
    	authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getUserRole()));
    	log.info(authorities);
    	return authorities;
    }

    private void authenticate(String email, String password) {
    	
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new CustomException("USER_DISABLED");
        } 
        catch(Exception e) {
        	log.info(e);
        }
    }
}
