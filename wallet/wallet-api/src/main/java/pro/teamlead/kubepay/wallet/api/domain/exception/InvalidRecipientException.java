package pro.teamlead.kubepay.wallet.api.domain.exception;

public class InvalidRecipientException extends WalletApiException {

    public InvalidRecipientException() {
        super("Invalid recipient");
    }

    public static final String TYPE = "WALLET_INVALID_RECIPIENT";

    @Override
    public String getType() {
        return TYPE;
    }
}
