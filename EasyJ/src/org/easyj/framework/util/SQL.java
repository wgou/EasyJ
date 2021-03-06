package org.easyj.framework.util;

import java.io.Serializable;
import java.util.List;

public class SQL {
	private String sql;//拼接之后带？的sql
	private List<String> cloumnName;  //列名集合
	private List<Object> paramsValue;//参数集合
	private String tableName;//表名
	private Serializable primaryKeyValue;//主键列值
	private String primaryKey;//主键
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public List<String> getCloumnName() {
		return cloumnName;
	}
	public void setCloumnName(List<String> cloumnName) {
		this.cloumnName = cloumnName;
	}
	public List<Object> getParamsValue() {
		return paramsValue;
	}
	public void setParamsValue(List<Object> paramsValue) {
		this.paramsValue = paramsValue;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public Serializable getPrimaryKeyValue() {
		return primaryKeyValue;
	}
	public void setPrimaryKeyValue(Serializable primaryKeyValue) {
		this.primaryKeyValue = primaryKeyValue;
	}
	public String getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}
	
}
