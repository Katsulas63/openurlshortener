/*
 * Copyright 2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package es.sopragroup.core.dao.rest;

import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.xml.transform.Source;

import org.springframework.web.client.RestOperations;
import org.springframework.xml.xpath.NodeMapper;
import org.springframework.xml.xpath.XPathOperations;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import es.sopragroup.core.entity.Customer;

public class CustomerClient {

    private final RestOperations restTemplate;

    private final XPathOperations xpathTemplate;
    
    private static final String SOURCE_REST_SERVER = "http://localhost:8080/restdemo/services/customers"; 

    public CustomerClient(RestOperations restTemplate, XPathOperations xpathTemplate) {
        this.restTemplate = restTemplate;
        this.xpathTemplate = xpathTemplate;
    }

    public void doIt() {
        List<Customer> customersStr = getCustomers();
        showCustomers(customersStr);
    }
    
    @SuppressWarnings("unchecked")
    private List<Customer> getCustomers() {
    	Customer customer = restTemplate.getForObject(SOURCE_REST_SERVER, Customer.class);
        
        List<Customer> customers = new ArrayList<Customer>();
        customers.add(customer);

        return customers;
    }

    private void showCustomers(List<Customer> customers) {
        JFrame frame = new JFrame("customer client");
        frame.setLayout(new GridLayout(2, customers.size() / 2));
        
        for (Customer customer : customers) {
        	frame.add(new JLabel(customer.getName()));
        }
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
