package br.com.api.produtos.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.api.produtos.repositorio.AuthenticationRepositorio;
import br.com.api.produtos.servico.TokenServico;

@Component
public class FilterToken extends OncePerRequestFilter {

    @Autowired
    private TokenServico tokenServico;

    @Autowired
    private AuthenticationRepositorio authenticationRepositorio;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

                String token;

                var authorizationHeader = request.getHeader("Authorization");

                if (authorizationHeader != null) {
                    token = authorizationHeader.replace("Bearer ", "");
                    var subject = this.tokenServico.getSubject(token);

                    var auth = this.authenticationRepositorio.findByUsername(subject);

                    var authentication = new UsernamePasswordAuthenticationToken(auth, 
                    null, auth.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(authentication);

                }

                filterChain.doFilter(request, response);

                    
    }
    
}
