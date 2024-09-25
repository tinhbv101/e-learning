package vn.hcmute.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.hcmute.elearning.entity.Ekyc;

import java.util.List;

public interface EkycRepository extends JpaRepository<Ekyc, String> {
    Ekyc getByCreatedBy(String userId);

    List<Ekyc> getEkycByNo(String idCard);
}
