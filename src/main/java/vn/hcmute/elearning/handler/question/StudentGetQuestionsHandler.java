package vn.hcmute.elearning.handler.question;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.question.request.StudentGetQuestionsRequest;
import vn.hcmute.elearning.dtos.question.response.QuestionResponse;
import vn.hcmute.elearning.dtos.question.response.StudentGetQuestionsResponse;
import vn.hcmute.elearning.entity.*;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.service.interfaces.*;
import vn.hcmute.elearning.service.specifications.QuestionSpecification;
import vn.hcmute.elearning.utils.Paginate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class StudentGetQuestionsHandler extends RequestHandler<StudentGetQuestionsRequest, StudentGetQuestionsResponse> {
    private final IExamService examService;
    private final IQuestionService questionService;
    private final QuestionSpecification questionSpecification;
    private final IOptionService optionService;
    private final IUserService userService;
    private final IFillCorrectService fillCorrectService;

    @Override
    public StudentGetQuestionsResponse handle(StudentGetQuestionsRequest request) {
        User user = userService.getUserById(request.getUserId());
        if (user == null) {
            throw new InternalException(ResponseCode.USER_NOT_FOUND);
        }
        Exam exam = examService.getExamById(request.getExamId());
        Lesson lesson = exam.getLesson();
        if (lesson == null) {
            throw new InternalException(ResponseCode.LESSON_NOT_FOUND);
        }
        Course course = lesson.getCourse();
        if (course == null) {
            throw new InternalException(ResponseCode.COURSE_NOT_FOUND);
        }
        Set<User> courseUsers = course.getUsers();
        if (courseUsers == null || !courseUsers.contains(user)) {
            throw new InternalException(ResponseCode.USER_INVALID);
        }
        Specification<Question> specification = Specification.where(questionSpecification.equalExam(exam))
            .and(questionSpecification.equalId(request.getId()));
        Page<Question> page = questionService.getQuestion(specification, request.getPageable());
        List<QuestionResponse> questions = page.getContent().parallelStream().map(question -> {
            List<Option> options = optionService.getOptionsByQuestion(question.getId());
            List<FillCorrect> fillCorrects = new ArrayList<>(fillCorrectService.getByQuestion(question.getId()));
            return new QuestionResponse(question, options, fillCorrects, true);
        }).collect(Collectors.toList());
        return new StudentGetQuestionsResponse(questions, new Paginate(page));

    }
}
