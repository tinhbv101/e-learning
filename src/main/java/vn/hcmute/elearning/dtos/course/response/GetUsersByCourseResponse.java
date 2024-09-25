package vn.hcmute.elearning.dtos.course.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.hcmute.elearning.core.BaseResponseData;
import vn.hcmute.elearning.model.user.UserDto;
import vn.hcmute.elearning.utils.Paginate;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetUsersByCourseResponse extends BaseResponseData {
    private List<UserDto> users;
    private Paginate paginate;
}
