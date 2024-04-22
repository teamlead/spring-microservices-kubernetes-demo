package pro.teamlead.kubepay.user.api.domain.model;

import pro.teamlead.kubepay.common.api.domain.exception.validation.ValidUserField;
import jakarta.validation.constraints.NotBlank;


public record CreateUserRequest(@ValidUserField String user,
                                @NotBlank String passwordHash) {

}
