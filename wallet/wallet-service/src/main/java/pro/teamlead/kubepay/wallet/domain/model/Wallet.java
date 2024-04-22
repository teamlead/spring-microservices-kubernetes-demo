package pro.teamlead.kubepay.wallet.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import pro.teamlead.kubepay.wallet.api.domain.exception.InsufficientFundsException;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

@Getter
public class Wallet {

    private final String address;
    private volatile BigDecimal balance;
    private final String user;

    @JsonCreator
    public Wallet(@NotNull @JsonProperty("address") String address,
                  @NotNull @JsonProperty("balance") BigDecimal balance,
                  @NotNull @JsonProperty("user") String user) {
        this.address = address;
        this.balance = balance;
        this.user = user;
    }

    public synchronized void topUp(@NotNull BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }

    public synchronized void transferFunds(@NotNull Wallet toWallet,
                              @NotNull BigDecimal amount) {
        if (this.balance.compareTo(amount) < 0) {
            throw new InsufficientFundsException();
        }
        this.balance = this.balance.subtract(amount);
        toWallet.topUp(amount);
    }
}
