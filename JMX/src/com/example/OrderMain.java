/*
 * Main.java - main class for the Hello MBean and QueueSampler MXBean example.
 * Create the Hello MBean and QueueSampler MXBean, register them in the platform
 * MBean server, then wait forever (or until the program is interrupted).
 */

package com.example;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

public class OrderMain {
    /* For simplicity, we declare "throws Exception".
       Real programs will usually want finer-grained exception handling. */
    public static void main(String[] args) throws Exception {
        // Get the Platform MBean Server
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();


        // Construct the ObjectName for the QueueSampler MXBean we will register
        ObjectName mxbeanName = new ObjectName("com.example:type=OrderImpl");

        // Create the Queue Sampler MXBean
        Order o=new Order(1, "A", "Unlimited", "cust1");
        Order o1=new Order(2, "A", "TVP", "cust2");
        Order o2=new Order(3, "A", "More Everithing", "cust3");
        
        OrdersImpl oi=new OrdersImpl();
        oi.add(o);
        oi.add(o1);
        oi.add(o2);
        // Register the Queue Sampler MXBean
        mbs.registerMBean(oi, mxbeanName);

        // Wait forever
        System.out.println("Waiting for incoming requests...");
        Thread.sleep(Long.MAX_VALUE);
    }
}
