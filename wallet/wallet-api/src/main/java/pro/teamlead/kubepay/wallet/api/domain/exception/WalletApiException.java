package pro.teamlead.kubepay.wallet.api.domain.exception;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import pro.teamlead.kubepay.common.api.domain.exception.ApiException;

import java.io.Serializable;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type",
        include = JsonTypeInfo.As.EXISTING_PROPERTY
)
@JsonSubTypes({
        @JsonSubTypes.Type(
                name = WalletAlreadyCreatedException.TYPE,
                value = WalletAlreadyCreatedException.class
        ),
        @JsonSubTypes.Type(
                name = BillingNotAvailableException.TYPE,
                value = BillingNotAvailableException.class
        ),
        @JsonSubTypes.Type(
                name = WalletNotFoundException.TYPE,
                value = WalletNotFoundException.class
        ),
        @JsonSubTypes.Type(
                name = InvalidRecipientException.TYPE,
                value = InvalidRecipientException.class
        ),
        @JsonSubTypes.Type(
                name = InsufficientFundsException.TYPE,
                value = InsufficientFundsException.class
        ),
})
public abstract class WalletApiException extends ApiException implements Serializable {

    protected WalletApiException(String message) {
        super(message);
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
    public abstract String getType();

}
