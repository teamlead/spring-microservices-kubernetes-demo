package pro.teamlead.kubepay.user;

import com.github.tomakehurst.wiremock.client.WireMock;
import pro.teamlead.kubepay.auth.api.domain.model.AuthToken;
import pro.teamlead.kubepay.auth.client.AuthClient;
import pro.teamlead.kubepay.auth.sdk.authority.Authority;
import pro.teamlead.kubepay.common.testing.integration.test.ApiControllerIntegrationTest;
import pro.teamlead.kubepay.common.testing.integration.testcase.TestCase;
import pro.teamlead.kubepay.user.domain.model.User;
import pro.teamlead.kubepay.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static pro.teamlead.kubepay.user.api.UserApiMethodDictionary.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserServiceApiControllerTest extends ApiControllerIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @MockBean
    private AuthClient authClient;

    @TestCase({
            "integration/service-api/create-user/success/request.json",
            "integration/service-api/create-user/success/response.json"
    })
    public void whenCreatingUser_thenReturnsSuccess(String request, String response) throws Exception {

        when(authClient.serviceToken(any())).thenReturn(new AuthToken("noop"));

        wireMockServer.stubFor(WireMock.any(WireMock.anyUrl())
                .willReturn(WireMock.ok()));

        var token = customToken(Authority.ROLE_SERVICE, Authority.AUTHORITY_USER_CREATE);

        mockMvc.perform(post(CREATE_USER)
                        .header(HttpHeaders.AUTHORIZATION, token)
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().json(response));
    }

    @TestCase({
            "integration/service-api/create-user/error/request.json"
    })
    public void whenCreatingUserWithoutPermission_thenReturnsForbidden(String request) throws Exception {

        mockMvc.perform(post(CREATE_USER)
                        .header(HttpHeaders.AUTHORIZATION, customToken(Authority.ROLE_SERVICE))
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());

        mockMvc.perform(post(CREATE_USER)
                        .header(HttpHeaders.AUTHORIZATION, customToken(Authority.ROLE_USER))
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @TestCase({
            "integration/service-api/get-user-info/success/user.json",
            "integration/service-api/get-user-info/success/response.json"
    })
    public void whenRequestingUserInfo_thenReturnsUserInfoSuccessfully(User user,
                                                                       String response) throws Exception {

        var token = customToken(Authority.ROLE_SERVICE, Authority.AUTHORITY_INTROSPECT_USER);

        userRepository.save(user);

        mockMvc.perform(get(GET_USER_INFO.replace("{user}", user.getUser()))
                        .header(HttpHeaders.AUTHORIZATION, token))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().json(response));
    }

    @TestCase({
            "integration/service-api/get-user-info/error/user.json"
    })
    public void whenRequestingUserInfoWithoutPermission_thenReturnsForbidden(User user) throws Exception {

        mockMvc.perform(get(GET_USER_INFO.replace("{user}", user.getUser()))
                        .header(HttpHeaders.AUTHORIZATION, customToken(Authority.ROLE_SERVICE)))
                .andExpect(status().isForbidden());

        mockMvc.perform(get(GET_USER_INFO.replace("{user}", user.getUser()))
                        .header(HttpHeaders.AUTHORIZATION, customToken(Authority.ROLE_USER)))
                .andExpect(status().isForbidden());
    }


    @TestCase({
            "integration/service-api/get-password-hash/success/user.json"
    })
    public void whenFetchingPasswordHash_thenReturnsHashSuccessfully(User user) throws Exception {

        var token = customToken(Authority.ROLE_SERVICE, Authority.AUTHORITY_VIEW_USER_PASSWORD);

        userRepository.save(user);

        mockMvc.perform(get(GET_PASSWORD_HASH.replace("{user}", user.getUser()))
                        .header(HttpHeaders.AUTHORIZATION, token))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().string(user.getPasswordHash()));
    }

    @TestCase({
            "integration/service-api/get-password-hash/error/user.json"
    })
    public void whenFetchingPasswordHashWithUnauthorizedRoles_thenReturnsForbidden(User user) throws Exception {

        mockMvc.perform(get(GET_PASSWORD_HASH.replace("{user}", user.getUser()))
                        .header(HttpHeaders.AUTHORIZATION, customToken(Authority.ROLE_SERVICE)))
                .andExpect(status().isForbidden());

        mockMvc.perform(get(GET_PASSWORD_HASH.replace("{user}", user.getUser()))
                        .header(HttpHeaders.AUTHORIZATION, customToken(Authority.ROLE_USER)))
                .andExpect(status().isForbidden());
    }
}
