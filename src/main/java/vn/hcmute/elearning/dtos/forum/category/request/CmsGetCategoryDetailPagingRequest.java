package vn.hcmute.elearning.dtos.forum.category.request;

import lombok.Getter;
import lombok.Setter;
import vn.hcmute.elearning.core.BaseRequestData;
import vn.hcmute.elearning.enums.forum.ForumCategoryStatus;

@Getter
@Setter
public class CmsGetCategoryDetailPagingRequest extends BaseRequestData {

    private String title;
    private String parentId;
    private ForumCategoryStatus status;
}
