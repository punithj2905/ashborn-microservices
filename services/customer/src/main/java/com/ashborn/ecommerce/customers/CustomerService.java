package com.ashborn.ecommerce.customers;

import static java.lang.String.format;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ashborn.ecommerce.exception.CustomerNotFoundException;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    public String createCustomer(CustomerRequest request) {
        var customer = mapper.toCustomer(request);
        customer.setId(generateId());
        log.info("Creating customer with email {}", request.email());
        repository.save(customer);
        log.info("Customer created with id {}", customer.getId());
        return customer.getId();
    }

    public void updateCustomer(CustomerRequest request) {

        var customer = repository.findById(request.id())
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("Cannot update customer :: No customer found with provided ID:: %s ",
                                request.id())));
        boolean updatedCustomer=mergeCustomer(customer, request);
        if(updatedCustomer){
            repository.save(customer);
            log.info("Customer updated with id {}", customer.getId());
          }

        
    }

    private boolean mergeCustomer(Customer customer, CustomerRequest request) {
        boolean updated=false;
        if (StringUtils.isNotBlank(request.firstName())) {
            customer.setFirstName(request.firstName());
            updated=true;
        }
        if (StringUtils.isNotBlank(request.lastName())) {
            customer.setLastName(request.lastName());
            updated=true;
        }
        if (StringUtils.isNotBlank(request.email())) {
            customer.setEmail(request.email());
            updated=true;
        }
        if (request.address() != null) {
            customer.setAddress(request.address());
            updated=true;

        }
        return updated;
    }

    public List<CustomerResponse> findAllCustomers() {
        return repository.findAll()
                .stream()
                .map(mapper::fromCustomer)
                .collect(Collectors.toList());
    }

    public Boolean existsById(String customerId) {
        return repository.existsById(customerId);
    }

    public CustomerResponse findById(String customerId) {
        return repository.findById(customerId)
                .map(mapper::fromCustomer)
                .orElseThrow(() -> new CustomerNotFoundException(
                        format("No customer is found with the provided Id::%s", customerId)));
    }

    public void deleteCustomer(String customerId) {
        repository.deleteById(customerId);
        log.info("Customer deleted with id {}", customerId);
    }
      private String generateId(){
    return UUID.randomUUID().toString();
    }
}
