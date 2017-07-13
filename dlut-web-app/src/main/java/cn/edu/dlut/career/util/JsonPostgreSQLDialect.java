package cn.edu.dlut.career.util;

import org.hibernate.dialect.PostgreSQL9Dialect;

import java.sql.Types;

/**
 * 数据库postgresql，用于支持jsonb的字段
 * HealerJean 4/7
 */

public class JsonPostgreSQLDialect extends PostgreSQL9Dialect {


  public JsonPostgreSQLDialect() {
    super();
    this.registerColumnType(Types.JAVA_OBJECT, "jsonb");
    this.registerHibernateType(Types.OTHER,
        "cn.edu.dlut.career.domain.company.StringJsonUserType");
  }
}
