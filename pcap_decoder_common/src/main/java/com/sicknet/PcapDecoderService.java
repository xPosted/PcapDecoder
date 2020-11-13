package com.sicknet;

import com.sicknet.packet.handler.SimplePacketHandler;
import com.sicknet.protocol.tcp.TcpProtocolDecoder;
import com.sicknet.protocol.tcp.model.payload.HolderFactory;
import com.sicknet.protocol.tcp.model.payload.inmemory.InMemoryPayloadFactory;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.Pcaps;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class PcapDecoderService {

    public TcpProtocolDecoder decodePcap(String path) throws Exception {

        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
        SimplePacketHandler packetHandler = new SimplePacketHandler();

        HolderFactory inMemHolderFactory = InMemoryPayloadFactory.builder().build();
        TcpProtocolDecoder tcpDecoder =
                new TcpProtocolDecoder(inMemHolderFactory);
        PcapHandle handle = Pcaps.openOffline(path);

        packetHandler.addDecoder(tcpDecoder);
        handle.loop(-1,packetHandler, singleThreadPool);
        singleThreadPool.shutdown();
        singleThreadPool.awaitTermination(1, TimeUnit.HOURS);
        return tcpDecoder;
    }




}
