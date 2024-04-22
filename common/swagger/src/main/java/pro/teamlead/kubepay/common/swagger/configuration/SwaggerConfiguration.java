package pro.teamlead.kubepay.common.swagger.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConditionalOnWebApplication
@ConditionalOnClass(OpenAPI.class)
public class SwaggerConfiguration {

    @Value("${spring.application.name}")
    private String serviceName;

    @Value("${spring.application.description}")
    private String title;

    @Value("${server.standalone}")
    private boolean standalone;

    @Bean
    @ConditionalOnMissingBean
    public OpenAPI openAPI(@Autowired(required = false) BuildProperties buildProperties,
                           @Autowired(required = false) GitProperties gitProperties) {

        var version = buildProperties != null ?
                buildProperties.getVersion() : "?";

        var git = gitProperties != null ?
                String.format("Git: %s (%s) %s",
                        gitProperties.getShortCommitId(),
                        gitProperties.getBranch(),
                        gitProperties.get("commit.message.full")) : "No Git info";

        var description = String.format("""
                        %s Documentation<br>
                        Version: %s<br>
                        %s<br>
                        Standalone: %s
                        """,
                title,
                version,
                git,
                standalone
        );

        var securitySchemeName = "bearerAuth";
        var server = new Server();

        if (standalone) {
            server.setUrl("/");
        } else {
            server.setUrl("/" + serviceName);
        }

        return new OpenAPI()
                .servers(List.of(server))
                .info((new Info())
                        .title(title)
                        .description(description)
                        .version(version))
                .addSecurityItem(new SecurityRequirement()
                        .addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));
    }

}
