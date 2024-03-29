package nl.lisaveldhuisen.les.security.auth;

import nl.lisaveldhuisen.les.coreapi.LoginDto;
import nl.lisaveldhuisen.les.coreapi.LoginUserDto;
import nl.lisaveldhuisen.les.coreapi.RegisterUserDto;
import nl.lisaveldhuisen.les.security.User;
import nl.lisaveldhuisen.les.security.UserRepository;
import nl.lisaveldhuisen.les.security.jwt.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    private final  AuthenticationManager authenticationManager;

     private final JwtTokenProvider jwtTokenProvider;

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final UserDetailsService userDetailsService;

    public AuthServiceImpl(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder, UserRepository userRepository, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(),
                loginDto.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtTokenProvider.generateToken(authentication.getName());
    }

    @Override
    public User signup(RegisterUserDto input) {
        User user = new User();
        user.setUsername(input.getUsernameOrEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setRoles(List.of());
        return userRepository.save(user);
    }
    @Override
    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsernameOrEmail(),
                        input.getPassword()
                )
        );
        return userRepository.findByUsername(input.getUsernameOrEmail());
    }
}
