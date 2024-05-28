package retrofit2.converter.scalars;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
final class ScalarRequestBodyConverter<T> implements Converter<T, RequestBody> {
    static final ScalarRequestBodyConverter<Object> INSTANCE = new ScalarRequestBodyConverter<>();
    private static final MediaType MEDIA_TYPE = MediaType.parse("text/plain; charset=UTF-8");

    /* JADX WARN: Multi-variable type inference failed */
    @Override // retrofit2.Converter
    public /* bridge */ /* synthetic */ RequestBody convert(Object obj) throws IOException {
        return convert2((ScalarRequestBodyConverter<T>) obj);
    }

    private ScalarRequestBodyConverter() {
    }

    @Override // retrofit2.Converter
    /* renamed from: convert  reason: avoid collision after fix types in other method */
    public RequestBody convert2(T t) throws IOException {
        return RequestBody.create(MEDIA_TYPE, String.valueOf(t));
    }
}
