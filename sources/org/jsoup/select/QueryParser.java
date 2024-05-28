package org.jsoup.select;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.helper.StringUtil;
import org.jsoup.helper.Validate;
import org.jsoup.parser.TokenQueue;
import org.jsoup.select.CombiningEvaluator;
import org.jsoup.select.Evaluator;
import org.jsoup.select.Selector;
import org.jsoup.select.StructuralEvaluator;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class QueryParser {
    private List<Evaluator> evals = new ArrayList();
    private String query;

    /* renamed from: tq */
    private TokenQueue f27424tq;
    private static final String[] combinators = {",", ">", "+", "~", " "};
    private static final Pattern NTH_AB = Pattern.compile("((\\+|-)?(\\d+)?)n(\\s*(\\+|-)?\\s*\\d+)?", 2);
    private static final Pattern NTH_B = Pattern.compile("(\\+|-)?(\\d+)");

    private QueryParser(String str) {
        this.query = str;
        this.f27424tq = new TokenQueue(str);
    }

    public static Evaluator parse(String str) {
        return new QueryParser(str).parse();
    }

    Evaluator parse() {
        this.f27424tq.consumeWhitespace();
        if (this.f27424tq.matchesAny(combinators)) {
            this.evals.add(new StructuralEvaluator.Root());
            combinator(this.f27424tq.consume());
        } else {
            findElements();
        }
        while (!this.f27424tq.isEmpty()) {
            boolean consumeWhitespace = this.f27424tq.consumeWhitespace();
            if (this.f27424tq.matchesAny(combinators)) {
                combinator(this.f27424tq.consume());
            } else if (consumeWhitespace) {
                combinator(' ');
            } else {
                findElements();
            }
        }
        if (this.evals.size() == 1) {
            return this.evals.get(0);
        }
        return new CombiningEvaluator.And(this.evals);
    }

    private void combinator(char c) {
        Evaluator and;
        Evaluator evaluator;
        boolean z;
        CombiningEvaluator.And and2;
        this.f27424tq.consumeWhitespace();
        Evaluator parse = parse(consumeSubQuery());
        if (this.evals.size() == 1) {
            and = this.evals.get(0);
            if (!(and instanceof CombiningEvaluator.C13539Or) || c == ',') {
                evaluator = and;
                z = false;
            } else {
                z = true;
                evaluator = and;
                and = ((CombiningEvaluator.C13539Or) and).rightMostEvaluator();
            }
        } else {
            and = new CombiningEvaluator.And(this.evals);
            evaluator = and;
            z = false;
        }
        this.evals.clear();
        if (c == '>') {
            and2 = new CombiningEvaluator.And(parse, new StructuralEvaluator.ImmediateParent(and));
        } else if (c == ' ') {
            and2 = new CombiningEvaluator.And(parse, new StructuralEvaluator.Parent(and));
        } else if (c == '+') {
            and2 = new CombiningEvaluator.And(parse, new StructuralEvaluator.ImmediatePreviousSibling(and));
        } else if (c == '~') {
            and2 = new CombiningEvaluator.And(parse, new StructuralEvaluator.PreviousSibling(and));
        } else if (c == ',') {
            if (and instanceof CombiningEvaluator.C13539Or) {
                CombiningEvaluator.C13539Or c13539Or = (CombiningEvaluator.C13539Or) and;
                c13539Or.add(parse);
                and2 = c13539Or;
            } else {
                CombiningEvaluator.C13539Or c13539Or2 = new CombiningEvaluator.C13539Or();
                c13539Or2.add(and);
                c13539Or2.add(parse);
                and2 = c13539Or2;
            }
        } else {
            throw new Selector.SelectorParseException("Unknown combinator: " + c, new Object[0]);
        }
        if (z) {
            ((CombiningEvaluator.C13539Or) evaluator).replaceRightMostEvaluator(and2);
            and2 = evaluator;
        }
        this.evals.add(and2);
    }

    private String consumeSubQuery() {
        StringBuilder sb = new StringBuilder();
        while (!this.f27424tq.isEmpty()) {
            if (this.f27424tq.matches("(")) {
                sb.append("(");
                sb.append(this.f27424tq.chompBalanced('(', ')'));
                sb.append(")");
            } else if (this.f27424tq.matches("[")) {
                sb.append("[");
                sb.append(this.f27424tq.chompBalanced('[', ']'));
                sb.append("]");
            } else if (this.f27424tq.matchesAny(combinators)) {
                break;
            } else {
                sb.append(this.f27424tq.consume());
            }
        }
        return sb.toString();
    }

    private void findElements() {
        if (this.f27424tq.matchChomp("#")) {
            byId();
        } else if (this.f27424tq.matchChomp(".")) {
            byClass();
        } else if (this.f27424tq.matchesWord()) {
            byTag();
        } else if (this.f27424tq.matches("[")) {
            byAttribute();
        } else if (this.f27424tq.matchChomp("*")) {
            allElements();
        } else if (this.f27424tq.matchChomp(":lt(")) {
            indexLessThan();
        } else if (this.f27424tq.matchChomp(":gt(")) {
            indexGreaterThan();
        } else if (this.f27424tq.matchChomp(":eq(")) {
            indexEquals();
        } else if (this.f27424tq.matches(":has(")) {
            has();
        } else if (this.f27424tq.matches(":contains(")) {
            contains(false);
        } else if (this.f27424tq.matches(":containsOwn(")) {
            contains(true);
        } else if (this.f27424tq.matches(":matches(")) {
            matches(false);
        } else if (this.f27424tq.matches(":matchesOwn(")) {
            matches(true);
        } else if (this.f27424tq.matches(":not(")) {
            not();
        } else if (this.f27424tq.matchChomp(":nth-child(")) {
            cssNthChild(false, false);
        } else if (this.f27424tq.matchChomp(":nth-last-child(")) {
            cssNthChild(true, false);
        } else if (this.f27424tq.matchChomp(":nth-of-type(")) {
            cssNthChild(false, true);
        } else if (this.f27424tq.matchChomp(":nth-last-of-type(")) {
            cssNthChild(true, true);
        } else if (this.f27424tq.matchChomp(":first-child")) {
            this.evals.add(new Evaluator.IsFirstChild());
        } else if (this.f27424tq.matchChomp(":last-child")) {
            this.evals.add(new Evaluator.IsLastChild());
        } else if (this.f27424tq.matchChomp(":first-of-type")) {
            this.evals.add(new Evaluator.IsFirstOfType());
        } else if (this.f27424tq.matchChomp(":last-of-type")) {
            this.evals.add(new Evaluator.IsLastOfType());
        } else if (this.f27424tq.matchChomp(":only-child")) {
            this.evals.add(new Evaluator.IsOnlyChild());
        } else if (this.f27424tq.matchChomp(":only-of-type")) {
            this.evals.add(new Evaluator.IsOnlyOfType());
        } else if (this.f27424tq.matchChomp(":empty")) {
            this.evals.add(new Evaluator.IsEmpty());
        } else if (this.f27424tq.matchChomp(":root")) {
            this.evals.add(new Evaluator.IsRoot());
        } else {
            throw new Selector.SelectorParseException("Could not parse query '%s': unexpected token at '%s'", this.query, this.f27424tq.remainder());
        }
    }

    private void byId() {
        String consumeCssIdentifier = this.f27424tq.consumeCssIdentifier();
        Validate.notEmpty(consumeCssIdentifier);
        this.evals.add(new Evaluator.C13540Id(consumeCssIdentifier));
    }

    private void byClass() {
        String consumeCssIdentifier = this.f27424tq.consumeCssIdentifier();
        Validate.notEmpty(consumeCssIdentifier);
        this.evals.add(new Evaluator.Class(consumeCssIdentifier.trim().toLowerCase()));
    }

    private void byTag() {
        String consumeElementSelector = this.f27424tq.consumeElementSelector();
        Validate.notEmpty(consumeElementSelector);
        if (consumeElementSelector.contains("|")) {
            consumeElementSelector = consumeElementSelector.replace("|", ":");
        }
        this.evals.add(new Evaluator.Tag(consumeElementSelector.trim().toLowerCase()));
    }

    private void byAttribute() {
        TokenQueue tokenQueue = new TokenQueue(this.f27424tq.chompBalanced('[', ']'));
        String consumeToAny = tokenQueue.consumeToAny("=", "!=", "^=", "$=", "*=", "~=");
        Validate.notEmpty(consumeToAny);
        tokenQueue.consumeWhitespace();
        if (tokenQueue.isEmpty()) {
            if (consumeToAny.startsWith("^")) {
                this.evals.add(new Evaluator.AttributeStarting(consumeToAny.substring(1)));
            } else {
                this.evals.add(new Evaluator.Attribute(consumeToAny));
            }
        } else if (tokenQueue.matchChomp("=")) {
            this.evals.add(new Evaluator.AttributeWithValue(consumeToAny, tokenQueue.remainder()));
        } else if (tokenQueue.matchChomp("!=")) {
            this.evals.add(new Evaluator.AttributeWithValueNot(consumeToAny, tokenQueue.remainder()));
        } else if (tokenQueue.matchChomp("^=")) {
            this.evals.add(new Evaluator.AttributeWithValueStarting(consumeToAny, tokenQueue.remainder()));
        } else if (tokenQueue.matchChomp("$=")) {
            this.evals.add(new Evaluator.AttributeWithValueEnding(consumeToAny, tokenQueue.remainder()));
        } else if (tokenQueue.matchChomp("*=")) {
            this.evals.add(new Evaluator.AttributeWithValueContaining(consumeToAny, tokenQueue.remainder()));
        } else if (tokenQueue.matchChomp("~=")) {
            this.evals.add(new Evaluator.AttributeWithValueMatching(consumeToAny, Pattern.compile(tokenQueue.remainder())));
        } else {
            throw new Selector.SelectorParseException("Could not parse attribute query '%s': unexpected token at '%s'", this.query, tokenQueue.remainder());
        }
    }

    private void allElements() {
        this.evals.add(new Evaluator.AllElements());
    }

    private void indexLessThan() {
        this.evals.add(new Evaluator.IndexLessThan(consumeIndex()));
    }

    private void indexGreaterThan() {
        this.evals.add(new Evaluator.IndexGreaterThan(consumeIndex()));
    }

    private void indexEquals() {
        this.evals.add(new Evaluator.IndexEquals(consumeIndex()));
    }

    private void cssNthChild(boolean z, boolean z2) {
        String lowerCase = this.f27424tq.chompTo(")").trim().toLowerCase();
        Matcher matcher = NTH_AB.matcher(lowerCase);
        Matcher matcher2 = NTH_B.matcher(lowerCase);
        int i = 2;
        int i2 = 0;
        if ("odd".equals(lowerCase)) {
            i2 = 1;
        } else if (!"even".equals(lowerCase)) {
            if (matcher.matches()) {
                i = matcher.group(3) != null ? Integer.parseInt(matcher.group(1).replaceFirst("^\\+", "")) : 1;
                if (matcher.group(4) != null) {
                    i2 = Integer.parseInt(matcher.group(4).replaceFirst("^\\+", ""));
                }
            } else if (matcher2.matches()) {
                i = 0;
                i2 = Integer.parseInt(matcher2.group().replaceFirst("^\\+", ""));
            } else {
                throw new Selector.SelectorParseException("Could not parse nth-index '%s': unexpected format", lowerCase);
            }
        }
        if (z2) {
            if (z) {
                this.evals.add(new Evaluator.IsNthLastOfType(i, i2));
            } else {
                this.evals.add(new Evaluator.IsNthOfType(i, i2));
            }
        } else if (z) {
            this.evals.add(new Evaluator.IsNthLastChild(i, i2));
        } else {
            this.evals.add(new Evaluator.IsNthChild(i, i2));
        }
    }

    private int consumeIndex() {
        String trim = this.f27424tq.chompTo(")").trim();
        Validate.isTrue(StringUtil.isNumeric(trim), "Index must be numeric");
        return Integer.parseInt(trim);
    }

    private void has() {
        this.f27424tq.consume(":has");
        String chompBalanced = this.f27424tq.chompBalanced('(', ')');
        Validate.notEmpty(chompBalanced, ":has(el) subselect must not be empty");
        this.evals.add(new StructuralEvaluator.Has(parse(chompBalanced)));
    }

    private void contains(boolean z) {
        this.f27424tq.consume(z ? ":containsOwn" : ":contains");
        String unescape = TokenQueue.unescape(this.f27424tq.chompBalanced('(', ')'));
        Validate.notEmpty(unescape, ":contains(text) query must not be empty");
        if (z) {
            this.evals.add(new Evaluator.ContainsOwnText(unescape));
        } else {
            this.evals.add(new Evaluator.ContainsText(unescape));
        }
    }

    private void matches(boolean z) {
        this.f27424tq.consume(z ? ":matchesOwn" : ":matches");
        String chompBalanced = this.f27424tq.chompBalanced('(', ')');
        Validate.notEmpty(chompBalanced, ":matches(regex) query must not be empty");
        if (z) {
            this.evals.add(new Evaluator.MatchesOwn(Pattern.compile(chompBalanced)));
        } else {
            this.evals.add(new Evaluator.Matches(Pattern.compile(chompBalanced)));
        }
    }

    private void not() {
        this.f27424tq.consume(":not");
        String chompBalanced = this.f27424tq.chompBalanced('(', ')');
        Validate.notEmpty(chompBalanced, ":not(selector) subselect must not be empty");
        this.evals.add(new StructuralEvaluator.Not(parse(chompBalanced)));
    }
}
