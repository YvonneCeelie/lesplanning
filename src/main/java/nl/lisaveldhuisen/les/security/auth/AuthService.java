package nl.lisaveldhuisen.les.security.auth;

import nl.lisaveldhuisen.les.coreapi.LoginDto;
import nl.lisaveldhuisen.les.coreapi.LoginUserDto;
import nl.lisaveldhuisen.les.coreapi.RegisterUserDto;
import nl.lisaveldhuisen.les.security.User;

public interface AuthService {
    String login(LoginDto loginDto);

    User signup(RegisterUserDto input);

    User authenticate(LoginUserDto input);
}
