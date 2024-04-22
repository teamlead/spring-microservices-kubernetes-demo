package pro.teamlead.kubepay.auth.api.domain.model;
import jakarta.validation.constraints.NotBlank;

public record AuthToken(@NotBlank String token) {

}
