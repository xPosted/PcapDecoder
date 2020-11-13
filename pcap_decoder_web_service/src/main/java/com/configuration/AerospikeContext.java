package com.configuration;

import java.util.Arrays;
import java.util.Collection;

import com.aerospike.client.Host;
import com.aerospike.client.policy.ClientPolicy;
import com.aerospike.client.policy.Policy;
import com.aerospike.client.policy.WritePolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.aerospike.config.AbstractAerospikeDataConfiguration;
import org.springframework.data.aerospike.repository.config.EnableAerospikeRepositories;


@Configuration
@EnableAerospikeRepositories(basePackages = {"com.aerospike.model.repository"})
public class AerospikeContext extends AbstractAerospikeDataConfiguration {

    private static final int DEFAULT_PORT = 3000;

    @Override
    protected Collection<Host> getHosts() {
        String hostsString = "127.0.0.1";
        Host[] hosts = Host.parseHosts(hostsString, DEFAULT_PORT);
        return Arrays.asList(hosts);
    }

    @Override
    protected String nameSpace() {
        return "test";
    }

    @Override
    protected ClientPolicy getClientPolicy() {

        ClientPolicy clientPolicy = new ClientPolicy();
        clientPolicy.failIfNotConnected = true;
        return clientPolicy;
    }

    @Bean
    public WritePolicy userStateWritePolicy() {

        WritePolicy writePolicy = new WritePolicy();
        writePolicy.maxRetries = 0;
        writePolicy.expiration = -1;
        return writePolicy;
    }

    @Bean
    public Policy userStateReadPolicy() {

        Policy policy = new Policy();
        policy.maxRetries = 2;
        policy.sleepBetweenRetries = 0;
        return policy;
    }


}
