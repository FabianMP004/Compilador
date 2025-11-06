package compiler.ast;
import java.util.*;

public class Program extends AstNode {
    public List<Decl> decls;
    public Program(List<Decl> decls) { this.decls = decls; }
    @Override
    public String toDot(StringBuilder sb) {
        String my = "node"+System.identityHashCode(this);
        sb.append(my + " [label=\"Program\"];\n");
        for (Decl d: decls) {
            String chid = "node"+System.identityHashCode(d);
            sb.append(my + " -> " + chid + ";\n");
            d.toDot(sb);
        }
        return my;
    }
}

