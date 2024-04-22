package pro.teamlead.kubepay.wallet.api.domain.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateWalletRequest(@NotBlank String user,
                                  @NotNull @Min(0) BigDecimal initialBalance) {

}
