package vn.hcmute.elearning.handler.answer;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.answer.request.GetSubmitAnswerByExamResultRequest;
import vn.hcmute.elearning.dtos.answer.response.AnswerResponse;
import vn.hcmute.elearning.dtos.answer.response.GetSubmitAnswerByExamResultResponse;
import vn.hcmute.elearning.dtos.question.response.QuestionResponse;
import vn.hcmute.elearning.entity.*;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.repository.ExamResultRepository;
import vn.hcmute.elearning.service.interfaces.IAnswerService;
import vn.hcmute.elearning.service.interfaces.IFillCorrectService;
import vn.hcmute.elearning.service.interfaces.IOptionService;
import vn.hcmute.elearning.service.interfaces.IQuestionService;
import vn.hcmute.elearning.utils.Paginate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetSubmitAnswerByExamResultHandler extends RequestHandler<GetSubmitAnswerByExamResultRequest, GetSubmitAnswerByExamResultResponse> {

    private final ExamResultRepository examResultRepository;
    private final IQuestionService questionService;
    private final IAnswerService answerService;
    private final IOptionService optionService;
    private final IFillCorrectService fillCorrectService;

    @Override
    public GetSubmitAnswerByExamResultResponse handle(GetSubmitAnswerByExamResultRequest request) {
        ExamResult examResult = examResultRepository.findById(request.getExamResultId())
            .orElseThrow(() -> new InternalException(ResponseCode.EXAM_RESULT_NOT_FOUND));
        Page<Question> page = questionService.getPageQuestionByExam(examResult.getExam(), request.getPageable());
        List<Question> questions = page.getContent();
        Map<String, QuestionResponse> questionMap = questions.stream()
            .map(question -> {
                List<Option> options = optionService.getOptionsByQuestion(question.getId());
                List<FillCorrect> fillCorrects = new ArrayList<>(fillCorrectService.getByQuestion(question.getId()));
                return new QuestionResponse(question, options, fillCorrects);
            })
            .collect(Collectors.toMap(QuestionResponse::getId, Function.identity()));
        List<Answer> answers = answerService.getByCode(examResult.getCode());
        Map<String, AnswerResponse> answerMap = answers.stream()
            .map(AnswerResponse::new)
            .collect(Collectors.toMap(AnswerResponse::getQuestionId, Function.identity()));
        List<GetSubmitAnswerByExamResultResponse.SubmitAnswer> response = new ArrayList<>();
        for (Question question : questions) {
            response.add(new GetSubmitAnswerByExamResultResponse.SubmitAnswer(questionMap.get(question.getId()), answerMap.get(question.getId())));
        }
        return new GetSubmitAnswerByExamResultResponse(response, new Paginate(page.getNumber(), page.getSize(), (int)page.getTotalElements() ,page.getTotalPages()));
    }
}
