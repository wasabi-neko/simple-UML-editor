package org.wasabineko.graphic;

import org.wasabineko.GeneralConfig;
import org.wasabineko.editorBehavior.EditorBehaviorAgent;
import org.wasabineko.graphic.shape.UMLObj;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * UMLCanvas
 * - Contains and manages all drawable UML objects.
 * - Emits mouseEvent to editorAgent.
 * - The mouseEvent Logic should leave to EditorBehavior to implement
 */
public class UMLCanvas extends JPanel {

    public UMLCanvas() {
        setLayout(null);
        setPreferredSize(GeneralConfig.getInstance().getDefaultCanvasSize());
//        setBackground(Color.lightGray); //TODO: get config color
        addMouseListener(new UMLCanvasMouseEventListener(this));
    }

    /**
     * pass the mouse event to its EditorBehaviorAgent to perform the action
     * @param event the mouse event
     */
    private void passMouseEventToAgent(MouseEvent event) {

        // detect objs from front-est to back-est
        Component[] list = this.getComponents();
        for (Component component : list) {
            assert(component instanceof UMLObj);

            UMLObj obj = (UMLObj) component;
            MouseEvent objEvent = SwingUtilities.convertMouseEvent(this, event, obj);
            if (obj.isInShape(objEvent)) {
                UMLObj topParent =  obj.getTopParent();
                MouseEvent newEvent = SwingUtilities.convertMouseEvent(this, event, topParent);
                EditorBehaviorAgent.getInstance().objMouseAction(topParent, newEvent);
                return;
            }
        }

        // if non-of the objets contains the mouse, then perform pure canvas mouse event
        EditorBehaviorAgent.getInstance().canvasMouseAction(this, event);
    }

    /**
     * A MouseEventListener class only for UMLCanvas.
     * Just pass everything to EditorBehaviorAgent to handle these events
     */
    private record UMLCanvasMouseEventListener(UMLCanvas targetCanvas) implements MouseListener {

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
        public void mouseEntered(MouseEvent mouseEvent) { /* do nothing */ }
        @Override
        public void mouseExited(MouseEvent mouseEvent) { /* do nothing */ }
    }

    private  record UMLCanvasMouseMotionListener(UMLCanvas targetCanvas) implements MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseMoved(MouseEvent mouseEvent) {

        }
    }
}