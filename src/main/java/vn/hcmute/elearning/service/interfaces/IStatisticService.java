package vn.hcmute.elearning.service.interfaces;

import vn.hcmute.elearning.dtos.statistic.response.CmsGetCardsInfoResponse;
import vn.hcmute.elearning.dtos.statistic.response.CmsGetStatisticResponse;
import vn.hcmute.elearning.dtos.statistic.response.TeacherGetCardsInfoResponse;
import vn.hcmute.elearning.dtos.statistic.response.TeacherGetStatisticResponse;

import java.time.LocalDate;

public interface IStatisticService {
    CmsGetCardsInfoResponse cmsGetCardsInfo(LocalDate fromDate, LocalDate toDate);
    CmsGetStatisticResponse cmsGetStatistic(LocalDate fromDate, LocalDate toDate);
    TeacherGetCardsInfoResponse teacherGetCardsInfo(String teacherId, LocalDate fromDate, LocalDate toDate);
    TeacherGetStatisticResponse teacherGetStatistic(String teacherId, LocalDate fromDate, LocalDate toDate);

}
