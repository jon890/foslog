package com.bifos.foslog.web.dto;

import com.bifos.foslog.domain.snackinthegarden.Customer;
import com.bifos.foslog.domain.snackinthegarden.CustomerState;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@NoArgsConstructor
@ToString
public class CustomerSaveRequestDto {

    private String name;
    private String address;
    private String phoneNumber;
    private String memo;
    private ContractRequestDto selectedDates;

    @Builder
    public CustomerSaveRequestDto(String name, String address, String phoneNumber, String memo, ContractRequestDto selectedDates) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.memo = memo;
        this.selectedDates = selectedDates;
    }

    public Customer toEntity() {
        return Customer.builder()
                .name(name)
                .address(address)
                .phoneNumber(phoneNumber)
                .memo(memo)
                .customerState(CustomerState.NOT_USED)
                .build();
    }

    @Getter
    @NoArgsConstructor
    @ToString
    public static class ContractRequestDto {

        private int year;
        private int month;
        private List<Integer> dates;

        @Builder
        public ContractRequestDto(int year, int month, List<Integer> dates) {
            this.year = year;
            this.month = month;
            this.dates = dates;
        }
    }
}
