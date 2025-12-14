package com.incubyte.sweetshop.service;

import com.incubyte.sweetshop.dto.LoginRequest;
import com.incubyte.sweetshop.dto.RegisterRequest;

/**
 * Handles authentication related operations.
 */
public interface AuthService {

    void register(RegisterRequest request);

    String login(LoginRequest request);
}
