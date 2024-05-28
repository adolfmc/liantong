package com.p210hp.hpl.sparta.xpath;

import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.hp.hpl.sparta.xpath.ExprFactory */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class ExprFactory {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static BooleanExpr createExpr(XPath xPath, SimpleStreamTokenizer simpleStreamTokenizer) throws XPathException, IOException {
        int parseInt;
        int parseInt2;
        int i = simpleStreamTokenizer.ttype;
        if (i == 64) {
            if (simpleStreamTokenizer.nextToken() != -3) {
                throw new XPathException(xPath, "after @", simpleStreamTokenizer, "name");
            }
            String str = simpleStreamTokenizer.sval;
            int nextToken = simpleStreamTokenizer.nextToken();
            if (nextToken != 33) {
                switch (nextToken) {
                    case 60:
                        simpleStreamTokenizer.nextToken();
                        if (simpleStreamTokenizer.ttype == 34 || simpleStreamTokenizer.ttype == 39) {
                            parseInt = Integer.parseInt(simpleStreamTokenizer.sval);
                        } else if (simpleStreamTokenizer.ttype == -2) {
                            parseInt = simpleStreamTokenizer.nval;
                        } else {
                            throw new XPathException(xPath, "right hand side of less-than", simpleStreamTokenizer, "quoted string or number");
                        }
                        simpleStreamTokenizer.nextToken();
                        return new AttrLessExpr(str, parseInt);
                    case 61:
                        simpleStreamTokenizer.nextToken();
                        if (simpleStreamTokenizer.ttype != 34 && simpleStreamTokenizer.ttype != 39) {
                            throw new XPathException(xPath, "right hand side of equals", simpleStreamTokenizer, "quoted string");
                        }
                        String str2 = simpleStreamTokenizer.sval;
                        simpleStreamTokenizer.nextToken();
                        return new AttrEqualsExpr(str, str2);
                    case 62:
                        simpleStreamTokenizer.nextToken();
                        if (simpleStreamTokenizer.ttype == 34 || simpleStreamTokenizer.ttype == 39) {
                            parseInt2 = Integer.parseInt(simpleStreamTokenizer.sval);
                        } else if (simpleStreamTokenizer.ttype == -2) {
                            parseInt2 = simpleStreamTokenizer.nval;
                        } else {
                            throw new XPathException(xPath, "right hand side of greater-than", simpleStreamTokenizer, "quoted string or number");
                        }
                        simpleStreamTokenizer.nextToken();
                        return new AttrGreaterExpr(str, parseInt2);
                    default:
                        return new AttrExistsExpr(str);
                }
            }
            simpleStreamTokenizer.nextToken();
            if (simpleStreamTokenizer.ttype != 61) {
                throw new XPathException(xPath, "after !", simpleStreamTokenizer, "=");
            }
            simpleStreamTokenizer.nextToken();
            if (simpleStreamTokenizer.ttype != 34 && simpleStreamTokenizer.ttype != 39) {
                throw new XPathException(xPath, "right hand side of !=", simpleStreamTokenizer, "quoted string");
            }
            String str3 = simpleStreamTokenizer.sval;
            simpleStreamTokenizer.nextToken();
            return new AttrNotEqualsExpr(str, str3);
        }
        switch (i) {
            case -3:
                if (!simpleStreamTokenizer.sval.equals("text")) {
                    throw new XPathException(xPath, "at beginning of expression", simpleStreamTokenizer, "text()");
                }
                if (simpleStreamTokenizer.nextToken() != 40) {
                    throw new XPathException(xPath, "after text", simpleStreamTokenizer, "(");
                }
                if (simpleStreamTokenizer.nextToken() != 41) {
                    throw new XPathException(xPath, "after text(", simpleStreamTokenizer, ")");
                }
                int nextToken2 = simpleStreamTokenizer.nextToken();
                if (nextToken2 != 33) {
                    if (nextToken2 == 61) {
                        simpleStreamTokenizer.nextToken();
                        if (simpleStreamTokenizer.ttype != 34 && simpleStreamTokenizer.ttype != 39) {
                            throw new XPathException(xPath, "right hand side of equals", simpleStreamTokenizer, "quoted string");
                        }
                        String str4 = simpleStreamTokenizer.sval;
                        simpleStreamTokenizer.nextToken();
                        return new TextEqualsExpr(str4);
                    }
                    return TextExistsExpr.INSTANCE;
                }
                simpleStreamTokenizer.nextToken();
                if (simpleStreamTokenizer.ttype != 61) {
                    throw new XPathException(xPath, "after !", simpleStreamTokenizer, "=");
                }
                simpleStreamTokenizer.nextToken();
                if (simpleStreamTokenizer.ttype != 34 && simpleStreamTokenizer.ttype != 39) {
                    throw new XPathException(xPath, "right hand side of !=", simpleStreamTokenizer, "quoted string");
                }
                String str5 = simpleStreamTokenizer.sval;
                simpleStreamTokenizer.nextToken();
                return new TextNotEqualsExpr(str5);
            case -2:
                int i2 = simpleStreamTokenizer.nval;
                simpleStreamTokenizer.nextToken();
                return new PositionEqualsExpr(i2);
            default:
                throw new XPathException(xPath, "at beginning of expression", simpleStreamTokenizer, "@, number, or text()");
        }
    }
}
