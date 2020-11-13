package com.dto;

import java.time.LocalDateTime;

import com.aerospike.model.SessionRecord;
import lombok.Data;

@Data
public class SessionDto {

    String id;

    int srcPort;
    int dstPort;

    String srcAddr;
    String dstAddr;

    LocalDateTime timestamp;

    String srcPayload;
    String dstPayload;
    String payload;

    public SessionDto(SessionRecord record) {
        this.id = record.getId();
        this.srcAddr = record.getSrcAddr();
        this.dstAddr = record.getDstAddr();
        this.srcPort = record.getSrcPort();
        this.dstPort = record.getDstPort();
        this.timestamp = record.getTimestamp();
    }
}
