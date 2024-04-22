package pro.teamlead.kubepay.wallet;

import com.github.tomakehurst.wiremock.client.WireMock;
import pro.teamlead.kubepay.auth.api.domain.model.AuthToken;
import pro.teamlead.kubepay.auth.client.AuthClient;
import pro.teamlead.kubepay.common.testing.integration.test.ApiControllerIntegrationTest;
import pro.teamlead.kubepay.common.testing.integration.testcase.TestCase;
import pro.teamlead.kubepay.wallet.domain.model.Wallet;
import pro.teamlead.kubepay.wallet.repository.WalletRepository;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

import static pro.teamlead.kubepay.wallet.api.WalletApiMethodDictionary.WALLET_TOPUP;
import static pro.teamlead.kubepay.wallet.api.WalletApiMethodDictionary.WALLET_TRANSFER_FUNDS;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class WalletPublicApiControllerTest extends ApiControllerIntegrationTest {

    @Autowired
    private WalletRepository walletRepository;

    @MockBean
    private AuthClient authClient;

    @TestCase({
            "integration/public-api/top-up/success/wallet.json",
            "integration/public-api/top-up/success/response.json"
    })
    public void whenTopUpRequested_thenWalletBalanceIncreases(Wallet wallet,
                                                              String response) throws Exception {

        //billing:
        //  url: ${wire-mock.url}
        wireMockServer.stubFor(WireMock.get(WireMock.anyUrl())
                .willReturn(WireMock.ok()));

        walletRepository.save(wallet);

        Assertions.assertEquals(BigDecimal.ZERO, wallet.getBalance());

        mockMvc.perform(get(WALLET_TOPUP)
                        .header(HttpHeaders.AUTHORIZATION, userToken()))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().json(response));

        Assertions.assertEquals(new BigDecimal(100), wallet.getBalance());
    }


    @TestCase({
            "integration/public-api/transfer-funds/success/wallet1.json",
            "integration/public-api/transfer-funds/success/wallet2.json",
            "integration/public-api/transfer-funds/success/mock.json",
            "integration/public-api/transfer-funds/success/request.json",
            "integration/public-api/transfer-funds/success/response.json",
            "integration/public-api/transfer-funds/success/amount.json",
    })
    public void whenTransferFundsRequested_thenWalletBalanceChanges(Wallet senderWallet,
                                                                    Wallet recipientWallet,
                                                                    String mock,
                                                                    String request,
                                                                    String response,
                                                                    BigDecimal amount) throws Exception {

        when(authClient.serviceToken(any())).thenReturn(new AuthToken("noop"));

        //user:
        //  url: ${wire-mock.url}
        wireMockServer.stubFor(WireMock.get(WireMock.anyUrl())
                .willReturn(WireMock.ok()
                        .withBody(mock)
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)));

        Assertions.assertEquals(amount, senderWallet.getBalance());
        Assertions.assertEquals(BigDecimal.ZERO, recipientWallet.getBalance());

        walletRepository.save(senderWallet);
        walletRepository.save(recipientWallet);

        mockMvc.perform(post(WALLET_TRANSFER_FUNDS)
                        .header(HttpHeaders.AUTHORIZATION, userToken())
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().json(response));

        Assertions.assertEquals(BigDecimal.ZERO, senderWallet.getBalance());
        Assertions.assertEquals(amount, recipientWallet.getBalance());
    }

}
