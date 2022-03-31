package org.wasabineko.graphic;

import org.wasabineko.GeneralConfig;
import org.wasabineko.editorBehavior.EditorBehavior;
import org.wasabineko.editorBehavior.EditorBehaviorAgent;
import org.wasabineko.graphic.shape.basicObj.BasicObj;
import org.wasabineko.graphic.shape.basicObj.UseCaseObj;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputMethodListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * UMLCanvas
 * - Contains and manages all drawable UML objects.
 * - Emits mouseEvent to editorAgent.
 * - The mouseEvent Logic should leave to EditorBehavior to implement
 */
public class UMLCanvas extends JPanel {
    private final LinkedList<BasicObj> objList = new LinkedList<>();  // for maintaining the obj depth attribute
    private EditorBehaviorAgent editorAgent;

    public UMLCanvas(EditorBehaviorAgent editorAgent) {
        super();
        this.editorAgent = editorAgent;
        setLayout(null);
        setPreferredSize(GeneralConfig.getInstance().getDefaultCanvasSize());
        setBackground(Color.lightGray); //TODO: get config color
        addMouseListener(new UMLCanvasMouseEventListener(this));
    }

    public void setEditorAgent(EditorBehaviorAgent editorAgent) {
        this.editorAgent = editorAgent;
    }

    public EditorBehaviorAgent getEditorAgent() {
        return this.editorAgent;
    }

    public void addObjOntop(BasicObj obj) {
        objList.add(obj);
        add(obj);
        setComponentZOrder(obj, 0);
        repaint();
    }

    public void eraseObj(BasicObj obj) {
        remove(obj);
        objList.remove(obj);
    }

    /**
     * Detect is there any basicObj on canvas contains the provided coordinate
     * @param x the X coordinate
     * @param y the Y coordinate
     * @return null if no obj detected. if the detected Obj has a group parent then return the group parent
     */
    private BasicObj detectObj(int x, int y) {
        // from front-est obj to back-est obj
        Iterator<BasicObj> objIter = objList.descendingIterator();
        while (objIter.hasNext()) {
            BasicObj obj = objIter.next();
            if (obj.isInShape(x, y)) {
                return (BasicObj) obj.getTopParent();
            }
        }
        return null;
    }

    /**
     * pass the mouse event to its EditorBehaviorAgent to perform the action
     * @param event the mouse event
     */
    private void passMouseEventToAgent(MouseEvent event) {
        BasicObj obj = detectObj(event.getX(), event.getY());
        if (obj == null) {
            editorAgent.canvasMouseAction(this, event);
        } else {
            editorAgent.objMouseAction(obj, event);
        }
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
            targetCanvas.passMouseEventToAgent(mouseEvent);
        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {
            targetCanvas.passMouseEventToAgent(mouseEvent);
        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {
            targetCanvas.passMouseEventToAgent(mouseEvent);
        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {
            /* do nothing */
        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {
            /* do nothing */
        }
    }
}