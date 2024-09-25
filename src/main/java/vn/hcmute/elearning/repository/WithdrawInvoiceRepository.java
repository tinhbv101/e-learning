package vn.hcmute.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import vn.hcmute.elearning.entity.WithdrawInvoice;

public interface WithdrawInvoiceRepository extends JpaRepository<WithdrawInvoice, String>, JpaSpecificationExecutor<WithdrawInvoice> {
}
