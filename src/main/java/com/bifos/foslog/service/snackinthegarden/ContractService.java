package com.bifos.foslog.service.snackinthegarden;

import com.bifos.foslog.domain.snackinthegarden.Contract;
import com.bifos.foslog.domain.snackinthegarden.ContractRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class ContractService {

    private final ContractRepository contractRepository;

    @Transactional
    public Iterable<Contract> saveAll(Iterable<Contract> contracts) {
        return contractRepository.saveAll(contracts);
    }
}
