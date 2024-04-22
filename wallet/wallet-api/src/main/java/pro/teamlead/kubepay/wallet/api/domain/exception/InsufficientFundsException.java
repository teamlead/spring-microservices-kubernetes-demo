package pro.teamlead.kubepay.wallet.api.domain.exception;

public class InsufficientFundsException extends WalletApiException {

    public InsufficientFundsException() {
        super("Insufficient funds");
    }

    public static final String TYPE = "WALLET_INSUFFICIENT_FUNDS";

    @Override
    public String getType() {
        return TYPE;
    }
}
