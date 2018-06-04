package com.RIA.filters;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static com.RIA.utils.SecurityConstants.*;

public class JwtFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest) req;
        final String authHeader = request.getHeader(HEADER_STRING);

        if (authHeader == null || !authHeader.startsWith(TOKEN_PREFIX)) {
            //Si el header es vacío o no comienza con el "TOKEN_PREFIX" se dispara una excepción
            throw new ServletException("Token not found");
        }

        //Recorto el prefijo del token
        final String compactJws = authHeader.substring(TOKEN_PREFIX.length());

        try {
            final Claims claims = Jwts.parser()
                    .setSigningKey(JWT_SECRET)
                    .parseClaimsJws(compactJws)
                    .getBody();
            //Este código se ejecuta si el token era correcto
            request.setAttribute("username", claims.get("username"));
        } catch (Exception e) {
            //Este código se ejecuta si el token no era correcto
            throw new JwtException("Token invalido", e);
        }

        chain.doFilter(req, res);
    }

}
