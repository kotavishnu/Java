package com.example;

import java.util.ArrayList;
import java.util.List;

public class OrdersImpl implements OrdersMXBean {

	List<Order> orders=new ArrayList<Order>();
	@Override
	public List<Order> getOrders() {
		return orders;
	}

	@Override
	public void clearOrders() {
		orders.clear();
	}

	public void add(Order o){
		orders.add(o);
	}
}
