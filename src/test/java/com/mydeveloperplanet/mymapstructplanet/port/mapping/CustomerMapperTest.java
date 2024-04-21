package com.mydeveloperplanet.mymapstructplanet.port.mapping;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.mydeveloperplanet.mymapstructplanet.port.dto.AddressDto;
import com.mydeveloperplanet.mymapstructplanet.port.dto.CustomerDto;
import com.mydeveloperplanet.mymapstructplanet.service.domain.Address;
import com.mydeveloperplanet.mymapstructplanet.service.domain.Customer;

import org.junit.jupiter.api.Test;

class CustomerMapperTest {

    @Test
    void givenCustomer_whenMaps_thenCustomerDto() {
        CustomerMapperImpl customerMapper = new CustomerMapperImpl();
        Customer customer = new Customer();
        customer.setCustomerId(2L);
        customer.setFirstName("John");
        customer.setLastName("Doe");
        Address address = new Address();
        address.setStreet("street");
        address.setHouseNumber(42);
        address.setZipcode("zipcode");
        address.setCity("city");
        customer.setAddress(address);

        CustomerDto customerDto = customerMapper.transformToCustomerDto(customer);
        assertThat( customerDto ).isNotNull();
        assertThat(customerDto.id()).isEqualTo(customer.getCustomerId());
        assertThat(customerDto.firstName()).isEqualTo(customer.getFirstName());
        assertThat(customerDto.lastName()).isEqualTo(customer.getLastName());

        AddressDto addressDto = customerDto.address();
        assertThat(addressDto.street()).isEqualTo(address.getStreet());
        assertThat(addressDto.houseNumber()).isEqualTo(String.valueOf(address.getHouseNumber()));
        assertThat(addressDto.zipcode()).isEqualTo(address.getZipcode());
        assertThat(addressDto.city()).isEqualTo(address.getCity());
    }

    @Test
    void givenCustomerDto_whenMaps_thenCustomer() {
        CustomerMapperImpl customerMapper = new CustomerMapperImpl();
        AddressDto addressDto = new AddressDto("street", "42", "zipcode", "city");
        CustomerDto customerDto = new CustomerDto(2L, "John", "Doe", addressDto);

        Customer customer = customerMapper.transformToCustomer(customerDto);
        assertThat( customer ).isNotNull();
        assertThat(customer.getCustomerId()).isEqualTo(customerDto.id());
        assertThat(customer.getFirstName()).isEqualTo(customerDto.firstName());
        assertThat(customer.getLastName()).isEqualTo(customerDto.lastName());

        Address address = customer.getAddress();
        assertThat(address.getStreet()).isEqualTo(addressDto.street());
        assertThat(address.getHouseNumber()).isEqualTo(Integer.valueOf(addressDto.houseNumber()));
        assertThat(address.getZipcode()).isEqualTo(addressDto.zipcode());
        assertThat(address.getCity()).isEqualTo(addressDto.city());

    }

}
