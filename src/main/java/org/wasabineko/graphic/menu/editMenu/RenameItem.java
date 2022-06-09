package org.wasabineko.graphic.menu.editMenu;

import org.wasabineko.editorBehavior.EditorBehaviorAgent;
import org.wasabineko.editorBehavior.concreteBehavior.SelectBehavior;
import org.wasabineko.graphic.menu.MenuItem;

import java.awt.event.ActionEvent;

public class RenameItem extends MenuItem {
    public RenameItem() {
        super("rename");
    }

    @Override
    public boolean isInWorkingCondition() {
        if (EditorBehaviorAgent.getInstance().getEditorBehavior() instanceof SelectBehavior b) {
            return b.isNowRenameAble();
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (isInWorkingCondition() && EditorBehaviorAgent.getInstance().getEditorBehavior() instanceof SelectBehavior b) {
            b.renameSelectedObj();
        }
    }
}
