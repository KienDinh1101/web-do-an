package application.controller.api;

import application.constant.MyConstant;
import application.model.api.FileUploadResult;
import application.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/upload")
public class UploadApiController {

    @Autowired
    FileStorageService storageService;

    @PostMapping("/upload-image")
    public FileUploadResult uploadImage(
            @RequestParam("file") MultipartFile file) {
        String message = "";
        FileUploadResult result = new FileUploadResult();
        try {
            String newFilename = storageService.store(file);
            message = "You successfully uploaded " +
                    file.getOriginalFilename() + "!";
            result.setMessage(message);
            result.setSuccess(true);
            result.setLink("/link/" +
                    newFilename);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @PostMapping("/upload-editor")
    public String uploadEditor(
            @RequestPart("upload") MultipartFile file, @RequestParam(value = "CKEditorFuncNum", required = false) String callback) {
        String imgUrl= "";
        try {
            String newFilename = storageService.store(file);
            imgUrl = MyConstant.MY_URL +"/link/" + newFilename;
        } catch (Exception e) {

        }

        StringBuffer sb = new StringBuffer();
        sb.append("<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction(");
        sb.append(callback);
        sb.append(",'");
        sb.append(imgUrl);
        sb.append("','image uploaded success!!')</script>");


        return sb.toString();
    }
}
