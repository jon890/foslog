package com.bifos.foslog.web;

import com.bifos.foslog.config.auth.LoginUser;
import com.bifos.foslog.config.auth.dto.SessionUser;
import com.bifos.foslog.service.snackinthegarden.SnackInTheGardenService;
import com.bifos.foslog.web.dto.CustomerListResponseDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class SnackInTheGardenController {

    private final SnackInTheGardenService snackInTheGardenService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/snack-in-the-garden")
    public String moveIndex() {
        return "snack-in-the-garden/index";
    }

    @GetMapping("/snack-in-the-garden/customer/list")
    public String moveCustomerList(Model model, @LoginUser SessionUser user) {
        if (user != null)
            model.addAttribute("loginUserName", user.getName());

        // find customer information
        List<CustomerListResponseDto> customers = snackInTheGardenService.findCustomerAllDesc();

        // debug
        logger.debug(customers.toString());

        model.addAttribute("customers", customers);
        return "snack-in-the-garden/customer/list";
    }

    @GetMapping("/snack-in-the-garden/customer/add")
    public String moveCustomerAdd(Model model, @LoginUser SessionUser user) {
        if (user != null)
            model.addAttribute("loginUserName", user.getName());

        return "snack-in-the-garden/customer/add";
    }
}
