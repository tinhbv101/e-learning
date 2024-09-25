package vn.hcmute.elearning.handler.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.option.request.CreateOptionRequest;
import vn.hcmute.elearning.dtos.question.request.CreateQuestionRequest;
import vn.hcmute.elearning.dtos.question.response.QuestionResponse;
import vn.hcmute.elearning.entity.Exam;
import vn.hcmute.elearning.entity.FillCorrect;
import vn.hcmute.elearning.entity.Option;
import vn.hcmute.elearning.entity.Question;
import vn.hcmute.elearning.enums.QuestionType;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.model.FillCorrectInfo;
import vn.hcmute.elearning.service.interfaces.*;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CreateQuestionHandler extends RequestHandler<CreateQuestionRequest, QuestionResponse> {
    private final IQuestionService questionService;
    private final IExamService examService;
    private final IOptionService optionService;
    private final IFillCorrectService fillCorrectService;
    private final IExamResultService examResultService;

    @Override
    public QuestionResponse handle(CreateQuestionRequest request) {
        Exam exam = examService.getExamById(request.getExamId());
        if (exam == null) {
            throw new InternalException(ResponseCode.EXAM_NOT_FOUND);
        }
        Question question = new Question();
        question.setExam(exam);
        question.setQuestionNo(request.getQuestionNo());
        question.setQuestionName(request.getQuestionName());
        question.setPoint(request.getPoint());
        question.setQuestionType(request.getQuestionType());
        question = questionService.createQuestion(question);
        List<Option> options = null;
        List<FillCorrect> fillCorrects = null;
        if (QuestionType.MULTIPLE_SELECT.equals(request.getQuestionType())
            || QuestionType.ONLY_ONE.equals(request.getQuestionType())) {
            optionService.deleteAllByQuestionId(question.getId());
            if (request.getOptions() != null && !request.getOptions().isEmpty()) {
                options = new ArrayList<>();
                if (QuestionType.ONLY_ONE.equals(request.getQuestionType())) {
                    optionService.deleteAllByQuestionId(question.getId());
                    int countCorrect = 0;
                    for (CreateOptionRequest createOptionRequest : request.getOptions()) {
                        if (Boolean.TRUE.equals(createOptionRequest.getCorrect())) {
                            countCorrect++;
                        }
                    }
                    if (countCorrect != 1) {
                        throw new RuntimeException("Phải có một đáp án đúng");
                    }
                }
                for (CreateOptionRequest createOptionRequest : request.getOptions()) {
                    Option option = new Option()
                        .setQuestion(question)
                        .setOptionName(createOptionRequest.getOptionName())
                        .setCorrect(createOptionRequest.getCorrect());
                    options.add(option);
                }
                options = optionService.saveAll(options);
            }
        } else {
            if (!CollectionUtils.isEmpty(request.getFillCorrects())) {
                fillCorrectService.deleteAllByQuestionId(question.getId());
                fillCorrects = new ArrayList<>();
                for (FillCorrectInfo fillCorrectInfo : request.getFillCorrects()) {
                    fillCorrects.add(new FillCorrect()
                        .setIndex(fillCorrectInfo.getIndex())
                        .setAnswer(fillCorrectInfo.getAnswer())
                        .setQuestion(question));
                }
                fillCorrects = fillCorrectService.saveAll(fillCorrects);
            }
        }
        return new QuestionResponse(question, options, fillCorrects);
    }
}
