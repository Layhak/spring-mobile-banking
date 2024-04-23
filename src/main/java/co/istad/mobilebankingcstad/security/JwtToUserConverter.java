package security;

import co.istad.mobilebankingcstad.domain.User;
import co.istad.mobilebankingcstad.features.user.UserRepository;
import co.istad.mobilebankingcstad.security.CustomUserDetail;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@RequiredArgsConstructor
public class JwtToUserConverter implements Converter<Jwt,UsernamePasswordAuthenticationToken> {
   private final UserRepository userRepository;

    @Override
    public UsernamePasswordAuthenticationToken convert(Jwt source) {
        User user = userRepository.findByEmail(source.getSubject()).orElseThrow(()->new BadCredentialsException("Invalid Tokend"));
        CustomUserDetail customUserDetail = new CustomUserDetail();
        customUserDetail.setUser(user);
        return new UsernamePasswordAuthenticationToken(
                customUserDetail,
                "",
                customUserDetail.getAuthorities()
        );
    }

    @Override
    public <U> Converter<Jwt, U> andThen(Converter<? super UsernamePasswordAuthenticationToken, ? extends U> after) {
        return Converter.super.andThen(after);
    }

}
