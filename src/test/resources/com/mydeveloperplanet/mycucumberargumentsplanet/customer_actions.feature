Feature: Customer Actions
  Actions to be made for a customer

  Scenario: Add customer
    Given an empty customer list
    When customer 'John' 'Doe' is added
    Then the customer 'John' 'Doe' is added to the customer list

  Scenario: Add customers
    Given an empty customer list
    When customer 'John' 'Doe' is added
    And customer 'David' 'Beckham' is added
    Then the customer 'John' 'Doe' is added to the customer list
    And the customer 'David' 'Beckham' is added to the customer list

  Scenario: Add customer to existing customers
    Given the following customers:
      | John  | Doe     |
      | David | Beckham |
    When customer 'Bruce' 'Springsteen' is added
    Then the customer 'Bruce' 'Springsteen' is added to the customer list

  Scenario: Add customer to existing customers with parameter type
    Given the following customers with parameter type:
      | John  | Doe     |
      | David | Beckham |
    When customer 'Bruce' 'Springsteen' is added
    Then the customer 'Bruce' 'Springsteen' is added to the customer list