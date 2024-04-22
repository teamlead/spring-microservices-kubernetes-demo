package pro.teamlead.kubepay.user.application;

import com.github.javafaker.Faker;
import pro.teamlead.kubepay.auth.sdk.user.UserPrincipal;
import pro.teamlead.kubepay.user.api.domain.model.CreateUserRequest;
import pro.teamlead.kubepay.user.api.domain.model.UserInfo;
import pro.teamlead.kubepay.user.domain.model.User;
import pro.teamlead.kubepay.user.domain.service.UserService;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import pro.teamlead.kubepay.user.api.domain.exception.UserNotFoundException;


@Slf4j
@Service
@RequiredArgsConstructor
@Observed
public class UserApplicationService {

    private final UserService userService;

    public UserInfo getUserInfo(@NotNull UserPrincipal principal) {
        return getUserInfo(principal.getUser());
    }

    public UserInfo getUserInfo(@NotNull String id) {
        var user = userService.getUser(id)
                .orElseThrow(UserNotFoundException::new);

        return new UserInfo(user.getUser(), user.getName(), user.getEnabled());
    }

    public UserInfo createUser(@NotNull CreateUserRequest request) {
        var faker = new Faker();
        var fakeName = faker.name().fullName();

        User user = userService.createUser(request.user(),
                                           request.passwordHash(),
                                           fakeName);

        return new UserInfo(user.getUser(), user.getName(), user.getEnabled());
    }

    public String getPasswordHash(@NotNull String id) {

        var user = userService.getUser(id)
                .orElseThrow(UserNotFoundException::new);

        return user.getPasswordHash();
    }
}
