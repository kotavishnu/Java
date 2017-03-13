package com.example;


public class Order implements OrderMXBean{
	Integer orderId;
	String type;
	String plan;
	String customer;
	
	public Order() {
		super();
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPlan() {
		return plan;
	}
	public void setPlan(String plan) {
		this.plan = plan;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public Order(Integer orderId, String type, String plan, String customer) {
		super();
		this.orderId = orderId;
		this.type = type;
		this.plan = plan;
		this.customer = customer;
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", type=" + type + ", plan="
				+ plan + ", customer=" + customer + "]";
	}
	
	
}
