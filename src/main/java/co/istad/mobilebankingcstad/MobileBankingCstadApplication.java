package co.istad.mobilebankingcstad;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Mobile Banking API",
                version = "1,0",
                description = "Mobile Banking API for Testing",
                contact = @Contact(
                        name = "Layhak",
                        email = "layhak@gmail.com"
                )
        ), security = @SecurityRequirement(name = "bearerAuth")

)
@SecurityScheme(
        name = "beareerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class MobileBankingCstadApplication {

    public static void main(String[] args) {
        SpringApplication.run(MobileBankingCstadApplication.class, args);
    }

}
