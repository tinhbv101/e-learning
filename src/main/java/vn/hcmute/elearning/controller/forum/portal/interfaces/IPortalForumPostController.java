package vn.hcmute.elearning.controller.forum.portal.interfaces;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.hcmute.elearning.core.controller.ICreateModelController;
import vn.hcmute.elearning.core.controller.IDeleteModelController;
import vn.hcmute.elearning.core.controller.IGetInfoListFilterPagingController;
import vn.hcmute.elearning.core.controller.IUpdateModelController;
import vn.hcmute.elearning.dtos.StatusResponse;
import vn.hcmute.elearning.dtos.forum.post.request.CreatePostRequest;
import vn.hcmute.elearning.dtos.forum.post.request.DeletePostRequest;
import vn.hcmute.elearning.dtos.forum.post.request.GetPostPagingRequest;
import vn.hcmute.elearning.dtos.forum.post.request.UpdatePostRequest;
import vn.hcmute.elearning.dtos.forum.post.response.PostInfo;

@Tag(name = "[PORTAL] Forum Post Controller")
@RequestMapping("/api/v1/forum/post")
public interface IPortalForumPostController extends
        ICreateModelController<CreatePostRequest, StatusResponse>,
        IUpdateModelController<UpdatePostRequest, StatusResponse>,
        IDeleteModelController<DeletePostRequest, StatusResponse>,
        IGetInfoListFilterPagingController<GetPostPagingRequest, PostInfo> {
}
