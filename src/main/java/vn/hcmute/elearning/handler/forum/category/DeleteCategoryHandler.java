package vn.hcmute.elearning.handler.forum.category;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.handler.IDeleteModelHandler;
import vn.hcmute.elearning.dtos.forum.category.request.DeleteCategoryRequest;
import vn.hcmute.elearning.entity.forum.ForumCategory;
import vn.hcmute.elearning.repository.forum.ForumCategoryRepository;

@Getter
@Component
@RequiredArgsConstructor
public class DeleteCategoryHandler implements IDeleteModelHandler<String, DeleteCategoryRequest, ForumCategory> {

    private final ForumCategoryRepository repository;
}
