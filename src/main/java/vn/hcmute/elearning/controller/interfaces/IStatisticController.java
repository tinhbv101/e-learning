package vn.hcmute.elearning.controller.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.hcmute.elearning.core.ResponseBase;
import vn.hcmute.elearning.dtos.statistic.request.CmsGetCardsInfoRequest;
import vn.hcmute.elearning.dtos.statistic.request.CmsGetStatisticRequest;
import vn.hcmute.elearning.dtos.statistic.request.TeacherGetCardsInfoRequest;
import vn.hcmute.elearning.dtos.statistic.request.TeacherGetStatisticRequest;
import vn.hcmute.elearning.dtos.statistic.response.CmsGetCardsInfoResponse;
import vn.hcmute.elearning.dtos.statistic.response.CmsGetStatisticResponse;
import vn.hcmute.elearning.dtos.statistic.response.TeacherGetCardsInfoResponse;
import vn.hcmute.elearning.dtos.statistic.response.TeacherGetStatisticResponse;

@Tag(name = "Statistic Controller")
@RequestMapping("/api/statistic")
public interface IStatisticController {

    @Operation(
        summary = "[Cms]Lấy thông tin card",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @GetMapping("/cms/v1/getCardsInfo")
    ResponseEntity<ResponseBase<CmsGetCardsInfoResponse>> cmsGetCardsInfo(@ParameterObject CmsGetCardsInfoRequest request);

    @Operation(
        summary = "[Cms]Lấy thông tin biểu đồ",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @GetMapping("/cms/v1/getStatistic")
    ResponseEntity<ResponseBase<CmsGetStatisticResponse>> cmsGetStatistic(@ParameterObject CmsGetStatisticRequest request);

    @Operation(
        summary = "[Teacher]Lấy thông tin card",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @GetMapping("/teacher/v1/getCardsInfo")
    ResponseEntity<ResponseBase<TeacherGetCardsInfoResponse>> teacherGetCardsInfo(@ParameterObject TeacherGetCardsInfoRequest request);

    @Operation(
        summary = "[Teacher]Lấy thông tin biểu đồ",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @GetMapping("/teacher/v1/getStatistic")
    ResponseEntity<ResponseBase<TeacherGetStatisticResponse>> teacherGetStatistic(@ParameterObject TeacherGetStatisticRequest request);
}
