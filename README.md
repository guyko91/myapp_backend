# myapp_backend

## 학습 내용
### Google Oauth 를 활용한 로그인 구현

 - 참조 레퍼런스
   - https://openid.net/specs/openid-connect-core-1_0.html#UserInfo
   - https://developers.google.com/identity/protocols/oauth2/web-server?hl=ko

### Spring Security 를 활용한 인증/인가 구현
 - JWT 토큰을 활용한 인증/인가 구현
 - Redis 를 활용한 토큰 저장 및 갱신 처리
 - 
### 도메인 주도 설계 (DDD) 구조 적용

### 프로젝트 구동을 위한 설정 (local 기준)
 - 구동 시 환경 변수 추가 필요 
   - GOOGLE-CLIENT-ID
   - GOOGLE-CLIENT-SECRET
   - REDIS-PASSWORD
 - Redis 서버 구동 필요 (port : 6379)