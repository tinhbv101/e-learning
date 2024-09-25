package vn.hcmute.elearning.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;
import vn.hcmute.elearning.common.Constant;
import vn.hcmute.elearning.controller.interfaces.IAnswerController;
import vn.hcmute.elearning.core.BaseController;
import vn.hcmute.elearning.core.ResponseBase;
import vn.hcmute.elearning.dtos.answer.request.GetCurrentAnswerRequest;
import vn.hcmute.elearning.dtos.answer.request.GetSubmitAnswerByExamResultRequest;
import vn.hcmute.elearning.dtos.answer.request.StartDoExamRequest;
import vn.hcmute.elearning.dtos.answer.request.SubmitAnswersRequest;
import vn.hcmute.elearning.dtos.answer.response.GetCurrentAnswerResponse;
import vn.hcmute.elearning.dtos.answer.response.GetSubmitAnswerByExamResultResponse;
import vn.hcmute.elearning.dtos.answer.response.StartDoExamResponse;
import vn.hcmute.elearning.dtos.answer.response.SubmitAnswersResponse;
import vn.hcmute.elearning.entity.Exam;
import vn.hcmute.elearning.entity.ExamResult;
import vn.hcmute.elearning.enums.ResponseCode;
import vn.hcmute.elearning.exception.InternalException;
import vn.hcmute.elearning.service.RedisService;
import vn.hcmute.elearning.service.interfaces.IExamResultService;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AnswerController extends BaseController implements IAnswerController {
    private final IExamResultService examResultService;
    private final RedisService redisService;
    private final SimpMessagingTemplate messagingTemplate;

    private static final String TOPIC_EXAM_SAVE_STATUS = "/exam/save-status";

    @Override
    public void cacheAnswers(SubmitAnswersRequest request) {
        log.info("has submit answers message: {}", request.getCode());

        ExamResult examResult = examResultService.getByCodeNotNull(request.getCode());
        Exam exam = examResult.getExam();
        if (exam == null) {
            throw new InternalException(ResponseCode.EXAM_NOT_FOUND);
        }
        try {
            long time = exam.getTimeMinute();
            redisService.setValue(Constant.REDIS_ANSWERS_PREFIX + request.getCode(), request);
            redisService.setTimeOut(Constant.REDIS_ANSWERS_PREFIX + request.getCode(), (time + 2) * 60L);
            messagingTemplate.convertAndSendToUser(examResult.getUser().getId(), TOPIC_EXAM_SAVE_STATUS, "success");
        } catch (RuntimeException e) {
            messagingTemplate.convertAndSendToUser(examResult.getUser().getId(), TOPIC_EXAM_SAVE_STATUS, "failed");
        }
    }

    @Override
    public ResponseEntity<ResponseBase<SubmitAnswersResponse>> submitAnswers(SubmitAnswersRequest request) {
        return this.execute(request, SubmitAnswersResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<GetSubmitAnswerByExamResultResponse>> getSubmitAnswerByExamResult(GetSubmitAnswerByExamResultRequest request, Pageable pageable) {
        return this.execute(request, GetSubmitAnswerByExamResultResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<StartDoExamResponse>> startDoExam(StartDoExamRequest request) {
        return this.execute(request, StartDoExamResponse.class);
    }

    @Override
    public ResponseEntity<ResponseBase<GetCurrentAnswerResponse>> getCurrentAnswers(String code) {
        return this.execute(new GetCurrentAnswerRequest(code), GetCurrentAnswerResponse.class);
    }
}
