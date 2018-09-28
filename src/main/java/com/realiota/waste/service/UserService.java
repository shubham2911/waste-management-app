package com.realiota.waste.service;

import com.realiota.waste.dto.LoginDTO;
import com.realiota.waste.dto.UserDTO;
import com.realiota.waste.entity.mysql.User;

import java.math.BigDecimal;

public interface UserService {

    User getByPhoneNumber(Long phoneNumber);

    User getByUserId(Long userId);

    UserDTO loginUser(LoginDTO loginDTO);

    UserDTO convert(User user);

    BigDecimal getBalance(Long phoneNumber);
}
