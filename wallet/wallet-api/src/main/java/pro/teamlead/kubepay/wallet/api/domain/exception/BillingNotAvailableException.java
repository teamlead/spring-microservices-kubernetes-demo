package pro.teamlead.kubepay.wallet.api.domain.exception;

public class BillingNotAvailableException extends WalletApiException {

    public BillingNotAvailableException() {
        super("Billing not available");
    }

    public static final String TYPE = "BILLING_NOT_AVAILABLE";

    @Override
    public String getType() {
        return TYPE;
    }
}
