package org.wasabineko.graphic;

import org.wasabineko.GeneralConfig;
import org.wasabineko.editorBehavior.EditorBehavior;
import org.wasabineko.editorBehavior.EditorBehaviorAgent;
import org.wasabineko.editorBehavior.concreteBehavior.SelectBehavior;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class MenuBar extends JMenuBar {
    EditorBehaviorAgent editorAgent;

    public MenuBar(EditorBehaviorAgent editorAgent) {
        super();
        this.editorAgent = editorAgent;

        JMenu fileMenu = new JMenu("File");
        EditMenu editMenu = new EditMenu(this);

        this.add(fileMenu);
        this.add(editMenu);

        this.setPreferredSize(GeneralConfig.getInstance().getDefaultMenuSize());
    }

    //=============================================================
    // ConditionMenuItem
    //=============================================================
    static abstract private class ConditionMenuItem extends JMenuItem implements ActionListener {
        ConditionMenuItem(String name) {
            super(name);
            this.addActionListener(this);
        }
        abstract public boolean workCondition();
    }

    //=============================================================
    // EditMenu Class
    //=============================================================
    static private class EditMenu extends JMenu {
        //------------------------------------------------------------
        // SubClasses
        //------------------------------------------------------------
        static private class SelectBehaviorConditionItem extends ConditionMenuItem {
            @FunctionalInterface
            public interface ConditionFunction {
                boolean function(SelectBehavior selectBehavior);
            }

            @FunctionalInterface
            public interface ActionFunction {
                void function(SelectBehavior selectBehavior);
            }

            MenuBar menuBar;
            ConditionFunction conditionFunction;
            ActionFunction actionFunction;

            public SelectBehaviorConditionItem(String name, MenuBar menuBar, ConditionFunction condition, ActionFunction action) {
                super(name);
                this.menuBar = menuBar;
                this.conditionFunction = condition;
                this.actionFunction = action;
            }

            @Override
            public boolean workCondition() {
                if (this.menuBar.editorAgent.getEditorBehaviorModeTag().isInlucdeTag(EditorBehavior.ModeTag.TAGS.SELECT_MODE)) {
                    return this.conditionFunction.function((SelectBehavior) menuBar.editorAgent.getEditorBehavior());   //TODO: bad :(
                }
                return false;
            }

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (this.workCondition()) {
                    this.actionFunction.function((SelectBehavior) menuBar.editorAgent.getEditorBehavior());
                }
            }
        }

        //------------------------------------------------------------
        // EditMenu Main Code
        //------------------------------------------------------------
        MenuBar parent;
        LinkedList<SelectBehaviorConditionItem> conditionItemList = new LinkedList<>();
        SelectBehaviorConditionItem groupItem;
        SelectBehaviorConditionItem ungroupItem;
        SelectBehaviorConditionItem renameItem;
        public EditMenu(MenuBar parent) {
            super("Edit");
            this.parent = parent;
//            this.addActionListener(actionEvent -> {
//                System.out.println("test open edit menu");
//                for (SelectBehaviorConditionItem conditionItem : conditionItemList) {
//                    System.out.println("test turn:" + conditionItem.workCondition());
//                    conditionItem.setEnabled(conditionItem.workCondition());
//                }
//            });

            groupItem = new SelectBehaviorConditionItem("group", parent,
                    SelectBehavior::isNowGroupAble, SelectBehavior::groupSelectedObjs);
            ungroupItem = new SelectBehaviorConditionItem("ungroup", parent,
                    SelectBehavior::isNowUngroupAble, SelectBehavior::ungroupSelectedObj);
            renameItem = new SelectBehaviorConditionItem("rename", parent,
                    SelectBehavior::isNowRenameAble, SelectBehavior::renameSelectedObj);

            this.conditionItemList.add(groupItem);
            this.conditionItemList.add(ungroupItem);
            this.conditionItemList.add(renameItem);

            this.add(groupItem);
            this.add(ungroupItem);
            this.add(renameItem);
        }
    }
}
