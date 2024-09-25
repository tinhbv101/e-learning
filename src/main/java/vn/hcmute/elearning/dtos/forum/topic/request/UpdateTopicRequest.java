package vn.hcmute.elearning.dtos.forum.topic.request;

import lombok.Getter;
import lombok.Setter;
import vn.hcmute.elearning.core.BaseRequestId;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UpdateTopicRequest extends BaseRequestId<Long> {

    @NotBlank
    @Size(max = 1000)
    private String title;
    private String tags;
}
