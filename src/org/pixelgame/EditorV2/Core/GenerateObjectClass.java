package org.pixelgame.EditorV2.Core;

import org.pixelgame.Engine.physics.Collider;
import org.pixelgame.Engine.physics.Collision;
import org.pixelgame.Engine.physics.Physics;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GenerateObjectClass {
    private static final String template = "package {PACKEGE};\n" +
                                           "\n" +
                                           "{IMPOERTS}\n" +
                                           "\n" +
                                           "import java.awt.*;\n" +
                                           "\n" +
                                           "public class {NAME} extends Sprite {\n" +
                                           "{INITS}\n" +
                                           "    public {NAME}(int id, Vector2<Integer> pos) {\n" +
                                           "        super(id, pos);\n" +
                                           "{SETDEFAULT}\n" +
                                           "    }\n" +
                                           "    @Override\n" +
                                           "    public void fixedupdate(float deltaTime) {\n" +
                                           "        super.fixedupdate(deltaTime);\n" +
                                           "{UPDATE}\n" +
                                           "    }\n" +
                                           "    @Override\n" +
                                           "    public void update(float deltaTime) {\n" +
                                           "        super.update(deltaTime);\n" +
                                           "{FIXEDUPDATE}\n" +
                                           "    }\n" +
                                           "    @Override\n" +
                                           "    public void render(Graphics g){\n" +
                                           "        super.render(g);\n" +
                                           "{RENDER}\n" +
                                           "    }\n" +
                                           "}\n";
    static StringBuilder IMPOERTS = new StringBuilder();
    static StringBuilder INITS = new StringBuilder();
    static StringBuilder SETDEFAULT = new StringBuilder();
    static StringBuilder UPDATE = new StringBuilder();
    static StringBuilder FIXEDUPDATE = new StringBuilder();
    static StringBuilder RENDER = new StringBuilder();

    public static String Generate(){
        IMPOERTS        .append("import org.pixelgame.Engine.Core.Vector2;\n")
                        .append("import org.pixelgame.Engine.object.Sprite;\n");
        return template
                .replace("{PACKEGE}","org.pixelgame.sprites")
                .replace("{IMPOERTS}",IMPOERTS)
                .replace("{NAME}","TestObject")
                .replace("{INITS}",INITS)
                .replace("{SETDEFAULT}",SETDEFAULT)
                .replace("{UPDATE}",UPDATE)
                .replace("{FIXEDUPDATE}",FIXEDUPDATE)
                .replace("{RENDER}",RENDER);
    }
    public static void addComponent(Class component){
        IMPOERTS.append("import ").append(component.getName()).append(";\n");
        INITS.append("\tpublic final ").append(component.getSimpleName()).append(" ").append(component.getSimpleName().toLowerCase()).append(";\n");
        SETDEFAULT.append("\t\t").append(component.getSimpleName().toLowerCase()).append("\t").append("= (").append(component.getSimpleName())
                .append(") AddComponent(new ").append(component.getSimpleName()).append("(this));\n");
    }
    public static void SaveFile(String name,String path,String value) throws IOException {
        Path file = Paths.get("/Users/daniilvdovin/IdeaProjects/sJGE/src/",path.replace('.','/'),"/",name);
        Files.createFile(file);
        Files.writeString(file, value);
    }
}
