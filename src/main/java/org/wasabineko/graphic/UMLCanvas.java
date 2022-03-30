package org.wasabineko.graphic;

import org.wasabineko.GeneralConfig;
import org.wasabineko.editorBehavior.EditorAgent;
import org.wasabineko.graphic.shape.basicObj.BasicObj;
import org.wasabineko.graphic.shape.basicObj.UseCaseObj;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

public class UMLCanvas extends JPanel {
    LinkedList<BasicObj> objList = new LinkedList<BasicObj>();  // for maintaining the obj depth attribute
    EditorAgent editorAgent;

    public UMLCanvas(EditorAgent editorAgent) {
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
     *  Mouse Event Listener Only for UMLCanvas
     */
    private static class UMLCanvasMouseEventListener implements MouseListener {
        private UMLCanvas targetCanvas;

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