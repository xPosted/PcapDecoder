package com.dto;

import java.time.LocalDateTime;

import com.aerospike.model.Uploads;
import lombok.Builder;
import lombok.Data;

@Data
public class UploadDto {

    String id;

    String description;
    LocalDateTime timestamp;
    String status;

    public UploadDto(Uploads uploads) {
        this.id = uploads.getId();
        this.description = uploads.getDescription();
        this.timestamp = uploads.getTimestamp();
        this.status = "OK";
    }

    public UploadDto(String status) {
        this.status = status;
    }
}
