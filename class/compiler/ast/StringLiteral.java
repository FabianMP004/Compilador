package compiler.ast;
public class StringLiteral extends Expr {
    public String value; public StringLiteral(String v){ this.value=v; }
    @Override public String toDot(StringBuilder sb){ String my="node"+System.identityHashCode(this); sb.append(my+" [label=\"String\\n"+escape(value)+"\"];\n"); return my; }
    private String escape(String s){ return s.replace("\"","\\\"").replace("\n","\\n"); }
}
