package co.istad.mobilebankingcstad.features.auth;

import lombok.Builder;

@Builder
public record RefreshTokenRequest(
        String refreshToken

) {
}
