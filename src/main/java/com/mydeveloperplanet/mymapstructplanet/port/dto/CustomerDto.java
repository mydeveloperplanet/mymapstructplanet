package com.mydeveloperplanet.mymapstructplanet.port.dto;

public record CustomerDto(Long id, String firstName, String lastName, AddressDto address) {
}
