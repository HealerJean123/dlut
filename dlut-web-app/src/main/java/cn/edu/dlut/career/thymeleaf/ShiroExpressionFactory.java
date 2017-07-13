package cn.edu.dlut.career.thymeleaf;

import org.thymeleaf.context.IExpressionContext;
import org.thymeleaf.expression.IExpressionObjectFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Shiro表达式的工厂类
 */
public class ShiroExpressionFactory implements IExpressionObjectFactory {
    private static final String SHIRO_VARIABLE_NAME = "shiro";
    private static final Set<String> ALL_EXPRESSION_OBJECT_NAMES = Collections.unmodifiableSet(
        new LinkedHashSet<>(Arrays.asList(SHIRO_VARIABLE_NAME)));

    @Override
    public Set<String> getAllExpressionObjectNames(){
        return ALL_EXPRESSION_OBJECT_NAMES;
    }

    @Override
    public Object buildObject(IExpressionContext context, final String expressionObjectName) {
        if (SHIRO_VARIABLE_NAME.equalsIgnoreCase(expressionObjectName)) {
            return new ShiroExpression();
        }
        return null;
    }

    @Override
    public boolean isCacheable(String expressionObjectName) {
        return expressionObjectName != null && SHIRO_VARIABLE_NAME.equalsIgnoreCase(expressionObjectName);
    }
}
