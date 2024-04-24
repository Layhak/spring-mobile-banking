package co.istad.mobilebankingcstad.security;

import co.istad.mobilebankingcstad.features.auth.dto.AuthResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
public class TokenGenerator {

    JwtEncoder jwtAccessTokenEncoder;

    JwtEncoder jwtRefreshTokenEncoder;

    public TokenGenerator(JwtEncoder jwtRefreshTokenEncoder, @Qualifier("jwtRefreshTokenEncoder") JwtEncoder jwtAccessTokenEncoder) {
        this.jwtRefreshTokenEncoder = jwtRefreshTokenEncoder;
        this.jwtAccessTokenEncoder = jwtAccessTokenEncoder;
    }

    //We can also create scope for the token from the userDetails object here
    private String createAccessToken(Authentication authentication) {
        CustomUserDetail userDetail = (CustomUserDetail) authentication.getPrincipal();
        Instant now = Instant.now();

        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .issuer("istad.co.mobile-banking")
                .issuedAt(now)
                .expiresAt(now.plus(5, ChronoUnit.HOURS))
                .subject(userDetail.getUsername())
                .build();

        return jwtAccessTokenEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
    }

    private String createRefreshToken(Authentication authentication) {
        CustomUserDetail userDetail = (CustomUserDetail) authentication.getPrincipal();
        Instant now = Instant.now();

        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .issuer("istad.co.mobile-banking")
                .issuedAt(now)
                .expiresAt(now.plus(7, ChronoUnit.HOURS))
                .subject(userDetail.getUsername())
                .build();

        return jwtRefreshTokenEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
    }

    public AuthResponse generateToken() {
        return AuthResponse.builder()
                .userId("1234567")
//                .accessToken(createAccessToken())
//                .refreshToken(createRefreshToken())
                .build();
    }
}
