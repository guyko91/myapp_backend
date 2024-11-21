package com.myapp.playground.domain.auth;

import com.myapp.playground.domain.auth.entity.AuthType;
import com.myapp.playground.domain.auth.entity.OAuthToken;
import com.myapp.playground.domain.auth.entity.User;
import com.myapp.playground.domain.auth.entity.UserAuth;
import com.myapp.playground.domain.auth.entity.UserToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthDomainService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    private final OauthAuthenticationClient oauthAuthenticationClient;
    private final OAuthUserInfoClient oauthUserInfoClient;

    @Transactional(propagation = Propagation.REQUIRED)
    public User joinUserWithOAuth(String authorizationCode, String redirectUri) {
        // Oauth 토큰 발급
        OAuthToken oauthToken = oauthAuthenticationClient.getTokenInfoBy(authorizationCode, redirectUri);

        User savedUser = createNewUserWith(oauthToken);
        UserAuth userAuth = createNewUserAuthWith(savedUser, oauthToken);

        return savedUser;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public UserToken createToken(User user) {
        // TODO : 토큰 생성 로직 구현
        return UserToken.dummy();
    }

    private User createNewUserWith(OAuthToken oauthToken) {
        User user = oauthUserInfoClient.getUserInfoBy(oauthToken);
        return userRepository.saveUser(user);
    }

    private UserAuth createNewUserAuthWith(User user, OAuthToken oauthToken) {
        UserAuth userAuth = UserAuth.createWith(user, AuthType.GOOGLE, oauthToken);
        return userRepository.saveUserAuth(userAuth);
    }


}
