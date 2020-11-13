package com.aerospike.model;

import java.net.InetAddress;
import java.time.LocalDateTime;

import com.sicknet.protocol.tcp.model.TcpSession;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.springframework.data.aerospike.mapping.Document;
import org.springframework.data.annotation.Id;

@Value
@Document(collection = "SESSIONS")
@Builder(toBuilder = true)
public class SessionRecord {

    @Id
    String id;

    String uploadId;

    int srcPort;
    int dstPort;

    String srcAddr;
    String dstAddr;

    LocalDateTime timestamp;

    public SessionRecord withSession(TcpSession session) {
        return toBuilder()
                .srcAddr(session.getDescription().getSrcAddr().getHostAddress())
                .dstAddr(session.getDescription().getDstAddr().getHostAddress())
                .srcPort(session.getDescription().getSrcPort())
                .dstPort(session.getDescription()

                                .getDstPort())
                .timestamp(session.getDescription().getTimestamp()).build();
    }
    public static SessionRecord initial(String id, String uploadId) {
        return new SessionRecord(id,uploadId,0,0,null,null,null);
    }
}
