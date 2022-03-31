package org.wasabineko.graphic;

import org.wasabineko.GeneralConfig;
import org.wasabineko.editorBehavior.EditorBehaviorAgent;
import org.wasabineko.graphic.shape.basicObj.BasicObj;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * UMLCanvas
 * - Contains and manages all drawable UML objects.
 * - Emits mouseEvent to editorAgent.
 * - The mouseEvent Logic should leave to EditorBehavior to implement
 */
public class UMLCanvas extends JPanel {
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
    public EditorBehaviorAgent getEditorAgent() { return this.editorAgent; }


    /**
     * pass the mouse event to its EditorBehaviorAgent to perform the action
     * @param event the mouse event
     */
    private void passMouseEventToAgent(MouseEvent event) {

        // detect objs from front-est to back-est
        Component[] list = this.getComponents();
        for (Component component : list) {
            assert(component instanceof BasicObj);

            BasicObj obj = (BasicObj) component;
            MouseEvent objEvent = SwingUtilities.convertMouseEvent(this, event, obj);
            if (obj.isInShape(objEvent)) {
                BasicObj topParent = (BasicObj) obj.getTopParent();
                MouseEvent newEvent = SwingUtilities.convertMouseEvent(this, event, topParent);
                editorAgent.objMouseAction(topParent, newEvent);
                return;
            }
        }

        // if non-of the objets contains the mouse, then perform pure canvas mouse event
        editorAgent.canvasMouseAction(this, event);
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
}