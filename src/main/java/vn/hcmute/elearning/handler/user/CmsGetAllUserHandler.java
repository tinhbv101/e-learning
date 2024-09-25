package vn.hcmute.elearning.handler.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.user.request.CmsGetAllUserRequest;
import vn.hcmute.elearning.dtos.user.response.CmsGetAllUserResponse;
import vn.hcmute.elearning.entity.User;
import vn.hcmute.elearning.service.UserService;
import vn.hcmute.elearning.service.specifications.UserSpecification;

@Component
@RequiredArgsConstructor
public class CmsGetAllUserHandler extends RequestHandler<CmsGetAllUserRequest, CmsGetAllUserResponse> {

    private final UserSpecification userSpecification;
    private final UserService userService;

    @Override
    public CmsGetAllUserResponse handle(CmsGetAllUserRequest request) {
        Specification<User> specification = Specification.where(
                userSpecification.likeFirstName(request.getFirstName())
                        .and(userSpecification.likeLastName(request.getLastName()))
                        .and(userSpecification.equalGender(request.getGender()))
                        .and(userSpecification.equalActive(request.getActive()))
                        .and(userSpecification.equalDelete(request.getDelete()))
                        .and(userSpecification.equalIsOcr(request.getIsOcr()))
                        .and(userSpecification.equalBan(request.getBan()))
                        .and(userSpecification.createFrom(request.fromDatetime(request.getCreateFrom()))
                        .and(userSpecification.createTo(request.toDatetime(request.getCreateTo())))
                        .and(userSpecification.isTeacher(request.getIsTeacher())))
        );
        Page<User> users = userService.getUsers(specification, request.getPageable());
        return new CmsGetAllUserResponse(users);
    }
}
