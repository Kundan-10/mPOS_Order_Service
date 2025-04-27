package com.mpos.jwtUtils;

import com.mpos.exception.UsernameNotFoundException;
import com.mpos.models.User;
import com.mpos.repository.UserRepository;
import jdk.jshell.spi.ExecutionControl;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.CodePointLength;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtUtils {
    private final UserRepository userRepo;

    public User getAuthenticatedUser() throws ExecutionControl.UserException, UsernameNotFoundException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UsernameNotFoundException("Unauthorized request.");
        }
        String email = authentication.getName();
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }
}
