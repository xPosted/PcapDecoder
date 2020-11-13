package com.sicknet.protocol.tcp.model.payload.inmemory;

import com.sicknet.protocol.tcp.model.TcpSessionDescription;
import com.sicknet.protocol.tcp.model.payload.PayloadHolder;
import com.sicknet.protocol.tcp.model.payload.HolderFactory;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class InMemoryPayloadFactory implements HolderFactory {

    @Override
    public PayloadHolder getHolder(TcpSessionDescription description) {
        return new InMemoryPayloadHolder(description);
    }
}
