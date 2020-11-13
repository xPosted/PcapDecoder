package com.aerospike.model.repository;

import java.util.List;

import com.aerospike.model.IpTcpRecord;
import com.sicknet.protocol.tcp.model.IpTcpEntity;
import org.springframework.data.aerospike.repository.AerospikeRepository;

public interface PacketRepository  extends AerospikeRepository<IpTcpRecord, String> {

    List<IpTcpRecord> findAllBySessionIdOrderByTimestamp(String sessionId);
}
