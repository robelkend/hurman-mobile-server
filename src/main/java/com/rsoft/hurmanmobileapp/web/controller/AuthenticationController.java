package com.rsoft.hurmanmobileapp.web.controller;

import com.rsoft.hurmanmobileapp.dto.AuthenticationRequest;
import com.rsoft.hurmanmobileapp.dto.AuthenticationResponse;
import com.rsoft.hurmanmobileapp.dto.ChangePasswordRequest;
import com.rsoft.hurmanmobileapp.dto.ChangePasswordResponse;
import com.rsoft.hurmanmobileapp.security.JwtUtil;
import com.rsoft.hurmanmobileapp.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/mobile")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    //Tout ce code va etre mis au niveau de security
    @RequestMapping(value = "/Authenticate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationService.addUser(authenticationRequest.getUserId(), authenticationRequest.getPassword());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUserId(), authenticationRequest.getPassword()));
        } catch (AuthenticationException e) {
            throw new Exception("Incorrect username or password", e);
        }
        final UserDetails userDetails = authenticationService.loadUserByUsername(authenticationRequest.getUserId());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
        AuthenticationResponse response = authenticationService.loadUserInfos(authenticationRequest);
        response.setJwt(jwt);
        authenticationService.resetUser(authenticationRequest.getUserId());
        return ResponseEntity.ok(response);
    }


    @RequestMapping(value = "/ChangePassword", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ChangePasswordResponse> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) throws Exception {
        ChangePasswordResponse r = authenticationService.changePassword(changePasswordRequest);
        return ResponseEntity.ok(r);
    }
}