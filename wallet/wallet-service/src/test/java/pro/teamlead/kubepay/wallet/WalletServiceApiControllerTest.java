package pro.teamlead.kubepay.wallet;

import pro.teamlead.kubepay.auth.sdk.authority.Authority;
import pro.teamlead.kubepay.common.testing.integration.test.ApiControllerIntegrationTest;
import pro.teamlead.kubepay.common.testing.integration.testcase.TestCase;
import pro.teamlead.kubepay.common.util.RandomStringUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static pro.teamlead.kubepay.wallet.api.WalletApiMethodDictionary.WALLET_CREATE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class WalletServiceApiControllerTest extends ApiControllerIntegrationTest {

    @TestCase({
            "integration/service-api/create-wallet/success/request.json",
            "integration/service-api/create-wallet/success/response.json",
    })
    public void whenRequestingWalletCreation_thenReturnSuccess(String request, String response) throws Exception {

        RandomStringUtil.setMock("random");

        var token = customToken(Authority.ROLE_SERVICE, Authority.AUTHORITY_WALLET_CREATE);

        mockMvc.perform(post(WALLET_CREATE)
                        .header(HttpHeaders.AUTHORIZATION, token)
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().json(response));
    }

    @TestCase({
            "integration/service-api/create-wallet/error/request.json",
    })
    public void whenRequestingWalletCreation_withoutProperAuthority_thenThrowForbidden(String request) throws Exception {

        mockMvc.perform(post(WALLET_CREATE)
                        .header(HttpHeaders.AUTHORIZATION, customToken(Authority.ROLE_SERVICE))
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());

        mockMvc.perform(post(WALLET_CREATE)
                        .header(HttpHeaders.AUTHORIZATION, customToken(Authority.ROLE_USER))
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

}
