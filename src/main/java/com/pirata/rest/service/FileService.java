package com.pirata.rest.service;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;

@Service
public class FileService {
    Bucket bucket;

    @EventListener
    public void init(ApplicationReadyEvent event){
        try{
            ClassPathResource serviceAccount = new ClassPathResource("firebase.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount.getInputStream()))
                .setStorageBucket("pir-ata.appspot.com")
                .build();

            FirebaseApp.initializeApp(options);

            bucket = StorageClient.getInstance().bucket();

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private String generateFileName(String originalFileName) {
        return UUID.randomUUID().toString() + "." + getExtension(originalFileName);
    }

    private String getExtension(String originalFileName) {
        String extension = "";

        int i = originalFileName.lastIndexOf(".");

        if(i > 0){
            extension = originalFileName.substring(i+1);
        }

        return extension;
    }
    
    public String uploadFile(MultipartFile file) throws IOException{
        String imageName = generateFileName(file.getOriginalFilename());

        Blob blob = bucket.create(imageName,file.getBytes());

        String downloadUrl = blob.getMediaLink();

        System.out.println(downloadUrl);

        return downloadUrl;
    }
}
