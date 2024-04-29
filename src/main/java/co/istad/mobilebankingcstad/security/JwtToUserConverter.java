package co.istad.mobilebankingcstad.security;

import co.istad.mobilebankingcstad.domain.User;
import co.istad.mobilebankingcstad.features.user.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Getter
@Setter
@RequiredArgsConstructor
@Component
public class JwtToUserConverter implements Converter<Jwt, JwtAuthenticationToken> {
    private static final Logger log = LoggerFactory.getLogger(JwtToUserConverter.class);
    private final UserRepository userRepository;

    @Override
    public JwtAuthenticationToken convert(Jwt source) {
        User user = userRepository.findByEmail(source.getSubject()).orElseThrow(() -> new BadCredentialsException("Invalid Tokend"));
        CustomUserDetail customUserDetail = new CustomUserDetail();
        customUserDetail.setUser(user);
        customUserDetail.getAuthorities().forEach(
                authority -> {
                    System.out.println(authority.getAuthority());
                }
        );
        System.out.println("User Authorities are : " + customUserDetail.getAuthorities());

        return new JwtAuthenticationToken(
                source,
                customUserDetail.getAuthorities()
        );
    }
//
//    @Override
//    public UsernamePasswordAuthenticationToken convert(Jwt source) {
//        User user = userRepository.findByEmail(source.getSubject()).orElseThrow(() -> new BadCredentialsException("Invalid Tokend"));
//        CustomUserDetail customUserDetail = new CustomUserDetail();
//        customUserDetail.setUser(user);
//        customUserDetail.getAuthorities().forEach(
//                authority -> {
//                    System.out.println(authority.getAuthority());
//                }
//        );
//        System.out.println("User Authorities are : " + customUserDetail.getAuthorities());
//
//        return new UsernamePasswordAuthenticationToken(
//                customUserDetail,
//                "",
//                customUserDetail.getAuthorities()
//        );
//    }
//
//    @Override
//    public <U> Converter<Jwt, U> andThen(Converter<? super UsernamePasswordAuthenticationToken, ? extends U> after) {
//        return Converter.super.andThen(after);
//    }

}
