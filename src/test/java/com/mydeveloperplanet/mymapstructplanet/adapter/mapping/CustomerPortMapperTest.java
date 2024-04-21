package com.mydeveloperplanet.mymapstructplanet.adapter.mapping;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.mydeveloperplanet.mymapstructplanet.model.CustomerFullData;
import com.mydeveloperplanet.mymapstructplanet.port.dto.AddressDto;
import com.mydeveloperplanet.mymapstructplanet.port.dto.CustomerDto;
//import com.mydeveloperplanet.mymapstructplanet.service.domain.Address;
import com.mydeveloperplanet.mymapstructplanet.model.Customer;

import org.junit.jupiter.api.Test;
import org.openapitools.jackson.nullable.JsonNullable;

class CustomerPortMapperTest {

    @Test
    void givenCustomerApi_whenMaps_thenCustomerDto() {
        CustomerPortMapperImpl customerPortMapper = new CustomerPortMapperImpl();
        Customer customerApi = new Customer();
                customerApi.setFirstName("John");
        customerApi.setLastName("Doe");
        customerApi.setStreet("street");
        customerApi.setNumber("42");
        customerApi.setPostalCode("zipcode");
        customerApi.setCity("city");

        CustomerDto customerDto = customerPortMapper.transformToCustomerDto(customerApi);
        assertThat( customerDto ).isNotNull();
        assertThat(customerDto.id()).isNull();
        assertThat(customerDto.firstName()).isEqualTo(customerApi.getFirstName());
        assertThat(customerDto.lastName()).isEqualTo(customerApi.getLastName());

        AddressDto addressDto = customerDto.address();
        assertThat(addressDto.street()).isEqualTo(customerApi.getStreet());
        assertThat(addressDto.houseNumber()).isEqualTo(customerApi.getNumber());
        assertThat(addressDto.zipcode()).isEqualTo(customerApi.getPostalCode());
        assertThat(addressDto.city()).isEqualTo(customerApi.getCity());
    }

    @Test
    void givenCustomerDto_whenMaps_thenCustomerApi() {
        CustomerPortMapperImpl customerPortMapper = new CustomerPortMapperImpl();
        AddressDto addressDto = new AddressDto("street", "42", "zipcode", "city");
        CustomerDto customerDto = new CustomerDto(2L, "John", "Doe", addressDto);

        CustomerFullData customerFullData = customerPortMapper.transformToCustomerApi(customerDto);
        assertThat( customerFullData ).isNotNull();
        assertThat(customerFullData.getCustomerId()).isEqualTo(customerDto.id());
        assertThat(customerFullData.getFirstName()).isEqualTo(customerDto.firstName());
        assertThat(customerFullData.getLastName()).isEqualTo(customerDto.lastName());

        assertThat(customerFullData.getStreet()).isEqualTo(addressDto.street());
        assertThat(customerFullData.getNumber()).isEqualTo(addressDto.houseNumber());
        assertThat(customerFullData.getPostalCode()).isEqualTo(addressDto.zipcode());
        assertThat(customerFullData.getCity()).isEqualTo(addressDto.city());

    }

}
