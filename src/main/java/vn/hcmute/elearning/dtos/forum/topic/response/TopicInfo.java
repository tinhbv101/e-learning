package vn.hcmute.elearning.dtos.forum.topic.response;

import lombok.Getter;
import lombok.Setter;
import vn.hcmute.elearning.core.BaseResponseAudit;
import vn.hcmute.elearning.dtos.forum.UserForum;

@Getter
@Setter
public class TopicInfo extends BaseResponseAudit<String> {
    private String id;
    private String title;
    private String tags;
    private String path;
    private String categoryId;
    private UserForum user;
}
