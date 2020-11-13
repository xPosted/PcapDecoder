package com.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageService {

    @Value("${service.home.path}")
    private String HOME_PATH;

    public String storePcapFile(MultipartFile file) throws IOException {

            byte[] bytes = file.getBytes();
            String fullPath = HOME_PATH + Instant.now().toEpochMilli() + ".pcap";
            BufferedOutputStream stream =
                    new BufferedOutputStream(new FileOutputStream(new File(fullPath)));
            stream.write(bytes);
            stream.close();
            return fullPath;

    }
}
