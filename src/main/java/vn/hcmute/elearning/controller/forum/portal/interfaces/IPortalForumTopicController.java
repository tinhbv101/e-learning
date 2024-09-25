package vn.hcmute.elearning.controller.forum.portal.interfaces;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.hcmute.elearning.core.controller.*;
import vn.hcmute.elearning.dtos.StatusResponse;
import vn.hcmute.elearning.dtos.forum.topic.request.*;
import vn.hcmute.elearning.dtos.forum.topic.response.TopicInfo;

@Tag(name = "[PORTAL] Forum Topic Controller")
@RequestMapping("/api/v1/forum/topic")
public interface IPortalForumTopicController extends
        ICreateModelController<CreateTopicRequest, StatusResponse>,
        IUpdateModelController<UpdateTopicRequest, StatusResponse>,
        IDeleteModelController<DeleteTopicRequest, StatusResponse>,
        IGetInfoListFilterPagingController<GetTopicPagingFilterRequest, TopicInfo>,
        IGetDetailByIdController<GetTopicDetailRequest, TopicInfo> {
}
