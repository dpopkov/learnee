package learn.ee.pj4wcustsupport.chat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.websocket.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public class ChatMessageCodec implements Encoder.BinaryStream<ChatMessage>, Decoder.BinaryStream<ChatMessage> {
    private static final Logger log = LogManager.getLogger();
    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.findAndRegisterModules();
        MAPPER.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
    }

    @Override
    public void encode(ChatMessage chatMessage, OutputStream os) throws EncodeException, IOException {
        log.traceEntry();
        try {
            MAPPER.writeValue(os, chatMessage);
        } catch (JsonGenerationException | JsonMappingException e) {
            throw new EncodeException(chatMessage, e.getMessage(), e);
        } finally {
            log.traceExit();
        }
    }

    @Override
    public ChatMessage decode(InputStream is) throws DecodeException, IOException {
        log.traceEntry();
        try {
            return ChatMessageCodec.MAPPER.readValue(is, ChatMessage.class);
        } catch (JsonParseException | JsonMappingException e) {
            throw new DecodeException((ByteBuffer) null, e.getMessage(), e);
        } finally {
            log.traceExit();
        }
    }

    @Override
    public void init(EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }
}
