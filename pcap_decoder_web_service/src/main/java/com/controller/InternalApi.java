package com.controller;

import java.util.List;

import com.dto.UploadResponsePage;
import com.service.UploadService;
import com.dto.UploadDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.availability.ApplicationAvailability;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/internal")
public class InternalApi {

    @Autowired
    private Integer value;
    @Autowired
    private ApplicationAvailability availability;
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    @Autowired
    private UploadService uploadService;

    @RequestMapping(value = "/upload", produces = "application/json", method = RequestMethod.POST, consumes = "multipart/form-data")
    public UploadDto handlePcap(@RequestParam MultipartFile file) {
        return uploadService.handleUpload(file);
    }

    @RequestMapping(value = "/list-uploads", produces = "application/json", method = RequestMethod.GET)
    public UploadResponsePage listUploads(@RequestParam(name = "page") int page, @RequestParam(name = "pageSize") int pageSize) {
        return uploadService.listUploads(page, pageSize);
    }

    @RequestMapping(value = "view-sessions", produces = "application/json", method = RequestMethod.GET)
    public UploadResponsePage viewSessions(@RequestParam(name = "upload_id") String uploadId,
                        @RequestParam(name = "page") int page,
                        @RequestParam(name = "pageSize") int pageSize) {
        return uploadService.viewSessions(uploadId,page,pageSize);
    }
}
