package vn.hcmute.elearning.handler.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.user.request.GetEkycInfoRequest;
import vn.hcmute.elearning.dtos.user.response.GetEkycInfoResponse;
import vn.hcmute.elearning.entity.User;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.mapper.IEkycMapper;
import vn.hcmute.elearning.service.interfaces.IEkycService;
import vn.hcmute.elearning.service.interfaces.ITeacherService;
import vn.hcmute.elearning.service.interfaces.IUserService;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetEkycInfoHandler extends RequestHandler<GetEkycInfoRequest, GetEkycInfoResponse> {
    private final IUserService iUserService;
    private final IEkycService iEkycService;
    private final ITeacherService iTeacherService;
    private final IEkycMapper iEkycMapper;

    @Override
    public GetEkycInfoResponse handle(GetEkycInfoRequest request) {
        User user = iUserService.getUserById(request.getUserId());
        if (Boolean.FALSE.equals(user.getIsOrc())) {
            throw new InternalException(ResponseCode.USER_NOT_AUTHENTICATED_OCR);
        }
        return new GetEkycInfoResponse(iEkycMapper.toEkycDTO(user.getTeacher().getEkyc()));
    }
}
