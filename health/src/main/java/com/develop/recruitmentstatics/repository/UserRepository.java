package com.develop.recruitmentstatics.repository;


import com.develop.recruitmentstatics.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByPincodeAndStatus(String pincode, User.CovidStatus covidStatus);
}
