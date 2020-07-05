package com.bifos.foslog.web.dto;

import com.bifos.foslog.domain.snackinthegarden.Customer;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

@Getter
@NoArgsConstructor
public class CustomerSaveRequestDto {

    private String name;
    private String location;
    private String phoneNumber;
    private String memo;
    private String expirationDate;

    @Builder
    public CustomerSaveRequestDto(String name, String location, String phoneNumber, String memo, String expirationDate) {
        this.name = name;
        this.location = location;
        this.phoneNumber = phoneNumber;
        this.memo = memo;
        this.expirationDate = expirationDate;
    }

    public Customer toEntity() {
        return Customer.builder()
                .name(name)
                .location(location)
                .phoneNumber(phoneNumber)
                .memo(memo)
                .expirationDate(
                        LocalDate.of(
                                Integer.parseInt(expirationDate.substring(0, 4)),
                                Month.of(Integer.parseInt(expirationDate.substring(4, 6))),
                                Integer.parseInt(expirationDate.substring(6, 8))
                        )
                )
                .build();
    }
}
