package co.istad.mobilebankingcstad.init;

import co.istad.mobilebankingcstad.domain.AccountType;
import co.istad.mobilebankingcstad.domain.Authority;
import co.istad.mobilebankingcstad.domain.Role;
import co.istad.mobilebankingcstad.features.accounttype.AccountTypeRepository;
import co.istad.mobilebankingcstad.features.authority.AuthorityRepository;
import co.istad.mobilebankingcstad.features.roles.RoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;


// populate database ( role with some data )
@Component
@RequiredArgsConstructor
public class DataInitializer {
    private final RoleRepository roleRepository;
    private final AccountTypeRepository accountTypeRepository;
    private final AuthorityRepository authorityRepository;

    @PostConstruct
    void authorityInit() {
        List<String> authorities = List.of("READ", "WRITE", "DELETE");
        if (authorityRepository.findAll().isEmpty()) {
            authorities.forEach(auth -> {
                Authority authority = new Authority();
                authority.setName(auth);
                authorityRepository.save(authority);
            });
        }
    }

    @PostConstruct
    void roleInit() {
        List<String> roles = List.of("ADMIN", "USER");
        if (roleRepository.findAll().isEmpty()) {
            var allAuth = new HashSet<>(authorityRepository.findAll());
            for (var role : roles) {
                var roleObj = new Role();
                if (role.equals("ADMIN")) {
                    roleObj.setAuthorities(new HashSet<>(allAuth));
                } else if (role.equals("USER")) {
                    roleObj.setAuthorities(allAuth.stream().filter(authority -> authority
                                    .getName()
                                    .equals("READ"))
                            .collect(Collectors.toSet()));
                }
                roleObj.setName(role);
                roleRepository.save(roleObj);
            }
        }

    }

    @PostConstruct
    void accountTypesInit() {
        List<AccountType> accountTypes = new ArrayList<>() {{
            add(new AccountType()
                    .setName("SAVINGS")
                    .setDescription("This is the type of account that you gain interest when you save your money in the bank"));

            add(new AccountType()
                    .setName("PAYROLLS")
                    .setDescription("This is account to get paid by company monthly."));
            add(new AccountType()
                    .setName("CARD")
                    .setDescription("Allow you to create different card for personal uses!"));
        }};

        if (accountTypeRepository.findAll().isEmpty()) {
            accountTypeRepository.saveAll(accountTypes);

        }
    }


}
