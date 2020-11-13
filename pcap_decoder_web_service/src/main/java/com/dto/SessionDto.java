package com.dto;

import java.time.LocalDateTime;

import com.aerospike.model.SessionRecord;

public class SessionDto {

    int srcPort;
    int dstPort;

    String srcAddr;
    String dstAddr;

    LocalDateTime timestamp;

    public SessionDto(SessionRecord record) {
        this.srcAddr = record.getSrcAddr();
        this.dstAddr = record.getDstAddr();
        this.srcPort = record.getSrcPort();
        this.dstPort = record.getDstPort();
        this.timestamp = record.getTimestamp();
    }
}
