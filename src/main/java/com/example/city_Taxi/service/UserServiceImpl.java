package com.example.city_Taxi.service;

import com.example.city_Taxi.dto.UserDTO;
import com.example.city_Taxi.mapper.UserMapper;
import com.example.city_Taxi.model.User;
import com.example.city_Taxi.repository.UserRepository;
import com.example.city_Taxi.util.Alert;
import com.example.city_Taxi.util.ResponseMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__({@Autowired}))
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseMessage registerUser(UserDTO userDTO) {
        try {
            User user = UserMapper.INSTANCE.userDTOToUser(userDTO);
            userRepository.save(user);
            return new ResponseMessage(200, Alert.registerSuccess, user);
        } catch (Exception e) {
            return new ResponseMessage(500, Alert.registerFailed, null);
        }
    }

    @Override
    public ResponseMessage updateUser(Long id, UserDTO userDTO) {
        return userRepository.findById(id).map(user -> {
            user.setUsername(userDTO.getUsername());
            user.setPassword(userDTO.getPassword());
            user.setEmail(userDTO.getEmail());
            user.setUserType(userDTO.getUserType());
            user.setProfileImage(userDTO.getProfileImage());
            userRepository.save(user);
            return new ResponseMessage(200, Alert.updateSuccess, user);
        }).orElse(new ResponseMessage(404, Alert.nosuchfound, null));
    }

    @Override
    public ResponseMessage getUserById(Long id) {
        return userRepository.findById(id).map(user -> {
            userRepository.delete(user);
            return new ResponseMessage(200, Alert.removeSuccess, null);
        }).orElse(new ResponseMessage(404, Alert.nosuchfound, null));
    }

    @Override
    public ResponseMessage deleteUser(Long id) {
        return userRepository.findById(id).map(user -> {
            userRepository.delete(user);
            return new ResponseMessage(200, Alert.removeSuccess, null);
        }).orElse(new ResponseMessage(404, Alert.nosuchfound, null));
    }

    @Override
    public ResponseMessage getAllUsers() {
        List<User> users = userRepository.findAll();
        return new ResponseMessage(200, Alert.ok, users);
    }

    @Override
    public ResponseMessage findUserByUsername(String username) {
        return userRepository.findByUsername(username).map(user -> new ResponseMessage(200, Alert.ok, user))
                .orElse(new ResponseMessage(404, Alert.nosuchfound, null));
    }

    @Override
    public ResponseMessage authenticate(UserDTO userDTO) {
        return userRepository.findByUsername(userDTO.getUsername())
                .map(existUser -> {
                    if (passwordEncoder.matches(userDTO.getPassword(), existUser.getPassword())) {
                        return new ResponseMessage(200, Alert.ok, existUser); // Success
                    }
                    return new ResponseMessage(401, Alert.saveFailed, null); // Unauthorized
                })
                .orElse(new ResponseMessage(404, Alert.nosuchfound, null)); // User not found
    }
}
