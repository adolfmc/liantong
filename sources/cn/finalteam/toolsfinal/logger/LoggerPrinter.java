package cn.finalteam.toolsfinal.logger;

import android.text.TextUtils;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONArrayInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public final class LoggerPrinter implements Printer {
    private static final int ASSERT = 7;
    private static final String BOTTOM_BORDER = "╚════════════════════════════════════════════════════════════════════════════════════════";
    private static final char BOTTOM_LEFT_CORNER = 9562;
    private static final int CHUNK_SIZE = 4000;
    private static final int DEBUG = 3;
    private static final String DOUBLE_DIVIDER = "════════════════════════════════════════════";
    private static final int ERROR = 6;
    private static final char HORIZONTAL_DOUBLE_LINE = 9553;
    private static final int INFO = 4;
    private static final int JSON_INDENT = 4;
    private static final String MIDDLE_BORDER = "╟────────────────────────────────────────────────────────────────────────────────────────";
    private static final char MIDDLE_CORNER = 9567;
    private static final int MIN_STACK_OFFSET = 3;
    private static final String SINGLE_DIVIDER = "────────────────────────────────────────────";
    private static final String TOP_BORDER = "╔════════════════════════════════════════════════════════════════════════════════════════";
    private static final char TOP_LEFT_CORNER = 9556;
    private static final int VERBOSE = 2;
    private static final int WARN = 5;
    private Settings settings;
    private String tag;
    private final ThreadLocal<String> localTag = new ThreadLocal<>();
    private final ThreadLocal<Integer> localMethodCount = new ThreadLocal<>();

    /* JADX INFO: Access modifiers changed from: protected */
    public Settings init(String str) {
        if (str == null) {
            throw new NullPointerException("tag may not be null");
        }
        if (str.trim().length() == 0) {
            throw new IllegalStateException("tag may not be empty");
        }
        this.tag = str;
        this.settings = new Settings();
        return this.settings;
    }

    @Override // cn.finalteam.toolsfinal.logger.Printer
    public Settings getSettings() {
        return this.settings;
    }

    @Override // cn.finalteam.toolsfinal.logger.Printer
    /* renamed from: t */
    public Printer mo22019t(String str, int i) {
        if (str != null) {
            this.localTag.set(str);
        }
        this.localMethodCount.set(Integer.valueOf(i));
        return this;
    }

    @Override // cn.finalteam.toolsfinal.logger.Printer
    /* renamed from: d */
    public void mo22024d(String str, Object... objArr) {
        log(3, str, objArr);
    }

    @Override // cn.finalteam.toolsfinal.logger.Printer
    /* renamed from: e */
    public void mo22022e(Throwable th) {
        mo22022e(th);
    }

    @Override // cn.finalteam.toolsfinal.logger.Printer
    /* renamed from: e */
    public void mo22023e(String str, Object... objArr) {
        mo22021e(null, str, objArr);
    }

    @Override // cn.finalteam.toolsfinal.logger.Printer
    /* renamed from: e */
    public void mo22021e(Throwable th, String str, Object... objArr) {
        if (th != null && str != null) {
            str = str + " : " + th.toString();
        }
        if (th != null && str == null) {
            str = th.toString();
        }
        if (str == null) {
            str = "No message/exception is set";
        }
        log(6, str, objArr);
    }

    @Override // cn.finalteam.toolsfinal.logger.Printer
    /* renamed from: w */
    public void mo22017w(String str, Object... objArr) {
        log(5, str, objArr);
    }

    @Override // cn.finalteam.toolsfinal.logger.Printer
    /* renamed from: i */
    public void mo22020i(String str, Object... objArr) {
        log(4, str, objArr);
    }

    @Override // cn.finalteam.toolsfinal.logger.Printer
    /* renamed from: v */
    public void mo22018v(String str, Object... objArr) {
        log(2, str, objArr);
    }

    @Override // cn.finalteam.toolsfinal.logger.Printer
    public void wtf(String str, Object... objArr) {
        log(7, str, objArr);
    }

    @Override // cn.finalteam.toolsfinal.logger.Printer
    public void json(String str) {
        if (TextUtils.isEmpty(str)) {
            mo22024d("Empty/Null json content", new Object[0]);
            return;
        }
        try {
            if (str.startsWith("{")) {
                JSONObject jSONObject = new JSONObject(str);
                mo22024d(!(jSONObject instanceof JSONObject) ? jSONObject.toString(4) : NBSJSONObjectInstrumentation.toString(jSONObject, 4), new Object[0]);
            } else if (str.startsWith("[")) {
                JSONArray jSONArray = new JSONArray(str);
                mo22024d(!(jSONArray instanceof JSONArray) ? jSONArray.toString(4) : NBSJSONArrayInstrumentation.toString(jSONArray, 4), new Object[0]);
            }
        } catch (JSONException e) {
            mo22023e(e.getCause().getMessage() + "\n" + str, new Object[0]);
        }
    }

    @Override // cn.finalteam.toolsfinal.logger.Printer
    public void xml(String str) {
        if (TextUtils.isEmpty(str)) {
            mo22024d("Empty/Null xml content", new Object[0]);
            return;
        }
        try {
            StreamSource streamSource = new StreamSource(new StringReader(str));
            StreamResult streamResult = new StreamResult(new StringWriter());
            Transformer newTransformer = TransformerFactory.newInstance().newTransformer();
            newTransformer.setOutputProperty("indent", "yes");
            newTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            newTransformer.transform(streamSource, streamResult);
            mo22024d(streamResult.getWriter().toString().replaceFirst(">", ">\n"), new Object[0]);
        } catch (TransformerException e) {
            mo22023e(e.getCause().getMessage() + "\n" + str, new Object[0]);
        }
    }

    @Override // cn.finalteam.toolsfinal.logger.Printer
    public void clear() {
        this.settings = null;
    }

    private synchronized void log(int i, String str, Object... objArr) {
        if (this.settings.getLogLevel() == LogLevel.NONE) {
            return;
        }
        String tag = getTag();
        String createMessage = createMessage(str, objArr);
        int methodCount = getMethodCount();
        logTopBorder(i, tag);
        logHeaderContent(i, tag, methodCount);
        byte[] bytes = createMessage.getBytes();
        int length = bytes.length;
        if (length <= 4000) {
            if (methodCount > 0) {
                logDivider(i, tag);
            }
            logContent(i, tag, createMessage);
            logBottomBorder(i, tag);
            return;
        }
        if (methodCount > 0) {
            logDivider(i, tag);
        }
        for (int i2 = 0; i2 < length; i2 += 4000) {
            logContent(i, tag, new String(bytes, i2, Math.min(length - i2, 4000)));
        }
        logBottomBorder(i, tag);
    }

    private void logTopBorder(int i, String str) {
        logChunk(i, str, TOP_BORDER);
    }

    private void logHeaderContent(int i, String str, int i2) {
        int i3;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (this.settings.isShowThreadInfo()) {
            logChunk(i, str, "║ Thread: " + Thread.currentThread().getName());
            logDivider(i, str);
        }
        String str2 = "";
        int stackOffset = getStackOffset(stackTrace) + this.settings.getMethodOffset();
        if (i2 + stackOffset > stackTrace.length) {
            i2 = (stackTrace.length - stackOffset) - 1;
        }
        while (i2 > 0) {
            if (i2 + stackOffset < stackTrace.length) {
                str2 = str2 + "   ";
                logChunk(i, str, "║ " + str2 + getSimpleClassName(stackTrace[i3].getClassName()) + "." + stackTrace[i3].getMethodName() + "  (" + stackTrace[i3].getFileName() + ":" + stackTrace[i3].getLineNumber() + ")");
            }
            i2--;
        }
    }

    private void logBottomBorder(int i, String str) {
        logChunk(i, str, BOTTOM_BORDER);
    }

    private void logDivider(int i, String str) {
        logChunk(i, str, MIDDLE_BORDER);
    }

    private void logContent(int i, String str, String str2) {
        String[] split;
        for (String str3 : str2.split(System.getProperty("line.separator"))) {
            logChunk(i, str, "║ " + str3);
        }
    }

    private void logChunk(int i, String str, String str2) {
        String formatTag = formatTag(str);
        if (i != 2) {
            switch (i) {
                case 4:
                    this.settings.getLogTool().mo22027i(formatTag, str2);
                    return;
                case 5:
                    this.settings.getLogTool().mo22025w(formatTag, str2);
                    return;
                case 6:
                    this.settings.getLogTool().mo22028e(formatTag, str2);
                    return;
                case 7:
                    this.settings.getLogTool().wtf(formatTag, str2);
                    return;
                default:
                    this.settings.getLogTool().mo22029d(formatTag, str2);
                    return;
            }
        }
        this.settings.getLogTool().mo22026v(formatTag, str2);
    }

    private String getSimpleClassName(String str) {
        return str.substring(str.lastIndexOf(".") + 1);
    }

    private String formatTag(String str) {
        if (!TextUtils.isEmpty(str) && !TextUtils.equals(this.tag, str)) {
            return this.tag + "-" + str;
        }
        return this.tag;
    }

    private String getTag() {
        String str = this.localTag.get();
        if (str != null) {
            this.localTag.remove();
            return str;
        }
        return this.tag;
    }

    private String createMessage(String str, Object... objArr) {
        return objArr.length == 0 ? str : String.format(str, objArr);
    }

    private int getMethodCount() {
        Integer num = this.localMethodCount.get();
        int methodCount = this.settings.getMethodCount();
        if (num != null) {
            this.localMethodCount.remove();
            methodCount = num.intValue();
        }
        if (methodCount >= 0) {
            return methodCount;
        }
        throw new IllegalStateException("methodCount cannot be negative");
    }

    private int getStackOffset(StackTraceElement[] stackTraceElementArr) {
        for (int i = 3; i < stackTraceElementArr.length; i++) {
            String className = stackTraceElementArr[i].getClassName();
            if (!className.equals(LoggerPrinter.class.getName()) && !className.equals(Logger.class.getName())) {
                return i - 1;
            }
        }
        return -1;
    }
}
