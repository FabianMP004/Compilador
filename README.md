# Compilador MiniC
Proyecto final de Compiladores

**Grupo:** 
- Fabian Miranda
- Cynthia Santos

---

## 📋 Requisitos

- Java JDK 8 o superior
- JFlex (versión 1.9.1 o superior)
- Java CUP (versión 11b o superior)
- Graphviz (opcional, para visualizar el AST)

## 🛠️ Herramientas necesarias

Las siguientes herramientas deben estar instaladas:
- **JFlex**: `C:\Users\fabia\Dropbox\PC\Desktop\Tools\jflex-1.9.1\lib\jflex-full-1.9.1.jar`
- **Java CUP**: `C:\Users\fabia\Dropbox\PC\Desktop\Tools\java-cup-bin-11b-20160615\`

## 🚀 Compilación y Ejecución

### Opción 1: Usar los scripts automáticos (RECOMENDADO)

1. **Compilar el proyecto:**
   ```batch
   compilar.bat
   ```

2. **Ejecutar el compilador:**
   ```batch
   ejecutar.bat
   ```

### Opción 2: Compilación manual

1. **Configurar variables de entorno:**
   ```batch
   set CLASSPATH=.;%cd%\classes;"C:\Users\fabia\Dropbox\PC\Desktop\Tools\java-cup-bin-11b-20160615\java-cup-11b-runtime.jar"
   set CUP_JAR="C:\Users\fabia\Dropbox\PC\Desktop\Tools\java-cup-bin-11b-20160615\java-cup-11b.jar"
   set CUP_RUNTIME_JAR="C:\Users\fabia\Dropbox\PC\Desktop\Tools\java-cup-bin-11b-20160615\java-cup-11b-runtime.jar"
   set FLEX_JAR="C:\Users\fabia\Dropbox\PC\Desktop\Tools\jflex-1.9.1\lib\jflex-full-1.9.1.jar"
   ```

2. **Cambiar al directorio src/compiler:**
   ```batch
   cd "C:\Users\fabia\Dropbox\PC\Desktop\GITHUB\Semestre 6\Compilador\src\compiler"
   ```

3. **Compilar AST:**
   ```batch
   javac -d classes ast\*.java
   ```

4. **Generar el parser con CUP:**
   ```batch
   cd parser
   java -jar %CUP_JAR% -parser Parser -symbols sym -nonterms parser.cup
   cd ..
   javac -d classes parser\sym.java
   ```

5. **Generar el scanner con JFlex:**
   ```batch
   java -jar %FLEX_JAR% scanner\Scanner.flex
   ```

6. **Compilar scanner y parser:**
   ```batch
   javac -d classes -cp classes;parser;%CUP_RUNTIME_JAR% scanner\Scanner.java
   javac -d classes -cp classes;%CUP_JAR% parser\Parser.java
   ```

7. **Compilar Main:**
   ```batch
   javac -d classes -cp classes;%CUP_JAR% Main.java
   ```

8. **Ejecutar el compilador:**
   ```batch
   java -cp classes;%CUP_JAR% compiler.Main ..\..\tests\demo.minic
   ```

## 📊 Visualizar el AST

Después de ejecutar el compilador, se genera un archivo `ast.dot` con la representación del árbol sintáctico.

Para convertirlo a imagen PNG:
```batch
dot -Tpng ast.dot -o ast.png
```

O para formato SVG:
```batch
dot -Tsvg ast.dot -o ast.svg
```

## 📁 Estructura del Proyecto

```
Compilador/
├── src/
│   └── compiler/
│       ├── ast/              # Clases del AST
│       │   ├── AstNode.java
│       │   ├── Program.java
│       │   ├── FuncDecl.java
│       │   ├── VarDecl.java
│       │   ├── Stmt.java
│       │   ├── Expr.java
│       │   └── ...
│       ├── parser/
│       │   ├── parser.cup    # Gramática CUP
│       │   ├── Parser.java   # (generado)
│       │   └── sym.java      # (generado)
│       ├── scanner/
│       │   ├── Scanner.flex  # Especificación léxica
│       │   └── Scanner.java  # (generado)
│       └── Main.java         # Punto de entrada
├── tests/
│   └── demo.minic            # Archivo de prueba
├── compilar.bat              # Script de compilación
├── ejecutar.bat              # Script de ejecución
└── README.md                 # Este archivo
```

## 🔧 Correcciones Realizadas

### Problemas solucionados:
1. **IntLiteral.java** - Ahora acepta String del parser y lo convierte a int
2. **FloatLiteral.java** - Ahora acepta String del parser y lo convierte a double
3. **StringLiteral.java** - Implementado método `toDot()`
4. **TypeNode.java** - Cambiado de enum a clase con método `toString()`
5. **CallExpr.java** - Implementado método `toDot()`
6. **ArrayAccess.java** - Implementado método `toDot()`
7. **ArrayAssignStmt.java** - Implementado método `toDot()`

## 🧪 Archivo de Prueba

El archivo `tests/demo.minic` contiene:
```c
int main() {
    int x;
    float y;
    x = 42;
    y = 3.14;
    if (x>0){
        while (y < 4.0){
            y = y + 1.0;
        }
    }
    return x;
}
```

## ❓ Solución de Problemas

### "Error: AST nulo"
- Este error ocurría porque los literales no podían parsear correctamente los valores String
- **Solución:** Se corrigieron los constructores de IntLiteral y FloatLiteral

### "Error léxico"
- Verifica que el archivo `.minic` tenga la sintaxis correcta
- Revisa que no haya caracteres especiales no soportados

### "Error sintáctico"
- Verifica la gramática en `parser.cup`
- Asegúrate de que el código MiniC siga la sintaxis definida

## 📝 Notas

- El archivo `ast.dot` se genera en: `src/compiler/ast.dot`
- Para cambiar el archivo de entrada, modifica la ruta en `ejecutar.bat` o pásala como argumento
- Los archivos `.class` se generan en `src/compiler/classes/`

## 🎯 Próximos Pasos

1. Análisis semántico
2. Generación de código intermedio
3. Optimización de código
4. Generación de código objeto

---

**Universidad:** [Tu Universidad]  
**Curso:** Compiladores  
**Fecha:** 2024
