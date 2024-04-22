package co.istad.mobilebankingcstad.init;

import co.istad.mobilebankingcstad.domain.AccountType;
import co.istad.mobilebankingcstad.domain.Role;
import co.istad.mobilebankingcstad.features.accounttype.AccountTypeRepository;
import co.istad.mobilebankingcstad.features.roles.RoleRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.PrePersist;
import lombok.RequiredArgsConstructor;
import org.hibernate.dialect.function.array.ArraySliceUnnestFunction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


// populate database ( role with some data )
@Component
@RequiredArgsConstructor
public class DataInitializer {
    private final RoleRepository roleRepository;
    private final AccountTypeRepository accountTypeRepository;
    @PostConstruct
    void roleInit(){
        List<String> roles = List.of("ADMIN","STUFF","USER");
        if(roleRepository.findAll().isEmpty()){
            for(var role : roles){
                var roleObj = new Role();
                roleObj.setName(role);
                roleRepository.save(roleObj);
            }
        }

    }

    @PostConstruct
    void accountTypesInit(){
        List<AccountType> accountTypes = new ArrayList<>(){{
            add(new AccountType()
                    .setName("SAVINGS")
                    .setDescription("This is the type of account that you gain interest when you save your money in the bank"));

            add(new AccountType()
                    .setName("PAYROLLS")
                    .setDescription("This is account to get paid by company monthly."));
            add(new AccountType()
                    .setName("Card")
                    .setDescription("Allow you to create different card for personal uses!"));
        }};

        if(accountTypeRepository.findAll().isEmpty())
        {
            accountTypeRepository.saveAll(accountTypes);

        }
    }
}
