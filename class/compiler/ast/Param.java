package compiler.ast;
public class Param extends AstNode {
    public TypeNode type; public String id;
    public Param(TypeNode t, String id){ this.type=t; this.id=id; }
    @Override
    public String toDot(StringBuilder sb) {
        String my = "node"+System.identityHashCode(this);
        sb.append(my + " [label=\"Param\\n"+id+" : "+type+"\"];\n");
        return my;
    }
}
