package compiler.ast;
import java.util.*;
public class IfStmt extends Stmt {
    public Expr cond;
    public java.util.List<Stmt> thenStmts;
    public java.util.List<Stmt> elseStmts;

    public IfStmt(Expr cond, List<Stmt> thenStmts, List<Stmt> elseStmts){
        super("IfStmt");
        this.cond = cond;
        this.thenStmts = thenStmts;
        this.elseStmts = elseStmts;

        add(cond);
        for(Stmt s: thenStmts) add(s);
        if(elseStmts!=null){
            for(Stmt s: elseStmts) add(s);
        }
    }
}

