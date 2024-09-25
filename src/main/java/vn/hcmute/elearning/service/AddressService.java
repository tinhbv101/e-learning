package vn.hcmute.elearning.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.hcmute.elearning.entity.District;
import vn.hcmute.elearning.entity.Province;
import vn.hcmute.elearning.entity.Ward;
import vn.hcmute.elearning.repository.DistrictReposiory;
import vn.hcmute.elearning.repository.ProvinceRepository;
import vn.hcmute.elearning.repository.WardRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final ProvinceRepository provinceRepository;
    private final DistrictReposiory districtReposiory;
    private final WardRepository wardRepository;

    public List<Province> getListProvinces() {
        return provinceRepository.findAll();
    }

    public List<District> getListDistrictsByProvinceId(Long provinceId) {
        return districtReposiory.findAllByProvinceId(provinceId);
    }

    public List<Ward> getListWardsByDistrictId(Long districtId) {
        return wardRepository.findAllByDistrictId(districtId);
    }

    public Province findProvinceById(Long id) {
        return provinceRepository.findById(id).orElse(null);
    }

    public District findDistrictById(Long id) {
        return districtReposiory.findById(id).orElse(null);
    }

    public Ward findWardById(Long id) {
        return wardRepository.findById(id).orElse(null);
    }

}
