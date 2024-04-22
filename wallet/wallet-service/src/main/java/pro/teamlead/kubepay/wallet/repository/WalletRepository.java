package pro.teamlead.kubepay.wallet.repository;

import pro.teamlead.kubepay.wallet.domain.model.Wallet;

public interface WalletRepository {

    Wallet findByAddress(String address);

    Wallet findByUser(String user);

    Wallet save(Wallet wallet);
}
