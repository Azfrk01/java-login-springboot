package com.example.demo.config;
import com.example.demo.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
    private final JwtUtil jwtUtil;
    public JwtAuthenticationFilter(JwtUtil jwtUtil){
        this.jwtUtil = jwtUtil;
    }
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException{
        String authorizationHeader=request.getHeader("Authorization");
        if(authorizationHeader==null || !authorizationHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }
        String jwtToken = authorizationHeader.substring(7);
        try{
            String username=jwtUtil.getUserNameFromToken(jwtToken);
            String role=jwtUtil.getRoleFromToken(jwtToken);
            if (username != null && SecurityContextHolder
                            .getContext()
                            .getAuthentication() == null && jwtUtil.validateToken(jwtToken)){
                List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + role));
                UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(username,null,authorities);
                SecurityContextHolder
                        .getContext()
                        .setAuthentication(authentication);
            }
        }catch(Exception ex){
            filterChain.doFilter(request, response);
            return;
        }
        filterChain.doFilter(request, response);
    }
}