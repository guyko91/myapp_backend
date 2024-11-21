package com.myapp.playground.presentation.auth;

import com.myapp.playground.application.auth.AuthApplication;
import com.myapp.playground.application.auth.dto.response.AuthJoinResult;
import com.myapp.playground.presentation.auth.dto.request.ServiceJoinApiRequest;
import com.myapp.playground.presentation.auth.dto.response.ServiceJoinApiResponse;
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
    public ResponseEntity<ServiceJoinApiResponse> join(
        @RequestBody @Valid ServiceJoinApiRequest serviceJoinApiRequest) {
        AuthJoinResult result = authApplication.join(serviceJoinApiRequest.toJoinCommand());
        return ResponseEntity.ok(ServiceJoinApiResponse.from(result));
    }

}
