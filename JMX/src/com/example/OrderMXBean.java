/*
 * QueueSamplerMXBean.java - MXBean interface describing the management
 * operations and attributes for the QueueSampler MXBean. In this case
 * there is a read-only attribute "QueueSample" and an operation "clearQueue".
 */

package com.example;


public interface OrderMXBean {
    public Integer getOrderId();
    public String getType();
    public String getPlan();
    public String getCustomer();
}
