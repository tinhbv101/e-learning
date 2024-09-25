package vn.hcmute.elearning.handler.teacher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.StatusResponse;
import vn.hcmute.elearning.dtos.teacher.request.RejectRequestRoleTeacherRequest;
import vn.hcmute.elearning.entity.Teacher;
import vn.hcmute.elearning.enums.MailTemplateEnum;
import vn.hcmute.elearning.enums.ReasonDenyEkyc;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.enums.TeacherStatus;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.model.email.EmailModel;
import vn.hcmute.elearning.service.interfaces.IEmailService;
import vn.hcmute.elearning.service.interfaces.ITeacherService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class RejectRequestRoleTeacherHandler extends RequestHandler<RejectRequestRoleTeacherRequest, StatusResponse> {
    private final ITeacherService teacherService;
    private final IEmailService iEmailService;

    @Override
    public StatusResponse handle(RejectRequestRoleTeacherRequest request) {
        Teacher teacher = teacherService.getById(request.getTeacherId());
        if (teacher == null) {
            throw new InternalException(ResponseCode.TEACHER_NOT_FOUND);
        }
        List<String> reasons = new ArrayList<>();
        if (!CollectionUtils.isEmpty(request.getReason())) {
            reasons.addAll(request.getReason().stream().map(ReasonDenyEkyc::getDescription).collect(Collectors.toList()));
        }
        if (request.getDescriptionReason() != null) {
            reasons.add(request.getDescriptionReason());
        }

        // todo push notify to mail with reason deny
        Map<String, Object> params = new HashMap<>();
        params.put("customerName", String.format("%s %s", teacher.getUser().getFirstName(), teacher.getUser().getLastName()));
        for (int i = 0; i < reasons.size(); i++) {
            params.put(String.format("display%s", i + 1), "block");
            params.put(String.format("reason%s", i + 1), reasons.get(i));
        }
        EmailModel model = EmailModel.builder()
                .to(new String[]{teacher.getUser().getEmail()})
                .isHtml(true)
                .templateName(MailTemplateEnum.REJECT_TEACHER.name())
                .subject(MailTemplateEnum.REJECT_TEACHER.getSubject())
                .parameterMap(params)
                .build();
        iEmailService.sendEmail(model);

        String reason = String.join(", ", reasons);

        teacher.setStatus(TeacherStatus.REJECTED)
                .setReasonDeny(reason);
        teacher.setApproveDate(LocalDateTime.now());
        teacherService.save(teacher);

        return new StatusResponse();
    }
}
