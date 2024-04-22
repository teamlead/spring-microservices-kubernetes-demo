package pro.teamlead.kubepay.wallet.api.domain.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TransferFundsRequest(@NotEmpty String to,
                                   @Min(0) @NotNull BigDecimal amount) {

}
