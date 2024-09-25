package vn.hcmute.elearning.handler.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.PageResponseData;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.chat.request.GetRecommendFriendFoStudentRequest;
import vn.hcmute.elearning.entity.User;
import vn.hcmute.elearning.mapper.IUserMapper;
import vn.hcmute.elearning.model.chat.UserInfoChat;
import vn.hcmute.elearning.service.interfaces.IUserService;

@Component
@RequiredArgsConstructor
public class GetRecommendFriendForStudentHandler extends RequestHandler<GetRecommendFriendFoStudentRequest, PageResponseData<UserInfoChat>> {
    private final IUserService iUserService;
    private final IUserMapper iUserMapper;

    @Override
    public PageResponseData<UserInfoChat> handle(GetRecommendFriendFoStudentRequest request) {
        Page<User> page = iUserService.getRecommendFiend(request.getUserId(), request.getPageable(), false);
        return new PageResponseData<>(iUserMapper.toListUserInfoChat(page.getContent()), page);
    }
}
