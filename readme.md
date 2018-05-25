https://spring.io/blog/2015/02/03/sso-with-oauth2-angular-js-and-spring-security-part-v

http://localhost:9999/oauth/authorize?response_type=code&client_id=acme&redirect_uri=http://example.com
curl acme:acmesecret@localhost:9999/uaa/oauth/token -d grant_type=authorization_code -d client_id=acme -d client_secret=acmesecret -d redirect_uri=http://example.com -d code=jYWioI