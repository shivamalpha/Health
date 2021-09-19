package com.develop.recruitmentstatics.repository;


import com.develop.recruitmentstatics.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {


}
