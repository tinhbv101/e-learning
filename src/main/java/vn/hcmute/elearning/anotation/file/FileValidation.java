package vn.hcmute.elearning.anotation.file;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class FileValidation implements ConstraintValidator<File, MultipartFile> {
    private static final Integer MB = 1024 * 1024;

    private long fileSize;
    private String[] extensions;
    private String message;

    @Override
    public void initialize(File file) {
        this.message = file.message();
        this.fileSize = file.fileSize();
        this.extensions = file.extensions();
    }

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        return validFileSize(value) && validExtension(value);
    }

    private boolean validFileSize(MultipartFile file) {
        return this.fileSize * MB >= file.getSize();
    }

    private boolean validExtension(MultipartFile file) {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        return Arrays.stream(this.extensions).anyMatch(e -> e.equalsIgnoreCase(extension));
    }
}
