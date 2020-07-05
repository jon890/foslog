package com.bifos.foslog.service.snackinthegarden;

import com.bifos.foslog.domain.snackinthegarden.Customer;
import com.bifos.foslog.domain.snackinthegarden.CustomerRepository;
import com.bifos.foslog.web.dto.CustomerSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalUnit;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SnackInTheGardenService {

    private final CustomerRepository customerRepository;

    @Transactional(readOnly = true)
    public List<Customer> findAllDesc() {
        return customerRepository.findAllDesc();
    }

    @Transactional(readOnly = true)
    public List<Customer> findAllExpirationDateWithInXDays(int x) {
        LocalDate today = LocalDate.now();
        LocalDate noticeDate = today.plusDays(x);
        return customerRepository.findAllExpirationDateWithInXDays(noticeDate);
    }

    @Transactional
    public Long save(CustomerSaveRequestDto requestDto) {
        return customerRepository.save(requestDto.toEntity()).getId();
    }
}
