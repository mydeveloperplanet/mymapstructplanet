package com.mydeveloperplanet.mymapstructplanet.adapter;

import com.mydeveloperplanet.mymapstructplanet.adapter.mapping.CustomerPortMapper;
import com.mydeveloperplanet.mymapstructplanet.api.CustomerApi;
import com.mydeveloperplanet.mymapstructplanet.model.Customer;
import com.mydeveloperplanet.mymapstructplanet.model.CustomerFullData;
import com.mydeveloperplanet.mymapstructplanet.port.dto.CustomerDto;
import com.mydeveloperplanet.mymapstructplanet.service.CustomerService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
class CustomerController implements CustomerApi {

    private final CustomerPortMapper customerPortMapper;
    private final CustomerService customerService;

    CustomerController(CustomerPortMapper customerPortMapper, CustomerService customerService) {
        this.customerPortMapper = customerPortMapper;
        this.customerService = customerService;
    }

    @Override
    public ResponseEntity<CustomerFullData> createCustomer(Customer customerApi) {

        CustomerDto customerDtoIn = customerPortMapper.transformToCustomerDto(customerApi);
        CustomerDto customerDtoOut = customerService.createCustomer(customerDtoIn);

        return ResponseEntity.ok(customerPortMapper.transformToCustomerApi(customerDtoOut));
    }

    @Override
    public ResponseEntity<CustomerFullData> getCustomer(Long customerId) {
        CustomerDto customerDtoOut = customerService.getCustomer(customerId);
        return ResponseEntity.ok(customerPortMapper.transformToCustomerApi(customerDtoOut));
    }

}
