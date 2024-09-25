package vn.hcmute.elearning.service.forum;

import org.springframework.stereotype.Component;
import vn.hcmute.elearning.entity.forum.ForumCategory;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.repository.forum.ForumCategoryRepository;
import vn.hcmute.elearning.service.BaseService;

@Component
public class ForumCategoryService extends BaseService<String, ForumCategory, ForumCategoryRepository> {

    public ForumCategoryService(ForumCategoryRepository repository) {
        super(repository);
    }

    @Override
    protected ResponseCode notFound() {
        return ResponseCode.FORUM_CATEGORY_NOT_FOUND;
    }
}
