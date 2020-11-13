package com.aerospike.model;

import java.net.InetAddress;
import java.time.LocalDateTime;

import com.sicknet.protocol.tcp.model.TcpSession;
import lombok.Value;
import org.springframework.data.aerospike.mapping.Document;
import org.springframework.data.annotation.Id;

@Value
@Document(collection = "SESSIONS")
public class SessionRecord {

    @Id
    String id;

    String uploadId;

    int srcPort;
    int dstPort;

    String srcAddr;
    String dstAddr;

    LocalDateTime timestamp;

    public SessionRecord(String id, String uploadId, TcpSession session) {
        this.id = id;
        this.uploadId = uploadId;
        this.srcAddr = session.getDescription().getSrcAddr().getHostAddress();
        this.dstAddr = session.getDescription().getDstAddr().getHostAddress();
        this.srcPort = session.getDescription().getSrcPort();
        this.dstPort = session.getDescription().getDstPort();
        this.timestamp = session.getDescription().getTimestamp();
    }
}
