package vn.hcmute.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import vn.hcmute.elearning.entity.Administrator;

public interface AdministratorRepository extends JpaRepository<Administrator, String>, JpaSpecificationExecutor<Administrator> {
    Administrator findByPhone(String phone);
}
