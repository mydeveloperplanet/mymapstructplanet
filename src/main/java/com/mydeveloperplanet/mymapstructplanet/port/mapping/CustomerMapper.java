package com.mydeveloperplanet.mymapstructplanet.port.mapping;

import com.mydeveloperplanet.mymapstructplanet.port.dto.CustomerDto;
import com.mydeveloperplanet.mymapstructplanet.service.domain.Customer;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(source = "customerId", target = "id")
    CustomerDto transformToCustomerDto(Customer customer);

    @Mapping(source = "id", target = "customerId")
    Customer transformToCustomer(CustomerDto customerDto);

}
