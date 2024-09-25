package vn.hcmute.elearning.handler.question;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.question.request.GetQuestionRequest;
import vn.hcmute.elearning.dtos.question.response.GetQuestionResponse;
import vn.hcmute.elearning.dtos.question.response.QuestionResponse;
import vn.hcmute.elearning.entity.Exam;
import vn.hcmute.elearning.entity.FillCorrect;
import vn.hcmute.elearning.entity.Option;
import vn.hcmute.elearning.entity.Question;
import vn.hcmute.elearning.service.interfaces.IExamService;
import vn.hcmute.elearning.service.interfaces.IFillCorrectService;
import vn.hcmute.elearning.service.interfaces.IOptionService;
import vn.hcmute.elearning.service.interfaces.IQuestionService;
import vn.hcmute.elearning.service.specifications.QuestionSpecification;
import vn.hcmute.elearning.utils.Paginate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetQuestionHandler extends RequestHandler<GetQuestionRequest, GetQuestionResponse> {
    private final IExamService examService;
    private final IQuestionService questionService;
    private final QuestionSpecification questionSpecification;
    private final IOptionService optionService;
    private final IFillCorrectService fillCorrectService;

    @Override
    public GetQuestionResponse handle(GetQuestionRequest request) {
        Exam exam = null;
        if (request.getExamId() != null) {
            exam = examService.getExamById(request.getExamId());
        }
        Specification<Question> specification = Specification.where(questionSpecification.equalExam(exam))
            .and(questionSpecification.equalId(request.getId()));
        Page<Question> page = questionService.getQuestion(specification, request.getPageable());
        List<QuestionResponse> questions = page.getContent().parallelStream().map(question -> {
            List<Option> options = optionService.getOptionsByQuestion(question.getId());
            List<FillCorrect> fillCorrects = new ArrayList<>(fillCorrectService.getByQuestion(question.getId()));
            return new QuestionResponse(question, options, fillCorrects);
        }).collect(Collectors.toList());
        return new GetQuestionResponse(questions, new Paginate(page));
    }
}
