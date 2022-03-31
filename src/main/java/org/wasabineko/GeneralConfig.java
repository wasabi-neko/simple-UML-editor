package org.wasabineko;

import java.awt.*;

/**
 * Use Singleton Pattern
 */
public class GeneralConfig {
    private static GeneralConfig instance;
    private final String winTitle;
    private final Dimension defaultWinSize;
    private final Dimension defaultToolBarSize;
    private final Dimension defaultCanvasSize;
    private final Dimension defaultMenuSize;

    private GeneralConfig() {
        winTitle = "simple-UML-editor";

        Dimension resolution = Toolkit.getDefaultToolkit().getScreenSize();
        System.out.println("resolution: " + resolution.width + " x " + resolution.height);  //TODO: remove this test line

        double scale = 0.7;
        defaultWinSize = new Dimension((int) (resolution.width * scale), ((int) (resolution.width * scale)));
        defaultToolBarSize = new Dimension(((int) (resolution.width * scale * 0.1)), ((int) (resolution.height * scale)));
        defaultCanvasSize = new Dimension(((int) (resolution.width * scale * 0.7)), ((int) (resolution.height * scale)));
        defaultMenuSize = new Dimension(((int)(resolution.width * scale)), ((int)(resolution.height * scale * 0.05)));
    }

    public static GeneralConfig getInstance() {
        if (GeneralConfig.instance == null) {
            GeneralConfig.instance = new GeneralConfig();
        }
        return GeneralConfig.instance;
    }

    public String getWinTitle() {
         return winTitle;
    }

    public Dimension getDefaultWinSize() { return defaultWinSize; }

    public Dimension getDefaultToolBarSize() {
        return defaultToolBarSize;
    }

    public Dimension getDefaultCanvasSize() {
        return  defaultCanvasSize;
    }

    public Dimension getDefaultMenuSize() { return defaultMenuSize; }
}
