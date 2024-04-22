package co.istad.mobilebankingcstad.features.accounts.dto;

import co.istad.mobilebankingcstad.domain.AccountType;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record AccountRequest (
        String accountNumber,
        String accountName,
        BigDecimal accountBalance,
        String accountType,
        String userId
){
}
