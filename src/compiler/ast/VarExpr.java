package compiler.ast;
public class VarExpr extends Expr {
    public String id;

    public VarExpr(String id){
        super("VarExpr");
        this.id = id;
    }
}