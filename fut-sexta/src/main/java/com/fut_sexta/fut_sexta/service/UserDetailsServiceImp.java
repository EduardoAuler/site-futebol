package com.fut_sexta.fut_sexta.service;

import com.fut_sexta.fut_sexta.model.User;
import com.fut_sexta.fut_sexta.model.UserDetailImplements;
import com.fut_sexta.fut_sexta.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImp implements UserDetailsService {

    private final UserRepository repository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("User not found"));

        return new UserDetailImplements(user);
    }
}
