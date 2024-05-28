package com.google.protobuf;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.Message;
import com.google.protobuf.MessageReflection;
import com.google.protobuf.TextFormatParseInfoTree;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p038j0.C0548c;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class TextFormat {
    private static final Logger logger = Logger.getLogger(TextFormat.class.getName());
    private static final Printer DEFAULT_PRINTER = new Printer();
    private static final Printer SINGLE_LINE_PRINTER = new Printer().setSingleLineMode(true);
    private static final Printer UNICODE_PRINTER = new Printer().setEscapeNonAscii(false);
    private static final Parser PARSER = Parser.newBuilder().build();

    private static int digitValue(byte b) {
        return (48 > b || b > 57) ? (97 > b || b > 122) ? (b - 65) + 10 : (b - 97) + 10 : b - 48;
    }

    private static boolean isHex(byte b) {
        return (48 <= b && b <= 57) || (97 <= b && b <= 102) || (65 <= b && b <= 70);
    }

    private static boolean isOctal(byte b) {
        return 48 <= b && b <= 55;
    }

    private TextFormat() {
    }

    public static void print(MessageOrBuilder messageOrBuilder, Appendable appendable) throws IOException {
        DEFAULT_PRINTER.print(messageOrBuilder, new TextGenerator(appendable));
    }

    public static void print(UnknownFieldSet unknownFieldSet, Appendable appendable) throws IOException {
        DEFAULT_PRINTER.printUnknownFields(unknownFieldSet, new TextGenerator(appendable));
    }

    public static void printUnicode(MessageOrBuilder messageOrBuilder, Appendable appendable) throws IOException {
        UNICODE_PRINTER.print(messageOrBuilder, new TextGenerator(appendable));
    }

    public static void printUnicode(UnknownFieldSet unknownFieldSet, Appendable appendable) throws IOException {
        UNICODE_PRINTER.printUnknownFields(unknownFieldSet, new TextGenerator(appendable));
    }

    public static String shortDebugString(MessageOrBuilder messageOrBuilder) {
        try {
            StringBuilder sb = new StringBuilder();
            SINGLE_LINE_PRINTER.print(messageOrBuilder, new TextGenerator(sb));
            return sb.toString().trim();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static String shortDebugString(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
        try {
            StringBuilder sb = new StringBuilder();
            SINGLE_LINE_PRINTER.printField(fieldDescriptor, obj, new TextGenerator(sb));
            return sb.toString().trim();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static String shortDebugString(UnknownFieldSet unknownFieldSet) {
        try {
            StringBuilder sb = new StringBuilder();
            SINGLE_LINE_PRINTER.printUnknownFields(unknownFieldSet, new TextGenerator(sb));
            return sb.toString().trim();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static String printToString(MessageOrBuilder messageOrBuilder) {
        try {
            StringBuilder sb = new StringBuilder();
            print(messageOrBuilder, sb);
            return sb.toString();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static String printToString(UnknownFieldSet unknownFieldSet) {
        try {
            StringBuilder sb = new StringBuilder();
            print(unknownFieldSet, sb);
            return sb.toString();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static String printToUnicodeString(MessageOrBuilder messageOrBuilder) {
        try {
            StringBuilder sb = new StringBuilder();
            UNICODE_PRINTER.print(messageOrBuilder, new TextGenerator(sb));
            return sb.toString();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static String printToUnicodeString(UnknownFieldSet unknownFieldSet) {
        try {
            StringBuilder sb = new StringBuilder();
            UNICODE_PRINTER.printUnknownFields(unknownFieldSet, new TextGenerator(sb));
            return sb.toString();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void printField(Descriptors.FieldDescriptor fieldDescriptor, Object obj, Appendable appendable) throws IOException {
        DEFAULT_PRINTER.printField(fieldDescriptor, obj, new TextGenerator(appendable));
    }

    public static String printFieldToString(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
        try {
            StringBuilder sb = new StringBuilder();
            printField(fieldDescriptor, obj, sb);
            return sb.toString();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void printFieldValue(Descriptors.FieldDescriptor fieldDescriptor, Object obj, Appendable appendable) throws IOException {
        DEFAULT_PRINTER.printFieldValue(fieldDescriptor, obj, new TextGenerator(appendable));
    }

    public static void printUnknownFieldValue(int i, Object obj, Appendable appendable) throws IOException {
        printUnknownFieldValue(i, obj, new TextGenerator(appendable));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void printUnknownFieldValue(int i, Object obj, TextGenerator textGenerator) throws IOException {
        int tagWireType = WireFormat.getTagWireType(i);
        if (tagWireType != 5) {
            switch (tagWireType) {
                case 0:
                    textGenerator.print(unsignedToString(((Long) obj).longValue()));
                    return;
                case 1:
                    textGenerator.print(String.format(null, "0x%016x", (Long) obj));
                    return;
                case 2:
                    textGenerator.print("\"");
                    textGenerator.print(escapeBytes((ByteString) obj));
                    textGenerator.print("\"");
                    return;
                case 3:
                    DEFAULT_PRINTER.printUnknownFields((UnknownFieldSet) obj, textGenerator);
                    return;
                default:
                    throw new IllegalArgumentException("Bad tag: " + i);
            }
        }
        textGenerator.print(String.format(null, "0x%08x", (Integer) obj));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static final class Printer {
        boolean escapeNonAscii;
        boolean singleLineMode;

        private Printer() {
            this.singleLineMode = false;
            this.escapeNonAscii = true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Printer setSingleLineMode(boolean z) {
            this.singleLineMode = z;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Printer setEscapeNonAscii(boolean z) {
            this.escapeNonAscii = z;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void print(MessageOrBuilder messageOrBuilder, TextGenerator textGenerator) throws IOException {
            for (Map.Entry<Descriptors.FieldDescriptor, Object> entry : messageOrBuilder.getAllFields().entrySet()) {
                printField(entry.getKey(), entry.getValue(), textGenerator);
            }
            printUnknownFields(messageOrBuilder.getUnknownFields(), textGenerator);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void printField(Descriptors.FieldDescriptor fieldDescriptor, Object obj, TextGenerator textGenerator) throws IOException {
            if (fieldDescriptor.isRepeated()) {
                for (Object obj2 : (List) obj) {
                    printSingleField(fieldDescriptor, obj2, textGenerator);
                }
                return;
            }
            printSingleField(fieldDescriptor, obj, textGenerator);
        }

        private void printSingleField(Descriptors.FieldDescriptor fieldDescriptor, Object obj, TextGenerator textGenerator) throws IOException {
            if (fieldDescriptor.isExtension()) {
                textGenerator.print("[");
                if (fieldDescriptor.getContainingType().getOptions().getMessageSetWireFormat() && fieldDescriptor.getType() == Descriptors.FieldDescriptor.Type.MESSAGE && fieldDescriptor.isOptional() && fieldDescriptor.getExtensionScope() == fieldDescriptor.getMessageType()) {
                    textGenerator.print(fieldDescriptor.getMessageType().getFullName());
                } else {
                    textGenerator.print(fieldDescriptor.getFullName());
                }
                textGenerator.print("]");
            } else if (fieldDescriptor.getType() == Descriptors.FieldDescriptor.Type.GROUP) {
                textGenerator.print(fieldDescriptor.getMessageType().getName());
            } else {
                textGenerator.print(fieldDescriptor.getName());
            }
            if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                if (this.singleLineMode) {
                    textGenerator.print(" { ");
                } else {
                    textGenerator.print(" {\n");
                    textGenerator.indent();
                }
            } else {
                textGenerator.print(": ");
            }
            printFieldValue(fieldDescriptor, obj, textGenerator);
            if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                if (this.singleLineMode) {
                    textGenerator.print("} ");
                    return;
                }
                textGenerator.outdent();
                textGenerator.print("}\n");
            } else if (this.singleLineMode) {
                textGenerator.print(" ");
            } else {
                textGenerator.print("\n");
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void printFieldValue(Descriptors.FieldDescriptor fieldDescriptor, Object obj, TextGenerator textGenerator) throws IOException {
            String replace;
            switch (fieldDescriptor.getType()) {
                case INT32:
                case SINT32:
                case SFIXED32:
                    textGenerator.print(((Integer) obj).toString());
                    return;
                case INT64:
                case SINT64:
                case SFIXED64:
                    textGenerator.print(((Long) obj).toString());
                    return;
                case BOOL:
                    textGenerator.print(((Boolean) obj).toString());
                    return;
                case FLOAT:
                    textGenerator.print(((Float) obj).toString());
                    return;
                case DOUBLE:
                    textGenerator.print(((Double) obj).toString());
                    return;
                case UINT32:
                case FIXED32:
                    textGenerator.print(TextFormat.unsignedToString(((Integer) obj).intValue()));
                    return;
                case UINT64:
                case FIXED64:
                    textGenerator.print(TextFormat.unsignedToString(((Long) obj).longValue()));
                    return;
                case STRING:
                    textGenerator.print("\"");
                    if (this.escapeNonAscii) {
                        replace = TextFormatEscaper.escapeText((String) obj);
                    } else {
                        replace = TextFormat.escapeDoubleQuotesAndBackslashes((String) obj).replace("\n", "\\n");
                    }
                    textGenerator.print(replace);
                    textGenerator.print("\"");
                    return;
                case BYTES:
                    textGenerator.print("\"");
                    if (obj instanceof ByteString) {
                        textGenerator.print(TextFormat.escapeBytes((ByteString) obj));
                    } else {
                        textGenerator.print(TextFormat.escapeBytes((byte[]) obj));
                    }
                    textGenerator.print("\"");
                    return;
                case ENUM:
                    textGenerator.print(((Descriptors.EnumValueDescriptor) obj).getName());
                    return;
                case MESSAGE:
                case GROUP:
                    print((Message) obj, textGenerator);
                    return;
                default:
                    return;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void printUnknownFields(UnknownFieldSet unknownFieldSet, TextGenerator textGenerator) throws IOException {
            for (Map.Entry<Integer, UnknownFieldSet.Field> entry : unknownFieldSet.asMap().entrySet()) {
                int intValue = entry.getKey().intValue();
                UnknownFieldSet.Field value = entry.getValue();
                printUnknownField(intValue, 0, value.getVarintList(), textGenerator);
                printUnknownField(intValue, 5, value.getFixed32List(), textGenerator);
                printUnknownField(intValue, 1, value.getFixed64List(), textGenerator);
                printUnknownField(intValue, 2, value.getLengthDelimitedList(), textGenerator);
                for (UnknownFieldSet unknownFieldSet2 : value.getGroupList()) {
                    textGenerator.print(entry.getKey().toString());
                    if (this.singleLineMode) {
                        textGenerator.print(" { ");
                    } else {
                        textGenerator.print(" {\n");
                        textGenerator.indent();
                    }
                    printUnknownFields(unknownFieldSet2, textGenerator);
                    if (this.singleLineMode) {
                        textGenerator.print("} ");
                    } else {
                        textGenerator.outdent();
                        textGenerator.print("}\n");
                    }
                }
            }
        }

        private void printUnknownField(int i, int i2, List<?> list, TextGenerator textGenerator) throws IOException {
            for (Object obj : list) {
                textGenerator.print(String.valueOf(i));
                textGenerator.print(": ");
                TextFormat.printUnknownFieldValue(i2, obj, textGenerator);
                textGenerator.print(this.singleLineMode ? " " : "\n");
            }
        }
    }

    public static String unsignedToString(int i) {
        if (i >= 0) {
            return Integer.toString(i);
        }
        return Long.toString(i & 4294967295L);
    }

    public static String unsignedToString(long j) {
        if (j >= 0) {
            return Long.toString(j);
        }
        return BigInteger.valueOf(j & Long.MAX_VALUE).setBit(63).toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static final class TextGenerator {
        private boolean atStartOfLine;
        private final StringBuilder indent;
        private final Appendable output;

        private TextGenerator(Appendable appendable) {
            this.indent = new StringBuilder();
            this.atStartOfLine = true;
            this.output = appendable;
        }

        public void indent() {
            this.indent.append("  ");
        }

        public void outdent() {
            int length = this.indent.length();
            if (length == 0) {
                throw new IllegalArgumentException(" Outdent() without matching Indent().");
            }
            this.indent.delete(length - 2, length);
        }

        public void print(CharSequence charSequence) throws IOException {
            int length = charSequence.length();
            int i = 0;
            for (int i2 = 0; i2 < length; i2++) {
                if (charSequence.charAt(i2) == '\n') {
                    int i3 = i2 + 1;
                    write(charSequence.subSequence(i, i3));
                    this.atStartOfLine = true;
                    i = i3;
                }
            }
            write(charSequence.subSequence(i, length));
        }

        private void write(CharSequence charSequence) throws IOException {
            if (charSequence.length() == 0) {
                return;
            }
            if (this.atStartOfLine) {
                this.atStartOfLine = false;
                this.output.append(this.indent);
            }
            this.output.append(charSequence);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static final class Tokenizer {
        private int column;
        private String currentToken;
        private int line;
        private final Matcher matcher;
        private int pos;
        private int previousColumn;
        private int previousLine;
        private final CharSequence text;
        private static final Pattern WHITESPACE = Pattern.compile("(\\s|(#.*$))++", 8);
        private static final Pattern TOKEN = Pattern.compile("[a-zA-Z_][0-9a-zA-Z_+-]*+|[.]?[0-9+-][0-9a-zA-Z_.+-]*+|\"([^\"\n\\\\]|\\\\.)*+(\"|\\\\?$)|'([^'\n\\\\]|\\\\.)*+('|\\\\?$)", 8);
        private static final Pattern DOUBLE_INFINITY = Pattern.compile("-?inf(inity)?", 2);
        private static final Pattern FLOAT_INFINITY = Pattern.compile("-?inf(inity)?f?", 2);
        private static final Pattern FLOAT_NAN = Pattern.compile("nanf?", 2);

        private Tokenizer(CharSequence charSequence) {
            this.pos = 0;
            this.line = 0;
            this.column = 0;
            this.previousLine = 0;
            this.previousColumn = 0;
            this.text = charSequence;
            this.matcher = WHITESPACE.matcher(charSequence);
            skipWhitespace();
            nextToken();
        }

        int getPreviousLine() {
            return this.previousLine;
        }

        int getPreviousColumn() {
            return this.previousColumn;
        }

        int getLine() {
            return this.line;
        }

        int getColumn() {
            return this.column;
        }

        public boolean atEnd() {
            return this.currentToken.length() == 0;
        }

        public void nextToken() {
            this.previousLine = this.line;
            this.previousColumn = this.column;
            while (this.pos < this.matcher.regionStart()) {
                if (this.text.charAt(this.pos) == '\n') {
                    this.line++;
                    this.column = 0;
                } else {
                    this.column++;
                }
                this.pos++;
            }
            if (this.matcher.regionStart() == this.matcher.regionEnd()) {
                this.currentToken = "";
                return;
            }
            this.matcher.usePattern(TOKEN);
            if (this.matcher.lookingAt()) {
                this.currentToken = this.matcher.group();
                Matcher matcher = this.matcher;
                matcher.region(matcher.end(), this.matcher.regionEnd());
            } else {
                this.currentToken = String.valueOf(this.text.charAt(this.pos));
                Matcher matcher2 = this.matcher;
                matcher2.region(this.pos + 1, matcher2.regionEnd());
            }
            skipWhitespace();
        }

        private void skipWhitespace() {
            this.matcher.usePattern(WHITESPACE);
            if (this.matcher.lookingAt()) {
                Matcher matcher = this.matcher;
                matcher.region(matcher.end(), this.matcher.regionEnd());
            }
        }

        public boolean tryConsume(String str) {
            if (this.currentToken.equals(str)) {
                nextToken();
                return true;
            }
            return false;
        }

        public void consume(String str) throws ParseException {
            if (tryConsume(str)) {
                return;
            }
            throw parseException("Expected \"" + str + "\".");
        }

        public boolean lookingAtInteger() {
            if (this.currentToken.length() == 0) {
                return false;
            }
            char charAt = this.currentToken.charAt(0);
            return ('0' <= charAt && charAt <= '9') || charAt == '-' || charAt == '+';
        }

        public boolean lookingAt(String str) {
            return this.currentToken.equals(str);
        }

        public String consumeIdentifier() throws ParseException {
            for (int i = 0; i < this.currentToken.length(); i++) {
                char charAt = this.currentToken.charAt(i);
                if (('a' > charAt || charAt > 'z') && (('A' > charAt || charAt > 'Z') && !(('0' <= charAt && charAt <= '9') || charAt == '_' || charAt == '.'))) {
                    throw parseException("Expected identifier. Found '" + this.currentToken + "'");
                }
            }
            String str = this.currentToken;
            nextToken();
            return str;
        }

        public boolean tryConsumeIdentifier() {
            try {
                consumeIdentifier();
                return true;
            } catch (ParseException unused) {
                return false;
            }
        }

        public int consumeInt32() throws ParseException {
            try {
                int parseInt32 = TextFormat.parseInt32(this.currentToken);
                nextToken();
                return parseInt32;
            } catch (NumberFormatException e) {
                throw integerParseException(e);
            }
        }

        public int consumeUInt32() throws ParseException {
            try {
                int parseUInt32 = TextFormat.parseUInt32(this.currentToken);
                nextToken();
                return parseUInt32;
            } catch (NumberFormatException e) {
                throw integerParseException(e);
            }
        }

        public long consumeInt64() throws ParseException {
            try {
                long parseInt64 = TextFormat.parseInt64(this.currentToken);
                nextToken();
                return parseInt64;
            } catch (NumberFormatException e) {
                throw integerParseException(e);
            }
        }

        public boolean tryConsumeInt64() {
            try {
                consumeInt64();
                return true;
            } catch (ParseException unused) {
                return false;
            }
        }

        public long consumeUInt64() throws ParseException {
            try {
                long parseUInt64 = TextFormat.parseUInt64(this.currentToken);
                nextToken();
                return parseUInt64;
            } catch (NumberFormatException e) {
                throw integerParseException(e);
            }
        }

        public boolean tryConsumeUInt64() {
            try {
                consumeUInt64();
                return true;
            } catch (ParseException unused) {
                return false;
            }
        }

        public double consumeDouble() throws ParseException {
            if (DOUBLE_INFINITY.matcher(this.currentToken).matches()) {
                boolean startsWith = this.currentToken.startsWith("-");
                nextToken();
                return startsWith ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
            } else if (this.currentToken.equalsIgnoreCase("nan")) {
                nextToken();
                return Double.NaN;
            } else {
                try {
                    double parseDouble = Double.parseDouble(this.currentToken);
                    nextToken();
                    return parseDouble;
                } catch (NumberFormatException e) {
                    throw floatParseException(e);
                }
            }
        }

        public boolean tryConsumeDouble() {
            try {
                consumeDouble();
                return true;
            } catch (ParseException unused) {
                return false;
            }
        }

        public float consumeFloat() throws ParseException {
            if (FLOAT_INFINITY.matcher(this.currentToken).matches()) {
                boolean startsWith = this.currentToken.startsWith("-");
                nextToken();
                return startsWith ? Float.NEGATIVE_INFINITY : Float.POSITIVE_INFINITY;
            } else if (FLOAT_NAN.matcher(this.currentToken).matches()) {
                nextToken();
                return Float.NaN;
            } else {
                try {
                    float parseFloat = Float.parseFloat(this.currentToken);
                    nextToken();
                    return parseFloat;
                } catch (NumberFormatException e) {
                    throw floatParseException(e);
                }
            }
        }

        public boolean tryConsumeFloat() {
            try {
                consumeFloat();
                return true;
            } catch (ParseException unused) {
                return false;
            }
        }

        public boolean consumeBoolean() throws ParseException {
            if (this.currentToken.equals("true") || this.currentToken.equals("True") || this.currentToken.equals("t") || this.currentToken.equals("1")) {
                nextToken();
                return true;
            } else if (this.currentToken.equals("false") || this.currentToken.equals("False") || this.currentToken.equals("f") || this.currentToken.equals("0")) {
                nextToken();
                return false;
            } else {
                throw parseException("Expected \"true\" or \"false\".");
            }
        }

        public String consumeString() throws ParseException {
            return consumeByteString().toStringUtf8();
        }

        public boolean tryConsumeString() {
            try {
                consumeString();
                return true;
            } catch (ParseException unused) {
                return false;
            }
        }

        public ByteString consumeByteString() throws ParseException {
            ArrayList arrayList = new ArrayList();
            consumeByteString(arrayList);
            while (true) {
                if (this.currentToken.startsWith("'") || this.currentToken.startsWith("\"")) {
                    consumeByteString(arrayList);
                } else {
                    return ByteString.copyFrom(arrayList);
                }
            }
        }

        private void consumeByteString(List<ByteString> list) throws ParseException {
            char charAt = this.currentToken.length() > 0 ? this.currentToken.charAt(0) : (char) 0;
            if (charAt != '\"' && charAt != '\'') {
                throw parseException("Expected string.");
            }
            if (this.currentToken.length() >= 2) {
                String str = this.currentToken;
                if (str.charAt(str.length() - 1) == charAt) {
                    try {
                        ByteString unescapeBytes = TextFormat.unescapeBytes(this.currentToken.substring(1, this.currentToken.length() - 1));
                        nextToken();
                        list.add(unescapeBytes);
                        return;
                    } catch (InvalidEscapeSequenceException e) {
                        throw parseException(e.getMessage());
                    }
                }
            }
            throw parseException("String missing ending quote.");
        }

        public ParseException parseException(String str) {
            return new ParseException(this.line + 1, this.column + 1, str);
        }

        public ParseException parseExceptionPreviousToken(String str) {
            return new ParseException(this.previousLine + 1, this.previousColumn + 1, str);
        }

        private ParseException integerParseException(NumberFormatException numberFormatException) {
            return parseException("Couldn't parse integer: " + numberFormatException.getMessage());
        }

        private ParseException floatParseException(NumberFormatException numberFormatException) {
            return parseException("Couldn't parse number: " + numberFormatException.getMessage());
        }

        public UnknownFieldParseException unknownFieldParseExceptionPreviousToken(String str, String str2) {
            return new UnknownFieldParseException(this.previousLine + 1, this.previousColumn + 1, str, str2);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class ParseException extends IOException {
        private static final long serialVersionUID = 3196188060225107702L;
        private final int column;
        private final int line;

        public ParseException(String str) {
            this(-1, -1, str);
        }

        public ParseException(int i, int i2, String str) {
            super(Integer.toString(i) + ":" + i2 + ": " + str);
            this.line = i;
            this.column = i2;
        }

        public int getLine() {
            return this.line;
        }

        public int getColumn() {
            return this.column;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class UnknownFieldParseException extends ParseException {
        private final String unknownField;

        public UnknownFieldParseException(String str) {
            this(-1, -1, "", str);
        }

        public UnknownFieldParseException(int i, int i2, String str, String str2) {
            super(i, i2, str2);
            this.unknownField = str;
        }

        public String getUnknownField() {
            return this.unknownField;
        }
    }

    public static Parser getParser() {
        return PARSER;
    }

    public static void merge(Readable readable, Message.Builder builder) throws IOException {
        PARSER.merge(readable, builder);
    }

    public static void merge(CharSequence charSequence, Message.Builder builder) throws ParseException {
        PARSER.merge(charSequence, builder);
    }

    public static void merge(Readable readable, ExtensionRegistry extensionRegistry, Message.Builder builder) throws IOException {
        PARSER.merge(readable, extensionRegistry, builder);
    }

    public static void merge(CharSequence charSequence, ExtensionRegistry extensionRegistry, Message.Builder builder) throws ParseException {
        PARSER.merge(charSequence, extensionRegistry, builder);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class Parser {
        private static final int BUFFER_SIZE = 4096;
        private final boolean allowUnknownFields;
        private TextFormatParseInfoTree.Builder parseInfoTreeBuilder;
        private final SingularOverwritePolicy singularOverwritePolicy;

        /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\10762272_dexfile_execute.dex */
        public enum SingularOverwritePolicy {
            ALLOW_SINGULAR_OVERWRITES,
            FORBID_SINGULAR_OVERWRITES
        }

        private Parser(boolean z, SingularOverwritePolicy singularOverwritePolicy, TextFormatParseInfoTree.Builder builder) {
            this.allowUnknownFields = z;
            this.singularOverwritePolicy = singularOverwritePolicy;
            this.parseInfoTreeBuilder = builder;
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\10762272_dexfile_execute.dex */
        public static class Builder {
            private boolean allowUnknownFields = false;
            private SingularOverwritePolicy singularOverwritePolicy = SingularOverwritePolicy.ALLOW_SINGULAR_OVERWRITES;
            private TextFormatParseInfoTree.Builder parseInfoTreeBuilder = null;

            public Builder setSingularOverwritePolicy(SingularOverwritePolicy singularOverwritePolicy) {
                this.singularOverwritePolicy = singularOverwritePolicy;
                return this;
            }

            public Builder setParseInfoTreeBuilder(TextFormatParseInfoTree.Builder builder) {
                this.parseInfoTreeBuilder = builder;
                return this;
            }

            public Parser build() {
                return new Parser(this.allowUnknownFields, this.singularOverwritePolicy, this.parseInfoTreeBuilder);
            }
        }

        public void merge(Readable readable, Message.Builder builder) throws IOException {
            merge(readable, ExtensionRegistry.getEmptyRegistry(), builder);
        }

        public void merge(CharSequence charSequence, Message.Builder builder) throws ParseException {
            merge(charSequence, ExtensionRegistry.getEmptyRegistry(), builder);
        }

        public void merge(Readable readable, ExtensionRegistry extensionRegistry, Message.Builder builder) throws IOException {
            merge(toStringBuilder(readable), extensionRegistry, builder);
        }

        private static StringBuilder toStringBuilder(Readable readable) throws IOException {
            StringBuilder sb = new StringBuilder();
            CharBuffer allocate = CharBuffer.allocate(4096);
            while (true) {
                int read = readable.read(allocate);
                if (read == -1) {
                    return sb;
                }
                allocate.flip();
                sb.append((CharSequence) allocate, 0, read);
            }
        }

        private void checkUnknownFields(List<String> list) throws ParseException {
            if (list.isEmpty()) {
                return;
            }
            StringBuilder sb = new StringBuilder("Input contains unknown fields and/or extensions:");
            for (String str : list) {
                sb.append('\n');
                sb.append(str);
            }
            if (this.allowUnknownFields) {
                TextFormat.logger.warning(sb.toString());
            } else {
                String[] split = list.get(0).split(":");
                throw new ParseException(Integer.valueOf(split[0]).intValue(), Integer.valueOf(split[1]).intValue(), sb.toString());
            }
        }

        public void merge(CharSequence charSequence, ExtensionRegistry extensionRegistry, Message.Builder builder) throws ParseException {
            Tokenizer tokenizer = new Tokenizer(charSequence);
            MessageReflection.BuilderAdapter builderAdapter = new MessageReflection.BuilderAdapter(builder);
            ArrayList arrayList = new ArrayList();
            while (!tokenizer.atEnd()) {
                mergeField(tokenizer, extensionRegistry, builderAdapter, arrayList);
            }
            checkUnknownFields(arrayList);
        }

        private void mergeField(Tokenizer tokenizer, ExtensionRegistry extensionRegistry, MessageReflection.MergeTarget mergeTarget, List<String> list) throws ParseException {
            mergeField(tokenizer, extensionRegistry, mergeTarget, this.parseInfoTreeBuilder, list);
        }

        private void mergeField(Tokenizer tokenizer, ExtensionRegistry extensionRegistry, MessageReflection.MergeTarget mergeTarget, TextFormatParseInfoTree.Builder builder, List<String> list) throws ParseException {
            ExtensionRegistry.ExtensionInfo extensionInfo;
            Descriptors.FieldDescriptor fieldDescriptor;
            int line = tokenizer.getLine();
            int column = tokenizer.getColumn();
            Descriptors.Descriptor descriptorForType = mergeTarget.getDescriptorForType();
            Descriptors.FieldDescriptor fieldDescriptor2 = null;
            if (tokenizer.tryConsume("[")) {
                StringBuilder sb = new StringBuilder(tokenizer.consumeIdentifier());
                while (tokenizer.tryConsume(".")) {
                    sb.append('.');
                    sb.append(tokenizer.consumeIdentifier());
                }
                ExtensionRegistry.ExtensionInfo findExtensionByName = mergeTarget.findExtensionByName(extensionRegistry, sb.toString());
                if (findExtensionByName == null) {
                    list.add((tokenizer.getPreviousLine() + 1) + ":" + (tokenizer.getPreviousColumn() + 1) + ":\t" + descriptorForType.getFullName() + ".[" + ((Object) sb) + "]");
                } else if (findExtensionByName.descriptor.getContainingType() != descriptorForType) {
                    throw tokenizer.parseExceptionPreviousToken("Extension \"" + ((Object) sb) + "\" does not extend message type \"" + descriptorForType.getFullName() + "\".");
                } else {
                    fieldDescriptor2 = findExtensionByName.descriptor;
                }
                tokenizer.consume("]");
                fieldDescriptor = fieldDescriptor2;
                extensionInfo = findExtensionByName;
            } else {
                String consumeIdentifier = tokenizer.consumeIdentifier();
                Descriptors.FieldDescriptor findFieldByName = descriptorForType.findFieldByName(consumeIdentifier);
                if (findFieldByName == null && (findFieldByName = descriptorForType.findFieldByName(consumeIdentifier.toLowerCase(Locale.US))) != null && findFieldByName.getType() != Descriptors.FieldDescriptor.Type.GROUP) {
                    findFieldByName = null;
                }
                if (findFieldByName != null && findFieldByName.getType() == Descriptors.FieldDescriptor.Type.GROUP && !findFieldByName.getMessageType().getName().equals(consumeIdentifier)) {
                    findFieldByName = null;
                }
                if (findFieldByName == null) {
                    list.add((tokenizer.getPreviousLine() + 1) + ":" + (tokenizer.getPreviousColumn() + 1) + ":\t" + descriptorForType.getFullName() + "." + consumeIdentifier);
                }
                extensionInfo = null;
                fieldDescriptor = findFieldByName;
            }
            if (fieldDescriptor == null) {
                if (tokenizer.tryConsume(":") && !tokenizer.lookingAt("{") && !tokenizer.lookingAt("<")) {
                    skipFieldValue(tokenizer);
                    return;
                } else {
                    skipFieldMessage(tokenizer);
                    return;
                }
            }
            if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                tokenizer.tryConsume(":");
                if (builder != null) {
                    consumeFieldValues(tokenizer, extensionRegistry, mergeTarget, fieldDescriptor, extensionInfo, builder.getBuilderForSubMessageField(fieldDescriptor), list);
                } else {
                    consumeFieldValues(tokenizer, extensionRegistry, mergeTarget, fieldDescriptor, extensionInfo, builder, list);
                }
            } else {
                tokenizer.consume(":");
                consumeFieldValues(tokenizer, extensionRegistry, mergeTarget, fieldDescriptor, extensionInfo, builder, list);
            }
            if (builder != null) {
                builder.setLocation(fieldDescriptor, TextFormatParseLocation.create(line, column));
            }
            if (tokenizer.tryConsume(";")) {
                return;
            }
            tokenizer.tryConsume(",");
        }

        private void consumeFieldValues(Tokenizer tokenizer, ExtensionRegistry extensionRegistry, MessageReflection.MergeTarget mergeTarget, Descriptors.FieldDescriptor fieldDescriptor, ExtensionRegistry.ExtensionInfo extensionInfo, TextFormatParseInfoTree.Builder builder, List<String> list) throws ParseException {
            if (fieldDescriptor.isRepeated() && tokenizer.tryConsume("[")) {
                if (tokenizer.tryConsume("]")) {
                    return;
                }
                while (true) {
                    consumeFieldValue(tokenizer, extensionRegistry, mergeTarget, fieldDescriptor, extensionInfo, builder, list);
                    if (tokenizer.tryConsume("]")) {
                        return;
                    }
                    tokenizer.consume(",");
                }
            } else {
                consumeFieldValue(tokenizer, extensionRegistry, mergeTarget, fieldDescriptor, extensionInfo, builder, list);
            }
        }

        private void consumeFieldValue(Tokenizer tokenizer, ExtensionRegistry extensionRegistry, MessageReflection.MergeTarget mergeTarget, Descriptors.FieldDescriptor fieldDescriptor, ExtensionRegistry.ExtensionInfo extensionInfo, TextFormatParseInfoTree.Builder builder, List<String> list) throws ParseException {
            String str;
            Object obj = null;
            if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                if (tokenizer.tryConsume("<")) {
                    str = ">";
                } else {
                    tokenizer.consume("{");
                    str = "}";
                }
                MessageReflection.MergeTarget newMergeTargetForField = mergeTarget.newMergeTargetForField(fieldDescriptor, extensionInfo != null ? extensionInfo.defaultInstance : null);
                while (!tokenizer.tryConsume(str)) {
                    if (tokenizer.atEnd()) {
                        throw tokenizer.parseException("Expected \"" + str + "\".");
                    }
                    mergeField(tokenizer, extensionRegistry, newMergeTargetForField, builder, list);
                }
                obj = newMergeTargetForField.finish();
            } else {
                switch (fieldDescriptor.getType()) {
                    case INT32:
                    case SINT32:
                    case SFIXED32:
                        obj = Integer.valueOf(tokenizer.consumeInt32());
                        break;
                    case INT64:
                    case SINT64:
                    case SFIXED64:
                        obj = Long.valueOf(tokenizer.consumeInt64());
                        break;
                    case BOOL:
                        obj = Boolean.valueOf(tokenizer.consumeBoolean());
                        break;
                    case FLOAT:
                        obj = Float.valueOf(tokenizer.consumeFloat());
                        break;
                    case DOUBLE:
                        obj = Double.valueOf(tokenizer.consumeDouble());
                        break;
                    case UINT32:
                    case FIXED32:
                        obj = Integer.valueOf(tokenizer.consumeUInt32());
                        break;
                    case UINT64:
                    case FIXED64:
                        obj = Long.valueOf(tokenizer.consumeUInt64());
                        break;
                    case STRING:
                        obj = tokenizer.consumeString();
                        break;
                    case BYTES:
                        obj = tokenizer.consumeByteString();
                        break;
                    case ENUM:
                        Descriptors.EnumDescriptor enumType = fieldDescriptor.getEnumType();
                        if (tokenizer.lookingAtInteger()) {
                            int consumeInt32 = tokenizer.consumeInt32();
                            obj = enumType.findValueByNumber(consumeInt32);
                            if (obj == null) {
                                throw tokenizer.parseExceptionPreviousToken("Enum type \"" + enumType.getFullName() + "\" has no value with number " + consumeInt32 + '.');
                            }
                        } else {
                            String consumeIdentifier = tokenizer.consumeIdentifier();
                            obj = enumType.findValueByName(consumeIdentifier);
                            if (obj == null) {
                                throw tokenizer.parseExceptionPreviousToken("Enum type \"" + enumType.getFullName() + "\" has no value named \"" + consumeIdentifier + "\".");
                            }
                        }
                        break;
                    case MESSAGE:
                    case GROUP:
                        throw new RuntimeException("Can't get here.");
                }
            }
            if (fieldDescriptor.isRepeated()) {
                mergeTarget.addRepeatedField(fieldDescriptor, obj);
            } else if (this.singularOverwritePolicy == SingularOverwritePolicy.FORBID_SINGULAR_OVERWRITES && mergeTarget.hasField(fieldDescriptor)) {
                throw tokenizer.parseExceptionPreviousToken("Non-repeated field \"" + fieldDescriptor.getFullName() + "\" cannot be overwritten.");
            } else if (this.singularOverwritePolicy == SingularOverwritePolicy.FORBID_SINGULAR_OVERWRITES && fieldDescriptor.getContainingOneof() != null && mergeTarget.hasOneof(fieldDescriptor.getContainingOneof())) {
                Descriptors.OneofDescriptor containingOneof = fieldDescriptor.getContainingOneof();
                throw tokenizer.parseExceptionPreviousToken("Field \"" + fieldDescriptor.getFullName() + "\" is specified along with field \"" + mergeTarget.getOneofFieldDescriptor(containingOneof).getFullName() + "\", another member of oneof \"" + containingOneof.getName() + "\".");
            } else {
                mergeTarget.setField(fieldDescriptor, obj);
            }
        }

        private void skipField(Tokenizer tokenizer) throws ParseException {
            if (tokenizer.tryConsume("[")) {
                do {
                    tokenizer.consumeIdentifier();
                } while (tokenizer.tryConsume("."));
                tokenizer.consume("]");
            } else {
                tokenizer.consumeIdentifier();
            }
            if (tokenizer.tryConsume(":") && !tokenizer.lookingAt("<") && !tokenizer.lookingAt("{")) {
                skipFieldValue(tokenizer);
            } else {
                skipFieldMessage(tokenizer);
            }
            if (tokenizer.tryConsume(";")) {
                return;
            }
            tokenizer.tryConsume(",");
        }

        private void skipFieldMessage(Tokenizer tokenizer) throws ParseException {
            String str;
            if (tokenizer.tryConsume("<")) {
                str = ">";
            } else {
                tokenizer.consume("{");
                str = "}";
            }
            while (!tokenizer.lookingAt(">") && !tokenizer.lookingAt("}")) {
                skipField(tokenizer);
            }
            tokenizer.consume(str);
        }

        private void skipFieldValue(Tokenizer tokenizer) throws ParseException {
            if (tokenizer.tryConsumeString()) {
                do {
                } while (tokenizer.tryConsumeString());
            } else if (tokenizer.tryConsumeIdentifier() || tokenizer.tryConsumeInt64() || tokenizer.tryConsumeUInt64() || tokenizer.tryConsumeDouble() || tokenizer.tryConsumeFloat()) {
            } else {
                throw tokenizer.parseException("Invalid field value: " + tokenizer.currentToken);
            }
        }
    }

    public static String escapeBytes(ByteString byteString) {
        return TextFormatEscaper.escapeBytes(byteString);
    }

    public static String escapeBytes(byte[] bArr) {
        return TextFormatEscaper.escapeBytes(bArr);
    }

    public static ByteString unescapeBytes(CharSequence charSequence) throws InvalidEscapeSequenceException {
        int i;
        ByteString copyFromUtf8 = ByteString.copyFromUtf8(charSequence.toString());
        byte[] bArr = new byte[copyFromUtf8.size()];
        int i2 = 0;
        int i3 = 0;
        while (i2 < copyFromUtf8.size()) {
            byte byteAt = copyFromUtf8.byteAt(i2);
            if (byteAt == 92) {
                i2++;
                if (i2 < copyFromUtf8.size()) {
                    byte byteAt2 = copyFromUtf8.byteAt(i2);
                    if (isOctal(byteAt2)) {
                        int digitValue = digitValue(byteAt2);
                        int i4 = i2 + 1;
                        if (i4 < copyFromUtf8.size() && isOctal(copyFromUtf8.byteAt(i4))) {
                            digitValue = (digitValue * 8) + digitValue(copyFromUtf8.byteAt(i4));
                            i2 = i4;
                        }
                        int i5 = i2 + 1;
                        if (i5 < copyFromUtf8.size() && isOctal(copyFromUtf8.byteAt(i5))) {
                            digitValue = (digitValue * 8) + digitValue(copyFromUtf8.byteAt(i5));
                            i2 = i5;
                        }
                        bArr[i3] = (byte) digitValue;
                        i3++;
                    } else {
                        if (byteAt2 == 34) {
                            i = i3 + 1;
                            bArr[i3] = 34;
                        } else if (byteAt2 == 39) {
                            i = i3 + 1;
                            bArr[i3] = 39;
                        } else if (byteAt2 == 92) {
                            i = i3 + 1;
                            bArr[i3] = C0548c.f1785i;
                        } else if (byteAt2 == 102) {
                            i = i3 + 1;
                            bArr[i3] = 12;
                        } else if (byteAt2 == 110) {
                            i = i3 + 1;
                            bArr[i3] = 10;
                        } else if (byteAt2 == 114) {
                            i = i3 + 1;
                            bArr[i3] = 13;
                        } else if (byteAt2 == 116) {
                            i = i3 + 1;
                            bArr[i3] = 9;
                        } else if (byteAt2 == 118) {
                            i = i3 + 1;
                            bArr[i3] = 11;
                        } else if (byteAt2 != 120) {
                            switch (byteAt2) {
                                case 97:
                                    i = i3 + 1;
                                    bArr[i3] = 7;
                                    break;
                                case 98:
                                    i = i3 + 1;
                                    bArr[i3] = 8;
                                    break;
                                default:
                                    throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\" + ((char) byteAt2) + '\'');
                            }
                        } else {
                            i2++;
                            if (i2 < copyFromUtf8.size() && isHex(copyFromUtf8.byteAt(i2))) {
                                int digitValue2 = digitValue(copyFromUtf8.byteAt(i2));
                                int i6 = i2 + 1;
                                if (i6 < copyFromUtf8.size() && isHex(copyFromUtf8.byteAt(i6))) {
                                    digitValue2 = (digitValue2 * 16) + digitValue(copyFromUtf8.byteAt(i6));
                                    i2 = i6;
                                }
                                bArr[i3] = (byte) digitValue2;
                                i = i3 + 1;
                            } else {
                                throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\x' with no digits");
                            }
                        }
                        i3 = i;
                    }
                } else {
                    throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\' at end of string.");
                }
            } else {
                bArr[i3] = byteAt;
                i3++;
            }
            i2++;
        }
        if (bArr.length == i3) {
            return ByteString.wrap(bArr);
        }
        return ByteString.copyFrom(bArr, 0, i3);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class InvalidEscapeSequenceException extends IOException {
        private static final long serialVersionUID = -8164033650142593304L;

        InvalidEscapeSequenceException(String str) {
            super(str);
        }
    }

    static String escapeText(String str) {
        return escapeBytes(ByteString.copyFromUtf8(str));
    }

    public static String escapeDoubleQuotesAndBackslashes(String str) {
        return TextFormatEscaper.escapeDoubleQuotesAndBackslashes(str);
    }

    static String unescapeText(String str) throws InvalidEscapeSequenceException {
        return unescapeBytes(str).toStringUtf8();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int parseInt32(String str) throws NumberFormatException {
        return (int) parseInteger(str, true, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int parseUInt32(String str) throws NumberFormatException {
        return (int) parseInteger(str, false, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long parseInt64(String str) throws NumberFormatException {
        return parseInteger(str, true, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long parseUInt64(String str) throws NumberFormatException {
        return parseInteger(str, false, true);
    }

    private static long parseInteger(String str, boolean z, boolean z2) throws NumberFormatException {
        int i = 0;
        boolean z3 = true;
        if (!str.startsWith("-", 0)) {
            z3 = false;
        } else if (!z) {
            throw new NumberFormatException("Number must be positive: " + str);
        } else {
            i = 1;
        }
        int i2 = 10;
        if (str.startsWith("0x", i)) {
            i += 2;
            i2 = 16;
        } else if (str.startsWith("0", i)) {
            i2 = 8;
        }
        String substring = str.substring(i);
        if (substring.length() < 16) {
            long parseLong = Long.parseLong(substring, i2);
            if (z3) {
                parseLong = -parseLong;
            }
            if (z2) {
                return parseLong;
            }
            if (z) {
                if (parseLong > 2147483647L || parseLong < -2147483648L) {
                    throw new NumberFormatException("Number out of range for 32-bit signed integer: " + str);
                }
                return parseLong;
            } else if (parseLong >= 4294967296L || parseLong < 0) {
                throw new NumberFormatException("Number out of range for 32-bit unsigned integer: " + str);
            } else {
                return parseLong;
            }
        }
        BigInteger bigInteger = new BigInteger(substring, i2);
        if (z3) {
            bigInteger = bigInteger.negate();
        }
        if (z2) {
            if (z) {
                if (bigInteger.bitLength() > 63) {
                    throw new NumberFormatException("Number out of range for 64-bit signed integer: " + str);
                }
            } else if (bigInteger.bitLength() > 64) {
                throw new NumberFormatException("Number out of range for 64-bit unsigned integer: " + str);
            }
        } else if (z) {
            if (bigInteger.bitLength() > 31) {
                throw new NumberFormatException("Number out of range for 32-bit signed integer: " + str);
            }
        } else if (bigInteger.bitLength() > 32) {
            throw new NumberFormatException("Number out of range for 32-bit unsigned integer: " + str);
        }
        return bigInteger.longValue();
    }
}
