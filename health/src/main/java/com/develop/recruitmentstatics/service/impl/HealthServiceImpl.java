package com.develop.recruitmentstatics.service.impl;


import com.develop.recruitmentstatics.entities.Admin;

import com.develop.recruitmentstatics.entities.User;
import com.develop.recruitmentstatics.enums.Zones;
import com.develop.recruitmentstatics.repository.AdminRepository;
import com.develop.recruitmentstatics.repository.UserRepository;
import com.develop.recruitmentstatics.request.*;

import com.develop.recruitmentstatics.response.*;
import com.develop.recruitmentstatics.service.HealthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;


import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class HealthServiceImpl implements HealthService {


    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserRepository userRepository;

    public RegisterUserResponse registerUser(RegisterUserRequest registerUserRequest) throws Exception {

        User user =
                User.builder().name(registerUserRequest.getName()).pincode(registerUserRequest.getPinCode()).phoneNumber(registerUserRequest.getPhoneNumber()).build();
        try {
            user = userRepository.save(user);
            return RegisterUserResponse.builder().userId(user.getId()).build();
        } catch (Exception e) {
            throw new Exception("User save failed");
        }

    }

    public RegisterAdminResponse registerAdmin(RegisterAdminRequest registerAdminRequest) throws Exception {

        Admin admin =
                Admin.builder().name(registerAdminRequest.getName()).pincode(registerAdminRequest.getPinCode()).phoneNumber(registerAdminRequest.getPhoneNumber()).build();
        try {
            admin = adminRepository.save(admin);
            return RegisterAdminResponse.builder().adminId(admin.getId()).build();
        } catch (Exception e) {
            throw new Exception("Admin save failed");
        }

    }


    public UpdateCovidResponse updateCovidResult(UpdateCovidRequest updateCovidRequest) throws Exception {

        Optional<User> user = userRepository.findById(updateCovidRequest.getUserId());

        if (!user.isPresent())
            throw new Exception("Invalid user Id");

        Optional<Admin> admin = adminRepository.findById(updateCovidRequest.getAdminId());

        if (!admin.isPresent())
            throw new Exception("Invalid admin Id");


        try {
            User userUpdated = user.get();
            userUpdated.setStatus(User.CovidStatus.valueOf(updateCovidRequest.getResult()));
            userUpdated.setAdmin(admin.get());
            User userSaved = userRepository.save(userUpdated);
            return UpdateCovidResponse.builder().updated(true).build();
        } catch (Exception e) {
            return UpdateCovidResponse.builder().updated(false).build();
        }

    }

    public GetZoneInfoResponse getZoneInfo(GetZoneInfo getZoneInfo) throws Exception {


        try {
            List<User> user = userRepository.findAllByPincodeAndStatus(getZoneInfo.getPinCode(), User.CovidStatus.POSITIVE);
            if (CollectionUtils.isEmpty(user))
                return GetZoneInfoResponse.builder().numCases(0).zoneType(Zones.GREEN.name()).build();
            else if (user.size() < 5)
                return GetZoneInfoResponse.builder().numCases(user.size()).zoneType("ORANGE").build();
            else
                return GetZoneInfoResponse.builder().numCases(user.size()).zoneType("RED").build();

        } catch (Exception e) {
            throw new Exception("Failed to fetch zone info");
        }

    }


    public SelfAssessmentResponse riskCalculator(SelfAssessmentRequest selfAssessmentRequest) throws Exception {


        try {
            Optional<User> user = userRepository.findById(selfAssessmentRequest.getUserId());

            if (!user.isPresent())
                throw new Exception("Failed to user info");

            if (CollectionUtils.isEmpty(selfAssessmentRequest.getSymptoms()) && !selfAssessmentRequest.getTravelHistory() && !selfAssessmentRequest.getContactWithCovidPatient())
                return SelfAssessmentResponse.builder().riskPercentage(5.0D).build();
            else if ((!CollectionUtils.isEmpty(selfAssessmentRequest.getSymptoms()) && selfAssessmentRequest.getSymptoms().size() == 1) && (!selfAssessmentRequest.getTravelHistory() || !selfAssessmentRequest.getContactWithCovidPatient()))
                return SelfAssessmentResponse.builder().riskPercentage(50.0D).build();
            else if ((!CollectionUtils.isEmpty(selfAssessmentRequest.getSymptoms()) && selfAssessmentRequest.getSymptoms().size() == 2) && (!selfAssessmentRequest.getTravelHistory() || !selfAssessmentRequest.getContactWithCovidPatient()))
                return SelfAssessmentResponse.builder().riskPercentage(75.0D).build();
            else if ((!CollectionUtils.isEmpty(selfAssessmentRequest.getSymptoms()) && selfAssessmentRequest.getSymptoms().size() > 2) && (!selfAssessmentRequest.getTravelHistory() || !selfAssessmentRequest.getContactWithCovidPatient()))
                return SelfAssessmentResponse.builder().riskPercentage(95.0D).build();
            else
                throw new Exception("Risk Could not be calculated");
        } catch (Exception e) {
            throw new Exception("Failed to calculate risk");
        }

    }


}
