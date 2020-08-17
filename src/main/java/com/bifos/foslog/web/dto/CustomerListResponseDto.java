package com.bifos.foslog.web.dto;

import com.bifos.foslog.domain.snackinthegarden.Contract;
import com.bifos.foslog.domain.snackinthegarden.Customer;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustomerListResponseDto {

    private final int rowNumber;
    private final String name;
    private final String address;
    private final String phoneNumber;
    private final String memo;
    private final List<Contract> contracts;

    public CustomerListResponseDto(int rowNumber, Customer entity, List<Contract> contracts) {
        this.rowNumber = rowNumber;
        this.name = entity.getName();
        this.address = entity.getAddress();
        this.phoneNumber = entity.getPhoneNumber();
        this.memo = entity.getMemo();
        this.contracts = contracts;
    }
}
