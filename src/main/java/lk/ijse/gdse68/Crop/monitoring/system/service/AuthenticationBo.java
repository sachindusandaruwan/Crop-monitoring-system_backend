package lk.ijse.gdse68.Crop.monitoring.system.service;

import lk.ijse.gdse68.Crop.monitoring.system.dto.UserDto;
import lk.ijse.gdse68.Crop.monitoring.system.jwtModel.JwtAuthResponse;
import lk.ijse.gdse68.Crop.monitoring.system.jwtModel.SignIn;

public interface AuthenticationBo {
    JwtAuthResponse signIn(SignIn signIn);
    JwtAuthResponse signUp(UserDto signUp);
    JwtAuthResponse refreshToken(String accessToken);
}
