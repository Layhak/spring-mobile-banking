package co.istad.mobilebankingcstad;

import io.github.cdimascio.dotenv.Dotenv;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Objects;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Mobile Banking API (Data Analytics Class)",
                version = "1.0",
                description = "Mobile Banking API",
                contact = @Contact(
                        name = "Admin",
                        email = "admin@gmail.com"

                ),
                termsOfService = "http://swagger.io/terms/",
                license = @io.swagger.v3.oas.annotations.info.License(
                        name = "Apache 2.0",
                        url = "http://www.apache.org/licenses/LICENSE-2.0.html"
                )
        ),
        security = @SecurityRequirement(name = "bearerAuth")
)
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)

public class MobileBankingCstadApplication {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        System.setProperty("SPRING_DATASOURCE_USERNAME", Objects.requireNonNull(dotenv.get("SPRING_DATASOURCE_USERNAME")));
        System.setProperty("SPRING_DATASOURCE_PASSWORD", Objects.requireNonNull(dotenv.get("SPRING_DATASOURCE_PASSWORD")));
        System.setProperty("SPRING_DATASOURCE_DBNAME", Objects.requireNonNull(dotenv.get("SPRING_DATASOURCE_DBNAME")));
        SpringApplication.run(MobileBankingCstadApplication.class, args);
    }

}
