package vn.hcmute.elearning.handler.statistic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.statistic.request.CmsGetCardsInfoRequest;
import vn.hcmute.elearning.dtos.statistic.response.CmsGetCardsInfoResponse;
import vn.hcmute.elearning.service.interfaces.IStatisticService;

@Component
@RequiredArgsConstructor
@Slf4j
public class CmsGetCardsInfoHandler extends RequestHandler<CmsGetCardsInfoRequest, CmsGetCardsInfoResponse> {

    private final IStatisticService statisticService;
    @Override
    public CmsGetCardsInfoResponse handle(CmsGetCardsInfoRequest request) {
        return statisticService.cmsGetCardsInfo(request.getFromDate(), request.getToDate());
    }
}
