package pro.teamlead.kubepay.auth.api.domain.model;

import pro.teamlead.kubepay.common.api.domain.exception.validation.ValidUserField;
import jakarta.validation.constraints.NotBlank;

public record AuthRequest(@ValidUserField String user,
                          @NotBlank String password) {

}
