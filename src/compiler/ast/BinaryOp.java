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
}

