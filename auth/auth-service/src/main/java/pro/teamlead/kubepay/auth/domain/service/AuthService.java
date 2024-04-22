package pro.teamlead.kubepay.auth.domain.service;

import pro.teamlead.kubepay.auth.api.domain.exception.InvalidCredentialsException;
import pro.teamlead.kubepay.auth.api.domain.exception.UserAlreadyExistsException;
import pro.teamlead.kubepay.auth.configuration.JwtConfiguration;
import pro.teamlead.kubepay.auth.domain.model.JwtToken;
import pro.teamlead.kubepay.auth.sdk.user.ServicePrincipal;
import pro.teamlead.kubepay.auth.sdk.util.JwtTokenProvider;
import pro.teamlead.kubepay.auth.sdk.authority.Authority;
import pro.teamlead.kubepay.user.api.domain.exception.UserNotFoundException;
import pro.teamlead.kubepay.user.api.domain.model.CreateUserRequest;
import pro.teamlead.kubepay.user.client.UserClient;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Slf4j
@Service
@RequiredArgsConstructor
@Observed
public class AuthService {

    private final UserClient userClient;

    private final JwtTokenProvider jwtTokenProvider;

    private final ServicePrincipal servicePrincipal;

    private final JwtConfiguration jwtConfiguration;

    private final PasswordEncoder passwordEncoder;

    public JwtToken createUser(String user, String password) {

        if (isUserExists(user)) {
            throw new UserAlreadyExistsException();
        }

        var request = new CreateUserRequest(user, passwordEncoder.encode(password));

        userClient.createUser(servicePrincipal, request);

        var token = jwtTokenProvider.createToken(user, jwtConfiguration.getJwtTTL(),
                Authority.ROLE_USER);

        return new JwtToken(token);
    }

    public JwtToken authUser(String user, String password) {

        var passwordHash = getPasswordHash(user);

        if (!passwordEncoder.matches(password, passwordHash)) {
            throw new InvalidCredentialsException();
        }

        var token = jwtTokenProvider.createToken(user, jwtConfiguration.getJwtTTL(),
                Authority.ROLE_USER);

        return new JwtToken(token);
    }

    private boolean isUserExists(String user) {
        try {
            userClient.getUserInfo(servicePrincipal, user);
            return true;
        } catch (UserNotFoundException e) {
            return false;
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    private String getPasswordHash(String user) {
        try {
            return userClient.getPasswordHash(servicePrincipal, user);
        } catch (UserNotFoundException e) {
            throw new InvalidCredentialsException();
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }
}
