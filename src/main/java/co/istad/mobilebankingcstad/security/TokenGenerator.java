package co.istad.mobilebankingcstad.security;

import co.istad.mobilebankingcstad.features.auth.dto.AuthResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
public class TokenGenerator {

    JwtEncoder jwtAccessTokenEncoder;

    JwtEncoder jwtRefreshTokenEncoder;

    public TokenGenerator(JwtEncoder jwtAccessTokenEncoder, @Qualifier("jwtRefreshTokenEncoder") JwtEncoder jwtRefreshTokenEncoder) {
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
                .expiresAt(now.plus(15, ChronoUnit.SECONDS))
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
                .expiresAt(now.plus(7, ChronoUnit.DAYS))
                .subject(userDetail.getUsername())
                .build();

        return jwtRefreshTokenEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
    }

    public AuthResponse generateToken(Authentication authentication) {

        if (!(authentication.getPrincipal() instanceof CustomUserDetail customUserDetail)) {
            throw new BadCredentialsException("Provided Token is not valid");
        }
        String refreshToken;
        if (authentication.getCredentials() instanceof Jwt jwt) {
            Instant now = Instant.now(),
                    expireAt = jwt.getExpiresAt();
            Duration duration = Duration.between(now, expireAt);
            long daysUntilExpire = duration.toDays();
            if (daysUntilExpire < 7) {
                refreshToken = createRefreshToken(authentication);
            } else {
                refreshToken = jwt.getTokenValue();
            }
        } else {
            refreshToken = createRefreshToken(authentication);
        }

        return AuthResponse.builder()
                .userId(customUserDetail.getUser().getId())
                .accessToken(createAccessToken(authentication))
                .refreshToken(refreshToken)
                .build();
    }
}
