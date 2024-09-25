package vn.hcmute.elearning.handler.forum.category;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.handler.IUpdateModelHandler;
import vn.hcmute.elearning.dtos.forum.category.request.UpdateCategoryRequest;
import vn.hcmute.elearning.entity.forum.ForumCategory;
import vn.hcmute.elearning.mapper.forum.ForumCategoryMapper;
import vn.hcmute.elearning.repository.forum.ForumCategoryRepository;

@Getter
@Component
@RequiredArgsConstructor
public class UpdateCategoryHandler implements IUpdateModelHandler<String, UpdateCategoryRequest, ForumCategory, ForumCategoryMapper> {

    private final ForumCategoryRepository repository;
    private final ForumCategoryMapper mapper;

    @Override
    public void postMapping(ForumCategory entity, UpdateCategoryRequest request) {
        if (request.getParentId() != null) {
            ForumCategory category = repository.findById(request.getParentId()).orElse(null);
            entity.setParentCategory(category);
        }
    }
}
