package compiler.ast;
public class Param extends AstNode {
    public TypeNode type; public String id;
    public Param(TypeNode type, String id) {
        super("Param");
        this.type = type; this.id = id;
    }
    @Override
    public String toDot(StringBuilder sb) {
        String my = "node"+System.identityHashCode(this);
        sb.append(my + " [label=\"Param\\n"+id+" : "+type+"\"];\n");
        return my;
    }
}
