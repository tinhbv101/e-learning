package vn.hcmute.elearning.service.interfaces;

import org.springframework.web.multipart.MultipartFile;
import vn.hcmute.elearning.entity.Ekyc;
import vn.hcmute.elearning.model.IdCardModel;

import java.util.List;

public interface IEkycService {
    Ekyc save(Ekyc ekyc);

    IdCardModel detectIdCard(MultipartFile imageFront, MultipartFile imageBack);

    Ekyc getById(String id);

    Ekyc getByUserId(String userId);

    List<Ekyc> getByIdCard(String idCard);
}
