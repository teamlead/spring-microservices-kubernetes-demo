package pro.teamlead.kubepay.common.testing.integration.testcase;

import com.fasterxml.jackson.databind.ObjectMapper;
import pro.teamlead.kubepay.common.json.ObjectMapperBuilder;
import org.junit.jupiter.api.extension.*;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class FileSourceProvider implements ParameterResolver, BeforeEachCallback {

    @SuppressWarnings("squid:S3077")
    private volatile String[] paths;

    private final ObjectMapper objectMapper = ObjectMapperBuilder.build();

    @Override
    public void beforeEach(ExtensionContext context) {
        context.getTestMethod().ifPresent(method -> {
            TestCase testParams = method.getAnnotation(TestCase.class);
            if (testParams != null) {
                paths = testParams.value();
            }
        });
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext,
                                     ExtensionContext extensionContext) {
        return paths != null && parameterContext.getIndex() < paths.length;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext,
                                   ExtensionContext extensionContext) {

        try {
            String jsonResource = getJsonResource(paths[parameterContext.getIndex()]);
            Class<?> type = parameterContext.getParameter().getType();
            if (type == String.class) {
                return jsonResource;
            } else {
                return objectMapper.readValue(jsonResource, type);
            }
        } catch (IOException e) {
            throw new ParameterResolutionException("Failed to read file", e);
        }
    }

    private String getJsonResource(String file) throws IOException {
        String classpathName = String.format("classpath:%s", file);
        Path path = ResourceUtils.getFile(classpathName).toPath();
        String result = new String(Files.readAllBytes(path));
        return result.trim();
    }
}
