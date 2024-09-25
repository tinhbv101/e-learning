package vn.hcmute.elearning.service.interfaces;

import vn.hcmute.elearning.model.SendMailModel;

public interface IAsyncService {
    void sendMail(SendMailModel mailModel);
}
