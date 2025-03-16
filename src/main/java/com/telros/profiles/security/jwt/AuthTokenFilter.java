package com.telros.profiles.security.jwt;

import com.telros.profiles.service.ProfileService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;


@Component
public class AuthTokenFilter extends OncePerRequestFilter {
  public static final String BEARER_PREFIX = "Bearer ";
  public static final String HEADER_NAME = "Authorization";

  private final JwtService jwtService;
  private final ProfileService profileService;

  public AuthTokenFilter(JwtService jwtService, ProfileService profileService) {
    this.jwtService = jwtService;
    this.profileService = profileService;
  }

  @Override
  protected void doFilterInternal(@NonNull HttpServletRequest request,
                                  @NonNull HttpServletResponse response,
                                  @NonNull FilterChain filterChain)
      throws ServletException, IOException {
    var authHeader = request.getHeader(HEADER_NAME);
    if (authHeader == null || !StringUtils.startsWithIgnoreCase(authHeader,
        BEARER_PREFIX)) {
      filterChain.doFilter(request, response);
      return;
    }
    var jwt = authHeader.substring(BEARER_PREFIX.length());
    var username = jwtService.extractUserName(jwt);
    if (!username.isEmpty() && SecurityContextHolder.getContext()
        .getAuthentication() == null) {
      UserDetails userDetails = profileService.loadUserByUsername(username);
      if (jwtService.isTokenValid(jwt, userDetails)) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
            userDetails,
            null,
            userDetails.getAuthorities()
        );
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        context.setAuthentication(authToken);
        SecurityContextHolder.setContext(context);
      }
    }
    filterChain.doFilter(request, response);
  }
}

