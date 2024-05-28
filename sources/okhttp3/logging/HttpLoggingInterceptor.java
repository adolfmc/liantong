package okhttp3.logging;

import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.concurrent.TimeUnit;
import okhttp3.Connection;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.platform.Platform;
import okio.Buffer;
import okio.BufferedSource;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class HttpLoggingInterceptor implements Interceptor {
    private static final Charset UTF8 = Charset.forName("UTF-8");
    private volatile Level level;
    private final Logger logger;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public enum Level {
        NONE,
        BASIC,
        HEADERS,
        BODY
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface Logger {
        public static final Logger DEFAULT = new Logger() { // from class: okhttp3.logging.HttpLoggingInterceptor.Logger.1
            @Override // okhttp3.logging.HttpLoggingInterceptor.Logger
            public void log(String str) {
                Platform.get().log(4, str, (Throwable) null);
            }
        };

        void log(String str);
    }

    public HttpLoggingInterceptor() {
        this(Logger.DEFAULT);
    }

    public HttpLoggingInterceptor(Logger logger) {
        this.level = Level.NONE;
        this.logger = logger;
    }

    public HttpLoggingInterceptor setLevel(Level level) {
        if (level == null) {
            throw new NullPointerException("level == null. Use Level.NONE instead.");
        }
        this.level = level;
        return this;
    }

    public Level getLevel() {
        return this.level;
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Headers headers;
        Level level = this.level;
        Request request = chain.request();
        if (level == Level.NONE) {
            return chain.proceed(request);
        }
        boolean z = level == Level.BODY;
        boolean z2 = z || level == Level.HEADERS;
        RequestBody body = request.body();
        boolean z3 = body != null;
        Connection connection = chain.connection();
        String str = "--> " + request.method() + ' ' + request.url() + ' ' + (connection != null ? connection.protocol() : Protocol.HTTP_1_1);
        if (!z2 && z3) {
            str = str + " (" + body.contentLength() + "-byte body)";
        }
        this.logger.log(str);
        if (z2) {
            if (z3) {
                if (body.contentType() != null) {
                    this.logger.log("Content-Type: " + body.contentType());
                }
                if (body.contentLength() != -1) {
                    this.logger.log("Content-Length: " + body.contentLength());
                }
            }
            Headers headers2 = request.headers();
            int size = headers2.size();
            for (int i = 0; i < size; i++) {
                String name = headers2.name(i);
                if (!"Content-Type".equalsIgnoreCase(name) && !"Content-Length".equalsIgnoreCase(name)) {
                    this.logger.log(name + ": " + headers2.value(i));
                }
            }
            if (!z || !z3) {
                this.logger.log("--> END " + request.method());
            } else if (bodyEncoded(request.headers())) {
                this.logger.log("--> END " + request.method() + " (encoded body omitted)");
            } else {
                Buffer buffer = new Buffer();
                body.writeTo(buffer);
                Charset charset = UTF8;
                MediaType contentType = body.contentType();
                if (contentType != null) {
                    charset = contentType.charset(UTF8);
                }
                this.logger.log("");
                if (isPlaintext(buffer)) {
                    this.logger.log(buffer.readString(charset));
                    this.logger.log("--> END " + request.method() + " (" + body.contentLength() + "-byte body)");
                } else {
                    this.logger.log("--> END " + request.method() + " (binary " + body.contentLength() + "-byte body omitted)");
                }
            }
        }
        long nanoTime = System.nanoTime();
        try {
            Response proceed = chain.proceed(request);
            long millis = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - nanoTime);
            ResponseBody body2 = proceed.body();
            long contentLength = body2.contentLength();
            String str2 = contentLength != -1 ? contentLength + "-byte" : "unknown-length";
            Logger logger = this.logger;
            StringBuilder sb = new StringBuilder();
            sb.append("<-- ");
            sb.append(proceed.code());
            sb.append(' ');
            sb.append(proceed.message());
            sb.append(' ');
            sb.append(proceed.request().url());
            sb.append(" (");
            sb.append(millis);
            sb.append("ms");
            sb.append(z2 ? "" : ", " + str2 + " body");
            sb.append(')');
            logger.log(sb.toString());
            if (z2) {
                int size2 = proceed.headers().size();
                for (int i2 = 0; i2 < size2; i2++) {
                    this.logger.log(headers.name(i2) + ": " + headers.value(i2));
                }
                if (!z || !HttpHeaders.hasBody(proceed)) {
                    this.logger.log("<-- END HTTP");
                } else if (bodyEncoded(proceed.headers())) {
                    this.logger.log("<-- END HTTP (encoded body omitted)");
                } else {
                    BufferedSource source = body2.source();
                    source.request(Long.MAX_VALUE);
                    Buffer buffer2 = source.buffer();
                    Charset charset2 = UTF8;
                    MediaType contentType2 = body2.contentType();
                    if (contentType2 != null) {
                        try {
                            charset2 = contentType2.charset(UTF8);
                        } catch (UnsupportedCharsetException unused) {
                            this.logger.log("");
                            this.logger.log("Couldn't decode the response body; charset is likely malformed.");
                            this.logger.log("<-- END HTTP");
                            return proceed;
                        }
                    }
                    if (!isPlaintext(buffer2)) {
                        this.logger.log("");
                        this.logger.log("<-- END HTTP (binary " + buffer2.size() + "-byte body omitted)");
                        return proceed;
                    }
                    if (contentLength != 0) {
                        this.logger.log("");
                        this.logger.log(buffer2.clone().readString(charset2));
                    }
                    this.logger.log("<-- END HTTP (" + buffer2.size() + "-byte body)");
                }
            }
            return proceed;
        } catch (Exception e) {
            this.logger.log("<-- HTTP FAILED: " + e);
            throw e;
        }
    }

    static boolean isPlaintext(Buffer buffer) {
        try {
            Buffer buffer2 = new Buffer();
            buffer.copyTo(buffer2, 0L, buffer.size() < 64 ? buffer.size() : 64L);
            for (int i = 0; i < 16; i++) {
                if (buffer2.exhausted()) {
                    return true;
                }
                int readUtf8CodePoint = buffer2.readUtf8CodePoint();
                if (Character.isISOControl(readUtf8CodePoint) && !Character.isWhitespace(readUtf8CodePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException unused) {
            return false;
        }
    }

    private boolean bodyEncoded(Headers headers) {
        String str = headers.get("Content-Encoding");
        return (str == null || str.equalsIgnoreCase("identity")) ? false : true;
    }
}
