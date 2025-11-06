package compiler.ast;
public class BinaryOp extends Expr {
    public String op; public Expr left, right;
    public BinaryOp(String op, Expr l, Expr r){ this.op=op; this.left=l; this.right=r; }
    @Override public String toDot(StringBuilder sb){
        String my="node"+System.identityHashCode(this); sb.append(my+" [label=\"Binary\\n"+op+"\"];\n");
        String L = left.toDot(sb); sb.append(my+" -> "+L+";\n");
        String R = right.toDot(sb); sb.append(my+" -> "+R+";\n");
        return my;
    }
}
