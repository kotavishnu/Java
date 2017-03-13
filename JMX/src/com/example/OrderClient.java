/*
 * Client.java - JMX client that interacts with the JMX agent. It gets
 * attributes and performs operations on the Hello MBean and the QueueSampler
 * MXBean example. It also listens for Hello MBean notifications.
 */

package com.example;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.management.AttributeChangeNotification;
import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.Notification;
import javax.management.NotificationListener;
import javax.management.ObjectInstance;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class OrderClient {

    /**
     * Inner class that will handle the notifications.
     */
    public static class ClientListener implements NotificationListener {
        public void handleNotification(Notification notification,
                                       Object handback) {
            echo("\nReceived notification:");
            echo("\tClassName: " + notification.getClass().getName());
            echo("\tSource: " + notification.getSource());
            echo("\tType: " + notification.getType());
            echo("\tMessage: " + notification.getMessage());
            if (notification instanceof AttributeChangeNotification) {
                AttributeChangeNotification acn =
                    (AttributeChangeNotification) notification;
                echo("\tAttributeName: " + acn.getAttributeName());
                echo("\tAttributeType: " + acn.getAttributeType());
                echo("\tNewValue: " + acn.getNewValue());
                echo("\tOldValue: " + acn.getOldValue());
            }
        }
    }

    /* For simplicity, we declare "throws Exception".
       Real programs will usually want finer-grained exception handling. */
    public static void main(String[] args) throws Exception {
        // Create an RMI connector client and
        // connect it to the RMI connector server
        //
        echo("\nCreate an RMI connector client and " +
             "connect it to the RMI connector server");
        JMXServiceURL url =
            new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:9999/jmxrmi");
        JMXConnector jmxc = JMXConnectorFactory.connect(url, null);

       
        // Get an MBeanServerConnection
        //
        echo("\nGet an MBeanServerConnection");
        MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();
        waitForEnterPressed();

        // Get domains from MBeanServer
        //
        echo("\nDomains:");
        String domains[] = mbsc.getDomains();
        Arrays.sort(domains);
        for (String domain : domains) {
            echo("\tDomain = " + domain);
        }
        waitForEnterPressed();

        // Get MBeanServer's default domain
        //
        echo("\nMBeanServer default domain = " + mbsc.getDefaultDomain());

        // Get MBean count
        //
        echo("\nMBean count = " + mbsc.getMBeanCount());

        // Query MBean names
        //
        echo("\nQuery MBeanServer MBeans:");
        Set<ObjectName> names =
            new TreeSet<ObjectName>(mbsc.queryNames(null, null));
        for (ObjectName name : names) {
            echo("\tObjectName = " + name);
        }
        waitForEnterPressed();


        // Construct the ObjectName for the QueueSampler MXBean
        //
        ObjectName mxbeanName =
            new ObjectName("com.example:type=OrderImpl");

        // Create a dedicated proxy for the MXBean instead of
        // going directly through the MBean server connection
        //
        OrdersMXBean mxbeanProxy =
            JMX.newMXBeanProxy(mbsc, mxbeanName, OrdersMXBean.class);

        Set<ObjectName> beans=mbsc.queryNames(new ObjectName("com.example:type=OrderImpl*"), null);
        
        for(ObjectName oi:beans){
        	OrdersMXBean ords=JMX.newMXBeanProxy(mbsc, oi, OrdersMXBean.class);
        	System.out.println("**************bean "+ords.getOrders());
        }
        // Get QueueSample attribute in QueueSampler MXBean
        //
        List<Order> orders = mxbeanProxy.getOrders();
        echo("\n Orders= " + orders);

        // Invoke "clearQueue" in QueueSampler MXBean
        //
        echo("\nInvoke clearQueue() in QueueSampler MXBean...");
        mxbeanProxy.clearOrders();

        waitForEnterPressed();

        // Close MBeanServer connection
        //
        echo("\nClose the connection to the server");
        jmxc.close();
        echo("\nBye! Bye!");
    }

    private static void echo(String msg) {
        System.out.println(msg);
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void waitForEnterPressed() {
        try {
            echo("\nPress <Enter> to continue...");
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
