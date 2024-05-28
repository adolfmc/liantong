package org.jsoup.parser;

import com.example.asus.detectionandalign.DetectionAuthentic;
import java.util.Iterator;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Token;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public enum HtmlTreeBuilderState {
    Initial { // from class: org.jsoup.parser.HtmlTreeBuilderState.1
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                return true;
            }
            if (token.isComment()) {
                htmlTreeBuilder.insert(token.asComment());
            } else if (token.isDoctype()) {
                Token.Doctype asDoctype = token.asDoctype();
                htmlTreeBuilder.getDocument().appendChild(new DocumentType(asDoctype.getName(), asDoctype.getPublicIdentifier(), asDoctype.getSystemIdentifier(), htmlTreeBuilder.getBaseUri()));
                if (asDoctype.isForceQuirks()) {
                    htmlTreeBuilder.getDocument().quirksMode(Document.QuirksMode.quirks);
                }
                htmlTreeBuilder.transition(BeforeHtml);
            } else {
                htmlTreeBuilder.transition(BeforeHtml);
                return htmlTreeBuilder.process(token);
            }
            return true;
        }
    },
    BeforeHtml { // from class: org.jsoup.parser.HtmlTreeBuilderState.2
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.isDoctype()) {
                htmlTreeBuilder.error(this);
                return false;
            }
            if (!token.isComment()) {
                if (HtmlTreeBuilderState.isWhitespace(token)) {
                    return true;
                }
                if (token.isStartTag() && token.asStartTag().name().equals("html")) {
                    htmlTreeBuilder.insert(token.asStartTag());
                    htmlTreeBuilder.transition(BeforeHead);
                } else if (token.isEndTag() && StringUtil.m211in(token.asEndTag().name(), "head", "body", "html", "br")) {
                    return anythingElse(token, htmlTreeBuilder);
                } else {
                    if (token.isEndTag()) {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                    return anythingElse(token, htmlTreeBuilder);
                }
            } else {
                htmlTreeBuilder.insert(token.asComment());
            }
            return true;
        }

        private boolean anythingElse(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            htmlTreeBuilder.insert("html");
            htmlTreeBuilder.transition(BeforeHead);
            return htmlTreeBuilder.process(token);
        }
    },
    BeforeHead { // from class: org.jsoup.parser.HtmlTreeBuilderState.3
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                return true;
            }
            if (token.isComment()) {
                htmlTreeBuilder.insert(token.asComment());
            } else if (token.isDoctype()) {
                htmlTreeBuilder.error(this);
                return false;
            } else if (token.isStartTag() && token.asStartTag().name().equals("html")) {
                return InBody.process(token, htmlTreeBuilder);
            } else {
                if (token.isStartTag() && token.asStartTag().name().equals("head")) {
                    htmlTreeBuilder.setHeadElement(htmlTreeBuilder.insert(token.asStartTag()));
                    htmlTreeBuilder.transition(InHead);
                } else if (token.isEndTag() && StringUtil.m211in(token.asEndTag().name(), "head", "body", "html", "br")) {
                    htmlTreeBuilder.process(new Token.StartTag("head"));
                    return htmlTreeBuilder.process(token);
                } else if (token.isEndTag()) {
                    htmlTreeBuilder.error(this);
                    return false;
                } else {
                    htmlTreeBuilder.process(new Token.StartTag("head"));
                    return htmlTreeBuilder.process(token);
                }
            }
            return true;
        }
    },
    InHead { // from class: org.jsoup.parser.HtmlTreeBuilderState.4
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                htmlTreeBuilder.insert(token.asCharacter());
                return true;
            }
            switch (token.type) {
                case Comment:
                    htmlTreeBuilder.insert(token.asComment());
                    break;
                case Doctype:
                    htmlTreeBuilder.error(this);
                    return false;
                case StartTag:
                    Token.StartTag asStartTag = token.asStartTag();
                    String name = asStartTag.name();
                    if (name.equals("html")) {
                        return InBody.process(token, htmlTreeBuilder);
                    }
                    if (StringUtil.m211in(name, "base", "basefont", "bgsound", "command", "link")) {
                        Element insertEmpty = htmlTreeBuilder.insertEmpty(asStartTag);
                        if (name.equals("base") && insertEmpty.hasAttr("href")) {
                            htmlTreeBuilder.maybeSetBaseUri(insertEmpty);
                            break;
                        }
                    } else if (name.equals("meta")) {
                        htmlTreeBuilder.insertEmpty(asStartTag);
                        break;
                    } else if (name.equals("title")) {
                        HtmlTreeBuilderState.handleRcData(asStartTag, htmlTreeBuilder);
                        break;
                    } else if (StringUtil.m211in(name, "noframes", "style")) {
                        HtmlTreeBuilderState.handleRawtext(asStartTag, htmlTreeBuilder);
                        break;
                    } else if (name.equals("noscript")) {
                        htmlTreeBuilder.insert(asStartTag);
                        htmlTreeBuilder.transition(InHeadNoscript);
                        break;
                    } else if (name.equals("script")) {
                        htmlTreeBuilder.insert(asStartTag);
                        htmlTreeBuilder.tokeniser.transition(TokeniserState.ScriptData);
                        htmlTreeBuilder.markInsertionMode();
                        htmlTreeBuilder.transition(Text);
                        break;
                    } else if (name.equals("head")) {
                        htmlTreeBuilder.error(this);
                        return false;
                    } else {
                        return anythingElse(token, htmlTreeBuilder);
                    }
                    break;
                case EndTag:
                    String name2 = token.asEndTag().name();
                    if (name2.equals("head")) {
                        htmlTreeBuilder.pop();
                        htmlTreeBuilder.transition(AfterHead);
                        break;
                    } else if (StringUtil.m211in(name2, "body", "html", "br")) {
                        return anythingElse(token, htmlTreeBuilder);
                    } else {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                default:
                    return anythingElse(token, htmlTreeBuilder);
            }
            return true;
        }

        private boolean anythingElse(Token token, TreeBuilder treeBuilder) {
            treeBuilder.process(new Token.EndTag("head"));
            return treeBuilder.process(token);
        }
    },
    InHeadNoscript { // from class: org.jsoup.parser.HtmlTreeBuilderState.5
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.isDoctype()) {
                htmlTreeBuilder.error(this);
                return true;
            } else if (token.isStartTag() && token.asStartTag().name().equals("html")) {
                return htmlTreeBuilder.process(token, InBody);
            } else {
                if (!token.isEndTag() || !token.asEndTag().name().equals("noscript")) {
                    if (HtmlTreeBuilderState.isWhitespace(token) || token.isComment() || (token.isStartTag() && StringUtil.m211in(token.asStartTag().name(), "basefont", "bgsound", "link", "meta", "noframes", "style"))) {
                        return htmlTreeBuilder.process(token, InHead);
                    }
                    if (token.isEndTag() && token.asEndTag().name().equals("br")) {
                        return anythingElse(token, htmlTreeBuilder);
                    }
                    if ((token.isStartTag() && StringUtil.m211in(token.asStartTag().name(), "head", "noscript")) || token.isEndTag()) {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                    return anythingElse(token, htmlTreeBuilder);
                }
                htmlTreeBuilder.pop();
                htmlTreeBuilder.transition(InHead);
                return true;
            }
        }

        private boolean anythingElse(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            htmlTreeBuilder.error(this);
            htmlTreeBuilder.process(new Token.EndTag("noscript"));
            return htmlTreeBuilder.process(token);
        }
    },
    AfterHead { // from class: org.jsoup.parser.HtmlTreeBuilderState.6
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                htmlTreeBuilder.insert(token.asCharacter());
                return true;
            } else if (token.isComment()) {
                htmlTreeBuilder.insert(token.asComment());
                return true;
            } else if (token.isDoctype()) {
                htmlTreeBuilder.error(this);
                return true;
            } else if (token.isStartTag()) {
                Token.StartTag asStartTag = token.asStartTag();
                String name = asStartTag.name();
                if (name.equals("html")) {
                    return htmlTreeBuilder.process(token, InBody);
                }
                if (name.equals("body")) {
                    htmlTreeBuilder.insert(asStartTag);
                    htmlTreeBuilder.framesetOk(false);
                    htmlTreeBuilder.transition(InBody);
                    return true;
                } else if (name.equals("frameset")) {
                    htmlTreeBuilder.insert(asStartTag);
                    htmlTreeBuilder.transition(InFrameset);
                    return true;
                } else if (StringUtil.m211in(name, "base", "basefont", "bgsound", "link", "meta", "noframes", "script", "style", "title")) {
                    htmlTreeBuilder.error(this);
                    Element headElement = htmlTreeBuilder.getHeadElement();
                    htmlTreeBuilder.push(headElement);
                    htmlTreeBuilder.process(token, InHead);
                    htmlTreeBuilder.removeFromStack(headElement);
                    return true;
                } else if (name.equals("head")) {
                    htmlTreeBuilder.error(this);
                    return false;
                } else {
                    anythingElse(token, htmlTreeBuilder);
                    return true;
                }
            } else if (token.isEndTag()) {
                if (StringUtil.m211in(token.asEndTag().name(), "body", "html")) {
                    anythingElse(token, htmlTreeBuilder);
                    return true;
                }
                htmlTreeBuilder.error(this);
                return false;
            } else {
                anythingElse(token, htmlTreeBuilder);
                return true;
            }
        }

        private boolean anythingElse(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            htmlTreeBuilder.process(new Token.StartTag("body"));
            htmlTreeBuilder.framesetOk(true);
            return htmlTreeBuilder.process(token);
        }
    },
    InBody { // from class: org.jsoup.parser.HtmlTreeBuilderState.7
        /* JADX WARN: Removed duplicated region for block: B:131:0x02b4  */
        /* JADX WARN: Removed duplicated region for block: B:149:0x0312  */
        /* JADX WARN: Removed duplicated region for block: B:153:0x031f  */
        /* JADX WARN: Removed duplicated region for block: B:159:0x034c A[LOOP:3: B:158:0x034a->B:159:0x034c, LOOP_END] */
        /* JADX WARN: Removed duplicated region for block: B:438:0x02a9 A[SYNTHETIC] */
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        boolean process(org.jsoup.parser.Token r41, org.jsoup.parser.HtmlTreeBuilder r42) {
            /*
                Method dump skipped, instructions count: 2800
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: org.jsoup.parser.HtmlTreeBuilderState.C134667.process(org.jsoup.parser.Token, org.jsoup.parser.HtmlTreeBuilder):boolean");
        }

        boolean anyOtherEndTag(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            String name = token.asEndTag().name();
            Iterator<Element> descendingIterator = htmlTreeBuilder.getStack().descendingIterator();
            while (descendingIterator.hasNext()) {
                Element next = descendingIterator.next();
                if (next.nodeName().equals(name)) {
                    htmlTreeBuilder.generateImpliedEndTags(name);
                    if (!name.equals(htmlTreeBuilder.currentElement().nodeName())) {
                        htmlTreeBuilder.error(this);
                    }
                    htmlTreeBuilder.popStackToClose(name);
                    return true;
                } else if (htmlTreeBuilder.isSpecial(next)) {
                    htmlTreeBuilder.error(this);
                    return false;
                }
            }
            return true;
        }
    },
    Text { // from class: org.jsoup.parser.HtmlTreeBuilderState.8
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.isCharacter()) {
                htmlTreeBuilder.insert(token.asCharacter());
                return true;
            } else if (token.isEOF()) {
                htmlTreeBuilder.error(this);
                htmlTreeBuilder.pop();
                htmlTreeBuilder.transition(htmlTreeBuilder.originalState());
                return htmlTreeBuilder.process(token);
            } else if (token.isEndTag()) {
                htmlTreeBuilder.pop();
                htmlTreeBuilder.transition(htmlTreeBuilder.originalState());
                return true;
            } else {
                return true;
            }
        }
    },
    InTable { // from class: org.jsoup.parser.HtmlTreeBuilderState.9
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.isCharacter()) {
                htmlTreeBuilder.newPendingTableCharacters();
                htmlTreeBuilder.markInsertionMode();
                htmlTreeBuilder.transition(InTableText);
                return htmlTreeBuilder.process(token);
            } else if (token.isComment()) {
                htmlTreeBuilder.insert(token.asComment());
                return true;
            } else if (token.isDoctype()) {
                htmlTreeBuilder.error(this);
                return false;
            } else {
                if (token.isStartTag()) {
                    Token.StartTag asStartTag = token.asStartTag();
                    String name = asStartTag.name();
                    if (name.equals("caption")) {
                        htmlTreeBuilder.clearStackToTableContext();
                        htmlTreeBuilder.insertMarkerToFormattingElements();
                        htmlTreeBuilder.insert(asStartTag);
                        htmlTreeBuilder.transition(InCaption);
                    } else if (name.equals("colgroup")) {
                        htmlTreeBuilder.clearStackToTableContext();
                        htmlTreeBuilder.insert(asStartTag);
                        htmlTreeBuilder.transition(InColumnGroup);
                    } else if (name.equals("col")) {
                        htmlTreeBuilder.process(new Token.StartTag("colgroup"));
                        return htmlTreeBuilder.process(token);
                    } else if (StringUtil.m211in(name, "tbody", "tfoot", "thead")) {
                        htmlTreeBuilder.clearStackToTableContext();
                        htmlTreeBuilder.insert(asStartTag);
                        htmlTreeBuilder.transition(InTableBody);
                    } else if (StringUtil.m211in(name, "td", "th", "tr")) {
                        htmlTreeBuilder.process(new Token.StartTag("tbody"));
                        return htmlTreeBuilder.process(token);
                    } else if (name.equals("table")) {
                        htmlTreeBuilder.error(this);
                        if (htmlTreeBuilder.process(new Token.EndTag("table"))) {
                            return htmlTreeBuilder.process(token);
                        }
                    } else if (StringUtil.m211in(name, "style", "script")) {
                        return htmlTreeBuilder.process(token, InHead);
                    } else {
                        if (name.equals("input")) {
                            if (!asStartTag.attributes.get("type").equalsIgnoreCase("hidden")) {
                                return anythingElse(token, htmlTreeBuilder);
                            }
                            htmlTreeBuilder.insertEmpty(asStartTag);
                        } else if (name.equals("form")) {
                            htmlTreeBuilder.error(this);
                            if (htmlTreeBuilder.getFormElement() != null) {
                                return false;
                            }
                            htmlTreeBuilder.setFormElement(htmlTreeBuilder.insertEmpty(asStartTag));
                        } else {
                            return anythingElse(token, htmlTreeBuilder);
                        }
                    }
                } else if (token.isEndTag()) {
                    String name2 = token.asEndTag().name();
                    if (name2.equals("table")) {
                        if (!htmlTreeBuilder.inTableScope(name2)) {
                            htmlTreeBuilder.error(this);
                            return false;
                        }
                        htmlTreeBuilder.popStackToClose("table");
                        htmlTreeBuilder.resetInsertionMode();
                    } else if (StringUtil.m211in(name2, "body", "caption", "col", "colgroup", "html", "tbody", "td", "tfoot", "th", "thead", "tr")) {
                        htmlTreeBuilder.error(this);
                        return false;
                    } else {
                        return anythingElse(token, htmlTreeBuilder);
                    }
                } else if (token.isEOF()) {
                    if (htmlTreeBuilder.currentElement().nodeName().equals("html")) {
                        htmlTreeBuilder.error(this);
                    }
                    return true;
                }
                return anythingElse(token, htmlTreeBuilder);
            }
        }

        boolean anythingElse(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            htmlTreeBuilder.error(this);
            if (StringUtil.m211in(htmlTreeBuilder.currentElement().nodeName(), "table", "tbody", "tfoot", "thead", "tr")) {
                htmlTreeBuilder.setFosterInserts(true);
                boolean process = htmlTreeBuilder.process(token, InBody);
                htmlTreeBuilder.setFosterInserts(false);
                return process;
            }
            return htmlTreeBuilder.process(token, InBody);
        }
    },
    InTableText { // from class: org.jsoup.parser.HtmlTreeBuilderState.10
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (C1346124.$SwitchMap$org$jsoup$parser$Token$TokenType[token.type.ordinal()] == 5) {
                Token.Character asCharacter = token.asCharacter();
                if (asCharacter.getData().equals(HtmlTreeBuilderState.nullString)) {
                    htmlTreeBuilder.error(this);
                    return false;
                }
                htmlTreeBuilder.getPendingTableCharacters().add(asCharacter);
                return true;
            }
            if (htmlTreeBuilder.getPendingTableCharacters().size() > 0) {
                for (Token.Character character : htmlTreeBuilder.getPendingTableCharacters()) {
                    if (!HtmlTreeBuilderState.isWhitespace(character)) {
                        htmlTreeBuilder.error(this);
                        if (StringUtil.m211in(htmlTreeBuilder.currentElement().nodeName(), "table", "tbody", "tfoot", "thead", "tr")) {
                            htmlTreeBuilder.setFosterInserts(true);
                            htmlTreeBuilder.process(character, InBody);
                            htmlTreeBuilder.setFosterInserts(false);
                        } else {
                            htmlTreeBuilder.process(character, InBody);
                        }
                    } else {
                        htmlTreeBuilder.insert(character);
                    }
                }
                htmlTreeBuilder.newPendingTableCharacters();
            }
            htmlTreeBuilder.transition(htmlTreeBuilder.originalState());
            return htmlTreeBuilder.process(token);
        }
    },
    InCaption { // from class: org.jsoup.parser.HtmlTreeBuilderState.11
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.isEndTag() && token.asEndTag().name().equals("caption")) {
                if (!htmlTreeBuilder.inTableScope(token.asEndTag().name())) {
                    htmlTreeBuilder.error(this);
                    return false;
                }
                htmlTreeBuilder.generateImpliedEndTags();
                if (!htmlTreeBuilder.currentElement().nodeName().equals("caption")) {
                    htmlTreeBuilder.error(this);
                }
                htmlTreeBuilder.popStackToClose("caption");
                htmlTreeBuilder.clearFormattingElementsToLastMarker();
                htmlTreeBuilder.transition(InTable);
                return true;
            } else if ((token.isStartTag() && StringUtil.m211in(token.asStartTag().name(), "caption", "col", "colgroup", "tbody", "td", "tfoot", "th", "thead", "tr")) || (token.isEndTag() && token.asEndTag().name().equals("table"))) {
                htmlTreeBuilder.error(this);
                if (htmlTreeBuilder.process(new Token.EndTag("caption"))) {
                    return htmlTreeBuilder.process(token);
                }
                return true;
            } else if (token.isEndTag() && StringUtil.m211in(token.asEndTag().name(), "body", "col", "colgroup", "html", "tbody", "td", "tfoot", "th", "thead", "tr")) {
                htmlTreeBuilder.error(this);
                return false;
            } else {
                return htmlTreeBuilder.process(token, InBody);
            }
        }
    },
    InColumnGroup { // from class: org.jsoup.parser.HtmlTreeBuilderState.12
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                htmlTreeBuilder.insert(token.asCharacter());
                return true;
            }
            int i = C1346124.$SwitchMap$org$jsoup$parser$Token$TokenType[token.type.ordinal()];
            if (i != 6) {
                switch (i) {
                    case 1:
                        htmlTreeBuilder.insert(token.asComment());
                        break;
                    case 2:
                        htmlTreeBuilder.error(this);
                        break;
                    case 3:
                        Token.StartTag asStartTag = token.asStartTag();
                        String name = asStartTag.name();
                        if (name.equals("html")) {
                            return htmlTreeBuilder.process(token, InBody);
                        }
                        if (name.equals("col")) {
                            htmlTreeBuilder.insertEmpty(asStartTag);
                            break;
                        } else {
                            return anythingElse(token, htmlTreeBuilder);
                        }
                    case 4:
                        if (token.asEndTag().name().equals("colgroup")) {
                            if (htmlTreeBuilder.currentElement().nodeName().equals("html")) {
                                htmlTreeBuilder.error(this);
                                return false;
                            }
                            htmlTreeBuilder.pop();
                            htmlTreeBuilder.transition(InTable);
                            break;
                        } else {
                            return anythingElse(token, htmlTreeBuilder);
                        }
                    default:
                        return anythingElse(token, htmlTreeBuilder);
                }
                return true;
            } else if (htmlTreeBuilder.currentElement().nodeName().equals("html")) {
                return true;
            } else {
                return anythingElse(token, htmlTreeBuilder);
            }
        }

        private boolean anythingElse(Token token, TreeBuilder treeBuilder) {
            if (treeBuilder.process(new Token.EndTag("colgroup"))) {
                return treeBuilder.process(token);
            }
            return true;
        }
    },
    InTableBody { // from class: org.jsoup.parser.HtmlTreeBuilderState.13
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            switch (C1346124.$SwitchMap$org$jsoup$parser$Token$TokenType[token.type.ordinal()]) {
                case 3:
                    Token.StartTag asStartTag = token.asStartTag();
                    String name = asStartTag.name();
                    if (name.equals("tr")) {
                        htmlTreeBuilder.clearStackToTableBodyContext();
                        htmlTreeBuilder.insert(asStartTag);
                        htmlTreeBuilder.transition(InRow);
                        return true;
                    } else if (StringUtil.m211in(name, "th", "td")) {
                        htmlTreeBuilder.error(this);
                        htmlTreeBuilder.process(new Token.StartTag("tr"));
                        return htmlTreeBuilder.process(asStartTag);
                    } else if (StringUtil.m211in(name, "caption", "col", "colgroup", "tbody", "tfoot", "thead")) {
                        return exitTableBody(token, htmlTreeBuilder);
                    } else {
                        return anythingElse(token, htmlTreeBuilder);
                    }
                case 4:
                    String name2 = token.asEndTag().name();
                    if (StringUtil.m211in(name2, "tbody", "tfoot", "thead")) {
                        if (!htmlTreeBuilder.inTableScope(name2)) {
                            htmlTreeBuilder.error(this);
                            return false;
                        }
                        htmlTreeBuilder.clearStackToTableBodyContext();
                        htmlTreeBuilder.pop();
                        htmlTreeBuilder.transition(InTable);
                        return true;
                    } else if (name2.equals("table")) {
                        return exitTableBody(token, htmlTreeBuilder);
                    } else {
                        if (StringUtil.m211in(name2, "body", "caption", "col", "colgroup", "html", "td", "th", "tr")) {
                            htmlTreeBuilder.error(this);
                            return false;
                        }
                        return anythingElse(token, htmlTreeBuilder);
                    }
                default:
                    return anythingElse(token, htmlTreeBuilder);
            }
        }

        private boolean exitTableBody(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (!htmlTreeBuilder.inTableScope("tbody") && !htmlTreeBuilder.inTableScope("thead") && !htmlTreeBuilder.inScope("tfoot")) {
                htmlTreeBuilder.error(this);
                return false;
            }
            htmlTreeBuilder.clearStackToTableBodyContext();
            htmlTreeBuilder.process(new Token.EndTag(htmlTreeBuilder.currentElement().nodeName()));
            return htmlTreeBuilder.process(token);
        }

        private boolean anythingElse(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            return htmlTreeBuilder.process(token, InTable);
        }
    },
    InRow { // from class: org.jsoup.parser.HtmlTreeBuilderState.14
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.isStartTag()) {
                Token.StartTag asStartTag = token.asStartTag();
                String name = asStartTag.name();
                if (StringUtil.m211in(name, "th", "td")) {
                    htmlTreeBuilder.clearStackToTableRowContext();
                    htmlTreeBuilder.insert(asStartTag);
                    htmlTreeBuilder.transition(InCell);
                    htmlTreeBuilder.insertMarkerToFormattingElements();
                    return true;
                } else if (StringUtil.m211in(name, "caption", "col", "colgroup", "tbody", "tfoot", "thead", "tr")) {
                    return handleMissingTr(token, htmlTreeBuilder);
                } else {
                    return anythingElse(token, htmlTreeBuilder);
                }
            } else if (token.isEndTag()) {
                String name2 = token.asEndTag().name();
                if (name2.equals("tr")) {
                    if (!htmlTreeBuilder.inTableScope(name2)) {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                    htmlTreeBuilder.clearStackToTableRowContext();
                    htmlTreeBuilder.pop();
                    htmlTreeBuilder.transition(InTableBody);
                    return true;
                } else if (name2.equals("table")) {
                    return handleMissingTr(token, htmlTreeBuilder);
                } else {
                    if (StringUtil.m211in(name2, "tbody", "tfoot", "thead")) {
                        if (!htmlTreeBuilder.inTableScope(name2)) {
                            htmlTreeBuilder.error(this);
                            return false;
                        }
                        htmlTreeBuilder.process(new Token.EndTag("tr"));
                        return htmlTreeBuilder.process(token);
                    } else if (StringUtil.m211in(name2, "body", "caption", "col", "colgroup", "html", "td", "th")) {
                        htmlTreeBuilder.error(this);
                        return false;
                    } else {
                        return anythingElse(token, htmlTreeBuilder);
                    }
                }
            } else {
                return anythingElse(token, htmlTreeBuilder);
            }
        }

        private boolean anythingElse(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            return htmlTreeBuilder.process(token, InTable);
        }

        private boolean handleMissingTr(Token token, TreeBuilder treeBuilder) {
            if (treeBuilder.process(new Token.EndTag("tr"))) {
                return treeBuilder.process(token);
            }
            return false;
        }
    },
    InCell { // from class: org.jsoup.parser.HtmlTreeBuilderState.15
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.isEndTag()) {
                String name = token.asEndTag().name();
                if (StringUtil.m211in(name, "td", "th")) {
                    if (!htmlTreeBuilder.inTableScope(name)) {
                        htmlTreeBuilder.error(this);
                        htmlTreeBuilder.transition(InRow);
                        return false;
                    }
                    htmlTreeBuilder.generateImpliedEndTags();
                    if (!htmlTreeBuilder.currentElement().nodeName().equals(name)) {
                        htmlTreeBuilder.error(this);
                    }
                    htmlTreeBuilder.popStackToClose(name);
                    htmlTreeBuilder.clearFormattingElementsToLastMarker();
                    htmlTreeBuilder.transition(InRow);
                    return true;
                } else if (StringUtil.m211in(name, "body", "caption", "col", "colgroup", "html")) {
                    htmlTreeBuilder.error(this);
                    return false;
                } else if (StringUtil.m211in(name, "table", "tbody", "tfoot", "thead", "tr")) {
                    if (!htmlTreeBuilder.inTableScope(name)) {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                    closeCell(htmlTreeBuilder);
                    return htmlTreeBuilder.process(token);
                } else {
                    return anythingElse(token, htmlTreeBuilder);
                }
            } else if (token.isStartTag() && StringUtil.m211in(token.asStartTag().name(), "caption", "col", "colgroup", "tbody", "td", "tfoot", "th", "thead", "tr")) {
                if (!htmlTreeBuilder.inTableScope("td") && !htmlTreeBuilder.inTableScope("th")) {
                    htmlTreeBuilder.error(this);
                    return false;
                }
                closeCell(htmlTreeBuilder);
                return htmlTreeBuilder.process(token);
            } else {
                return anythingElse(token, htmlTreeBuilder);
            }
        }

        private boolean anythingElse(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            return htmlTreeBuilder.process(token, InBody);
        }

        private void closeCell(HtmlTreeBuilder htmlTreeBuilder) {
            if (htmlTreeBuilder.inTableScope("td")) {
                htmlTreeBuilder.process(new Token.EndTag("td"));
            } else {
                htmlTreeBuilder.process(new Token.EndTag("th"));
            }
        }
    },
    InSelect { // from class: org.jsoup.parser.HtmlTreeBuilderState.16
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            switch (C1346124.$SwitchMap$org$jsoup$parser$Token$TokenType[token.type.ordinal()]) {
                case 1:
                    htmlTreeBuilder.insert(token.asComment());
                    return true;
                case 2:
                    htmlTreeBuilder.error(this);
                    return false;
                case 3:
                    Token.StartTag asStartTag = token.asStartTag();
                    String name = asStartTag.name();
                    if (name.equals("html")) {
                        return htmlTreeBuilder.process(asStartTag, InBody);
                    }
                    if (name.equals("option")) {
                        htmlTreeBuilder.process(new Token.EndTag("option"));
                        htmlTreeBuilder.insert(asStartTag);
                        return true;
                    } else if (name.equals("optgroup")) {
                        if (htmlTreeBuilder.currentElement().nodeName().equals("option")) {
                            htmlTreeBuilder.process(new Token.EndTag("option"));
                        } else if (htmlTreeBuilder.currentElement().nodeName().equals("optgroup")) {
                            htmlTreeBuilder.process(new Token.EndTag("optgroup"));
                        }
                        htmlTreeBuilder.insert(asStartTag);
                        return true;
                    } else if (name.equals("select")) {
                        htmlTreeBuilder.error(this);
                        return htmlTreeBuilder.process(new Token.EndTag("select"));
                    } else if (StringUtil.m211in(name, "input", "keygen", "textarea")) {
                        htmlTreeBuilder.error(this);
                        if (htmlTreeBuilder.inSelectScope("select")) {
                            htmlTreeBuilder.process(new Token.EndTag("select"));
                            return htmlTreeBuilder.process(asStartTag);
                        }
                        return false;
                    } else if (name.equals("script")) {
                        return htmlTreeBuilder.process(token, InHead);
                    } else {
                        return anythingElse(token, htmlTreeBuilder);
                    }
                case 4:
                    String name2 = token.asEndTag().name();
                    if (name2.equals("optgroup")) {
                        if (htmlTreeBuilder.currentElement().nodeName().equals("option") && htmlTreeBuilder.aboveOnStack(htmlTreeBuilder.currentElement()) != null && htmlTreeBuilder.aboveOnStack(htmlTreeBuilder.currentElement()).nodeName().equals("optgroup")) {
                            htmlTreeBuilder.process(new Token.EndTag("option"));
                        }
                        if (htmlTreeBuilder.currentElement().nodeName().equals("optgroup")) {
                            htmlTreeBuilder.pop();
                            return true;
                        }
                        htmlTreeBuilder.error(this);
                        return true;
                    } else if (name2.equals("option")) {
                        if (htmlTreeBuilder.currentElement().nodeName().equals("option")) {
                            htmlTreeBuilder.pop();
                            return true;
                        }
                        htmlTreeBuilder.error(this);
                        return true;
                    } else if (name2.equals("select")) {
                        if (!htmlTreeBuilder.inSelectScope(name2)) {
                            htmlTreeBuilder.error(this);
                            return false;
                        }
                        htmlTreeBuilder.popStackToClose(name2);
                        htmlTreeBuilder.resetInsertionMode();
                        return true;
                    } else {
                        return anythingElse(token, htmlTreeBuilder);
                    }
                case 5:
                    Token.Character asCharacter = token.asCharacter();
                    if (asCharacter.getData().equals(HtmlTreeBuilderState.nullString)) {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                    htmlTreeBuilder.insert(asCharacter);
                    return true;
                case 6:
                    if (htmlTreeBuilder.currentElement().nodeName().equals("html")) {
                        return true;
                    }
                    htmlTreeBuilder.error(this);
                    return true;
                default:
                    return anythingElse(token, htmlTreeBuilder);
            }
        }

        private boolean anythingElse(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            htmlTreeBuilder.error(this);
            return false;
        }
    },
    InSelectInTable { // from class: org.jsoup.parser.HtmlTreeBuilderState.17
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.isStartTag() && StringUtil.m211in(token.asStartTag().name(), "caption", "table", "tbody", "tfoot", "thead", "tr", "td", "th")) {
                htmlTreeBuilder.error(this);
                htmlTreeBuilder.process(new Token.EndTag("select"));
                return htmlTreeBuilder.process(token);
            } else if (token.isEndTag() && StringUtil.m211in(token.asEndTag().name(), "caption", "table", "tbody", "tfoot", "thead", "tr", "td", "th")) {
                htmlTreeBuilder.error(this);
                if (htmlTreeBuilder.inTableScope(token.asEndTag().name())) {
                    htmlTreeBuilder.process(new Token.EndTag("select"));
                    return htmlTreeBuilder.process(token);
                }
                return false;
            } else {
                return htmlTreeBuilder.process(token, InSelect);
            }
        }
    },
    AfterBody { // from class: org.jsoup.parser.HtmlTreeBuilderState.18
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                return htmlTreeBuilder.process(token, InBody);
            }
            if (token.isComment()) {
                htmlTreeBuilder.insert(token.asComment());
                return true;
            } else if (token.isDoctype()) {
                htmlTreeBuilder.error(this);
                return false;
            } else if (token.isStartTag() && token.asStartTag().name().equals("html")) {
                return htmlTreeBuilder.process(token, InBody);
            } else {
                if (token.isEndTag() && token.asEndTag().name().equals("html")) {
                    if (htmlTreeBuilder.isFragmentParsing()) {
                        htmlTreeBuilder.error(this);
                        return false;
                    }
                    htmlTreeBuilder.transition(AfterAfterBody);
                    return true;
                } else if (token.isEOF()) {
                    return true;
                } else {
                    htmlTreeBuilder.error(this);
                    htmlTreeBuilder.transition(InBody);
                    return htmlTreeBuilder.process(token);
                }
            }
        }
    },
    InFrameset { // from class: org.jsoup.parser.HtmlTreeBuilderState.19
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                htmlTreeBuilder.insert(token.asCharacter());
            } else if (token.isComment()) {
                htmlTreeBuilder.insert(token.asComment());
            } else if (token.isDoctype()) {
                htmlTreeBuilder.error(this);
                return false;
            } else if (token.isStartTag()) {
                Token.StartTag asStartTag = token.asStartTag();
                String name = asStartTag.name();
                if (name.equals("html")) {
                    return htmlTreeBuilder.process(asStartTag, InBody);
                }
                if (name.equals("frameset")) {
                    htmlTreeBuilder.insert(asStartTag);
                } else if (name.equals(DetectionAuthentic.FRAME)) {
                    htmlTreeBuilder.insertEmpty(asStartTag);
                } else if (name.equals("noframes")) {
                    return htmlTreeBuilder.process(asStartTag, InHead);
                } else {
                    htmlTreeBuilder.error(this);
                    return false;
                }
            } else if (token.isEndTag() && token.asEndTag().name().equals("frameset")) {
                if (htmlTreeBuilder.currentElement().nodeName().equals("html")) {
                    htmlTreeBuilder.error(this);
                    return false;
                }
                htmlTreeBuilder.pop();
                if (!htmlTreeBuilder.isFragmentParsing() && !htmlTreeBuilder.currentElement().nodeName().equals("frameset")) {
                    htmlTreeBuilder.transition(AfterFrameset);
                }
            } else if (token.isEOF()) {
                if (!htmlTreeBuilder.currentElement().nodeName().equals("html")) {
                    htmlTreeBuilder.error(this);
                    return true;
                }
            } else {
                htmlTreeBuilder.error(this);
                return false;
            }
            return true;
        }
    },
    AfterFrameset { // from class: org.jsoup.parser.HtmlTreeBuilderState.20
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                htmlTreeBuilder.insert(token.asCharacter());
                return true;
            } else if (token.isComment()) {
                htmlTreeBuilder.insert(token.asComment());
                return true;
            } else if (token.isDoctype()) {
                htmlTreeBuilder.error(this);
                return false;
            } else if (token.isStartTag() && token.asStartTag().name().equals("html")) {
                return htmlTreeBuilder.process(token, InBody);
            } else {
                if (token.isEndTag() && token.asEndTag().name().equals("html")) {
                    htmlTreeBuilder.transition(AfterAfterFrameset);
                    return true;
                } else if (token.isStartTag() && token.asStartTag().name().equals("noframes")) {
                    return htmlTreeBuilder.process(token, InHead);
                } else {
                    if (token.isEOF()) {
                        return true;
                    }
                    htmlTreeBuilder.error(this);
                    return false;
                }
            }
        }
    },
    AfterAfterBody { // from class: org.jsoup.parser.HtmlTreeBuilderState.21
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.isComment()) {
                htmlTreeBuilder.insert(token.asComment());
                return true;
            } else if (token.isDoctype() || HtmlTreeBuilderState.isWhitespace(token) || (token.isStartTag() && token.asStartTag().name().equals("html"))) {
                return htmlTreeBuilder.process(token, InBody);
            } else {
                if (token.isEOF()) {
                    return true;
                }
                htmlTreeBuilder.error(this);
                htmlTreeBuilder.transition(InBody);
                return htmlTreeBuilder.process(token);
            }
        }
    },
    AfterAfterFrameset { // from class: org.jsoup.parser.HtmlTreeBuilderState.22
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.isComment()) {
                htmlTreeBuilder.insert(token.asComment());
                return true;
            } else if (token.isDoctype() || HtmlTreeBuilderState.isWhitespace(token) || (token.isStartTag() && token.asStartTag().name().equals("html"))) {
                return htmlTreeBuilder.process(token, InBody);
            } else {
                if (token.isEOF()) {
                    return true;
                }
                if (token.isStartTag() && token.asStartTag().name().equals("noframes")) {
                    return htmlTreeBuilder.process(token, InHead);
                }
                htmlTreeBuilder.error(this);
                return false;
            }
        }
    },
    ForeignContent { // from class: org.jsoup.parser.HtmlTreeBuilderState.23
        @Override // org.jsoup.parser.HtmlTreeBuilderState
        boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            return true;
        }
    };
    
    private static String nullString = String.valueOf((char) 0);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder);

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isWhitespace(Token token) {
        if (token.isCharacter()) {
            String data = token.asCharacter().getData();
            for (int i = 0; i < data.length(); i++) {
                if (!StringUtil.isWhitespace(data.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void handleRcData(Token.StartTag startTag, HtmlTreeBuilder htmlTreeBuilder) {
        htmlTreeBuilder.insert(startTag);
        htmlTreeBuilder.tokeniser.transition(TokeniserState.Rcdata);
        htmlTreeBuilder.markInsertionMode();
        htmlTreeBuilder.transition(Text);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void handleRawtext(Token.StartTag startTag, HtmlTreeBuilder htmlTreeBuilder) {
        htmlTreeBuilder.insert(startTag);
        htmlTreeBuilder.tokeniser.transition(TokeniserState.Rawtext);
        htmlTreeBuilder.markInsertionMode();
        htmlTreeBuilder.transition(Text);
    }
}
