package vn.hcmute.elearning.dtos.user.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Pageable;
import vn.hcmute.elearning.core.BaseRequestData;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetUnregisterCourseRequest extends BaseRequestData {
    private Pageable pageable;
}
