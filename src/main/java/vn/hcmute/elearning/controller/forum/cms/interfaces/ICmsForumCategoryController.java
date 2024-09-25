package vn.hcmute.elearning.controller.forum.cms.interfaces;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.hcmute.elearning.core.controller.*;
import vn.hcmute.elearning.dtos.StatusResponse;
import vn.hcmute.elearning.dtos.forum.category.request.*;
import vn.hcmute.elearning.dtos.forum.category.response.CategoryDetail;

@Tag(name = "[CMS] Forum Category Controller")
@RequestMapping("/api/v1/cms/forum/category")
public interface ICmsForumCategoryController extends
        ICreateModelController<CreateCategoryRequest, StatusResponse>,
        IGetDetailByIdController<GetCategoryDetailRequest, CategoryDetail>,
        IGetDetailFilterPagingController<CmsGetCategoryDetailPagingRequest, CategoryDetail>,
        IUpdateModelController<UpdateCategoryRequest, StatusResponse>,
        IDeleteModelController<DeleteCategoryRequest, StatusResponse> {
}
