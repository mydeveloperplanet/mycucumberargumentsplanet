package com.mydeveloperplanet.mycucumberargumentsplanet;

import java.util.Map;

import io.cucumber.java.DataTableType;

public class ParameterTypes {
    @DataTableType
    public StepDefinitions.Customer customerEntry(Map<String, String> entry) {
        return new StepDefinitions.Customer(
                entry.get("firstName"),
                entry.get("lastName"));
    }
}
