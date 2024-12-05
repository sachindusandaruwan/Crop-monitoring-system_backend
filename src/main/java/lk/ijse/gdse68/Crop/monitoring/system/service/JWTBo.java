package lk.ijse.gdse68.Crop.monitoring.system.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JWTBo {
    String extractUsername(String token);
    String generateToken(UserDetails userDetails);
    String refreshToken(UserDetails userDetails);
    boolean isTokenValid(String token, UserDetails userDetails);
}
