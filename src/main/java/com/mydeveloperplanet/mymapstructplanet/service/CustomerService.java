package com.mydeveloperplanet.mymapstructplanet.service;

import java.util.HashMap;

import com.mydeveloperplanet.mymapstructplanet.port.dto.CustomerDto;
import com.mydeveloperplanet.mymapstructplanet.port.mapping.CustomerMapper;
import com.mydeveloperplanet.mymapstructplanet.service.domain.Customer;

import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerMapper customerMapper;

    private final HashMap<Long, Customer> customers = new HashMap<>();
    private Long index = 0L;

    CustomerService(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = customerMapper.transformToCustomer(customerDto);
        customer.setCustomerId(index);
        customers.put(index, customer);
        index++;
        return customerMapper.transformToCustomerDto(customer);
    }

    public CustomerDto getCustomer(Long customerId) {
        if (customers.containsKey(customerId)) {
            return customerMapper.transformToCustomerDto(customers.get(customerId));
        } else {
            return null;
        }
    }
}
