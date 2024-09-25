package vn.hcmute.elearning.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import vn.hcmute.elearning.model.email.EmailModel;
import vn.hcmute.elearning.service.interfaces.IEmailService;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService implements IEmailService {
    private final JavaMailSender javaMailSender;

    private final TemplateEngine htmlTemplateEngine;

    @Value("${spring.mail.from}")
    private String mailFrom;

    @Async
    @SneakyThrows
    @Override
    public void sendEmail(EmailModel model) {
        try {
            Context context = prepareContext(model);
            MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
            MimeMessageHelper message = prepareMessage(mimeMessage, model);
            String textContent = this.htmlTemplateEngine.process(model.getTemplateName(), context);
            message.setText(this.reWriteVar(textContent, model.getParameterMap()), model.isHtml());
            this.javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            log.error("Lỗi gửi mail tới người dùng: {}", e.getMessage());
        }
        log.info("Gửi mail thành công tới: {}", String.join(", ", model.getTo()));
    }

    private String reWriteVar(String context, Map<String, Object> map) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            context = context.replace(String.format("[(${%s})]", entry.getKey()), entry.getValue().toString());
        }
        return context;
    }

    private Context prepareContext(EmailModel model) {
        // Prepare the evaluation context
        Context ctx = new Context();
        if (Objects.nonNull(model.getParameterMap())) {
            Set<String> keySet = model.getParameterMap().keySet();
            keySet.forEach(s -> ctx.setVariable(s, model.getParameterMap().get(s)));
        }
        return ctx;
    }

    @SneakyThrows
    private MimeMessageHelper prepareMessage(MimeMessage mimeMessage, EmailModel model) {
        // Prepare message using a Spring helper
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                "UTF-8");
        //Todo: addsubject
        message.setSubject(model.getSubject());
        message.setFrom(mailFrom);
        message.setTo(model.getTo());
        if (model.isHasAttachment()) {
            for (Map.Entry<String, Object> entry : model.getAttachments().entrySet()) {
                String key = entry.getKey();
                Object data = entry.getValue();
                if (data instanceof File) {
                    try (InputStream inputStream = new FileInputStream((File) data)) {
                        message.addAttachment(key, new ByteArrayResource(inputStream.readAllBytes()));
                    } catch (IOException e) {
                        log.error("export error: {}", e.getMessage());
                    } finally {
                        Files.delete(((File) data).toPath());
                    }
                } else if (data instanceof InputStream) {
                    InputStream inputStream = (InputStream) data;
                    message.addAttachment(key, new ByteArrayResource(inputStream.readAllBytes()));
                } else if (data instanceof byte[]) {
                    message.addAttachment(key, new ByteArrayResource((byte[]) data));
                }
            }
        }

        return message;
    }
}
