package pro.teamlead.kubepay.common.testing.integration.testcase;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.*;

@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Test
@ExtendWith(FileSourceProvider.class)
public @interface TestCase {

    String[] value() default {};
}
