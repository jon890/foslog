package com.bifos.foslog.web;

import com.bifos.foslog.service.snackinthegarden.SnackInTheGardenService;
import com.bifos.foslog.web.dto.CustomerDetailResponseDto;
import com.bifos.foslog.web.dto.CustomerSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
public class SnackInTheGardenApiController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final SnackInTheGardenService service;

    @PostMapping("/api/v1/customers")
    public Long save(@RequestBody CustomerSaveRequestDto requestDto) {
        logger.debug("BiFoS : /api/v1/customers 시작");
        logger.debug("BiFoS : /api/v1/customers 데이터");
        logger.debug(requestDto.toString());
        return service.save(requestDto);
    }

    @GetMapping("/api/v1/customers/{id}")
    public CustomerDetailResponseDto findById(@PathVariable Long id) {
        return service.findById(id);
    }
}