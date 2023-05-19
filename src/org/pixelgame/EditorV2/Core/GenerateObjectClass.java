package org.pixelgame.EditorV2.Core;

import org.pixelgame.Engine.Core.JavaDelegate;
import org.pixelgame.Engine.physics.Collision;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GenerateObjectClass {
    private final String template = "package {PACKEGE};\n" +
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
                                           "{INITCODE}\n" +
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
    private StringBuilder IMPOERTS = new StringBuilder();
    private StringBuilder INITS = new StringBuilder();
    private StringBuilder SETDEFAULT = new StringBuilder();
    private StringBuilder INITCODE = new StringBuilder();
    private StringBuilder UPDATE = new StringBuilder();
    private StringBuilder FIXEDUPDATE = new StringBuilder();
    private StringBuilder RENDER = new StringBuilder();

    private String Name;
    private String Path;
    private String Value;

    private boolean imagefirtstime = true;
    public GenerateObjectClass Generate(){
        IMPOERTS        .append("import org.pixelgame.Engine.Core.Vector2;\n")
                        .append("import org.pixelgame.Engine.object.Sprite;\n");
        Value = template
                .replace("{PACKEGE}",Path)
                .replace("{IMPOERTS}",IMPOERTS)
                .replace("{NAME}",Name)
                .replace("{INITS}",INITS)
                .replace("{SETDEFAULT}",SETDEFAULT)
                .replace("{INITCODE}",INITCODE)
                .replace("{UPDATE}",UPDATE)
                .replace("{FIXEDUPDATE}",FIXEDUPDATE)
                .replace("{RENDER}",RENDER);
        return this;
    }
    public GenerateObjectClass setName(String name){
        Name = name;
        return this;
    }
    public GenerateObjectClass setPath(String path){
        Path = path;
        return this;
    }
    public GenerateObjectClass addComponent(Class component){
        addComponent(component,false);
        return this;
    }
    public GenerateObjectClass addComponent(Class component,boolean widthDefaultListener){
        addImports(component.getName());
        INITS.append("\tpublic final ").append(component.getSimpleName()).append(" ").append(component.getSimpleName().toLowerCase()).append(";\n");
        SETDEFAULT.append("\t\t").append(component.getSimpleName().toLowerCase()).append("\t").append("= (").append(component.getSimpleName())
                .append(") AddComponent(new ").append(component.getSimpleName()).append("(this));\n");
        if(widthDefaultListener){
            switch (component.getSimpleName()){
                case "Collision":
                    addImports("org.pixelgame.Engine.EventSystem.IOnCollisionListener");
                    INITCODE.append("\t\t").append("collision.addListener(new IOnCollisionListener() {\n" +
                            "            @Override\n" +
                            "            public void CollisionEnter(Physics sender) {\n" +
                            "                System.out.println(\"Sprite (\"+ id +\") CollisionEnter(): sender.id(\"+sender.GetParent().id+\")\");\n" +
                            "            }\n" +
                            "            @Override\n" +
                            "            public void CollisionExit(Physics sender) {\n" +
                            "                System.out.println(\"Sprite (\"+ id +\") CollisionExit: sender.id(\"+sender.GetParent().id+\")\");\n" +
                            "            }\n" +
                            "        });");
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + component.getSimpleName());
            }
        }
        return this;
    }
    public GenerateObjectClass addInitLogic(StringBuilder stringBuilder){
        INITCODE.append("\t\t").append(stringBuilder);
        return this;
    }
    public GenerateObjectClass addImports(String value){
        IMPOERTS.append("import ").append(value).append(";\n");
        return this;
    }
    public GenerateObjectClass addInits(Class type,String name){
        addInits(type,name,false,"");
        return this;
    }
    public GenerateObjectClass addInits(Class type,String name,boolean isinit,Object defaultvalue){
        INITS.append("\tpublic ").append(type.getSimpleName()).append(" ").append(name);
                if(isinit) {
                    if (type.getSimpleName().equals(Integer.class.getSimpleName())){
                        INITS.append(" = ").append(defaultvalue).append(";\n");
                    }
                    else if (type.getSimpleName().equals(String.class.getSimpleName())){
                        INITS.append(" = \"").append(defaultvalue).append("\";\n");
                    }else
                    INITS.append(" = new ").append(type.getSimpleName()).append("(").append(defaultvalue).append(");\n");
                }
                    else INITS.append(";\n");
        return this;
    }
    public GenerateObjectClass addSpriteImage(String name,String path){
        if(imagefirtstime){
            addImports("org.pixelgame.Engine.graphics.SpriteImage");
            imagefirtstime = false;
        }
        INITS.append("\tpublic final SpriteImage ").append(name).append(" = new SpriteImage(\"").append(path).append("\");\n");
        return this;
    }
    public void SaveFile() {
        Path file = Paths.get("/Users/daniilvdovin/IdeaProjects/sJGE/src/",Path.replace('.','/'),"/",Name+".java");
        try {
            Files.createFile(file);
            Files.writeString(file, Value);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
