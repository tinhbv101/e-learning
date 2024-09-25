package vn.hcmute.elearning.dtos.user.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import vn.hcmute.elearning.core.BaseResponseData;
import vn.hcmute.elearning.model.UserResponse;
import vn.hcmute.elearning.utils.Paginate;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class GetUsersResponse extends BaseResponseData {
    private List<UserResponse> users;
    private Paginate paginate;
}
