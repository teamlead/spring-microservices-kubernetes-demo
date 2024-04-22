package pro.teamlead.kubepay.wallet;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import pro.teamlead.kubepay.common.testing.integration.test.ApiControllerIntegrationTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class SwaggerApiDocsTest extends ApiControllerIntegrationTest {

    @Value("${springdoc.api-docs.path}")
    private String restApiDocPath;

    @Test
    void whenSwaggerDocsRequested_thenReturnSuccess() throws Exception {

        mockMvc.perform(get(restApiDocPath))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}
