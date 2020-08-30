package com.bifos.foslog.web;

import com.bifos.foslog.domain.snackinthegarden.Contract;
import com.bifos.foslog.domain.snackinthegarden.ContractRepository;
import com.bifos.foslog.domain.snackinthegarden.Customer;
import com.bifos.foslog.domain.snackinthegarden.CustomerRepository;
import com.bifos.foslog.web.dto.CustomerSaveRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SnackInTheGardenApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @After
    public void tearDown() throws Exception {
        customerRepository.deleteAll();
        contractRepository.deleteAll();
    }

    @Test
    @WithMockUser(roles = "USER")
    public void Customers_등록된다() throws Exception {
        // given
        String name = "김병태";
        String address = "광주광역시";
        String memo = "memo";
        String phoneNumber = "010-0000-0000";

        int year = 2020;
        int month = 8;
        int date = 30;

        CustomerSaveRequestDto requestDto = CustomerSaveRequestDto.builder()
                .name(name)
                .address(address)
                .memo(memo)
                .phoneNumber(phoneNumber)
                .selectedDates(CustomerSaveRequestDto.ContractRequestDto.builder()
                        .year(year)
                        .month(month)
                        .dates(Arrays.asList(date))
                        .build())
                .build();

        String url = "http://localhost:" + port + "/api/v1/customers";

        // when
        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        // then
        List<Customer> all = customerRepository.findAll();
        assertThat(all.get(0).getName()).isEqualTo(name);
        assertThat(all.get(0).getAddress()).isEqualTo(address);
        assertThat(all.get(0).getMemo()).isEqualTo(memo);
        assertThat(all.get(0).getPhoneNumber()).isEqualTo(phoneNumber);

        List<Contract> contracts = contractRepository.findAllByCustomerId(all.get(0).getId());
        assertThat(contracts.get(0).getYear()).isEqualTo(year);
        assertThat(contracts.get(0).getMonth()).isEqualTo(month);
        assertThat(contracts.get(0).getDate()).isEqualTo(date);
    }
}
