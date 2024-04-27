package co.istad.mobilebankingcstad.features.auth;

import co.istad.mobilebankingcstad.features.auth.dto.AuthRequest;
import co.istad.mobilebankingcstad.features.auth.dto.AuthResponse;
import co.istad.mobilebankingcstad.features.user.UserService;
import co.istad.mobilebankingcstad.features.user.dto.UserRequest;
import co.istad.mobilebankingcstad.features.user.dto.UserResponse;
import co.istad.mobilebankingcstad.utils.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@SecurityRequirements(value = {})
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthRestController {
    private final AuthServiceImpl authService;
    private final UserService userService;

    @PostMapping("login")
    @Operation(summary = "Login User"
            , requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(schema = @Schema(implementation = AuthRequest.class),
                    examples = @ExampleObject(value = """
                            {
                              "email":"layhak@gmail.com",
                              "password": "string"
                            }
                            """)
            )))
    public BaseResponse<AuthResponse> login(@RequestBody AuthRequest request) {
        return BaseResponse.<AuthResponse>ok().setPayload(authService.login(request));
    }

    @PostMapping("refresh")
    public BaseResponse<AuthResponse> refreshToken(@RequestBody RefreshTokenRequest request) {
        return BaseResponse.<AuthResponse>ok().setPayload(authService.refreshToken(request));
    }


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Register new user"
            , requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(schema = @Schema(implementation = UserRequest.class),
                    examples = @ExampleObject(value = """
                            {
                              "fullName": "Heng Layhak",
                              "username": "Layhak",
                              "gender": "male",
                              "password": "string",
                              "email":"layhak@gmail.com",
                              "profileImage": "string",
                              "phoneNumber": "string",
                              "cityOrProvince": "string",
                              "khanOrDistrict": "string",
                              "sangkatOrCommune": "string",
                              "employeeType": "string",
                              "companyName": "string",
                              "mainSourceOfIncome": "string",
                              "monthlyIncomeRange": 0,
                              "studentIdCard": "string",
                            "roles":["ADMIN"]
                            }
                            """)

            )
    )
    )
    public BaseResponse<UserResponse> registerUser(
            @Valid @RequestBody UserRequest userRequest) {
        return BaseResponse.<UserResponse>createSuccess()
                .setPayload(userService.createUser(userRequest));
    }
}
