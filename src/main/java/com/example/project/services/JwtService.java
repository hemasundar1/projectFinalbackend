package com.example.project.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.project.Exception.CustomException;
import com.example.project.Util.JwtUtil;
import com.example.project.models.UserModel;
import com.example.project.modelsDto.JwtRequest;
import com.example.project.modelsDto.JwtResponse;
import com.example.project.repository.UserRepository;



@Service
public class JwtService implements UserDetailsService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
        String email = jwtRequest.getEmail();
        String password = jwtRequest.getPassword();
        authenticate(email, password);
        UserDetails userDetails = loadUserByUsername(email);
        System.out.print(userDetails);
        String newGeneratedToken = jwtUtil.generateToken(userDetails);
   
        UserModel user = userRepo.findByEmail(email);
        return new JwtResponse(user, newGeneratedToken);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserModel user = userRepo.findByEmail(email);

        if(user != null ) {
        	System.out.println("User is valid");
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
    	System.out.println(authorities);
    	return authorities;
    }

    private void authenticate(String email, String password) throws Exception {
    	
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new CustomException("USER_DISABLED");
        } 
//        catch (BadCredentialsException e) {
//        	System.out.println("2");
//            throw new CustomException("INVALID_CREDENTIALS");
//            
//        }
        catch(Exception e) {
        	System.out.println("3");
        	System.out.println(e);
        }
    }
}
