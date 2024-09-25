package vn.hcmute.elearning.controller.forum.portal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.hcmute.elearning.controller.forum.portal.interfaces.IPortalForumCategoryController;
import vn.hcmute.elearning.core.BaseController;
import vn.hcmute.elearning.core.BaseResponseList;
import vn.hcmute.elearning.core.ResponseBase;
import vn.hcmute.elearning.dtos.forum.category.request.GetCategoryListRequest;
import vn.hcmute.elearning.dtos.forum.category.response.CategoryInfo;

@RestController
public class PortalForumCategoryController extends BaseController implements IPortalForumCategoryController {

    @Override
    public ResponseEntity<ResponseBase<BaseResponseList<CategoryInfo>>> getInfoListWithFilter(GetCategoryListRequest request) {
        return IPortalForumCategoryController.super.getInfoListWithFilter(request);
    }
}
