package pro.teamlead.kubepay.wallet.api.domain.exception;

public class WalletNotFoundException extends WalletApiException {

    public WalletNotFoundException() {
        super("Wallet not found");
    }

    public static final String TYPE = "WALLET_NOT_FOUND";

    @Override
    public String getType() {
        return TYPE;
    }
}
