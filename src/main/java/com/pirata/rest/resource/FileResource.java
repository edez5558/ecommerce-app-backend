package com.pirata.rest.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.base.Optional;
import com.pirata.rest.service.FileService;

@RestController
@RequestMapping("/api/file")
public class FileResource {
    @Autowired
    private FileService fileService; 

    @RequestMapping(value = "/upload", headers = ("content-type=multipart/*"), method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Optional<String>> upload(@RequestParam(name = "file") MultipartFile file){
        try{
            return ResponseEntity.ok(Optional.of(fileService.uploadFile(file)));
        }catch(Exception e){
            return ResponseEntity.ok(Optional.fromNullable(null));
        }
    }
}
