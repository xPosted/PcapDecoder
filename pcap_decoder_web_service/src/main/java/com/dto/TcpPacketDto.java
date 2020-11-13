package com.dto;

import java.time.LocalDateTime;

import com.sicknet.protocol.tcp.model.IpTcpEntity;
import lombok.Data;

@Data
public class TcpPacketDto {

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

    private int payloadLen;
}
