package com.develop.recruitmentstatics.service;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;

/**
 * Created by harshasai on 30/10/17.
 */
public class ServiceUtils {

    public static HttpHeaders getCommonHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }



}
