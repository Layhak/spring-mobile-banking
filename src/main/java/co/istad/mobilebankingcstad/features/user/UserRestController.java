package co.istad.mobilebankingcstad.features.user;


import co.istad.mobilebankingcstad.features.user.dto.UserResponse;
import co.istad.mobilebankingcstad.features.user.dto.UserUpdateRequest;
import co.istad.mobilebankingcstad.utils.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
//provide default values for requestbody and params
public class UserRestController {
    private final UserService userService;

//    @GetMapping("/me")
//    public BaseResponse<UserResponse> getCurrentUserInfo() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String id = "";
//        if (authentication.getPrincipal() instanceof CustomUserDetail) {
//            CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();
//            id = customUserDetail.getUser().getId();
//        }
//
//        return BaseResponse.<UserResponse>ok().setPayload(
//                userService.getUserById(id)
//        );
//    }

    //    @GetMapping("/me")
//    public BaseResponse<UserResponse> getCurrentUserInfo(@AuthenticationPrincipal CustomUserDetail customUserDetail) {
//        return BaseResponse.<UserResponse>ok().setPayload(
//                userService.getUserById(customUserDetail.getUser().getId())
//        );
//    }
    @GetMapping("/me")
    public BaseResponse<UserResponse> getCurrentUserInfo(@AuthenticationPrincipal Jwt jwt) {
        System.out.println("These are the information extracted from jwt:");
        System.out.println(jwt.getSubject());
        System.out.println(jwt.getTokenValue());
//        System.out.println(jwt.getIssuer());
        return BaseResponse.<UserResponse>ok().setPayload(
                userService.getUserById(jwt.getId())
        );
    }

    @GetMapping
    @Operation(summary = "Get all users")
    public BaseResponse<List<UserResponse>> getAllUser() {
        return BaseResponse.<List<UserResponse>>ok()
                .setPayload(userService.getAllUsers());

    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by id")
    public BaseResponse<UserResponse> getUserById(@PathVariable String id) {
        return BaseResponse.<UserResponse>ok()
                .setPayload(userService.getUserById(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user by id")
    public BaseResponse<?> deleteUserById(@PathVariable String id) {
        userService.deleteUserById(id);
        return BaseResponse.deleteSuccess();
    }


    @PatchMapping("/{id}")
    @Operation(summary = "Update user by id")
    // configure swagger to provide the default request body for updating the user
    public BaseResponse<UserResponse> updateUserByID(
            @PathVariable() String id, @RequestBody UserUpdateRequest userRequest) {
        return BaseResponse.<UserResponse>updateSuccess()
                .setPayload(userService.updateUserById(id, userRequest));
    }


    @PatchMapping("/{id}/disable")
    public BaseResponse<UserResponse> disableUser(@PathVariable() String id) {
        return BaseResponse.<UserResponse>ok()
                .setPayload(userService.disableUser(id));
    }

    @PatchMapping("/{id}/enable")
    public BaseResponse<UserResponse> enableUser(@PathVariable String id) {
        return BaseResponse.<UserResponse>ok()
                .setPayload(userService.enableUser(id));
    }

}
