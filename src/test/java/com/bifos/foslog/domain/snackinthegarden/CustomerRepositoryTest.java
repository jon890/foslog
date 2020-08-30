package com.bifos.foslog.domain.snackinthegarden;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Before
    public void setUp() {
        CustomerState state = CustomerState.DELIVERING;

        String name1 = "김유선";
        String address1 = "동림동 운암산 풍경채 101동 203호";

        String name2 = "차은혜";
        String address2 = "용봉동 가디언빌라 204호";

        String name3 = "이혜인";
        String address3 = "용봉동 214번길 6, 아라맨션 B동 501호";

        customerRepository.save(customerRepository.save(Customer.builder()
                .name(name1)
                .address(address1)
                .customerState(state)
                .build()));

        customerRepository.save(customerRepository.save(Customer.builder()
                .name(name2)
                .address(address2)
                .customerState(state)
                .build()));

        customerRepository.save(customerRepository.save(Customer.builder()
                .name(name3)
                .address(address3)
                .customerState(state)
                .build()));
    }

    @After
    public void cleanup() {
        customerRepository.deleteAll();
    }

    @Test
    public void 고객정보저장_불러오기() {
        // given
        String name = "김유선";
        String address = "동림동 운암산 풍경채 101동 203호";
        CustomerState state = CustomerState.DELIVERING;

        customerRepository.deleteAll();
        customerRepository.save(Customer.builder()
                .name(name)
                .address(address)
                .customerState(state)
                .build());

        // when
        List<Customer> customers = customerRepository.findAllDesc();

        // then
        Customer customer = customers.get(0);
        assertThat(customer.getName()).isEqualTo(name);
        assertThat(customer.getAddress()).isEqualTo(address);
    }

}
