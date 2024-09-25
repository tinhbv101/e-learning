package vn.hcmute.elearning.handler.forum.category;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.PageResponseData;
import vn.hcmute.elearning.core.handler.IGetDetailFilterPagingHandler;
import vn.hcmute.elearning.dtos.forum.category.request.CmsGetCategoryDetailPagingRequest;
import vn.hcmute.elearning.dtos.forum.category.response.CategoryDetail;
import vn.hcmute.elearning.entity.forum.ForumCategory;
import vn.hcmute.elearning.mapper.forum.ForumCategoryMapper;
import vn.hcmute.elearning.repository.forum.ForumCategoryRepository;

@Getter
@Component
@RequiredArgsConstructor
public class CmsGetCategoryDetailFilterPagingHandler implements IGetDetailFilterPagingHandler<String, CmsGetCategoryDetailPagingRequest, CategoryDetail, ForumCategory, ForumCategoryMapper> {

    private final ForumCategoryMapper mapper;
    private final ForumCategoryRepository repository;

    @Override
    public PageResponseData<CategoryDetail> handle(CmsGetCategoryDetailPagingRequest request) {
        String status = request.getStatus() != null ? request.getStatus().name() : null;
        return new PageResponseData<>(repository.findAllCategoryDetailPaging(
                request.getTitle(),
                request.getParentId(),
                status,
                request.getPageable()
        ));
    }
}
