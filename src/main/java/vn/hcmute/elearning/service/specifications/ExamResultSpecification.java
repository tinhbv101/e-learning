package vn.hcmute.elearning.service.specifications;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.entity.Exam;
import vn.hcmute.elearning.entity.ExamResult;
import vn.hcmute.elearning.entity.User;

@Component
public class ExamResultSpecification {
    public Specification<ExamResult> equalExamId(String examId) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isNotBlank(examId)) {
                return criteriaBuilder.equal(root.join(ExamResult.Fields.exam).get(Exam.Fields.id), examId);
            }
            return null;
        };
    }
    public Specification<ExamResult> equalUserId(String userId) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isNotBlank(userId)) {
                return criteriaBuilder.equal(root.join(ExamResult.Fields.user).get(User.Fields.id), userId);
            }
            return null;
        };
    }

}
