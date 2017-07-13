package cn.edu.dlut.career.thymeleaf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.context.IExpressionContext;
import org.thymeleaf.expression.IExpressionObjectFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 公共代码表达式创建的工厂类
 */
public class PubCodeExpressionFactory implements IExpressionObjectFactory {
    private static final String PUBCODE_VARIABLE_NAME = "pubcode";
    private static final Set<String> ALL_EXPRESSION_OBJECT_NAMES = Collections.unmodifiableSet(
        new LinkedHashSet<>(Arrays.asList(PUBCODE_VARIABLE_NAME)));

    @Override
    public Set<String> getAllExpressionObjectNames(){
        return ALL_EXPRESSION_OBJECT_NAMES;
    }

    @Override
    public Object buildObject(IExpressionContext context, final String expressionObjectName) {
        if (PUBCODE_VARIABLE_NAME.equalsIgnoreCase(expressionObjectName)) {
            return new PubCodeExpression();
        }
        return null;
    }

    @Override
    public boolean isCacheable(String expressionObjectName) {
        return expressionObjectName != null && PUBCODE_VARIABLE_NAME.equalsIgnoreCase(expressionObjectName);
    }
}
