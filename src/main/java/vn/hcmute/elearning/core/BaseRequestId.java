package vn.hcmute.elearning.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
public class BaseRequestId<ID> extends BaseRequestData implements BaseID<ID>{

    private ID id;
}
