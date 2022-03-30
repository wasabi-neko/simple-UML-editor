package org.wasabineko.graphic;

import org.wasabineko.GeneralConfig;
import org.wasabineko.editorBehavior.EditorAgent;
import org.wasabineko.graphic.shape.basicObj.BasicObj;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class UMLCanvas extends JPanel {
    LinkedList<BasicObj> objList = new LinkedList<BasicObj>();
    EditorAgent editorAgent;

    public UMLCanvas(EditorAgent editorAgent) {
        super();
        this.editorAgent = editorAgent;
        setPreferredSize(GeneralConfig.getInstance().getDefaultCanvasSize());
        setBackground(Color.GRAY);
    }

    public void createObj(int x, int y, BasicObj obj) {
//        pass
    }

    public void eraseObj(BasicObj obj) {
//        pass
    }
}
