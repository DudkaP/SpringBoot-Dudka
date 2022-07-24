package com.example.springbootdudka.security.filters;

import com.example.springbootdudka.controllers.dao.CustomerDAO;
import com.example.springbootdudka.controllers.models.Customer;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class CustomFilter extends OncePerRequestFilter {

    private CustomerDAO customerDAO;

    public CustomFilter(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Bearer")) {
            String token = authorization.replace("Bearer ", "");
            String subject = Jwts.parser()
                    .setSigningKey("lockKey".getBytes())
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();

            Customer customerByLogin = customerDAO.findByLogin(subject);

            if (customerByLogin != null) {
                SecurityContextHolder
                        .getContext()
                        .setAuthentication(
                                new UsernamePasswordAuthenticationToken(
                                        customerByLogin.getLogin(),
                                        customerByLogin.getPassword(),
                                        Arrays.asList(new SimpleGrantedAuthority(customerByLogin.getRole()))
                                )
                        );
            }
        }
        filterChain.doFilter(request, response);
    }
}

