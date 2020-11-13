package com.aerospike.model;

import java.net.InetAddress;
import java.time.LocalDateTime;

import com.sicknet.protocol.tcp.model.IpTcpEntity;
import lombok.Builder;
import lombok.Data;
import lombok.Value;
import org.springframework.data.aerospike.mapping.Document;
import org.springframework.data.annotation.Id;

@Value
@Builder(toBuilder = true)
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

    public IpTcpRecord withPacket(IpTcpEntity packet) {
        return toBuilder()
        .srcAddr(packet.getSrcAddr().getHostAddress())
        .dstAddr(packet.getDstAddr().getHostAddress())
        .srcPort(packet.getSrcPort())
        .dstPort(packet.getDstPort())
        .seq(packet.getSeq())
        .ack(packet.getAck())
        .SYN(packet.isSYN())
        .ACK0(packet.isACK0())
        .URG(packet.isURG())
        .PSH(packet.isPSH())
        .FIN(packet.isFIN())
        .RST(packet.isRST())
        .timestamp(packet.getTimestamp())
        .payloadLen(packet.getPayloadLen())
        .payload(packet.getPayload()).build();
    }

    public static IpTcpRecord initial(String id, String sessionId) {
        return new IpTcpRecord(id,sessionId,null,null,0,0,0,0,false,false,false,false,false,false,null,null,0);
    }
}
