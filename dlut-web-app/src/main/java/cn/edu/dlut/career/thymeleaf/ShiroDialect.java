package cn.edu.dlut.career.thymeleaf;

import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.dialect.IExpressionObjectDialect;
import org.thymeleaf.expression.IExpressionObjectFactory;

/**
 * Shiro 表达式处理的Thymeleaf方言
 */
public class ShiroDialect extends AbstractDialect implements IExpressionObjectDialect {
    public static final String NAME = "Shiro Dialect";
    private final IExpressionObjectFactory SHIRO_EXPRESSION_OBJECT_FACTORY = new ShiroExpressionFactory();

    public ShiroDialect(){
        super(NAME);
    }

    @Override
    public IExpressionObjectFactory getExpressionObjectFactory(){
        return SHIRO_EXPRESSION_OBJECT_FACTORY;
    }
}
