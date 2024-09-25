package vn.hcmute.elearning.handler.teacher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.PageResponseData;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.teacher.request.GetListTeacherRequest;
import vn.hcmute.elearning.entity.Teacher;
import vn.hcmute.elearning.mapper.ITeacherMapper;
import vn.hcmute.elearning.model.teacher.TeacherDto;
import vn.hcmute.elearning.service.interfaces.ITeacherService;
import vn.hcmute.elearning.service.specifications.TeacherSpecification;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetListTeacherHandler extends RequestHandler<GetListTeacherRequest, PageResponseData<TeacherDto>> {
    private final TeacherSpecification teacherSpecification;
    private final ITeacherService teacherService;
    private final ITeacherMapper teacherMapper;

    @Override
    public PageResponseData<TeacherDto> handle(GetListTeacherRequest request) {
        Specification<Teacher> specification = Specification
                .where(teacherSpecification.equalStatus(request.getStatus()))
                .and(teacherSpecification.likeUserName(request.getTeacherName()))
                .and(teacherSpecification.gteCreateDate(request.fromDatetime(request.getFromDate())))
                .and(teacherSpecification.lteCreateDate(request.toDatetime(request.getToDate())))
                .and(teacherSpecification.gteApproveDate(request.fromDatetime(request.getApproveFromDate())))
                .and(teacherSpecification.lteApproveDate(request.fromDatetime(request.getApproveToDate())))
                .and(teacherSpecification.equalPhone(request.getPhone()));

        Page<Teacher> page = teacherService.getListTeacher(specification, request.getPageable());
        List<TeacherDto> teacherDtos = teacherMapper.toListTeacherDto(page.getContent());
        return new PageResponseData<>(teacherDtos, page);
    }
}
