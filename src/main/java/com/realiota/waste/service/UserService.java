package com.realiota.waste.service;

import com.realiota.waste.dto.LoginDTO;
import com.realiota.waste.dto.UserDTO;
import com.realiota.waste.entity.mysql.User;

public interface UserService {

    User getByPhoneNumber(Long phoneNumber);

    UserDTO loginUser(LoginDTO loginDTO);

    UserDTO convert(User user);
}
