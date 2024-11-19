package com.myapp.playground.presentation.auth;

import com.myapp.playground.application.auth.AuthApplication;
import com.myapp.playground.application.auth.dto.response.AuthJoinResult;
import com.myapp.playground.presentation.auth.dto.request.ServiceJoinRequest;
import com.myapp.playground.presentation.auth.dto.response.ServiceJoinResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthApplication authApplication;

    @PostMapping("join")
    public ResponseEntity<ServiceJoinResponse> join(
        @RequestBody @Valid ServiceJoinRequest serviceJoinRequest) {
        AuthJoinResult result = authApplication.join(serviceJoinRequest.toJoinCommand());
        return ResponseEntity.ok(ServiceJoinResponse.from(result));
    }

}
