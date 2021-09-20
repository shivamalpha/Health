package com.develop.recruitmentstatics.controllers;

import com.develop.recruitmentstatics.request.*;
import com.develop.recruitmentstatics.response.*;
import com.develop.recruitmentstatics.service.HealthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/health/v1")
public class HealthController {

    @Autowired
    private HealthService healthService;


    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public RegisterUserResponse registerUser(@Valid @RequestBody RegisterUserRequest registerUserRequest) throws Exception {
        return healthService.registerUser(registerUserRequest);

    }

    @RequestMapping(value = "/registerAdmin", method = RequestMethod.POST)
    public RegisterAdminResponse registerAdmin(@Valid @RequestBody RegisterAdminRequest registerAdminRequest) throws Exception {
        return healthService.registerAdmin(registerAdminRequest);

    }

    @RequestMapping(value = "/calculateRisk", method = RequestMethod.POST)
    public SelfAssessmentResponse calculateRisk(@Valid @RequestBody SelfAssessmentRequest selfAssessmentRequest) throws  Exception {
        return healthService.riskCalculator(selfAssessmentRequest);

    }

    @RequestMapping(value = "/updateCovidResult", method = RequestMethod.POST)
    public UpdateCovidResponse updateCovidResult(@Valid @RequestBody UpdateCovidRequest updateCovidRequest) throws  Exception {
        return healthService.updateCovidResult(updateCovidRequest);
    }

    @RequestMapping(value = "/getZoneInfo", method = RequestMethod.POST)
    public GetZoneInfoResponse getZoneInfo(@Valid @RequestBody GetZoneInfo getZoneInfo) throws  Exception {
        return healthService.getZoneInfo(getZoneInfo);
    }
}
