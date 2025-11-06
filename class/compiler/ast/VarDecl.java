package compiler.ast;
public class VarDecl extends Decl {
    public TypeNode type;
    public String id;
    public Integer size; // null if not array
    public VarDecl(TypeNode t, String id) { this.type=t; this.id=id; this.size=null; }
    public VarDecl(TypeNode t, String id, int size) { this.type=t; this.id=id; this.size=size; }
    @Override
    public String toDot(StringBuilder sb) {
        String my = "node"+System.identityHashCode(this);
        sb.append(my + " [label=\"VarDecl\\n"+id+" : "+type+""
            + (size!=null?("["+size+"]"):"") + "\"];\n");
        return my;
    }
}
