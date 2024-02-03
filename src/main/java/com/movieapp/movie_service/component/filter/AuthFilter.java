package com.movieapp.movie_service.component.filter;

import com.movieapp.movie_service.client.AuthClient;
import feign.FeignException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthFilter extends OncePerRequestFilter {

    private final AuthClient authClient;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwtToken;
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            response.setStatus(403);
            return;
        }
        jwtToken = authHeader.substring(7);
        try{
            ResponseEntity<String> authResponse = authClient.validate("Bearer " + jwtToken);
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    authResponse.getBody(),
                    null,
                    List.of(new SimpleGrantedAuthority("USER"))
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            if(authResponse.getStatusCode().is2xxSuccessful()) {
                filterChain.doFilter(request, response);
            }
        } catch (FeignException.FeignClientException e) {
            response.setStatus(403);
        }
    }
}
