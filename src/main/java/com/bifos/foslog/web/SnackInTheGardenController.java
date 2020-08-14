package com.bifos.foslog.web;

import com.bifos.foslog.config.auth.LoginUser;
import com.bifos.foslog.config.auth.dto.SessionUser;
import com.bifos.foslog.service.snackinthegarden.SnackInTheGardenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class SnackInTheGardenController {

    private final SnackInTheGardenService snackInTheGardenService;

    @GetMapping("/snack-in-the-garden")
    public String moveIndex() {
        return "/snack-in-the-garden/index";
    }

    @GetMapping("/snack-in-the-garden/customer/list")
    public String moveCustomerList(Model model, @LoginUser SessionUser user) {
        if (user != null)
            model.addAttribute("loginUserName", user.getName());

        model.addAttribute("customer-list", snackInTheGardenService.findAllExpirationDateAsc());
        return "/snack-in-the-garden/customer/list";
    }

    @GetMapping("/snack-in-the-garden/customer/add")
    public String moveCustomerAdd(Model model, @LoginUser SessionUser user) {
        if (user != null)
            model.addAttribute("loginUserName", user.getName());

        return "/snack-in-the-garden/customer/add";
    }
}
