package cn.edu.dlut.career.thymeleaf;

import org.apache.shiro.SecurityUtils;
import org.thymeleaf.util.Validate;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

/**
 * Shiro的表达式
 */
public final class ShiroExpression {
    public ShiroExpression(){}

    /**
     * 判断当前用户是否已经登录认证
     * @return
     */
    public boolean isAuthenticated() {
        return SecurityUtils.getSubject() != null && SecurityUtils.getSubject().isAuthenticated();
    }

    /**
     * 判断当前用户是否未登录认证
     * @return
     */
    public boolean isNotAuthenticated() {
        return SecurityUtils.getSubject() == null || !SecurityUtils.getSubject().isAuthenticated();
    }

    /**
     * 判断当前用户是否是可以识别的用户
     * @return
     */
    public boolean isUser() {
        return SecurityUtils.getSubject() != null && SecurityUtils.getSubject().getPrincipal() != null;
    }

    /**
     * 判断当前用户是否是匿名用户
     * @return
     */
    public boolean isGuest() {
        return SecurityUtils.getSubject() == null || SecurityUtils.getSubject().getPrincipal() == null;
    }

    /**
     * 判断当前用户是否拥有某种权限
     * @param p
     * @return
     */
    public boolean hasPermission(final String p) {
        return SecurityUtils.getSubject() != null && SecurityUtils.getSubject().isPermitted(p);
    }

    /**
     * 判断当前用户是否缺少某种权限
     * @param p
     * @return
     */
    public boolean lacksPermission(final String p) {
        return SecurityUtils.getSubject() == null || !SecurityUtils.getSubject().isPermitted(p);
    }

    /**
     * 判断当前用户是否拥有某种角色
     * @param role
     * @return
     */
    public boolean hasRole(final String role) {
        return SecurityUtils.getSubject() != null && SecurityUtils.getSubject().hasRole(role);
    }

    /**
     * 判断当前用户是否不拥有某种角色
     * @param role
     * @return
     */
    public boolean lacksRole(final String role) {
        return SecurityUtils.getSubject() == null || !SecurityUtils.getSubject().hasRole(role);
    }

    /**
     * 判断当前用户是否拥有其中任一种角色
     * @param roles
     * @return
     */
    public boolean hasAnyRoles(final  String... roles) {
        if (SecurityUtils.getSubject() != null) {
            for (final String role :roles) {
                if (SecurityUtils.getSubject().hasRole(role)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 返回当前用户基本信息
     * @return
     */
    public String principal(){
        return getPrincipalText(null,null);
    }

    /**
     * 返回当前用户基本信息中指定的属性值
     * @param property
     * @return
     */
    public String principal(String property) {
        return getPrincipalProperty(property);
    }

    /**
     * 返回当前用户基本信息中指定数据类型的某属性值
     * @param type
     * @param property
     * @return
     */
    public String principal(String type, String property) {
        return getPrincipalProperty(type, property);
    }

    private static String getPrincipalText(final String type, final String property) {
        if (SecurityUtils.getSubject() == null) return "";

        Object principal = SecurityUtils.getSubject().getPrincipal();

        if (type != null || property != null) {
            if (type != null) principal = getPrincipalFromClassName(type);
        }
        if (principal != null) {
            return (property == null) ? principal.toString() : "";
        }

        return principal != null ? principal.toString() : "";
    }

    private static Object getPrincipalFromClassName(final String type) {
        Object principal = null;

        try {
            final Class<?> cls = Class.forName(type);
            principal = SecurityUtils.getSubject().getPrincipals().oneByType(cls);
        } catch (final ClassNotFoundException e) {
            Validate.isTrue(false, "Unable to find class for name [" + type + "]");
        }
        return principal;
    }

    private static String getPrincipalProperty(final Object principal, final String property) {
        try {
            final BeanInfo beanInfo = Introspector.getBeanInfo(principal.getClass());
            for (final PropertyDescriptor pd : beanInfo.getPropertyDescriptors()) {
                if (pd.getName().equals(property)) {
                    final Object value = pd.getReadMethod().invoke(principal, (Object[]) null);
                    return String.valueOf(value);
                }
            }
        } catch (final Exception e) {
            Validate.isTrue(false, "Error reading property [" + property
                + "] from principal of type [" + principal.getClass().getName() + "]");
        }

        Validate.isTrue(false, "Property ["+ property +"] not found in principal of type ["
            + principal.getClass().getName() +"]");
        return "";
    }

    private static String getPrincipalProperty(final String property) {
        return getPrincipalProperty(SecurityUtils.getSubject().getPrincipal(), property);
    }
}
