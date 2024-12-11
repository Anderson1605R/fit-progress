package br.com.rochadev.fit_progress.security;

import br.com.rochadev.fit_progress.data.DetalhesUsuarioData;
import br.com.rochadev.fit_progress.model.UsuarioModel;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class JwtAuthFilter extends UsernamePasswordAuthenticationFilter {
    @Value("${jwt.secret}")
    private String tokenSenha;

    @Value("${jwt.expiration}")
    private int tokenExpiration;
    private final AuthenticationManager authenticationManager;

    public JwtAuthFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UsuarioModel usuario = new ObjectMapper().readValue(request.getInputStream(), UsuarioModel.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuario.getEmail(),
                    usuario.getSenha(),
                    new ArrayList<>()));
        } catch (IOException e) {
            throw new RuntimeException("Falha ao autenticar usuário", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        DetalhesUsuarioData usuarioData = (DetalhesUsuarioData) authResult.getPrincipal();

        String token = JWT.create()
                .withSubject(usuarioData.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + tokenExpiration))
                .sign(Algorithm.HMAC512(tokenSenha));
        response.getWriter().write(token);
        response.getWriter().flush();


    }
}
