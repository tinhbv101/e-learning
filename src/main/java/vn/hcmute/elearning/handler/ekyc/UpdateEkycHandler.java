package vn.hcmute.elearning.handler.ekyc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.ekyc.request.UpdateEkycRequest;
import vn.hcmute.elearning.dtos.ekyc.response.UpdateEkycResponse;
import vn.hcmute.elearning.entity.Ekyc;
import vn.hcmute.elearning.entity.Teacher;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.enums.TeacherStatus;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.mapper.IEkycMapper;
import vn.hcmute.elearning.service.interfaces.IEkycService;
import vn.hcmute.elearning.service.interfaces.ITeacherService;

@Component
@RequiredArgsConstructor
public class UpdateEkycHandler extends RequestHandler<UpdateEkycRequest, UpdateEkycResponse> {
    private final IEkycService iEkycService;
    private final IEkycMapper iEkycMapper;
    private final ITeacherService iTeacherService;

    @Override
    public UpdateEkycResponse handle(UpdateEkycRequest request) {
        Ekyc ekyc = iEkycService.getById(request.getEkycId());
        if (ekyc == null) {
            throw new InternalException(ResponseCode.EKYC_NOT_FOUND);
        }
        if (iEkycService.getByIdCard(request.getCardNo()).size() > 1) {
            throw new InternalException(ResponseCode.ID_CARD_EXISTED);
        }
        Teacher teacher = iTeacherService.getByUserId(request.getUserId());
        if (teacher != null && (TeacherStatus.ACTIVE.equals(teacher.getStatus()) || TeacherStatus.BAN.equals(teacher.getStatus()))) {
            throw new InternalException(ResponseCode.TEACHER_REQUEST_APPROVED);
        }
        ekyc.setName(setIfRecent(ekyc.getName(), request.getFullName()))
                .setBirthday(setIfRecent(ekyc.getBirthday(), request.getBirthday()))
                .setSex(setIfRecent(ekyc.getSex(), request.getGender()))
                .setProvince(setIfRecent(ekyc.getProvince(), request.getProvince()))
                .setDistrict(setIfRecent(ekyc.getDistrict(), request.getDistrict()))
                .setWard(setIfRecent(ekyc.getWard(), request.getWard()))
                .setAddress(setIfRecent(ekyc.getAddress(), request.getAddress()))
                .setHometown(setIfRecent(ekyc.getHometown(), request.getHometown()))
                .setNo(setIfRecent(ekyc.getNo(), request.getCardNo()))
                .setIssueBy(setIfRecent(ekyc.getIssueBy(), request.getIssueBy()))
                .setIssueDate(setIfRecent(ekyc.getIssueDate(), request.getIssueDate()));
        Ekyc ekycUpdate = iEkycService.save(ekyc);
        return new UpdateEkycResponse(iEkycMapper.toEkycDTO(ekycUpdate));
    }

    public String setIfRecent(String source, String request) {
        return StringUtils.hasText(request) ? request : source;
    }
}
