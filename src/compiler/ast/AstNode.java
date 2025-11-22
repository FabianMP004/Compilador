package compiler.ast;

import java.util.ArrayList;
import java.util.List;

public abstract class AstNode {

    public String label;
    public List<AstNode> children = new ArrayList<>();

    public AstNode(String label){
        this.label = label;
    }

    public void add(AstNode child){
        children.add(child);
    }

    public String toDot(StringBuilder sb){
        String my = "node" + System.identityHashCode(this);
        sb.append(my + " [label=\"" + label + "\"];\n");
        for(AstNode c: children){
            String ch = c.toDot(sb);
            sb.append(my + " -> " + ch + ";\n");
        }
        return my;
    }
}
