package mc.open.aspect;


import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.Collection;

import mc.open.annotation.desensitization.DesRule;
import mc.open.annotation.desensitization.Desensitize;
import mc.open.annotation.desensitization.SubDes;
import mc.open.utils.SensitiveInfoUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author macheng
 * @date 2021/8/16 08:04
 */
@Aspect
@Component
@Slf4j
public class DesensitizeAspect {
    @Pointcut(value = "@annotation(com.huida.platform.common.annotation.desensitization.Desensitize)")
    public void cutMethod() {

    }

    @Around("cutMethod() && @annotation(desensitize)")
    public Object around(ProceedingJoinPoint joinPoint, Desensitize desensitize) throws Throwable {
        Object object = joinPoint.proceed(joinPoint.getArgs());
        if (object instanceof Collection) {
            //集合类型脱敏
            if (((Collection<?>) object).isEmpty()){
                return object;
            }
            ((Collection<?>) object).forEach(x -> {
                try {
                    processFields(x);
                } catch (Exception e) {
                    log.error("脱敏失败");
                }
            });
        } else {
            //非集合类型脱敏
            if (object == null) {
                return null;
            }
            processFields(object);
        }

        return object;
    }


    private void process(Object object, Field field) throws IllegalAccessException {

        //脱敏字段处理
        DesRule desRule = field.getAnnotation(DesRule.class);
        if (desRule != null) {
            setValue(object, field, desRule);
        }
        //下级属性脱敏
        SubDes subDes = field.getAnnotation(SubDes.class);
        if (subDes != null ) {
            if (field.get(object) instanceof Collection) {
                Object[] collection = ((Collection<?>) field.get(object)).toArray();
                for (Object x : collection) {
                    processFields(x);
                }
            } else {
                processFields(field.get(object));
            }

        }
    }


    private void processFields(Object object) throws IllegalAccessException {
        Field[] declaredFields = object.getClass().getDeclaredFields();
        for (Field deField : declaredFields) {
            deField.setAccessible(true);
            if (deField.get(object) != null) {
                process(object, deField);
            }
        }
    }

    private void setValue(Object object, Field field, DesRule desRule) throws IllegalAccessException {
        String str;
        String result = "";
        str = (String) field.get(object);
        switch (desRule.desRuleType()) {
            case ID:
                result = SensitiveInfoUtils.idNo(str, desRule.mask());
                break;
            case EMAIL:
                result = SensitiveInfoUtils.email(str, desRule.mask());
                break;
            case ADRRESS:
                result = SensitiveInfoUtils.address(str, desRule.mask());
                break;
            case MOBILE:
                result = SensitiveInfoUtils.mobilePhone(str, desRule.mask());
                break;
            default:
                break;
        }
        field.set(object, result);
    }


}
