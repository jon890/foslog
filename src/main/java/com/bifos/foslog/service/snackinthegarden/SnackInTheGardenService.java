package com.bifos.foslog.service.snackinthegarden;

import com.bifos.foslog.domain.snackinthegarden.Contract;
import com.bifos.foslog.domain.snackinthegarden.ContractRepository;
import com.bifos.foslog.domain.snackinthegarden.Customer;
import com.bifos.foslog.domain.snackinthegarden.CustomerRepository;
import com.bifos.foslog.web.dto.CustomerDetailResponseDto;
import com.bifos.foslog.web.dto.CustomerListResponseDto;
import com.bifos.foslog.web.dto.CustomerSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SnackInTheGardenService {

    private final CustomerRepository customerRepository;
    private final ContractRepository contractRepository;

    @Transactional(readOnly = true)
    public List<CustomerListResponseDto> findCustomerAllDesc() {
        // find customer information
        List<Customer> customers = customerRepository.findAllDesc();

        // row number 생성
        AtomicInteger rowNumber = new AtomicInteger();
        List<CustomerListResponseDto> list = customers.stream()
                .map(customer -> {
                    rowNumber.getAndIncrement();
                    List<Contract> contracts = contractRepository.findAllByCustomerId(customer.getId());
                    return new CustomerListResponseDto(rowNumber.get(), customer, contracts);
                })
                .collect(Collectors.toList());
        return list;
    }

    @Transactional
    public Long save(CustomerSaveRequestDto requestDto) {

        // 사용자 정보 저장
        Long customerId = customerRepository.save(requestDto.toEntity()).getId();

        // 선택한 날짜 정보
        CustomerSaveRequestDto.ContractRequestDto selectedDates = requestDto.getSelectedDates();
        List<Contract> contracts = new ArrayList<>();
        for (int date : selectedDates.getDates()) {
            contracts.add(Contract.builder()
                    .customerId(customerId)
                    .year(selectedDates.getYear())
                    .month(selectedDates.getMonth())
                    .date(date)
                    .build());
        }
        contractRepository.saveAll(contracts);

        return customerId;
    }

    @Transactional(readOnly = true)
    public CustomerDetailResponseDto findById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 고객이 없습니다. id=" + id));
        List<Contract> contracts = contractRepository.findAllByCustomerId(customer.getId());
        return new CustomerDetailResponseDto(customer, contracts);
    }

    @Deprecated
    @Transactional(readOnly = true)
    public List<Customer> findAllExpirationDateWithInXDays(int x) {
        LocalDate today = LocalDate.now();
        LocalDate noticeDate = today.plusDays(x);
        return customerRepository.findAllExpirationDateWithInXDays(today, noticeDate);
    }
}
