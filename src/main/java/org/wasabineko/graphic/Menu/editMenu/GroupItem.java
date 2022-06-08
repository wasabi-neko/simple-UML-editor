package org.wasabineko.graphic.Menu.editMenu;

import org.wasabineko.editorBehavior.EditorBehaviorAgent;
import org.wasabineko.editorBehavior.concreteBehavior.SelectBehavior;
import org.wasabineko.graphic.Menu.MenuItem;

import java.awt.event.ActionEvent;

public class GroupItem extends MenuItem {

    public GroupItem() {
        super("group");
    }

    public boolean isInWorkingCondition() {
        if (EditorBehaviorAgent.getInstance().getEditorBehavior() instanceof  SelectBehavior b) {
            return b.isNowGroupAble();
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (this.isInWorkingCondition() &&
                EditorBehaviorAgent.getInstance().getEditorBehavior() instanceof SelectBehavior b) {
            b.groupSelectedObjs();
        }
    }
}
