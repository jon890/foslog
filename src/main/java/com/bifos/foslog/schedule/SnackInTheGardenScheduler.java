package com.bifos.foslog.schedule;

import com.bifos.foslog.domain.snackinthegarden.Customer;
import com.bifos.foslog.service.snackinthegarden.SnackInTheGardenService;
import com.bifos.foslog.util.Gmail;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SnackInTheGardenScheduler {

    private final SnackInTheGardenService snackInTheGardenService;
    private final Environment environment;

    /**
     * 매일 새벽 한 시에 계약일이 곧 만료되는 사람을 알린다
     */
    @Scheduled(cron = "10 * * * * *", zone = "Asia/Seoul")
    public void noticeExpirationDateWithInXDays() throws Exception {

        String title = "[SNACK-IN-THE-GARDEN] 오늘의 곧 계약 만료 리스트";

        int noticeDate = Integer.parseInt(environment.getProperty("snackinthegarden.expiration.day"));
        List<Customer> customers = snackInTheGardenService.findAllExpirationDateWithInXDays(noticeDate);

        StringBuilder message = new StringBuilder();

        for (Customer c : customers) {
            message.append(c.getName());
            message.append(" 고객님은 ");
            message.append("주문 날짜가 ");
            message.append(c.getExpirationDate());
            message.append(" 까지 입니다!");
        }

        Gmail.send(
                environment.getProperty("snackinthegarden.email.from"),
                environment.getProperty("snackinthegarden.email.password"),
                environment.getProperty("snackinthegarden.email.to"),
                "",
                title,
                message.toString());
    }
}
