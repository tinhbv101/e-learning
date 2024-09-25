package vn.hcmute.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import vn.hcmute.elearning.entity.District;

import java.util.List;

public interface DistrictReposiory extends JpaRepository<District, Long>, JpaSpecificationExecutor<District> {
    List<District> findAllByProvinceId(Long provinceId);
}
