package vn.hcmute.elearning.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import vn.hcmute.elearning.entity.WithdrawInvoice;
import vn.hcmute.elearning.repository.WithdrawInvoiceRepository;
import vn.hcmute.elearning.service.interfaces.IWithdrawInvoiceService;

@Service
@RequiredArgsConstructor
public class WithdrawInvoiceService implements IWithdrawInvoiceService {
    private final WithdrawInvoiceRepository withdrawInvoiceRepository;
    @Override
    public WithdrawInvoice save(WithdrawInvoice withdrawInvoice) {
        return withdrawInvoiceRepository.save(withdrawInvoice);
    }

    @Override
    public Page<WithdrawInvoice> getAll(Specification<WithdrawInvoice> spec, Pageable pageable) {
        return withdrawInvoiceRepository.findAll(spec, pageable);
    }
}
