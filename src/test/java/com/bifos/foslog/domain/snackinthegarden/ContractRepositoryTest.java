package com.bifos.foslog.domain.snackinthegarden;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContractRepositoryTest {

    @Autowired
    ContractRepository contractRepository;

    @After
    public void cleanUp() {
        contractRepository.deleteAll();
    }

    @Test
    public void 계약_정보가_저장된다() {
        // given
        Long customerId = 1L;
        int year = 2020;
        int month = 8;
        int date = 17;

        contractRepository.save(
                Contract.builder()
                        .customerId(customerId)
                        .year(year)
                        .month(month)
                        .date(date)
                        .build()
        );

        // when
        List<Contract> contractList = contractRepository.findAll();

        // then
        Contract contract = contractList.get(0);
        assertThat(contract.getCustomerId()).isEqualTo(customerId);
        assertThat(contract.getYear()).isEqualTo(year);
        assertThat(contract.getMonth()).isEqualTo(month);
        assertThat(contract.getDate()).isEqualTo(date);
    }

}
