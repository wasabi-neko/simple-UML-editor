package org.wasabineko.graphic.menu.editMenu;

import org.wasabineko.editorBehavior.EditorBehaviorAgent;
import org.wasabineko.editorBehavior.concreteBehavior.SelectBehavior;
import org.wasabineko.graphic.menu.MenuItem;

import java.awt.event.ActionEvent;

public class UngroupItem extends MenuItem {
    public UngroupItem() {
        super("ungroup");
    }

    @Override
    public boolean isInWorkingCondition() {
        if (EditorBehaviorAgent.getInstance().getEditorBehavior() instanceof SelectBehavior b) {
            return b.isNowUngroupAble();
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (isInWorkingCondition() && EditorBehaviorAgent.getInstance().getEditorBehavior() instanceof SelectBehavior b) {
            b.ungroupSelectedObj();
        }
    }
}
