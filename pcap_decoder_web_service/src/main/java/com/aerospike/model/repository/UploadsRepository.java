package com.aerospike.model.repository;

import com.aerospike.model.Uploads;
import org.springframework.data.aerospike.repository.AerospikeRepository;

public interface UploadsRepository  extends AerospikeRepository<Uploads, String> {
}
