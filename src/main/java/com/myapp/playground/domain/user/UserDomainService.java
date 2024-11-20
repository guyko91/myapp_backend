package com.myapp.playground.domain.user;

import com.myapp.playground.domain.user.dto.request.UserJoinCommand;
import com.myapp.playground.domain.user.dto.response.UserJoinResult;
import com.myapp.playground.domain.user.entity.AuthType;
import com.myapp.playground.domain.user.entity.OAuthToken;
import com.myapp.playground.domain.user.entity.User;
import com.myapp.playground.domain.user.entity.UserAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserDomainService {

    private final UserRepository userRepository;
    private final OauthUseCase oauthUseCase;
    private final OAuthUserUseCase oauthUserUseCase;

    @Transactional(propagation = Propagation.REQUIRED)
    public UserJoinResult joinOAuthUser(UserJoinCommand command) {

        // Oauth 토큰 발급
        String authorizationCode = command.authorizationCode();
        String redirectUri = command.redirectUri();
        OAuthToken oauthToken = oauthUseCase.getTokenInfoBy(authorizationCode, redirectUri);

        User savedUser = createNewUserWith(oauthToken);
        UserAuth userAuth = createNewUserAuthWith(savedUser, oauthToken);

        return new UserJoinResult(savedUser, userAuth);
    }

    private User createNewUserWith(OAuthToken oauthToken) {
        User user = oauthUserUseCase.getUserInfoBy(oauthToken);
        return userRepository.saveUser(user);
    }

    private UserAuth createNewUserAuthWith(User user, OAuthToken oauthToken) {
        UserAuth userAuth = UserAuth.createWith(user, AuthType.GOOGLE, oauthToken);
        return userRepository.saveUserAuth(userAuth);
    }


}
