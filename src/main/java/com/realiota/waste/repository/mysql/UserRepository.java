package com.realiota.waste.repository.mysql;

import com.realiota.waste.entity.mysql.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByPhoneNumber(Long phoneNumber);

    User findByPhoneNumberAndPassword(Long phoneNumber, String password);
}
