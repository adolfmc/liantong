package org.jsoup.parser;

import org.jsoup.parser.Token;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public enum TokeniserState {
    Data { // from class: org.jsoup.parser.TokeniserState.1
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char current = characterReader.current();
            if (current == 0) {
                tokeniser.error(this);
                tokeniser.emit(characterReader.consume());
            } else if (current == '&') {
                tokeniser.advanceTransition(CharacterReferenceInData);
            } else if (current == '<') {
                tokeniser.advanceTransition(TagOpen);
            } else if (current == 65535) {
                tokeniser.emit(new Token.EOF());
            } else {
                tokeniser.emit(characterReader.consumeToAny('&', '<', 0));
            }
        }
    },
    CharacterReferenceInData { // from class: org.jsoup.parser.TokeniserState.2
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char[] consumeCharacterReference = tokeniser.consumeCharacterReference(null, false);
            if (consumeCharacterReference == null) {
                tokeniser.emit('&');
            } else {
                tokeniser.emit(consumeCharacterReference);
            }
            tokeniser.transition(Data);
        }
    },
    Rcdata { // from class: org.jsoup.parser.TokeniserState.3
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char current = characterReader.current();
            if (current == 0) {
                tokeniser.error(this);
                characterReader.advance();
                tokeniser.emit((char) 65533);
            } else if (current == '&') {
                tokeniser.advanceTransition(CharacterReferenceInRcdata);
            } else if (current == '<') {
                tokeniser.advanceTransition(RcdataLessthanSign);
            } else if (current == 65535) {
                tokeniser.emit(new Token.EOF());
            } else {
                tokeniser.emit(characterReader.consumeToAny('&', '<', 0));
            }
        }
    },
    CharacterReferenceInRcdata { // from class: org.jsoup.parser.TokeniserState.4
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char[] consumeCharacterReference = tokeniser.consumeCharacterReference(null, false);
            if (consumeCharacterReference == null) {
                tokeniser.emit('&');
            } else {
                tokeniser.emit(consumeCharacterReference);
            }
            tokeniser.transition(Rcdata);
        }
    },
    Rawtext { // from class: org.jsoup.parser.TokeniserState.5
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char current = characterReader.current();
            if (current == 0) {
                tokeniser.error(this);
                characterReader.advance();
                tokeniser.emit((char) 65533);
            } else if (current == '<') {
                tokeniser.advanceTransition(RawtextLessthanSign);
            } else if (current == 65535) {
                tokeniser.emit(new Token.EOF());
            } else {
                tokeniser.emit(characterReader.consumeToAny('<', 0));
            }
        }
    },
    ScriptData { // from class: org.jsoup.parser.TokeniserState.6
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char current = characterReader.current();
            if (current == 0) {
                tokeniser.error(this);
                characterReader.advance();
                tokeniser.emit((char) 65533);
            } else if (current == '<') {
                tokeniser.advanceTransition(ScriptDataLessthanSign);
            } else if (current == 65535) {
                tokeniser.emit(new Token.EOF());
            } else {
                tokeniser.emit(characterReader.consumeToAny('<', 0));
            }
        }
    },
    PLAINTEXT { // from class: org.jsoup.parser.TokeniserState.7
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char current = characterReader.current();
            if (current == 0) {
                tokeniser.error(this);
                characterReader.advance();
                tokeniser.emit((char) 65533);
            } else if (current == 65535) {
                tokeniser.emit(new Token.EOF());
            } else {
                tokeniser.emit(characterReader.consumeTo((char) 0));
            }
        }
    },
    TagOpen { // from class: org.jsoup.parser.TokeniserState.8
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char current = characterReader.current();
            if (current == '!') {
                tokeniser.advanceTransition(MarkupDeclarationOpen);
            } else if (current == '/') {
                tokeniser.advanceTransition(EndTagOpen);
            } else if (current == '?') {
                tokeniser.advanceTransition(BogusComment);
            } else if (characterReader.matchesLetter()) {
                tokeniser.createTagPending(true);
                tokeniser.transition(TagName);
            } else {
                tokeniser.error(this);
                tokeniser.emit('<');
                tokeniser.transition(Data);
            }
        }
    },
    EndTagOpen { // from class: org.jsoup.parser.TokeniserState.9
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.isEmpty()) {
                tokeniser.eofError(this);
                tokeniser.emit("</");
                tokeniser.transition(Data);
            } else if (characterReader.matchesLetter()) {
                tokeniser.createTagPending(false);
                tokeniser.transition(TagName);
            } else if (characterReader.matches('>')) {
                tokeniser.error(this);
                tokeniser.advanceTransition(Data);
            } else {
                tokeniser.error(this);
                tokeniser.advanceTransition(BogusComment);
            }
        }
    },
    TagName { // from class: org.jsoup.parser.TokeniserState.10
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            tokeniser.tagPending.appendTagName(characterReader.consumeToAny('\t', '\n', '\r', '\f', ' ', '/', '>', 0).toLowerCase());
            switch (characterReader.consume()) {
                case 0:
                    tokeniser.tagPending.appendTagName(TokeniserState.replacementStr);
                    return;
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    tokeniser.transition(BeforeAttributeName);
                    return;
                case '/':
                    tokeniser.transition(SelfClosingStartTag);
                    return;
                case '>':
                    tokeniser.emitTagPending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.transition(Data);
                    return;
                default:
                    return;
            }
        }
    },
    RcdataLessthanSign { // from class: org.jsoup.parser.TokeniserState.11
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.matches('/')) {
                tokeniser.createTempBuffer();
                tokeniser.advanceTransition(RCDATAEndTagOpen);
                return;
            }
            if (characterReader.matchesLetter()) {
                if (!characterReader.containsIgnoreCase("</" + tokeniser.appropriateEndTagName())) {
                    tokeniser.tagPending = new Token.EndTag(tokeniser.appropriateEndTagName());
                    tokeniser.emitTagPending();
                    characterReader.unconsume();
                    tokeniser.transition(Data);
                    return;
                }
            }
            tokeniser.emit("<");
            tokeniser.transition(Rcdata);
        }
    },
    RCDATAEndTagOpen { // from class: org.jsoup.parser.TokeniserState.12
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.matchesLetter()) {
                tokeniser.createTagPending(false);
                tokeniser.tagPending.appendTagName(Character.toLowerCase(characterReader.current()));
                tokeniser.dataBuffer.append(Character.toLowerCase(characterReader.current()));
                tokeniser.advanceTransition(RCDATAEndTagName);
                return;
            }
            tokeniser.emit("</");
            tokeniser.transition(Rcdata);
        }
    },
    RCDATAEndTagName { // from class: org.jsoup.parser.TokeniserState.13
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.matchesLetter()) {
                String consumeLetterSequence = characterReader.consumeLetterSequence();
                tokeniser.tagPending.appendTagName(consumeLetterSequence.toLowerCase());
                tokeniser.dataBuffer.append(consumeLetterSequence);
                return;
            }
            switch (characterReader.consume()) {
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    if (tokeniser.isAppropriateEndTagToken()) {
                        tokeniser.transition(BeforeAttributeName);
                        return;
                    } else {
                        anythingElse(tokeniser, characterReader);
                        return;
                    }
                case '/':
                    if (tokeniser.isAppropriateEndTagToken()) {
                        tokeniser.transition(SelfClosingStartTag);
                        return;
                    } else {
                        anythingElse(tokeniser, characterReader);
                        return;
                    }
                case '>':
                    if (tokeniser.isAppropriateEndTagToken()) {
                        tokeniser.emitTagPending();
                        tokeniser.transition(Data);
                        return;
                    }
                    anythingElse(tokeniser, characterReader);
                    return;
                default:
                    anythingElse(tokeniser, characterReader);
                    return;
            }
        }

        private void anythingElse(Tokeniser tokeniser, CharacterReader characterReader) {
            tokeniser.emit("</" + tokeniser.dataBuffer.toString());
            characterReader.unconsume();
            tokeniser.transition(Rcdata);
        }
    },
    RawtextLessthanSign { // from class: org.jsoup.parser.TokeniserState.14
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.matches('/')) {
                tokeniser.createTempBuffer();
                tokeniser.advanceTransition(RawtextEndTagOpen);
                return;
            }
            tokeniser.emit('<');
            tokeniser.transition(Rawtext);
        }
    },
    RawtextEndTagOpen { // from class: org.jsoup.parser.TokeniserState.15
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.matchesLetter()) {
                tokeniser.createTagPending(false);
                tokeniser.transition(RawtextEndTagName);
                return;
            }
            tokeniser.emit("</");
            tokeniser.transition(Rawtext);
        }
    },
    RawtextEndTagName { // from class: org.jsoup.parser.TokeniserState.16
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.matchesLetter()) {
                String consumeLetterSequence = characterReader.consumeLetterSequence();
                tokeniser.tagPending.appendTagName(consumeLetterSequence.toLowerCase());
                tokeniser.dataBuffer.append(consumeLetterSequence);
            } else if (tokeniser.isAppropriateEndTagToken() && !characterReader.isEmpty()) {
                char consume = characterReader.consume();
                switch (consume) {
                    case '\t':
                    case '\n':
                    case '\f':
                    case '\r':
                    case ' ':
                        tokeniser.transition(BeforeAttributeName);
                        return;
                    case '/':
                        tokeniser.transition(SelfClosingStartTag);
                        return;
                    case '>':
                        tokeniser.emitTagPending();
                        tokeniser.transition(Data);
                        return;
                    default:
                        tokeniser.dataBuffer.append(consume);
                        anythingElse(tokeniser, characterReader);
                        return;
                }
            } else {
                anythingElse(tokeniser, characterReader);
            }
        }

        private void anythingElse(Tokeniser tokeniser, CharacterReader characterReader) {
            tokeniser.emit("</" + tokeniser.dataBuffer.toString());
            tokeniser.transition(Rawtext);
        }
    },
    ScriptDataLessthanSign { // from class: org.jsoup.parser.TokeniserState.17
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char consume = characterReader.consume();
            if (consume == '!') {
                tokeniser.emit("<!");
                tokeniser.transition(ScriptDataEscapeStart);
            } else if (consume == '/') {
                tokeniser.createTempBuffer();
                tokeniser.transition(ScriptDataEndTagOpen);
            } else {
                tokeniser.emit("<");
                characterReader.unconsume();
                tokeniser.transition(ScriptData);
            }
        }
    },
    ScriptDataEndTagOpen { // from class: org.jsoup.parser.TokeniserState.18
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.matchesLetter()) {
                tokeniser.createTagPending(false);
                tokeniser.transition(ScriptDataEndTagName);
                return;
            }
            tokeniser.emit("</");
            tokeniser.transition(ScriptData);
        }
    },
    ScriptDataEndTagName { // from class: org.jsoup.parser.TokeniserState.19
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.matchesLetter()) {
                String consumeLetterSequence = characterReader.consumeLetterSequence();
                tokeniser.tagPending.appendTagName(consumeLetterSequence.toLowerCase());
                tokeniser.dataBuffer.append(consumeLetterSequence);
            } else if (tokeniser.isAppropriateEndTagToken() && !characterReader.isEmpty()) {
                char consume = characterReader.consume();
                switch (consume) {
                    case '\t':
                    case '\n':
                    case '\f':
                    case '\r':
                    case ' ':
                        tokeniser.transition(BeforeAttributeName);
                        return;
                    case '/':
                        tokeniser.transition(SelfClosingStartTag);
                        return;
                    case '>':
                        tokeniser.emitTagPending();
                        tokeniser.transition(Data);
                        return;
                    default:
                        tokeniser.dataBuffer.append(consume);
                        anythingElse(tokeniser, characterReader);
                        return;
                }
            } else {
                anythingElse(tokeniser, characterReader);
            }
        }

        private void anythingElse(Tokeniser tokeniser, CharacterReader characterReader) {
            tokeniser.emit("</" + tokeniser.dataBuffer.toString());
            tokeniser.transition(ScriptData);
        }
    },
    ScriptDataEscapeStart { // from class: org.jsoup.parser.TokeniserState.20
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.matches('-')) {
                tokeniser.emit('-');
                tokeniser.advanceTransition(ScriptDataEscapeStartDash);
                return;
            }
            tokeniser.transition(ScriptData);
        }
    },
    ScriptDataEscapeStartDash { // from class: org.jsoup.parser.TokeniserState.21
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.matches('-')) {
                tokeniser.emit('-');
                tokeniser.advanceTransition(ScriptDataEscapedDashDash);
                return;
            }
            tokeniser.transition(ScriptData);
        }
    },
    ScriptDataEscaped { // from class: org.jsoup.parser.TokeniserState.22
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.isEmpty()) {
                tokeniser.eofError(this);
                tokeniser.transition(Data);
                return;
            }
            char current = characterReader.current();
            if (current == 0) {
                tokeniser.error(this);
                characterReader.advance();
                tokeniser.emit((char) 65533);
            } else if (current == '-') {
                tokeniser.emit('-');
                tokeniser.advanceTransition(ScriptDataEscapedDash);
            } else if (current == '<') {
                tokeniser.advanceTransition(ScriptDataEscapedLessthanSign);
            } else {
                tokeniser.emit(characterReader.consumeToAny('-', '<', 0));
            }
        }
    },
    ScriptDataEscapedDash { // from class: org.jsoup.parser.TokeniserState.23
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.isEmpty()) {
                tokeniser.eofError(this);
                tokeniser.transition(Data);
                return;
            }
            char consume = characterReader.consume();
            if (consume == 0) {
                tokeniser.error(this);
                tokeniser.emit((char) 65533);
                tokeniser.transition(ScriptDataEscaped);
            } else if (consume == '-') {
                tokeniser.emit(consume);
                tokeniser.transition(ScriptDataEscapedDashDash);
            } else if (consume == '<') {
                tokeniser.transition(ScriptDataEscapedLessthanSign);
            } else {
                tokeniser.emit(consume);
                tokeniser.transition(ScriptDataEscaped);
            }
        }
    },
    ScriptDataEscapedDashDash { // from class: org.jsoup.parser.TokeniserState.24
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.isEmpty()) {
                tokeniser.eofError(this);
                tokeniser.transition(Data);
                return;
            }
            char consume = characterReader.consume();
            if (consume == 0) {
                tokeniser.error(this);
                tokeniser.emit((char) 65533);
                tokeniser.transition(ScriptDataEscaped);
            } else if (consume == '-') {
                tokeniser.emit(consume);
            } else if (consume == '<') {
                tokeniser.transition(ScriptDataEscapedLessthanSign);
            } else if (consume == '>') {
                tokeniser.emit(consume);
                tokeniser.transition(ScriptData);
            } else {
                tokeniser.emit(consume);
                tokeniser.transition(ScriptDataEscaped);
            }
        }
    },
    ScriptDataEscapedLessthanSign { // from class: org.jsoup.parser.TokeniserState.25
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.matchesLetter()) {
                tokeniser.createTempBuffer();
                tokeniser.dataBuffer.append(Character.toLowerCase(characterReader.current()));
                tokeniser.emit("<" + characterReader.current());
                tokeniser.advanceTransition(ScriptDataDoubleEscapeStart);
            } else if (characterReader.matches('/')) {
                tokeniser.createTempBuffer();
                tokeniser.advanceTransition(ScriptDataEscapedEndTagOpen);
            } else {
                tokeniser.emit('<');
                tokeniser.transition(ScriptDataEscaped);
            }
        }
    },
    ScriptDataEscapedEndTagOpen { // from class: org.jsoup.parser.TokeniserState.26
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.matchesLetter()) {
                tokeniser.createTagPending(false);
                tokeniser.tagPending.appendTagName(Character.toLowerCase(characterReader.current()));
                tokeniser.dataBuffer.append(characterReader.current());
                tokeniser.advanceTransition(ScriptDataEscapedEndTagName);
                return;
            }
            tokeniser.emit("</");
            tokeniser.transition(ScriptDataEscaped);
        }
    },
    ScriptDataEscapedEndTagName { // from class: org.jsoup.parser.TokeniserState.27
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.matchesLetter()) {
                String consumeLetterSequence = characterReader.consumeLetterSequence();
                tokeniser.tagPending.appendTagName(consumeLetterSequence.toLowerCase());
                tokeniser.dataBuffer.append(consumeLetterSequence);
            } else if (tokeniser.isAppropriateEndTagToken() && !characterReader.isEmpty()) {
                char consume = characterReader.consume();
                switch (consume) {
                    case '\t':
                    case '\n':
                    case '\f':
                    case '\r':
                    case ' ':
                        tokeniser.transition(BeforeAttributeName);
                        return;
                    case '/':
                        tokeniser.transition(SelfClosingStartTag);
                        return;
                    case '>':
                        tokeniser.emitTagPending();
                        tokeniser.transition(Data);
                        return;
                    default:
                        tokeniser.dataBuffer.append(consume);
                        anythingElse(tokeniser, characterReader);
                        return;
                }
            } else {
                anythingElse(tokeniser, characterReader);
            }
        }

        private void anythingElse(Tokeniser tokeniser, CharacterReader characterReader) {
            tokeniser.emit("</" + tokeniser.dataBuffer.toString());
            tokeniser.transition(ScriptDataEscaped);
        }
    },
    ScriptDataDoubleEscapeStart { // from class: org.jsoup.parser.TokeniserState.28
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.matchesLetter()) {
                String consumeLetterSequence = characterReader.consumeLetterSequence();
                tokeniser.dataBuffer.append(consumeLetterSequence.toLowerCase());
                tokeniser.emit(consumeLetterSequence);
                return;
            }
            char consume = characterReader.consume();
            switch (consume) {
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                case '/':
                case '>':
                    if (tokeniser.dataBuffer.toString().equals("script")) {
                        tokeniser.transition(ScriptDataDoubleEscaped);
                    } else {
                        tokeniser.transition(ScriptDataEscaped);
                    }
                    tokeniser.emit(consume);
                    return;
                default:
                    characterReader.unconsume();
                    tokeniser.transition(ScriptDataEscaped);
                    return;
            }
        }
    },
    ScriptDataDoubleEscaped { // from class: org.jsoup.parser.TokeniserState.29
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char current = characterReader.current();
            if (current == 0) {
                tokeniser.error(this);
                characterReader.advance();
                tokeniser.emit((char) 65533);
            } else if (current == '-') {
                tokeniser.emit(current);
                tokeniser.advanceTransition(ScriptDataDoubleEscapedDash);
            } else if (current == '<') {
                tokeniser.emit(current);
                tokeniser.advanceTransition(ScriptDataDoubleEscapedLessthanSign);
            } else if (current == 65535) {
                tokeniser.eofError(this);
                tokeniser.transition(Data);
            } else {
                tokeniser.emit(characterReader.consumeToAny('-', '<', 0));
            }
        }
    },
    ScriptDataDoubleEscapedDash { // from class: org.jsoup.parser.TokeniserState.30
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char consume = characterReader.consume();
            if (consume == 0) {
                tokeniser.error(this);
                tokeniser.emit((char) 65533);
                tokeniser.transition(ScriptDataDoubleEscaped);
            } else if (consume == '-') {
                tokeniser.emit(consume);
                tokeniser.transition(ScriptDataDoubleEscapedDashDash);
            } else if (consume == '<') {
                tokeniser.emit(consume);
                tokeniser.transition(ScriptDataDoubleEscapedLessthanSign);
            } else if (consume == 65535) {
                tokeniser.eofError(this);
                tokeniser.transition(Data);
            } else {
                tokeniser.emit(consume);
                tokeniser.transition(ScriptDataDoubleEscaped);
            }
        }
    },
    ScriptDataDoubleEscapedDashDash { // from class: org.jsoup.parser.TokeniserState.31
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char consume = characterReader.consume();
            if (consume == 0) {
                tokeniser.error(this);
                tokeniser.emit((char) 65533);
                tokeniser.transition(ScriptDataDoubleEscaped);
            } else if (consume == '-') {
                tokeniser.emit(consume);
            } else if (consume == '<') {
                tokeniser.emit(consume);
                tokeniser.transition(ScriptDataDoubleEscapedLessthanSign);
            } else if (consume == '>') {
                tokeniser.emit(consume);
                tokeniser.transition(ScriptData);
            } else if (consume == 65535) {
                tokeniser.eofError(this);
                tokeniser.transition(Data);
            } else {
                tokeniser.emit(consume);
                tokeniser.transition(ScriptDataDoubleEscaped);
            }
        }
    },
    ScriptDataDoubleEscapedLessthanSign { // from class: org.jsoup.parser.TokeniserState.32
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.matches('/')) {
                tokeniser.emit('/');
                tokeniser.createTempBuffer();
                tokeniser.advanceTransition(ScriptDataDoubleEscapeEnd);
                return;
            }
            tokeniser.transition(ScriptDataDoubleEscaped);
        }
    },
    ScriptDataDoubleEscapeEnd { // from class: org.jsoup.parser.TokeniserState.33
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.matchesLetter()) {
                String consumeLetterSequence = characterReader.consumeLetterSequence();
                tokeniser.dataBuffer.append(consumeLetterSequence.toLowerCase());
                tokeniser.emit(consumeLetterSequence);
                return;
            }
            char consume = characterReader.consume();
            switch (consume) {
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                case '/':
                case '>':
                    if (tokeniser.dataBuffer.toString().equals("script")) {
                        tokeniser.transition(ScriptDataEscaped);
                    } else {
                        tokeniser.transition(ScriptDataDoubleEscaped);
                    }
                    tokeniser.emit(consume);
                    return;
                default:
                    characterReader.unconsume();
                    tokeniser.transition(ScriptDataDoubleEscaped);
                    return;
            }
        }
    },
    BeforeAttributeName { // from class: org.jsoup.parser.TokeniserState.34
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char consume = characterReader.consume();
            switch (consume) {
                case 0:
                    tokeniser.error(this);
                    tokeniser.tagPending.newAttribute();
                    characterReader.unconsume();
                    tokeniser.transition(AttributeName);
                    return;
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    return;
                case '\"':
                case '\'':
                case '<':
                case '=':
                    tokeniser.error(this);
                    tokeniser.tagPending.newAttribute();
                    tokeniser.tagPending.appendAttributeName(consume);
                    tokeniser.transition(AttributeName);
                    return;
                case '/':
                    tokeniser.transition(SelfClosingStartTag);
                    return;
                case '>':
                    tokeniser.emitTagPending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.tagPending.newAttribute();
                    characterReader.unconsume();
                    tokeniser.transition(AttributeName);
                    return;
            }
        }
    },
    AttributeName { // from class: org.jsoup.parser.TokeniserState.35
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            tokeniser.tagPending.appendAttributeName(characterReader.consumeToAny('\t', '\n', '\r', '\f', ' ', '/', '=', '>', 0, '\"', '\'', '<').toLowerCase());
            char consume = characterReader.consume();
            switch (consume) {
                case 0:
                    tokeniser.error(this);
                    tokeniser.tagPending.appendAttributeName((char) 65533);
                    return;
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    tokeniser.transition(AfterAttributeName);
                    return;
                case '\"':
                case '\'':
                case '<':
                    tokeniser.error(this);
                    tokeniser.tagPending.appendAttributeName(consume);
                    return;
                case '/':
                    tokeniser.transition(SelfClosingStartTag);
                    return;
                case '=':
                    tokeniser.transition(BeforeAttributeValue);
                    return;
                case '>':
                    tokeniser.emitTagPending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.transition(Data);
                    return;
                default:
                    return;
            }
        }
    },
    AfterAttributeName { // from class: org.jsoup.parser.TokeniserState.36
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char consume = characterReader.consume();
            switch (consume) {
                case 0:
                    tokeniser.error(this);
                    tokeniser.tagPending.appendAttributeName((char) 65533);
                    tokeniser.transition(AttributeName);
                    return;
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    return;
                case '\"':
                case '\'':
                case '<':
                    tokeniser.error(this);
                    tokeniser.tagPending.newAttribute();
                    tokeniser.tagPending.appendAttributeName(consume);
                    tokeniser.transition(AttributeName);
                    return;
                case '/':
                    tokeniser.transition(SelfClosingStartTag);
                    return;
                case '=':
                    tokeniser.transition(BeforeAttributeValue);
                    return;
                case '>':
                    tokeniser.emitTagPending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.tagPending.newAttribute();
                    characterReader.unconsume();
                    tokeniser.transition(AttributeName);
                    return;
            }
        }
    },
    BeforeAttributeValue { // from class: org.jsoup.parser.TokeniserState.37
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char consume = characterReader.consume();
            switch (consume) {
                case 0:
                    tokeniser.error(this);
                    tokeniser.tagPending.appendAttributeValue((char) 65533);
                    tokeniser.transition(AttributeValue_unquoted);
                    return;
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    return;
                case '\"':
                    tokeniser.transition(AttributeValue_doubleQuoted);
                    return;
                case '&':
                    characterReader.unconsume();
                    tokeniser.transition(AttributeValue_unquoted);
                    return;
                case '\'':
                    tokeniser.transition(AttributeValue_singleQuoted);
                    return;
                case '<':
                case '=':
                case '`':
                    tokeniser.error(this);
                    tokeniser.tagPending.appendAttributeValue(consume);
                    tokeniser.transition(AttributeValue_unquoted);
                    return;
                case '>':
                    tokeniser.error(this);
                    tokeniser.emitTagPending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.transition(Data);
                    return;
                default:
                    characterReader.unconsume();
                    tokeniser.transition(AttributeValue_unquoted);
                    return;
            }
        }
    },
    AttributeValue_doubleQuoted { // from class: org.jsoup.parser.TokeniserState.38
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            String consumeToAny = characterReader.consumeToAny('\"', '&', 0);
            if (consumeToAny.length() > 0) {
                tokeniser.tagPending.appendAttributeValue(consumeToAny);
            }
            char consume = characterReader.consume();
            if (consume == 0) {
                tokeniser.error(this);
                tokeniser.tagPending.appendAttributeValue((char) 65533);
            } else if (consume == '\"') {
                tokeniser.transition(AfterAttributeValue_quoted);
            } else if (consume != '&') {
                if (consume != 65535) {
                    return;
                }
                tokeniser.eofError(this);
                tokeniser.transition(Data);
            } else {
                char[] consumeCharacterReference = tokeniser.consumeCharacterReference('\"', true);
                if (consumeCharacterReference != null) {
                    tokeniser.tagPending.appendAttributeValue(consumeCharacterReference);
                } else {
                    tokeniser.tagPending.appendAttributeValue('&');
                }
            }
        }
    },
    AttributeValue_singleQuoted { // from class: org.jsoup.parser.TokeniserState.39
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            String consumeToAny = characterReader.consumeToAny('\'', '&', 0);
            if (consumeToAny.length() > 0) {
                tokeniser.tagPending.appendAttributeValue(consumeToAny);
            }
            char consume = characterReader.consume();
            if (consume == 0) {
                tokeniser.error(this);
                tokeniser.tagPending.appendAttributeValue((char) 65533);
            } else if (consume != 65535) {
                switch (consume) {
                    case '&':
                        char[] consumeCharacterReference = tokeniser.consumeCharacterReference('\'', true);
                        if (consumeCharacterReference != null) {
                            tokeniser.tagPending.appendAttributeValue(consumeCharacterReference);
                            return;
                        } else {
                            tokeniser.tagPending.appendAttributeValue('&');
                            return;
                        }
                    case '\'':
                        tokeniser.transition(AfterAttributeValue_quoted);
                        return;
                    default:
                        return;
                }
            } else {
                tokeniser.eofError(this);
                tokeniser.transition(Data);
            }
        }
    },
    AttributeValue_unquoted { // from class: org.jsoup.parser.TokeniserState.40
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            String consumeToAny = characterReader.consumeToAny('\t', '\n', '\r', '\f', ' ', '&', '>', 0, '\"', '\'', '<', '=', '`');
            if (consumeToAny.length() > 0) {
                tokeniser.tagPending.appendAttributeValue(consumeToAny);
            }
            char consume = characterReader.consume();
            switch (consume) {
                case 0:
                    tokeniser.error(this);
                    tokeniser.tagPending.appendAttributeValue((char) 65533);
                    return;
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    tokeniser.transition(BeforeAttributeName);
                    return;
                case '\"':
                case '\'':
                case '<':
                case '=':
                case '`':
                    tokeniser.error(this);
                    tokeniser.tagPending.appendAttributeValue(consume);
                    return;
                case '&':
                    char[] consumeCharacterReference = tokeniser.consumeCharacterReference('>', true);
                    if (consumeCharacterReference != null) {
                        tokeniser.tagPending.appendAttributeValue(consumeCharacterReference);
                        return;
                    } else {
                        tokeniser.tagPending.appendAttributeValue('&');
                        return;
                    }
                case '>':
                    tokeniser.emitTagPending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.transition(Data);
                    return;
                default:
                    return;
            }
        }
    },
    AfterAttributeValue_quoted { // from class: org.jsoup.parser.TokeniserState.41
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            switch (characterReader.consume()) {
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    tokeniser.transition(BeforeAttributeName);
                    return;
                case '/':
                    tokeniser.transition(SelfClosingStartTag);
                    return;
                case '>':
                    tokeniser.emitTagPending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.error(this);
                    characterReader.unconsume();
                    tokeniser.transition(BeforeAttributeName);
                    return;
            }
        }
    },
    SelfClosingStartTag { // from class: org.jsoup.parser.TokeniserState.42
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char consume = characterReader.consume();
            if (consume == '>') {
                tokeniser.tagPending.selfClosing = true;
                tokeniser.emitTagPending();
                tokeniser.transition(Data);
            } else if (consume == 65535) {
                tokeniser.eofError(this);
                tokeniser.transition(Data);
            } else {
                tokeniser.error(this);
                tokeniser.transition(BeforeAttributeName);
            }
        }
    },
    BogusComment { // from class: org.jsoup.parser.TokeniserState.43
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            characterReader.unconsume();
            Token.Comment comment = new Token.Comment();
            comment.bogus = true;
            comment.data.append(characterReader.consumeTo('>'));
            tokeniser.emit(comment);
            tokeniser.advanceTransition(Data);
        }
    },
    MarkupDeclarationOpen { // from class: org.jsoup.parser.TokeniserState.44
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.matchConsume("--")) {
                tokeniser.createCommentPending();
                tokeniser.transition(CommentStart);
            } else if (characterReader.matchConsumeIgnoreCase("DOCTYPE")) {
                tokeniser.transition(Doctype);
            } else if (characterReader.matchConsume("[CDATA[")) {
                tokeniser.transition(CdataSection);
            } else {
                tokeniser.error(this);
                tokeniser.advanceTransition(BogusComment);
            }
        }
    },
    CommentStart { // from class: org.jsoup.parser.TokeniserState.45
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char consume = characterReader.consume();
            if (consume == 0) {
                tokeniser.error(this);
                tokeniser.commentPending.data.append((char) 65533);
                tokeniser.transition(Comment);
            } else if (consume == '-') {
                tokeniser.transition(CommentStartDash);
            } else if (consume == '>') {
                tokeniser.error(this);
                tokeniser.emitCommentPending();
                tokeniser.transition(Data);
            } else if (consume == 65535) {
                tokeniser.eofError(this);
                tokeniser.emitCommentPending();
                tokeniser.transition(Data);
            } else {
                tokeniser.commentPending.data.append(consume);
                tokeniser.transition(Comment);
            }
        }
    },
    CommentStartDash { // from class: org.jsoup.parser.TokeniserState.46
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char consume = characterReader.consume();
            if (consume == 0) {
                tokeniser.error(this);
                tokeniser.commentPending.data.append((char) 65533);
                tokeniser.transition(Comment);
            } else if (consume == '-') {
                tokeniser.transition(CommentStartDash);
            } else if (consume == '>') {
                tokeniser.error(this);
                tokeniser.emitCommentPending();
                tokeniser.transition(Data);
            } else if (consume == 65535) {
                tokeniser.eofError(this);
                tokeniser.emitCommentPending();
                tokeniser.transition(Data);
            } else {
                tokeniser.commentPending.data.append(consume);
                tokeniser.transition(Comment);
            }
        }
    },
    Comment { // from class: org.jsoup.parser.TokeniserState.47
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char current = characterReader.current();
            if (current == 0) {
                tokeniser.error(this);
                characterReader.advance();
                tokeniser.commentPending.data.append((char) 65533);
            } else if (current == '-') {
                tokeniser.advanceTransition(CommentEndDash);
            } else if (current == 65535) {
                tokeniser.eofError(this);
                tokeniser.emitCommentPending();
                tokeniser.transition(Data);
            } else {
                tokeniser.commentPending.data.append(characterReader.consumeToAny('-', 0));
            }
        }
    },
    CommentEndDash { // from class: org.jsoup.parser.TokeniserState.48
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char consume = characterReader.consume();
            if (consume == 0) {
                tokeniser.error(this);
                StringBuilder sb = tokeniser.commentPending.data;
                sb.append('-');
                sb.append((char) 65533);
                tokeniser.transition(Comment);
            } else if (consume == '-') {
                tokeniser.transition(CommentEnd);
            } else if (consume == 65535) {
                tokeniser.eofError(this);
                tokeniser.emitCommentPending();
                tokeniser.transition(Data);
            } else {
                StringBuilder sb2 = tokeniser.commentPending.data;
                sb2.append('-');
                sb2.append(consume);
                tokeniser.transition(Comment);
            }
        }
    },
    CommentEnd { // from class: org.jsoup.parser.TokeniserState.49
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char consume = characterReader.consume();
            if (consume == 0) {
                tokeniser.error(this);
                StringBuilder sb = tokeniser.commentPending.data;
                sb.append("--");
                sb.append((char) 65533);
                tokeniser.transition(Comment);
            } else if (consume == '!') {
                tokeniser.error(this);
                tokeniser.transition(CommentEndBang);
            } else if (consume == '-') {
                tokeniser.error(this);
                tokeniser.commentPending.data.append('-');
            } else if (consume == '>') {
                tokeniser.emitCommentPending();
                tokeniser.transition(Data);
            } else if (consume == 65535) {
                tokeniser.eofError(this);
                tokeniser.emitCommentPending();
                tokeniser.transition(Data);
            } else {
                tokeniser.error(this);
                StringBuilder sb2 = tokeniser.commentPending.data;
                sb2.append("--");
                sb2.append(consume);
                tokeniser.transition(Comment);
            }
        }
    },
    CommentEndBang { // from class: org.jsoup.parser.TokeniserState.50
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char consume = characterReader.consume();
            if (consume == 0) {
                tokeniser.error(this);
                StringBuilder sb = tokeniser.commentPending.data;
                sb.append("--!");
                sb.append((char) 65533);
                tokeniser.transition(Comment);
            } else if (consume == '-') {
                tokeniser.commentPending.data.append("--!");
                tokeniser.transition(CommentEndDash);
            } else if (consume == '>') {
                tokeniser.emitCommentPending();
                tokeniser.transition(Data);
            } else if (consume == 65535) {
                tokeniser.eofError(this);
                tokeniser.emitCommentPending();
                tokeniser.transition(Data);
            } else {
                StringBuilder sb2 = tokeniser.commentPending.data;
                sb2.append("--!");
                sb2.append(consume);
                tokeniser.transition(Comment);
            }
        }
    },
    Doctype { // from class: org.jsoup.parser.TokeniserState.51
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            switch (characterReader.consume()) {
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    tokeniser.transition(BeforeDoctypeName);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.createDoctypePending();
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.error(this);
                    tokeniser.transition(BeforeDoctypeName);
                    return;
            }
        }
    },
    BeforeDoctypeName { // from class: org.jsoup.parser.TokeniserState.52
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.matchesLetter()) {
                tokeniser.createDoctypePending();
                tokeniser.transition(DoctypeName);
                return;
            }
            char consume = characterReader.consume();
            switch (consume) {
                case 0:
                    tokeniser.error(this);
                    tokeniser.doctypePending.name.append((char) 65533);
                    tokeniser.transition(DoctypeName);
                    return;
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.createDoctypePending();
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.createDoctypePending();
                    tokeniser.doctypePending.name.append(consume);
                    tokeniser.transition(DoctypeName);
                    return;
            }
        }
    },
    DoctypeName { // from class: org.jsoup.parser.TokeniserState.53
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.matchesLetter()) {
                tokeniser.doctypePending.name.append(characterReader.consumeLetterSequence().toLowerCase());
                return;
            }
            char consume = characterReader.consume();
            switch (consume) {
                case 0:
                    tokeniser.error(this);
                    tokeniser.doctypePending.name.append((char) 65533);
                    return;
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    tokeniser.transition(AfterDoctypeName);
                    return;
                case '>':
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.doctypePending.name.append(consume);
                    return;
            }
        }
    },
    AfterDoctypeName { // from class: org.jsoup.parser.TokeniserState.54
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.isEmpty()) {
                tokeniser.eofError(this);
                tokeniser.doctypePending.forceQuirks = true;
                tokeniser.emitDoctypePending();
                tokeniser.transition(Data);
            } else if (characterReader.matchesAny('\t', '\n', '\r', '\f', ' ')) {
                characterReader.advance();
            } else if (characterReader.matches('>')) {
                tokeniser.emitDoctypePending();
                tokeniser.advanceTransition(Data);
            } else if (characterReader.matchConsumeIgnoreCase("PUBLIC")) {
                tokeniser.transition(AfterDoctypePublicKeyword);
            } else if (characterReader.matchConsumeIgnoreCase("SYSTEM")) {
                tokeniser.transition(AfterDoctypeSystemKeyword);
            } else {
                tokeniser.error(this);
                tokeniser.doctypePending.forceQuirks = true;
                tokeniser.advanceTransition(BogusDoctype);
            }
        }
    },
    AfterDoctypePublicKeyword { // from class: org.jsoup.parser.TokeniserState.55
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            switch (characterReader.consume()) {
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    tokeniser.transition(BeforeDoctypePublicIdentifier);
                    return;
                case '\"':
                    tokeniser.error(this);
                    tokeniser.transition(DoctypePublicIdentifier_doubleQuoted);
                    return;
                case '\'':
                    tokeniser.error(this);
                    tokeniser.transition(DoctypePublicIdentifier_singleQuoted);
                    return;
                case '>':
                    tokeniser.error(this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.error(this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.transition(BogusDoctype);
                    return;
            }
        }
    },
    BeforeDoctypePublicIdentifier { // from class: org.jsoup.parser.TokeniserState.56
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            switch (characterReader.consume()) {
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    return;
                case '\"':
                    tokeniser.transition(DoctypePublicIdentifier_doubleQuoted);
                    return;
                case '\'':
                    tokeniser.transition(DoctypePublicIdentifier_singleQuoted);
                    return;
                case '>':
                    tokeniser.error(this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.error(this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.transition(BogusDoctype);
                    return;
            }
        }
    },
    DoctypePublicIdentifier_doubleQuoted { // from class: org.jsoup.parser.TokeniserState.57
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char consume = characterReader.consume();
            if (consume == 0) {
                tokeniser.error(this);
                tokeniser.doctypePending.publicIdentifier.append((char) 65533);
            } else if (consume == '\"') {
                tokeniser.transition(AfterDoctypePublicIdentifier);
            } else if (consume == '>') {
                tokeniser.error(this);
                tokeniser.doctypePending.forceQuirks = true;
                tokeniser.emitDoctypePending();
                tokeniser.transition(Data);
            } else if (consume == 65535) {
                tokeniser.eofError(this);
                tokeniser.doctypePending.forceQuirks = true;
                tokeniser.emitDoctypePending();
                tokeniser.transition(Data);
            } else {
                tokeniser.doctypePending.publicIdentifier.append(consume);
            }
        }
    },
    DoctypePublicIdentifier_singleQuoted { // from class: org.jsoup.parser.TokeniserState.58
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char consume = characterReader.consume();
            if (consume == 0) {
                tokeniser.error(this);
                tokeniser.doctypePending.publicIdentifier.append((char) 65533);
            } else if (consume == '\'') {
                tokeniser.transition(AfterDoctypePublicIdentifier);
            } else if (consume == '>') {
                tokeniser.error(this);
                tokeniser.doctypePending.forceQuirks = true;
                tokeniser.emitDoctypePending();
                tokeniser.transition(Data);
            } else if (consume == 65535) {
                tokeniser.eofError(this);
                tokeniser.doctypePending.forceQuirks = true;
                tokeniser.emitDoctypePending();
                tokeniser.transition(Data);
            } else {
                tokeniser.doctypePending.publicIdentifier.append(consume);
            }
        }
    },
    AfterDoctypePublicIdentifier { // from class: org.jsoup.parser.TokeniserState.59
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            switch (characterReader.consume()) {
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    tokeniser.transition(BetweenDoctypePublicAndSystemIdentifiers);
                    return;
                case '\"':
                    tokeniser.error(this);
                    tokeniser.transition(DoctypeSystemIdentifier_doubleQuoted);
                    return;
                case '\'':
                    tokeniser.error(this);
                    tokeniser.transition(DoctypeSystemIdentifier_singleQuoted);
                    return;
                case '>':
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.error(this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.transition(BogusDoctype);
                    return;
            }
        }
    },
    BetweenDoctypePublicAndSystemIdentifiers { // from class: org.jsoup.parser.TokeniserState.60
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            switch (characterReader.consume()) {
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    return;
                case '\"':
                    tokeniser.error(this);
                    tokeniser.transition(DoctypeSystemIdentifier_doubleQuoted);
                    return;
                case '\'':
                    tokeniser.error(this);
                    tokeniser.transition(DoctypeSystemIdentifier_singleQuoted);
                    return;
                case '>':
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.error(this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.transition(BogusDoctype);
                    return;
            }
        }
    },
    AfterDoctypeSystemKeyword { // from class: org.jsoup.parser.TokeniserState.61
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            switch (characterReader.consume()) {
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    tokeniser.transition(BeforeDoctypeSystemIdentifier);
                    return;
                case '\"':
                    tokeniser.error(this);
                    tokeniser.transition(DoctypeSystemIdentifier_doubleQuoted);
                    return;
                case '\'':
                    tokeniser.error(this);
                    tokeniser.transition(DoctypeSystemIdentifier_singleQuoted);
                    return;
                case '>':
                    tokeniser.error(this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.error(this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.emitDoctypePending();
                    return;
            }
        }
    },
    BeforeDoctypeSystemIdentifier { // from class: org.jsoup.parser.TokeniserState.62
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            switch (characterReader.consume()) {
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    return;
                case '\"':
                    tokeniser.transition(DoctypeSystemIdentifier_doubleQuoted);
                    return;
                case '\'':
                    tokeniser.transition(DoctypeSystemIdentifier_singleQuoted);
                    return;
                case '>':
                    tokeniser.error(this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.error(this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.transition(BogusDoctype);
                    return;
            }
        }
    },
    DoctypeSystemIdentifier_doubleQuoted { // from class: org.jsoup.parser.TokeniserState.63
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char consume = characterReader.consume();
            if (consume == 0) {
                tokeniser.error(this);
                tokeniser.doctypePending.systemIdentifier.append((char) 65533);
            } else if (consume == '\"') {
                tokeniser.transition(AfterDoctypeSystemIdentifier);
            } else if (consume == '>') {
                tokeniser.error(this);
                tokeniser.doctypePending.forceQuirks = true;
                tokeniser.emitDoctypePending();
                tokeniser.transition(Data);
            } else if (consume == 65535) {
                tokeniser.eofError(this);
                tokeniser.doctypePending.forceQuirks = true;
                tokeniser.emitDoctypePending();
                tokeniser.transition(Data);
            } else {
                tokeniser.doctypePending.systemIdentifier.append(consume);
            }
        }
    },
    DoctypeSystemIdentifier_singleQuoted { // from class: org.jsoup.parser.TokeniserState.64
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char consume = characterReader.consume();
            if (consume == 0) {
                tokeniser.error(this);
                tokeniser.doctypePending.systemIdentifier.append((char) 65533);
            } else if (consume == '\'') {
                tokeniser.transition(AfterDoctypeSystemIdentifier);
            } else if (consume == '>') {
                tokeniser.error(this);
                tokeniser.doctypePending.forceQuirks = true;
                tokeniser.emitDoctypePending();
                tokeniser.transition(Data);
            } else if (consume == 65535) {
                tokeniser.eofError(this);
                tokeniser.doctypePending.forceQuirks = true;
                tokeniser.emitDoctypePending();
                tokeniser.transition(Data);
            } else {
                tokeniser.doctypePending.systemIdentifier.append(consume);
            }
        }
    },
    AfterDoctypeSystemIdentifier { // from class: org.jsoup.parser.TokeniserState.65
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            switch (characterReader.consume()) {
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    return;
                case '>':
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.error(this);
                    tokeniser.transition(BogusDoctype);
                    return;
            }
        }
    },
    BogusDoctype { // from class: org.jsoup.parser.TokeniserState.66
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char consume = characterReader.consume();
            if (consume == '>') {
                tokeniser.emitDoctypePending();
                tokeniser.transition(Data);
            } else if (consume != 65535) {
            } else {
                tokeniser.emitDoctypePending();
                tokeniser.transition(Data);
            }
        }
    },
    CdataSection { // from class: org.jsoup.parser.TokeniserState.67
        @Override // org.jsoup.parser.TokeniserState
        void read(Tokeniser tokeniser, CharacterReader characterReader) {
            tokeniser.emit(characterReader.consumeTo("]]>"));
            characterReader.matchConsume("]]>");
            tokeniser.transition(Data);
        }
    };
    
    private static final char eof = 65535;
    private static final char nullChar = 0;
    private static final char replacementChar = 65533;
    private static final String replacementStr = String.valueOf((char) 65533);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void read(Tokeniser tokeniser, CharacterReader characterReader);
}
