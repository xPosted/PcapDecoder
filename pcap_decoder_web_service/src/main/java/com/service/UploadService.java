package com.service;


import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.aerospike.model.IpTcpRecord;
import com.aerospike.model.SessionRecord;
import com.aerospike.model.Uploads;
import com.aerospike.model.repository.PacketRepository;
import com.aerospike.model.repository.SessionRepository;
import com.aerospike.model.repository.UploadsRepository;
import com.dto.SessionDto;
import com.dto.UploadResponsePage;
import com.sicknet.PcapDecoderService;
import com.dto.UploadDto;
import com.sicknet.protocol.tcp.TcpProtocolDecoder;
import com.sicknet.protocol.tcp.model.IpTcpEntity;
import com.sicknet.protocol.tcp.model.TcpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadService {


    private PcapDecoderService pcapDecoderService = new PcapDecoderService();
    @Autowired
    private StorageService storageService;
    @Autowired
    private UploadsRepository uploadsRepository;
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private PacketRepository packetRepository;

    public SessionDto sessionDetails(String sessionId, boolean srcPayload, boolean dstPayload, boolean payload) {
        SessionRecord record = sessionRepository.findById(sessionId).orElseThrow(() -> new RuntimeException("Session not found"));
        SessionDto preparedDto = new SessionDto(record);
        List<IpTcpRecord> packets = packetRepository.findAllBySessionIdOrderByTimestamp(sessionId);
        StringBuilder sessionPayload = new StringBuilder();
        packets.stream().forEach(packet -> sessionPayload.append(packet.getPayload()));
         preparedDto.setPayload(new String(Base64.getDecoder().decode(sessionPayload.toString())));
         return preparedDto;
    }

    public UploadResponsePage viewSessions(String uploadId, int page, int size) {
        Page<SessionRecord> sessions = sessionRepository.findAllByUploadIdOrderByTimestamp(uploadId, PageRequest.of(page, size));
        List<SessionDto> sessionDtos = sessions.get().map(SessionDto::new).collect(Collectors.toList());
        return UploadResponsePage.builder().uploads(sessionDtos).page(page).itemsPerPage(size).pageCount(sessions.getTotalPages()).build();
    }

    public UploadResponsePage listUploads(int page, int size) {
         Page<Uploads> uploads = uploadsRepository.findAll(PageRequest.of(page, size));
         List<UploadDto> uploadDtos = uploads.get().map(UploadDto::new).collect(Collectors.toList());
        return UploadResponsePage.builder().uploads(uploadDtos).page(page).itemsPerPage(uploads.getTotalPages()).build();
    }

    public UploadDto handleUpload(MultipartFile file) {
        try {
            String pathToPcap = storageService.storePcapFile(file);
            TcpProtocolDecoder tcpProtocolDecoder = pcapDecoderService.decodePcap(pathToPcap);
            persist(tcpProtocolDecoder);
        } catch (Exception e) {
            e.printStackTrace();
            return new UploadDto("ERROR");
        }
        return new UploadDto("STARTED");
    }

    public void persist(TcpProtocolDecoder decoder) {

        String uploadID = UUID.randomUUID().toString();
        Uploads uploads = new Uploads(uploadID, "description", LocalDateTime.now());
        uploadsRepository.save(uploads);
        decoder.closedSessionMap.values().stream().forEach(session -> persistSession(uploadID, session));
    }

    public void persistSession(String uploadId, TcpSession session) {
        String sessionId = UUID.randomUUID().toString();
        SessionRecord sessionRecord = SessionRecord.initial(sessionId, uploadId).withSession(session);
        sessionRepository.save(sessionRecord);
        session.getPacketQueue().stream().forEach(packet -> persistPacket(sessionId, packet));
    }

    public void persistPacket(String sessionId, IpTcpEntity packet) {
        IpTcpRecord packetRecord = IpTcpRecord.initial(UUID.randomUUID().toString(), sessionId).withPacket(packet);
        packetRepository.save(packetRecord);
    }
}
