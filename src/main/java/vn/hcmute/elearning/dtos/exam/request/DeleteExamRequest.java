package vn.hcmute.elearning.dtos.exam.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import vn.hcmute.elearning.core.BaseRequestData;

@Data
@EqualsAndHashCode(callSuper = true)
public class DeleteExamRequest extends BaseRequestData {
    private String id;
}
