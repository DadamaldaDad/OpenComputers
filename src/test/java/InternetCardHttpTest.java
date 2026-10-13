package li.cil.oc.server.component;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class InternetCardHttpTest {
    @Test
    public void usesInputStreamForSuccessfulResponses() throws Exception {
        StubHttpURLConnection connection = new StubHttpURLConnection(200, "ok", "ignored");
        assertEquals("ok", read(InternetCard.responseStream(connection, false)));
        assertEquals(1, connection.inputStreamCalls);
        assertEquals(0, connection.errorStreamCalls);
    }

    @Test
    public void preservesLegacyExceptionForErrorResponses() throws Exception {
        StubHttpURLConnection connection = new StubHttpURLConnection(404, "unused", "not found");
        assertThrows(IOException.class, () -> InternetCard.responseStream(connection, false));
        assertEquals(1, connection.inputStreamCalls);
        assertEquals(0, connection.errorStreamCalls);
    }

    @Test
    public void returnsErrorBodyWhenExplicitlyAllowed() throws Exception {
        StubHttpURLConnection connection = new StubHttpURLConnection(404, "unused", "not found");
        assertEquals("not found", read(InternetCard.responseStream(connection, true)));
        assertEquals(0, connection.inputStreamCalls);
        assertEquals(1, connection.errorStreamCalls);
    }

    @Test
    public void returnsEmptyStreamWhenAllowedErrorHasNoBody() throws Exception {
        StubHttpURLConnection connection = new StubHttpURLConnection(503, "unused", null);
        assertEquals("", read(InternetCard.responseStream(connection, true)));
        assertEquals(0, connection.inputStreamCalls);
        assertEquals(1, connection.errorStreamCalls);
    }

    @Test
    public void usesInputStreamForSuccessfulResponsesWhenErrorBodiesAreEnabled() throws Exception {
        StubHttpURLConnection connection = new StubHttpURLConnection(204, "", "must not be read");
        assertEquals("", read(InternetCard.responseStream(connection, true)));
        assertEquals(1, connection.inputStreamCalls);
        assertEquals(0, connection.errorStreamCalls);
    }

    private static String read(InputStream stream) throws IOException {
        try (stream) { return new String(stream.readAllBytes(), StandardCharsets.UTF_8); }
    }

    private static final class StubHttpURLConnection extends HttpURLConnection {
        private final int code;
        private final String inputBody;
        private final String errorBody;
        int inputStreamCalls;
        int errorStreamCalls;

        StubHttpURLConnection(int code, String inputBody, String errorBody) throws Exception {
            super(new URL("http://example.invalid"));
            this.code = code;
            this.inputBody = inputBody;
            this.errorBody = errorBody;
        }

        @Override public void connect() {}
        @Override public void disconnect() {}
        @Override public boolean usingProxy() { return false; }
        @Override public int getResponseCode() { return code; }

        @Override
        public InputStream getInputStream() throws IOException {
            inputStreamCalls++;
            if (code >= 300) throw new IOException("HTTP error");
            return new ByteArrayInputStream(inputBody.getBytes(StandardCharsets.UTF_8));
        }

        @Override
        public InputStream getErrorStream() {
            errorStreamCalls++;
            return errorBody == null ? null : new ByteArrayInputStream(errorBody.getBytes(StandardCharsets.UTF_8));
        }
    }
}
