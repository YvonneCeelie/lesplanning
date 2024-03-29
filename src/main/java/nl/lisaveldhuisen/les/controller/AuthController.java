package nl.lisaveldhuisen.les.controller;

import nl.lisaveldhuisen.les.coreapi.*;
import nl.lisaveldhuisen.les.security.User;
import nl.lisaveldhuisen.les.security.auth.AuthService;
import nl.lisaveldhuisen.les.security.jwt.JwtTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/auth")
@RestController
public class AuthController {
    final private AuthService authService;
    final private JwtTokenProvider jwtTokenProvider;

    public AuthController(AuthService authService, JwtTokenProvider jwtTokenProvider) {
        this.authService = authService;
        this.jwtTokenProvider = jwtTokenProvider;
    }
    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authService.authenticate(loginUserDto);

        String jwtToken = jwtTokenProvider.generateToken(authenticatedUser.getUsername());

        LoginResponse loginResponse = new LoginResponse(jwtToken, jwtTokenProvider.getJwtExpirationDate());

        return ResponseEntity.ok(loginResponse);
    }
}
