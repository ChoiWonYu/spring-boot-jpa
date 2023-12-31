package jpabook.jpashop.config;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import jpabook.jpashop.config.jwt.TokenProvider;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

  private final MemberService memberService;
  private final String secretKey;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    final String authorization= request.getHeader(HttpHeaders.AUTHORIZATION);
    log.info("authorization : {}",authorization);

    //토큰을 보내지 않으면 block
    if(authorization==null || !authorization.startsWith("Bearer ")){
      log.error("invalid authorization");
      filterChain.doFilter(request,response);
      return;
    }

    //Token 꺼내기
    String token=authorization.split(" ")[1];

    //Token 만료되었는지 검사
    if(TokenProvider.isExpired(token,secretKey)){
      log.error("Token이 만료 되었습니다.");
      filterChain.doFilter(request,response);
      return;
    }

    //token에서 userName 꺼내기
    String userName=TokenProvider.getuserName(token,secretKey);
    log.info("userName : {}",userName);

    UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userName,null,
        List.of(new SimpleGrantedAuthority("USER")));
    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    filterChain.doFilter(request,response);
  }
}
