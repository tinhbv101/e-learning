package vn.hcmute.elearning.dtos.forum.post.request;

import lombok.Getter;
import lombok.Setter;
import vn.hcmute.elearning.core.BaseRequestData;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreatePostRequest extends BaseRequestData {

    @NotBlank
    private String content;
    @NotNull
    private Long topicId;
}
