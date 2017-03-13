/*
 * QueueSamplerMXBean.java - MXBean interface describing the management
 * operations and attributes for the QueueSampler MXBean. In this case
 * there is a read-only attribute "QueueSample" and an operation "clearQueue".
 */

package com.example;

import java.util.List;

public interface OrdersMXBean {
    public List<Order> getOrders();
    public void clearOrders();
}
