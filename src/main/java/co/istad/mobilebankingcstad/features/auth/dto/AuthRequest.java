package co.istad.mobilebankingcstad.features.auth.dto;

public record AuthRequest(
        String email,
        String password
) {
}
