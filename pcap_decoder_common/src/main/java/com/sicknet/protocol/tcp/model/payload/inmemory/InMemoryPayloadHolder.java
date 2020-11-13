package com.sicknet.protocol.tcp.model.payload.inmemory;

import com.sicknet.protocol.tcp.model.IpTcpEntity;
import com.sicknet.protocol.tcp.model.TcpSessionDescription;
import com.sicknet.protocol.tcp.model.payload.PayloadHolder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class InMemoryPayloadHolder implements PayloadHolder {

    private ByteArrayOutputStream sourcePayload = new ByteArrayOutputStream();
    private ByteArrayOutputStream destinationPayload = new ByteArrayOutputStream();
    private TcpSessionDescription description;


    public InMemoryPayloadHolder(TcpSessionDescription description) {
        this.description = description;
    }

    @Override
    public void put(IpTcpEntity ipTcpEntity, byte[] payload) {
        try {
            if (ipTcpEntity.getSrcAddr().equals(description.getSrcAddr())) {
                sourcePayload.write(payload);
            }
        } catch (IOException io) {
            throw new RuntimeException(io);
        }

        try {
            if (ipTcpEntity.getSrcAddr().equals(description.getDstAddr())) {
                destinationPayload.write(payload);
            }
        } catch (IOException io) {
            throw new RuntimeException(io);
        }
    }

    @Override
    public void onSessionClosed() {
        try {
            sourcePayload.close();
            destinationPayload.close();
        } catch (IOException io) {
            throw new RuntimeException(io);
        }

    }

    @Override
    public byte[] getRawSourceData() {
        return sourcePayload.toByteArray();
    }

    @Override
    public byte[] getRawDestinationData() {
        return destinationPayload.toByteArray();
    }

    @Override
    public InputStream getSourceDataStream() {
        return new ByteArrayInputStream(sourcePayload.toByteArray());
    }

    @Override
    public InputStream getDestinationDataStream() {
        return new ByteArrayInputStream(destinationPayload.toByteArray());
    }
}
