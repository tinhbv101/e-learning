package vn.hcmute.elearning.service.interfaces;

import vn.hcmute.elearning.model.email.EmailModel;

public interface IEmailService {
    void sendEmail(EmailModel model);
}
