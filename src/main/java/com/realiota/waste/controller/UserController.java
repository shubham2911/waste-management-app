package com.realiota.waste.controller;

import com.realiota.waste.dto.LoginDTO;
import com.realiota.waste.dto.Response;
import com.realiota.waste.dto.UserDTO;
import com.realiota.waste.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response<UserDTO> login(@RequestBody LoginDTO loginDTO) {
        return new Response<>(userService.loginUser(loginDTO));
    }
}