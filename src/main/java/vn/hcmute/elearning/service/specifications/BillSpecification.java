package vn.hcmute.elearning.service.specifications;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.entity.Invoice;
import vn.hcmute.elearning.entity.User;
import vn.hcmute.elearning.service.interfaces.IUserService;

@Component
@RequiredArgsConstructor
public class BillSpecification {
    private final IUserService userService;

    public Specification<Invoice> equalUser(String userId) {
        User user = userService.getUserById(userId);
        return (root, query, criteriaBuilder) -> {
            if (user != null) {
                return criteriaBuilder.equal(root.get(Invoice.Fields.user), user);
            }
            return null;
        };
    }
}
