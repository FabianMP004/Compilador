
package compiler.ast;
import java.util.*;

public class IfStmt extends Stmt {
    public Expr cond;
    public AstNode thenNode;
    public AstNode elseNode;

    public IfStmt(Expr cond, AstNode thenNode, AstNode elseNode){
        super("IfStmt");
        this.cond = cond;
        this.thenNode = thenNode;
        this.elseNode = elseNode;

        add(cond);
        if(thenNode != null) add(thenNode);
        if(elseNode != null) add(elseNode);
    }

    @Override
    public String toDot(StringBuilder sb) {
        String my = "node" + System.identityHashCode(this);
        sb.append(my + " [label=\"IfStmt\"];\n");
        
        String condNode = "node" + System.identityHashCode(cond);
        sb.append(my + " -> " + condNode + ";\n");
        cond.toDot(sb);
        
        if(thenNode != null){
            String thenDot = "node" + System.identityHashCode(thenNode);
            sb.append(my + " -> " + thenDot + ";\n");
            thenNode.toDot(sb);
        }
        
        if(elseNode != null){
            String elseDot = "node" + System.identityHashCode(elseNode);
            sb.append(my + " -> " + elseDot + ";\n");
            elseNode.toDot(sb);
        }
        
        return my;
    }
}
