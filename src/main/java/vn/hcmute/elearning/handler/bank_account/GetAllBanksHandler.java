package vn.hcmute.elearning.handler.bank_account;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import vn.hcmute.elearning.core.RequestHandler;
import vn.hcmute.elearning.dtos.bank_account.request.GetAllBanksRequest;
import vn.hcmute.elearning.dtos.bank_account.response.BankResponse;
import vn.hcmute.elearning.dtos.bank_account.response.GetAllBanksResponse;
import vn.hcmute.elearning.entity.Bank;
import vn.hcmute.elearning.service.interfaces.IBankService;
import vn.hcmute.elearning.utils.Paginate;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetAllBanksHandler extends RequestHandler<GetAllBanksRequest, GetAllBanksResponse> {
    private final IBankService bankService;

    @Override
    public GetAllBanksResponse handle(GetAllBanksRequest request) {
        Page<Bank> page = bankService.getAll(request.getSpecification(), request.getPageable());
        List<BankResponse> response = page.stream().parallel().map(BankResponse::new).collect(Collectors.toList());
        return new GetAllBanksResponse(response,new Paginate(page));
    }
}
