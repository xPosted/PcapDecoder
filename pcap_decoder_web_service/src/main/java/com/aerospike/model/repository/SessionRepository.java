package com.aerospike.model.repository;

import com.aerospike.model.SessionRecord;
import org.springframework.data.aerospike.repository.AerospikeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SessionRepository  extends AerospikeRepository<SessionRecord, String> {

    Page<SessionRecord> findAllByUploadIdOrderByTimestamp(String uploadId, Pageable pageable);
}
