package com.sicknet.protocol.tcp.model.payload;

import com.sicknet.protocol.tcp.model.TcpSessionDescription;

public interface HolderFactory {
    public PayloadHolder getHolder(TcpSessionDescription description);
}
