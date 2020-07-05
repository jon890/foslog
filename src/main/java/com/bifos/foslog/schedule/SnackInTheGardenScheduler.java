package com.bifos.foslog.schedule;

import com.bifos.foslog.domain.snackinthegarden.Customer;
import com.bifos.foslog.service.snackinthegarden.SnackInTheGardenService;
import com.bifos.foslog.util.Gmail;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SnackInTheGardenScheduler {

    private final SnackInTheGardenService snackInTheGardenService;

    @Value("${snackinthegarden.notice.day}")
    private String noticeDay;

    @Value("${snackinthegarden.notice.recipient.email}")
    private String recipientEmail;

    @Value("${snackinthegarden.admin.google.email}")
    private String adminGoogleEmail;

    @Value("${snackinthegarden.admin.google.password}")
    private String adminGooglePassword;

    /**
     * 매일 새벽 한 시에 계약일이 곧 만료되는 사람을 알린다
     */
    @Scheduled(cron = "0 0 10 * * *", zone = "Asia/Seoul")
    public void noticeExpirationDateWithInXDays() throws Exception {

        LocalDate today = LocalDate.now();

        String title = "[SNACK-IN-THE-GARDEN]" +
                today +
                " 다가오는 계약종료 리스트";

        List<Customer> customers = snackInTheGardenService.findAllExpirationDateWithInXDays(Integer.parseInt(noticeDay));

        StringBuilder message = new StringBuilder();

        for (Customer c : customers) {
            message.append(c.getName());
            message.append(" 고객님은 ");
            message.append("주문 날짜가 ");
            message.append(c.getExpirationDate());
            message.append(" 까지 입니다!");
        }

        Gmail.send(
                adminGoogleEmail,
                adminGooglePassword,
                recipientEmail,
                "",
                title,
                message.toString());
    }
}
