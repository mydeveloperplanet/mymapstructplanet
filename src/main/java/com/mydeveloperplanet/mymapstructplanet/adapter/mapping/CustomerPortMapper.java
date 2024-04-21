package com.mydeveloperplanet.mymapstructplanet.adapter.mapping;

import com.mydeveloperplanet.mymapstructplanet.model.Customer;
import com.mydeveloperplanet.mymapstructplanet.model.CustomerFullData;
import com.mydeveloperplanet.mymapstructplanet.port.dto.CustomerDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerPortMapper {

    @Mapping(source = "street", target = "address.street")
    @Mapping(source = "number", target = "address.houseNumber")
    @Mapping(source = "postalCode", target = "address.zipcode")
    @Mapping(source = "city", target = "address.city")
    CustomerDto transformToCustomerDto(Customer customerApi);

    @Mapping(source = "id", target = "customerId")
    @Mapping(source = "address.street", target = "street")
    @Mapping(source = "address.houseNumber", target = "number")
    @Mapping(source = "address.zipcode", target = "postalCode")
    @Mapping(source = "address.city", target = "city")
    CustomerFullData transformToCustomerApi(CustomerDto customerDto);

}
