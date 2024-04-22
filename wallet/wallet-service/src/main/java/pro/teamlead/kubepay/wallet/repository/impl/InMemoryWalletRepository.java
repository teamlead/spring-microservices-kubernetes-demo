package pro.teamlead.kubepay.wallet.repository.impl;

import pro.teamlead.kubepay.wallet.domain.model.Wallet;
import pro.teamlead.kubepay.wallet.repository.WalletRepository;
import io.micrometer.observation.annotation.Observed;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This repository implementation is intended solely for testing purposes.
 * It is NOT production-ready and should not be used in a live environment.
 * Ensure that a production-ready WalletRepository implementation is used in actual deployments.
 */
@Repository
@Observed
@ConditionalOnMissingBean(DataSource.class)
public class InMemoryWalletRepository implements WalletRepository {

    private final Map<String, Wallet> wallets = new ConcurrentHashMap<>();

    public Wallet findByAddress(@NotNull String address) {
        for (Wallet wallet : wallets.values()) {
            if (wallet.getAddress().equals(address)) {
                return wallet;
            }
        }
        return null;
    }

    public Wallet findByUser(@NotNull String user) {
        return wallets.get(user);
    }

    public Wallet save(@NotNull Wallet wallet) {
        wallets.put(wallet.getUser(), wallet);
        return wallet;
    }
}
