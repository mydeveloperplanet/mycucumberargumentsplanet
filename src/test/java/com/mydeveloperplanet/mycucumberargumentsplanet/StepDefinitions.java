package com.mydeveloperplanet.mycucumberargumentsplanet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import java.util.List;
import java.util.Objects;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions extends CucumberSpringConfiguration {

    final int port = Integer.parseInt(System.getProperty("port"));
    final RestClient restClient = RestClient.create();

    @Given("an empty customer list")
    public void an_empty_customer_list() {
        ResponseEntity<Void> response = restClient.delete()
                .uri("http://localhost:"+ port + "/customer")
                .retrieve()
                .toBodilessEntity();
    }

    @When("customer {string} {string} is added")
    public void customer_firstname_lastname_is_added(String firstName, String lastName) {
        Customer customer = new Customer(firstName, lastName);
        ResponseEntity<Void> response = restClient.post()
                .uri("http://localhost:"+ port + "/customer")
                .contentType(APPLICATION_JSON)
                .body(customer)
                .retrieve()
                .toBodilessEntity();
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
    }

    @Then("the customer {string} {string} is added to the customer list")
    public void the_customer_first_name_last_name_is_added_to_the_customer_list(String firstName, String lastName) {
        List<Customer> customers = restClient.get()
                .uri("http://localhost:"+ port + "/customer")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
        assertThat(customers).contains(new Customer(firstName, lastName));
    }

    @Given("the following customers:")
    public void the_following_customers(io.cucumber.datatable.DataTable dataTable) {

        for (List<String> customer : dataTable.asLists()) {
            customer_firstname_lastname_is_added(customer.get(0), customer.get(1));
        }
    }

    @Given("the following customers with parameter type:")
    public void the_following_customers_with_parameter_type(List<Customer> customers) {

        for (Customer customer : customers) {
            customer_firstname_lastname_is_added(customer.getFirstName(), customer.getLastName());
        }
    }


    public static class Customer {
        private String firstName;
        private String lastName;

        Customer(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Customer customer = (Customer) o;
            return Objects.equals(firstName, customer.firstName) && Objects.equals(lastName, customer.lastName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(firstName, lastName);
        }
    }

}
