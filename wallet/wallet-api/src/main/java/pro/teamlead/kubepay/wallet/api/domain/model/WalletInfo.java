package pro.teamlead.kubepay.wallet.api.domain.model;

import java.math.BigDecimal;

public record WalletInfo(String address, BigDecimal balance) {

}
