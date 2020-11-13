package com.aerospike.model.repository;

import com.aerospike.model.IpTcpRecord;
import org.springframework.data.aerospike.repository.AerospikeRepository;

public interface PacketRepository  extends AerospikeRepository<IpTcpRecord, String> {
}
