package com.realiota.waste.service.impl;

import com.realiota.waste.dto.LoginDTO;
import com.realiota.waste.dto.UserDTO;
import com.realiota.waste.entity.mysql.User;
import com.realiota.waste.exception.WasteManagementException;
import com.realiota.waste.repository.mysql.UserRepository;
import com.realiota.waste.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public User getByPhoneNumber(Long phoneNumber) {
        User user = userRepository.findByPhoneNumber(phoneNumber);
        if (user == null) {
            throw new WasteManagementException("User not found.");
        }
        return user;
    }

    @Override
    public User getByUserId(Long userId) {
        Optional<User> optional = userRepository.findById(userId);
        if (Optional.empty().equals(optional)) {
            throw new WasteManagementException("User not found.");
        }
        return optional.get();
    }

    @Override
    @Transactional
    public UserDTO loginUser(LoginDTO loginDTO) {
        log.info("Login request received for phone number : {}", loginDTO.getPhoneNumber());
        User user = userRepository.findByPhoneNumberAndPassword(loginDTO.getPhoneNumber(), loginDTO.getPassword());
        if (user == null) {
            throw new WasteManagementException("Incorrect Login Credentials");
        }
        return convert(user);
    }

    @Override
    public UserDTO convert(User user) {
        return new UserDTO(user.getName(), user.getPhoneNumber(), user.getUserType(), user.getGreenMoneyBalance());
    }
}
