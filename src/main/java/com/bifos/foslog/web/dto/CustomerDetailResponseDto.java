package com.bifos.foslog.web.dto;

import com.bifos.foslog.domain.snackinthegarden.Contract;
import com.bifos.foslog.domain.snackinthegarden.Customer;
import lombok.Getter;

import java.util.List;

@Getter
public class CustomerDetailResponseDto {

    private final String name;
    private final String address;
    private final String phoneNumber;
    private final String memo;
    private final List<Contract> contracts;

    public CustomerDetailResponseDto(Customer entity, List<Contract> contracts) {
        this.name = entity.getName();
        this.address = entity.getAddress();
        this.phoneNumber = entity.getPhoneNumber();
        this.memo = entity.getMemo();
        this.contracts = contracts;
    }
}
