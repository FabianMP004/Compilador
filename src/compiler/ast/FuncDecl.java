package compiler.ast;
import java.util.*;

public class FuncDecl extends Decl {
public TypeNode type;
public String id;
public List<Param> params;
    public List<AstNode> body;

    public FuncDecl(TypeNode t, String id, List<Param> params, List<AstNode> body) {
        super(id);
        this.type = t;
        this.id = id;
        this.params = params;
        this.body = body;
    }

@Override
public String toDot(StringBuilder sb) {
    String my = "node" + System.identityHashCode(this);
    sb.append(my + " [label=\"FuncDecl\\n" + id + " : " + type + "\"];\n");
    for (Param p : params) {
        String ch = "node" + System.identityHashCode(p);
        sb.append(my + " -> " + ch + ";\n");
        p.toDot(sb);
    }
        for (AstNode n : body) {
            String ch = "node" + System.identityHashCode(n);
            sb.append(my + " -> " + ch + ";\n");
            n.toDot(sb);
    }
    return my;
}


}