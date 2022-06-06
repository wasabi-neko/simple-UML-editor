package org.wasabineko;

import org.wasabineko.editorBehavior.EditorBehaviorAgent;
import org.wasabineko.graphic.EditPanel;
import org.wasabineko.graphic.Menu.MenuBar;

import javax.swing.*;

public class App {
    static public void main(String[] arg) {
        System.setProperty("sun.java2d.uiScale", "2.0");    // TODO: this method seems duml dum

        JFrame frame = new JFrame(GeneralConfig.getInstance().getWinTitle());

        EditPanel editPanel = new EditPanel();
        MenuBar menuBar = new MenuBar();

        frame.setContentPane(editPanel);
        frame.setJMenuBar(menuBar);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
