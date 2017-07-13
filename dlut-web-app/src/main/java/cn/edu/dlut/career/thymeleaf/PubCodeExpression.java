package cn.edu.dlut.career.thymeleaf;

import cn.edu.dlut.career.util.PubCodeUtil;

/**
 * 公共代码处理的表达式
 */
public final class PubCodeExpression {
    public PubCodeExpression(){}

    /**
     * 根据类型和代码获取该公共代码的值
     * @param type 类型
     * @param code 代码
     * @return
     */
    public String codeName(String type, String code) {
        return PubCodeUtil.getName(type, code);
    }
}
