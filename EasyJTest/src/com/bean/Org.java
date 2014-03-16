package com.bean;

import org.easyj.framework.core.annotation.Column;
import org.easyj.framework.core.annotation.Table;

@Table(name="ORG")
public class Org {
	private int id;
    private String name;
    private int orderNum;
    private int pid;
    @Column(name="id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="order_num")
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	@Column(name="pid")
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
    
}
