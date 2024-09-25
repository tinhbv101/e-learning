package vn.hcmute.elearning.dtos.question.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import vn.hcmute.elearning.core.BaseRequestData;
import vn.hcmute.elearning.dtos.option.request.CreateOptionRequest;
import vn.hcmute.elearning.enums.QuestionType;
import vn.hcmute.elearning.model.FillCorrectInfo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class UpdateQuestionRequest extends BaseRequestData {
    @NotBlank
    private String id;
    @NotNull
    private Integer questionNo;
    private String questionName;

    private List<FillCorrectInfo> fillCorrects;
    @NotNull
    private Float point;
    @NotNull
    private QuestionType questionType;

    private List<CreateOptionRequest> options;
}
