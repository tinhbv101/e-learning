package vn.hcmute.elearning.handler.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.user.request.GetUsersRequest;
import vn.hcmute.elearning.dtos.user.response.GetUsersResponse;
import vn.hcmute.elearning.entity.User;
import vn.hcmute.elearning.mapper.IUserMapper;
import vn.hcmute.elearning.model.UserResponse;
import vn.hcmute.elearning.service.interfaces.IUserService;
import vn.hcmute.elearning.service.specifications.UserSpecification;
import vn.hcmute.elearning.utils.Paginate;

import java.util.List;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class GetUsersHandler extends RequestHandler<GetUsersRequest, GetUsersResponse> {
    private final IUserService userService;
    private final UserSpecification userSpecification;
    private final IUserMapper userMapper;

    @Override
    public GetUsersResponse handle(GetUsersRequest request) {
        Specification<User> specification = Specification.where(userSpecification.likeFirstName(request.getFirstName()))
            .and(userSpecification.equalBirthday(request.getBirthday()))
            .and(userSpecification.likeLastName(request.getLastName()))
            .and(userSpecification.equalGender(request.getGender()))
            .and(userSpecification.equalPhone(request.getPhone()))
            .and(userSpecification.likeCity(request.getCity()))
            .and(userSpecification.equalHomeNumber(request.getHomeNumber()))
            .and(userSpecification.likeDistrict(request.getDistrict()))
            .and(userSpecification.likeEmail(request.getEmail())
                .and(userSpecification.likeStreetName(request.getStreetName())))
            .and(userSpecification.likeWards(request.getWards()));
        Page<User> page = userService.getUsers(specification, request.getPageable());
        List<UserResponse> userResponseList = page.getContent().parallelStream().map(UserResponse::new).collect(Collectors.toList());
        return new GetUsersResponse(userResponseList, new Paginate(page));
    }
}
