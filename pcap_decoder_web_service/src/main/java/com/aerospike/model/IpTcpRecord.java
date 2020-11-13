package com.aerospike.model;

import java.net.InetAddress;
import java.time.LocalDateTime;

import com.sicknet.protocol.tcp.model.IpTcpEntity;
import lombok.Data;
import lombok.Value;
import org.springframework.data.aerospike.mapping.Document;
import org.springframework.data.annotation.Id;

@Value
@Document(collection = "TCP_PACKETS")
public class IpTcpRecord {

    @Id
    private String id;

    private String sessionId;

    private String srcAddr;
    private String dstAddr;
    private int srcPort;
    private int dstPort;

    private int seq;
    private int ack;

    private boolean SYN;
    private boolean ACK0;
    private boolean URG;
    private boolean PSH;
    private boolean FIN;
    private boolean RST;

    private LocalDateTime timestamp;
    private String payload;
    private int payloadLen;

    public IpTcpRecord(String id, String sessionId, IpTcpEntity packet) {
        this.id = id;
        this.sessionId = sessionId;
        this.srcAddr = packet.getSrcAddr().getHostAddress();
        this.dstAddr = packet.getDstAddr().getHostAddress();
        this.srcPort = packet.getSrcPort();
        this.dstPort = packet.getDstPort();
        this.seq = packet.getSeq();
        this.ack = packet.getAck();
        this.SYN = packet.isSYN();
        this.ACK0 = packet.isACK0();
        this.URG = packet.isURG();
        this.PSH = packet.isPSH();
        this.FIN = packet.isFIN();
        this.RST = packet.isRST();
        this.timestamp = packet.getTimestamp();
        this.payloadLen = packet.getPayloadLen();
        this.payload = packet.getPayload();
    }
}
