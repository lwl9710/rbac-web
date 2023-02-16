package com.basic.project.service;

import com.basic.project.dto.ResponseDTO;
import java.util.Map;

public interface AuthenticationService {
    ResponseDTO<String> login(Map<String, String> params);

    boolean hasPermission(String roleId, String uri);
}
