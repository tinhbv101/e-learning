package vn.hcmute.elearning.controller.forum.cms;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.hcmute.elearning.controller.forum.cms.interfaces.ICmsForumCategoryController;
import vn.hcmute.elearning.core.BaseController;
import vn.hcmute.elearning.core.PageResponseData;
import vn.hcmute.elearning.core.ResponseBase;
import vn.hcmute.elearning.dtos.StatusResponse;
import vn.hcmute.elearning.dtos.forum.category.request.*;
import vn.hcmute.elearning.dtos.forum.category.response.CategoryDetail;
import vn.hcmute.elearning.dtos.forum.category.response.CategoryInfo;

@RestController
public class ForumCategoryController extends BaseController implements ICmsForumCategoryController {

    @Override
    public ResponseEntity<ResponseBase<StatusResponse>> createModel(CreateCategoryRequest request) {
        return ICmsForumCategoryController.super.createModel(request);
    }

    @Override
    public ResponseEntity<ResponseBase<StatusResponse>> deleteModel(DeleteCategoryRequest id) {
        return ICmsForumCategoryController.super.deleteModel(id);
    }

    @Override
    public ResponseEntity<ResponseBase<CategoryDetail>> getDetailById(GetCategoryDetailRequest id) {
        return ICmsForumCategoryController.super.getDetailById(id);
    }

    @Override
    public ResponseEntity<ResponseBase<StatusResponse>> updateModel(UpdateCategoryRequest request) {
        return ICmsForumCategoryController.super.updateModel(request);
    }

    @Override
    public ResponseEntity<ResponseBase<PageResponseData<CategoryDetail>>> getDetailWithFilterPaging(CmsGetCategoryDetailPagingRequest request, Pageable pageable) {
        return ICmsForumCategoryController.super.getDetailWithFilterPaging(request, pageable);
    }
}
