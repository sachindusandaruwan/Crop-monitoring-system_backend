package lk.ijse.gdse68.Crop.monitoring.system.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.gdse68.Crop.monitoring.system.service.JWTBo;
import lk.ijse.gdse68.Crop.monitoring.system.service.UserBo;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class JWTConfig extends OncePerRequestFilter {
    private final JWTBo jwtBo;
    private final UserBo userBo;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String initToken = request.getHeader("Authorization");
        String userEmail;
        String jwtToken;

        //Initial validation
        if (StringUtils.isEmpty(initToken) || !initToken.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        //Token received
        jwtToken = initToken.substring(7);
        userEmail=jwtBo.extractUsername(jwtToken);

        //User validation
        if (StringUtils.isEmpty(userEmail) && SecurityContextHolder.getContext().getAuthentication()==null){

            //load user details based on the email
            var loadUser=userBo.userDetailsService().loadUserByUsername(userEmail);
            if (jwtBo.isTokenValid(jwtToken,loadUser)){
                SecurityContext emptyContext=SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken authToken=
                        new UsernamePasswordAuthenticationToken(loadUser,null,loadUser.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetails(request));
                emptyContext.setAuthentication(authToken);
                SecurityContextHolder.setContext(emptyContext);
            }

        }

    }
}
