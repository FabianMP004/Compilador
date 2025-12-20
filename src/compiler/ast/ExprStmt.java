
package compiler.ast;
import java.util.*;

public class ExprStmt extends Stmt {
    public Expr expr;
    public List<AstNode> nodes;

    // Constructor para expresiones simples
    public ExprStmt(Expr e){
        super("ExprStmt");
        this.expr = e;
        this.nodes = null;
        if(e != null) add(e);
    }

    // Constructor para bloques de código (bloques compuestos)
    public ExprStmt(List<AstNode> nodes){
        super("Block");
        this.nodes = nodes;
        this.expr = null;
        if(nodes != null){
            for(AstNode n: nodes){
                add(n);
            }
        }
    }

    @Override
    public String toDot(StringBuilder sb) {
        String my = "node" + System.identityHashCode(this);
        sb.append(my + " [label=\"" + label + "\"];\n");
        
        if(expr != null){
            String ch = "node" + System.identityHashCode(expr);
            sb.append(my + " -> " + ch + ";\n");
            expr.toDot(sb);
        }
        
        if(nodes != null){
            for(AstNode n: nodes){
                String ch = "node" + System.identityHashCode(n);
                sb.append(my + " -> " + ch + ";\n");
                n.toDot(sb);
            }
        }
        
        return my;
    }
}
