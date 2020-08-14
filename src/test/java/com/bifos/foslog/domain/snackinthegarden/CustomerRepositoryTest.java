package com.bifos.foslog.domain.snackinthegarden;

import com.bifos.foslog.domain.snackinthegarden.Customer;
import com.bifos.foslog.domain.snackinthegarden.CustomerRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Before
    public void setUp() {
        String name1 = "김유선";
        String location1 = "동림동 운암산 풍경채 101동 203호";
        LocalDate expirationDate1 = LocalDate.now().plusDays(2);

        String name2 = "차은혜";
        String location2 = "용봉동 가디언빌라 204호";
        LocalDate expirationDate2 = LocalDate.now().minusDays(1);

        String name3 = "이혜인";
        String location3 = "용봉동 214번길 6, 아라맨션 B동 501호";
        LocalDate expirationDate3 = LocalDate.now().plusDays(3);

        customerRepository.save(customerRepository.save(Customer.builder()
                .name(name1)
                .location(location1)
                .expirationDate(expirationDate1)
                .build()));

        customerRepository.save(customerRepository.save(Customer.builder()
                .name(name2)
                .location(location2)
                .expirationDate(expirationDate2)
                .build()));

        customerRepository.save(customerRepository.save(Customer.builder()
                .name(name3)
                .location(location3)
                .expirationDate(expirationDate3)
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
        String location = "동림동 운암산 풍경채 101동 203호";
        LocalDate expirationDate = LocalDate.of(2020, Month.JULY, 7);

        customerRepository.deleteAll();
        customerRepository.save(Customer.builder()
                .name(name)
                .location(location)
                .expirationDate(expirationDate)
                .build());

        // when
        List<Customer> customers = customerRepository.findAllDesc();

        // then
        Customer customer = customers.get(0);
        assertThat(customer.getName()).isEqualTo(name);
        assertThat(customer.getAddress()).isEqualTo(location);
        assertThat(customer.getExpirationDate()).isEqualTo(expirationDate);
    }

    @Test
    public void 계약기간이_X일_미만으로_남은_고객들_불러오기() {
        // given
        int x = 2;
        LocalDate today = LocalDate.now();
        LocalDate noticeDate = today.plusDays(2);

        // when
        List<Customer> customers = customerRepository.findAllExpirationDateWithInXDays(today, noticeDate);

        // then
        assertThat(customers.size()).isEqualTo(1);
        System.out.println(customers);
    }

    @Test
    public void 계약기간이_짧게_남은순으로_모든_고객을_가져온다() {
        // given

        // when
        List<Customer> customers = customerRepository.findAllExpirationDateAsc();

        // then
        assertThat(customers.size()).isEqualTo(3);
        assertThat(customers.get(0).getName()).isEqualTo("차은혜");
        System.out.println(customers);
    }

}
