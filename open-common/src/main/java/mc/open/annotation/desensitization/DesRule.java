package mc.open.annotation.desensitization;


import mc.open.constant.DesensitizationRule;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author macheng
 * @date 2021/8/16 08:04
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DesRule {
    DesensitizationRule desRuleType();
    String mask() default "*";
}
