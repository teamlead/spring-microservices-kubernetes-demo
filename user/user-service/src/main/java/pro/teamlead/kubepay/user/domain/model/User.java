package pro.teamlead.kubepay.user.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
public class User {

    @SuppressWarnings("squid:S1700")
    private final String user;
    private final String name;
    private volatile Boolean enabled;
    private final String passwordHash;

    @JsonCreator
    public User(@NotNull @JsonProperty("user") String user,
                @NotNull @JsonProperty("name") String name,
                @NotNull @JsonProperty("enabled") Boolean enabled,
                @NotNull @JsonProperty("passwordHash") String passwordHash) {
        this.user = user;
        this.name = name;
        this.enabled = enabled;
        this.passwordHash = passwordHash;
    }
}
