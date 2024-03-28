package nl.lisaveldhuisen.les.service;

import nl.lisaveldhuisen.les.security.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}
