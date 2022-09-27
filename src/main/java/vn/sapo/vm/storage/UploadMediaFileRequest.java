package vn.sapo.vm.storage;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

public class UploadMediaFileRequest {
    @NotNull
    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
