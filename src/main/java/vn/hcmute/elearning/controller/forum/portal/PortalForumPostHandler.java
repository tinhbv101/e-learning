package vn.hcmute.elearning.controller.forum.portal;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.hcmute.elearning.controller.forum.portal.interfaces.IPortalForumPostController;
import vn.hcmute.elearning.core.BaseController;
import vn.hcmute.elearning.core.PageResponseData;
import vn.hcmute.elearning.core.ResponseBase;
import vn.hcmute.elearning.dtos.StatusResponse;
import vn.hcmute.elearning.dtos.forum.post.request.CreatePostRequest;
import vn.hcmute.elearning.dtos.forum.post.request.DeletePostRequest;
import vn.hcmute.elearning.dtos.forum.post.request.GetPostPagingRequest;
import vn.hcmute.elearning.dtos.forum.post.request.UpdatePostRequest;
import vn.hcmute.elearning.dtos.forum.post.response.PostInfo;

@RestController
public class PortalForumPostHandler extends BaseController implements IPortalForumPostController {

    @Override
    public ResponseEntity<ResponseBase<StatusResponse>> createModel(CreatePostRequest request) {
        return IPortalForumPostController.super.createModel(request);
    }

    @Override
    public ResponseEntity<ResponseBase<StatusResponse>> deleteModel(DeletePostRequest id) {
        return IPortalForumPostController.super.deleteModel(id);
    }

    @Override
    public ResponseEntity<ResponseBase<PageResponseData<PostInfo>>> getInfoListWithFilterPaging(GetPostPagingRequest request, Pageable pageable) {
        return IPortalForumPostController.super.getInfoListWithFilterPaging(request, pageable);
    }

    @Override
    public ResponseEntity<ResponseBase<StatusResponse>> updateModel(UpdatePostRequest request) {
        return IPortalForumPostController.super.updateModel(request);
    }
}
