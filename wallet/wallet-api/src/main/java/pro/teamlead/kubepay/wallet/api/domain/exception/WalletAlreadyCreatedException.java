package pro.teamlead.kubepay.wallet.api.domain.exception;

public class WalletAlreadyCreatedException extends WalletApiException {

    public WalletAlreadyCreatedException() {
        super("Wallet already created");
    }

    public static final String TYPE = "WALLET_ALREADY_CREATED";

    @Override
    public String getType() {
        return TYPE;
    }
}
