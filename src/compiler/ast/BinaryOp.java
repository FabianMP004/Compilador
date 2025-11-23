package compiler.ast;
public class BinaryOp extends Expr {
    public Expr left;
    public Expr right;
    public String op;

    public BinaryOp(String op, Expr left, Expr right){
        super("BinaryOp");
        this.op = op;
        this.left = left;
        this.right = right;
        add(left);
        add(right);
    }

    @Override
    public String toDot(StringBuilder sb) {
        String my = "node" + System.identityHashCode(this);
        sb.append(my + " [label=\"" + op + "\"];\n");
        
        String leftNode = "node" + System.identityHashCode(left);
        sb.append(my + " -> " + leftNode + ";\n");
        left.toDot(sb);
        
        String rightNode = "node" + System.identityHashCode(right);
        sb.append(my + " -> " + rightNode + ";\n");
        right.toDot(sb);
        
        return my;
    }

    @Override
    public String toString() {
        return "(" + left + " " + op + " " + right + ")";
    }
}
