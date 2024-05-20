package com.mydeveloperplanet.mycucumberargumentsplanet;

import java.util.List;

import com.mydeveloperplanet.mycucumberargumentsplanet.api.CustomerApi;
import com.mydeveloperplanet.mycucumberargumentsplanet.model.Customer;
import com.mydeveloperplanet.mycucumberargumentsplanet.model.CustomerFullData;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
class CustomerController implements CustomerApi {

    private final CustomerService customerService;

    CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public ResponseEntity<CustomerFullData> createCustomer(Customer apiCustomer) {
        com.mydeveloperplanet.mycucumberargumentsplanet.domain.Customer customer = new com.mydeveloperplanet.mycucumberargumentsplanet.domain.Customer();
        customer.setFirstName(apiCustomer.getFirstName());
        customer.setLastName(apiCustomer.getLastName());

        return ResponseEntity.ok(domainToApi(customerService.createCustomer(customer)));
    }

    @Override
    public ResponseEntity<CustomerFullData> getCustomer(Long customerId) {
        com.mydeveloperplanet.mycucumberargumentsplanet.domain.Customer customer = customerService.getCustomer(customerId);
        return ResponseEntity.ok(domainToApi(customer));
    }

    @Override
    public ResponseEntity<Void> deleteCustomers() {
        customerService.deleteCustomers();
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<CustomerFullData>> getCustomers() {
        return ResponseEntity.ok(customerService.getCustomers().stream().map(this::domainToApi).toList());
    }

    private CustomerFullData domainToApi(com.mydeveloperplanet.mycucumberargumentsplanet.domain.Customer customer) {
        CustomerFullData cfd = new CustomerFullData();
        cfd.setCustomerId(customer.getCustomerId());
        cfd.setFirstName(customer.getFirstName());
        cfd.setLastName(customer.getLastName());
        return cfd;
    }

}
