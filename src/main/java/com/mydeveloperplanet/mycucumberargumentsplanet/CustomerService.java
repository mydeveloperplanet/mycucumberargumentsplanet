package com.mydeveloperplanet.mycucumberargumentsplanet;

import java.util.Collection;
import java.util.HashMap;

import com.mydeveloperplanet.mycucumberargumentsplanet.domain.Customer;

import org.springframework.stereotype.Service;

@Service
class CustomerService {

    private final HashMap<Long, Customer> customers = new HashMap<>();
    private Long index = 0L;

    Customer createCustomer(Customer customer) {
        customer.setCustomerId(index);
        customers.put(index, customer);
        index++;
        return customer;
    }

    Customer getCustomer(Long customerId) {
        if (customers.containsKey(customerId)) {
            return customers.get(customerId);
        } else {
            return null;
        }
    }

    Collection<Customer> getCustomers() {
        return customers.values();
    }

    void deleteCustomers() {
        customers.clear();
    }
}
