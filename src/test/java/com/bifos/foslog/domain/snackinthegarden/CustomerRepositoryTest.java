package com.bifos.foslog.domain.snackinthegarden;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
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

    @Autowired
    Environment environment;

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

        customerRepository.save(Customer.builder()
                .name(name)
                .location(location)
                .expirationDate(expirationDate)
                .build());

        // when
        List<Customer> customers = customerRepository.findAllDesc();

        // thenx
        Customer customer = customers.get(0);
        assertThat(customer.getName()).isEqualTo(name);
        assertThat(customer.getLocation()).isEqualTo(location);
        assertThat(customer.getExpirationDate()).isEqualTo(expirationDate);
    }

    @Test
    public void 계약기간이_X일_미만으로_남은_고객들_불러오기() {
        // given
        int x = 2;
        LocalDate today = LocalDate.now();
        LocalDate noticeDate = today.plusDays(2);

        String name1 = "김유선";
        String location1 = "동림동 운암산 풍경채 101동 203호";
        LocalDate expirationDate1 = LocalDate.now().plusDays(2);

        String name2 = "차은혜";
        String location2 = "용봉동 가디언빌라 204호";
        LocalDate expirationDate2 = LocalDate.now().minusDays(1);

        String name3 = "이혜인";
        String location3 = "용봉동 214번길 6, 아라맨션 B동 501호";
        LocalDate expirationDate3 = LocalDate.now().plusDays(3);

        customerRepository.save(Customer.builder()
                .name(name1)
                .location(location1)
                .expirationDate(expirationDate1)
                .build());

        customerRepository.save(Customer.builder()
                .name(name2)
                .location(location2)
                .expirationDate(expirationDate2)
                .build());

        customerRepository.save(Customer.builder()
                .name(name3)
                .location(location3)
                .expirationDate(expirationDate3)
                .build());

        // when
        List<Customer> customers = customerRepository.findAllExpirationDateWithInXDays(today, noticeDate);

        // then
        assertThat(customers.size()).isEqualTo(1);
        System.out.println(customers);
    }

    @Test
    public void Application_SnackInTheGarden의_환경변수를_읽는다() {
        // given
        String expirationDate = "snackinthegarden.expiration.date";

        // when
        String getExpirationDate = environment.getProperty(expirationDate);

        // then
        assertThat(getExpirationDate).isEqualTo("2");
    }
}
