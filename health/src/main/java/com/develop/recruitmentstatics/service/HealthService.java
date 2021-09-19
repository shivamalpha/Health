package com.develop.recruitmentstatics.service;


import com.develop.recruitmentstatics.request.*;
import com.develop.recruitmentstatics.response.*;
import org.springframework.stereotype.Service;

@Service
public interface HealthService {

    RegisterUserResponse registerUser(RegisterUserRequest registerUserRequest) throws Exception;

    RegisterAdminResponse registerAdmin(RegisterAdminRequest registerAdminRequest) throws Exception;

    UpdateCovidResponse updateCovidResult(UpdateCovidRequest updateCovidRequest) throws Exception;

     GetZoneInfoResponse getZoneInfo(GetZoneInfo getZoneInfo) throws Exception;

    SelfAssessmentResponse riskCalculator(SelfAssessmentRequest selfAssessmentRequest) throws Exception;

}
