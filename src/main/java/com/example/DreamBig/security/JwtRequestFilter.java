package com.example.DreamBig.security;

import com.example.DreamBig.model.CustomUserDetails;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Set;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtRequestFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7); // Витягування токена з заголовку

            if (jwtUtil.validateToken(token)) {
                Claims claims = jwtUtil.extractAllClaims(token);

                // Перевірка на тип привілеїв перед їх перетворенням
                Object privilegesObj = claims.get("privileges");
                Set<String> privileges = null;

                // Якщо привілеї присутні і мають тип Set, то перетворюємо
                if (privilegesObj instanceof Set<?>) {
                    privileges = (Set<String>) privilegesObj;
                }

                // Якщо привілеї є і правильно перетворені, створюємо CustomUserDetails
                if (privileges != null) {
                    UserDetails userDetails = new CustomUserDetails(
                            claims.getSubject(), // email
                            null, // передаємо null для пароля
                            claims.get("role").toString(), // роль користувача
                            privileges // список привілеїв
                    );

                    // Створення токену аутентифікації та додавання його в контекст
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities()
                    );
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                } else {
                    // Якщо привілеї відсутні або не правильного типу, логування помилки
                    System.out.println("Привілеї не знайдено або мають невірний тип.");
                }
            }
        }
        chain.doFilter(request, response); // Продовжуємо обробку запиту
    }
}
