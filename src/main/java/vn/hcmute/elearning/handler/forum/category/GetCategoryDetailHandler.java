package vn.hcmute.elearning.handler.forum.category;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.handler.IGetDetailModelByIdHandler;
import vn.hcmute.elearning.dtos.forum.category.request.GetCategoryDetailRequest;
import vn.hcmute.elearning.dtos.forum.category.response.CategoryDetail;
import vn.hcmute.elearning.entity.forum.ForumCategory;
import vn.hcmute.elearning.mapper.forum.ForumCategoryMapper;
import vn.hcmute.elearning.repository.forum.ForumCategoryRepository;

@Getter
@Component
@RequiredArgsConstructor
public class GetCategoryDetailHandler implements IGetDetailModelByIdHandler<String, GetCategoryDetailRequest, CategoryDetail, ForumCategory, ForumCategoryMapper> {

    private final ForumCategoryRepository repository;
    private final ForumCategoryMapper mapper;
}
