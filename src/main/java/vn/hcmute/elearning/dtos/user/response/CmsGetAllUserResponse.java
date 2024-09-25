package vn.hcmute.elearning.dtos.user.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.domain.Page;
import vn.hcmute.elearning.core.BaseResponseData;
import vn.hcmute.elearning.entity.User;
import vn.hcmute.elearning.model.UserResponse;
import vn.hcmute.elearning.utils.Paginate;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CmsGetAllUserResponse extends BaseResponseData {
    private List<UserResponse> users;
    private Paginate paginate;

    public CmsGetAllUserResponse(Page<User> users) {
        this.users = users.stream().map(UserResponse::new).collect(Collectors.toList());
        this.paginate = new Paginate(users);
    }
}
