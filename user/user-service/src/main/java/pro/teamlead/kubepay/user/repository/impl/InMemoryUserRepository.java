package pro.teamlead.kubepay.user.repository.impl;

import pro.teamlead.kubepay.user.domain.model.User;
import pro.teamlead.kubepay.user.repository.UserRepository;
import io.micrometer.observation.annotation.Observed;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This repository implementation is intended solely for testing purposes.
 * It is NOT production-ready and should not be used in a live environment.
 * Ensure that a production-ready UserRepository implementation is used in actual deployments.
 */
@Repository
@Observed
@ConditionalOnMissingBean(DataSource.class)
public class InMemoryUserRepository implements UserRepository {

    private final Map<String, User> users = new ConcurrentHashMap<>();

    public User findByUser(@NotNull String user) {
        return users.get(user);
    }

    public User save(@NotNull User user) {
        users.put(user.getUser(), user);
        return user;
    }
}
