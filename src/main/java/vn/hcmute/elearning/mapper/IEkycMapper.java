package vn.hcmute.elearning.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import vn.hcmute.elearning.entity.Ekyc;
import vn.hcmute.elearning.model.EkycDTO;
import vn.hcmute.elearning.model.IdCardModel;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface IEkycMapper {
    Ekyc idCardModelToEkyc(IdCardModel idCardModel);
    EkycDTO toEkycDTO(Ekyc ekyc);
}
