package com.aerospike.model;

import java.time.LocalDateTime;

import lombok.Value;
import org.springframework.data.aerospike.mapping.Document;
import org.springframework.data.annotation.Id;

@Value
@Document(collection = "UPLOADS")
public class Uploads {

    @Id
    String id;

    String description;
    LocalDateTime timestamp;
}
