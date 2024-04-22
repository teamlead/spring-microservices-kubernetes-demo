package pro.teamlead.kubepay.auth;

import com.github.tomakehurst.wiremock.client.WireMock;
import pro.teamlead.kubepay.auth.component.ServiceTokenProvider;
import pro.teamlead.kubepay.auth.domain.model.JwtToken;
import pro.teamlead.kubepay.auth.sdk.authority.Authority;
import pro.teamlead.kubepay.common.testing.integration.test.ApiControllerIntegrationTest;
import pro.teamlead.kubepay.common.testing.integration.testcase.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static pro.teamlead.kubepay.auth.api.AuthApiMethodDictionary.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class UnprotectedApiControllerTest extends ApiControllerIntegrationTest {

    @MockBean
    protected ServiceTokenProvider serviceTokenProvider;

    @BeforeEach
    protected void beforeEach() {
        when(serviceTokenProvider.createSelfServiceToken())
                .thenReturn(new JwtToken("noop"));

        when(serviceTokenProvider.getServiceToken(any()))
                .thenReturn(new JwtToken("noop"));

        when(provider.createToken(anyString(), anyInt(), any(Authority[].class)))
                .thenReturn("noop");
    }

    @TestCase({
            "integration/unprotected-api/signup/success/mock.json",
            "integration/unprotected-api/signup/success/request.json",
            "integration/unprotected-api/signup/success/response.json",
    })
    public void whenSignupRequested_thenAuthTokenReturns(String mock,
                                                         String request,
                                                         String response) throws Exception {

        //user:
        //  url: ${wire-mock.url}
        // check if user already exists
        wireMockServer.stubFor(WireMock.get(WireMock.anyUrl())
                .willReturn(WireMock.badRequest()
                        .withBody(mock)
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)));

        // create user in user-service
        wireMockServer.stubFor(WireMock.post(WireMock.anyUrl())
                .willReturn(WireMock.ok()));

        mockMvc.perform(post(AUTH_SIGNUP)
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().json(response));
    }

    @TestCase({
            "integration/unprotected-api/login/success/mock.json",
            "integration/unprotected-api/login/success/request.json",
            "integration/unprotected-api/login/success/response.json",
    })
    public void whenLoginRequested_thenAuthTokenReturns(String mock,
                                                        String request,
                                                        String response) throws Exception {
        //user:
        //  url: ${wire-mock.url}
        wireMockServer.stubFor(WireMock.get(WireMock.anyUrl())
                .willReturn(WireMock.ok()
                        .withBody(mock)));

        mockMvc.perform(post(AUTH_LOGIN)
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().json(response));
    }

    @TestCase({
            "integration/unprotected-api/login/error/mock.json",
            "integration/unprotected-api/login/error/request.json",
            "integration/unprotected-api/login/error/response.json",
    })
    public void whenLoginRequestedWithInvalidPassword_thenThrowsException(String mock,
                                                                          String request,
                                                                          String response) throws Exception {
        //user:
        //  url: ${wire-mock.url}
        // get user password hash
        wireMockServer.stubFor(WireMock.get(WireMock.anyUrl())
                .willReturn(WireMock.ok()
                        .withBody(mock)));

        mockMvc.perform(post(AUTH_LOGIN)
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().json(response));
    }

    @TestCase({
            "integration/unprotected-api/service-token/success/response.json",
    })
    public void whenServiceTokenRequested_thenServiceTokenReturns(String response) throws Exception {

        mockMvc.perform(get(SERVICE_TOKEN.replace("{key}", "test")))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().json(response));
    }
}
