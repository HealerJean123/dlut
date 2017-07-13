package cn.edu.dlut.career.thymeleaf;

import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.dialect.IExpressionObjectDialect;
import org.thymeleaf.expression.IExpressionObjectFactory;

/**
 * 公共代码表达式处理的Thymeleaf方言
 */
public class PubCodeDialect extends AbstractDialect implements IExpressionObjectDialect {
    public static final String NAME = "PubCode Dialect";
    private final IExpressionObjectFactory PUBCODE_EXPRESSION_OBJECT_FACTORY = new PubCodeExpressionFactory();

    public PubCodeDialect(){
        super(NAME);
    }

    @Override
    public IExpressionObjectFactory getExpressionObjectFactory(){
        return PUBCODE_EXPRESSION_OBJECT_FACTORY;
    }
}
