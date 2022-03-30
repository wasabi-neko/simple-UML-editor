package org.wasabineko.graphic;

import org.wasabineko.GeneralConfig;
import org.wasabineko.editorBehavior.EditorBehaviorAgent;
import org.wasabineko.graphic.shape.basicObj.BasicObj;
import org.wasabineko.graphic.shape.basicObj.UseCaseObj;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

/**
 * UMLCanvas
 * - Contains and manages all drawable UML objects.
 * - Emits mouseEvent to editorAgent.
 * - The mouseEvent Logic should leave to EditorBehavior to implement
 */
public class UMLCanvas extends JPanel {
    LinkedList<BasicObj> objList = new LinkedList<BasicObj>();  // for maintaining the obj depth attribute
    EditorBehaviorAgent editorAgent;

    public UMLCanvas(EditorBehaviorAgent editorAgent) {
        super();
        this.editorAgent = editorAgent;
        setLayout(null);
        setPreferredSize(GeneralConfig.getInstance().getDefaultCanvasSize());
        setBackground(Color.lightGray); //TODO: get config color
        addMouseListener(new UMLCanvasMouseEventListener(this));
    }

    public void addObj(BasicObj obj) {
        add(obj);
        objList.add(obj);
    }

    public void eraseObj(BasicObj obj) {
        remove(obj);
        objList.remove(obj);
    }

    /**
     *  A MouseEventListener class only for UMLCanvas.
     *  Just pass everything to EditorBehaviorAgent to handle these events
     */
    private static class UMLCanvasMouseEventListener implements MouseListener {
        private final UMLCanvas targetCanvas;

        public UMLCanvasMouseEventListener(UMLCanvas targetCanvas) {
            this.targetCanvas = targetCanvas;
        }

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
//            TODO: tmp
            UseCaseObj obj = new UseCaseObj(mouseEvent.getX(), mouseEvent.getY());
            targetCanvas.addObj(obj);
            targetCanvas.setComponentZOrder(obj, 0);
            targetCanvas.repaint();
        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {

        }
    }
}