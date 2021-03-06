package com.bifos.foslog.schedule;

import com.bifos.foslog.service.snackinthegarden.SnackInTheGardenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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
     * 매일 아침 10시에 계약일이 곧 만료되는 사람을 알린다
     */
//    @Deprecated
//    @Scheduled(cron = "0 0 10 * * *", zone = "Asia/Seoul")
//    public void noticeExpirationDateWithInXDays() throws Exception {
//
//        LocalDate today = LocalDate.now();
//
//        String title = "[SNACK-IN-THE-GARDEN]" +
//                today +
//                " 다가오는 계약종료 리스트";
//
//        List<Customer> customers = snackInTheGardenService.findAllExpirationDateWithInXDays(Integer.parseInt(noticeDay));
//
//        StringBuilder message = new StringBuilder("<h1>" + today + " 다가오는 계약 종료 리스트</h1>");
//        message.append("<br>");
//
//        if (customers.size() == 0) {
//            message.append("곧 계약이 종료되는 사람이 없습니다");
//        } else {
//            for (Customer c : customers) {
//                message.append("<p>");
//                message.append("<strong>").append(c.getName()).append("</strong>");
//                message.append(" 고객님은 ");
//                message.append("주문 날짜가 ");
//                // todo kbt : 고쳐
////                message.append("<span style=`font-weight:600; color:red;`>").append(c.getExpirationDate()).append("</span>");
//                message.append(" 까지 입니다!");
//                message.append("</p>");
//            }
//        }
//
//        Gmail.send(
//                adminGoogleEmail,
//                adminGooglePassword,
//                recipientEmail,
//                "",
//                title,
//                message.toString());
//    }
}
