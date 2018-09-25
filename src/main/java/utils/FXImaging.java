package utils;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @program: DrawingBoard
 * @author: Lijie
 * @description: ${description}
 * @create: 2018-09-23 19:06
 **/
public class FXImaging implements ComponentListener {
    private JFXPanel fxPanel;
    private int TIME = 200;
    private File file;
    private JFrame frame;
    private BoundingBox boundbox;
    private Timer timer;
    private Stage stage;
    private Scene scene;
    private ObservableList<Node> list;
    private Node node;

    public void sceneToImage(final Scene scene, final File save, double width, double height) {
        stage = (Stage) scene.getWindow();
        this.scene = scene;
        BoundingBox bound = null;
        if (width > 0 && height > 0) {
            bound = new BoundingBox(0, 0, width, height);
        }
        initAndShowGUI(scene, save, bound);
    }

    // 整个屏幕保存为图片
    public void sceneToImage(final Scene scene, final File save) {
        sceneToImage(scene, save, 0, 0);
    }

    // 把一个node保存为图片
    public void nodeToImage(final Node node, final ObservableList<Node> list, final File save) {
        nodeToImage(node, list, save, 0, 0);
    }

    public void nodeToImage(final Node node, final ObservableList<Node> list, final File save, final double width, final double height) {
        stage = (Stage) node.getScene().getWindow();
        scene = node.getScene();
        this.node = node;
        this.list = list;
        BoundingBox bound = null;
        if (width > 0 && height > 0) {
            bound = new BoundingBox(0, 0, width, height);
        }
        initAndShowGUI(node, save, bound);
    }

    private void initAndShowGUI(final Node node, File f, BoundingBox bound) {
        Group root = new Group();
        Scene sc = new Scene(root);
        root.getChildren().add(node);
        initAndShowGUI(sc, f, bound);

    }

    private void initAndShowGUI(final Scene sc, File f, BoundingBox bound) {
        file = f;
        if (bound == null)
            boundbox = new BoundingBox(0, 0, stage.getWidth() + 16, stage.getHeight() + 38);//
        else
            boundbox = new BoundingBox(0, 0, bound.getWidth() + 16, bound.getHeight() + 38);
        // 在这里加上Stage边框的上下高度和 以及左右宽度和，然后再在最后的时候减去这个高度和宽度；
        // 如果不这样，最终会导致图片右边16个像素和下面38个像素的图像会变成透明色

        frame = new JFrame();
        // Frame.setUndecorated(true);
        fxPanel = new JFXPanel();
        fxPanel.setScene(sc);
        fxPanel.addComponentListener(this);

        frame.add(fxPanel);
        frame.setSize((int) boundbox.getWidth(), (int) boundbox.getHeight());
        if (stage != null) {
            frame.setLocation((int) stage.getX(), (int) stage.getY());
            Platform.runLater(new Runnable() {
                public void run() {
                    stage.hide();
                }

            });
        }
        frame.setVisible(true);

    }

    private void save(Container container, Bounds bounds, File file) {
        try {
            String extension = "";
            String name = file.getName();
            if (name.contains(".")) {
                int start = name.lastIndexOf(".");
                extension = file.getName().substring(start + 1);

            } else {
                extension = "jpg";
            }
            ImageIO.write(toBufferedImage(container, bounds), extension, file);

        } catch (java.lang.Exception exception) {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(null, "The image couldn't be saved", "Error", JOptionPane.ERROR_MESSAGE);
            restore();
        }
    }

    private void restore() {
        if (node != null) {
            restoreNode();
        } else {
            restoreScene();
        }
    }

    private void restoreNode() {
        Platform.runLater(new Runnable() {
            public void run() {
                list.add(node);
                stage.show();
            }

        });
    }

    private void restoreScene() {
        Platform.runLater(new Runnable() {
            public void run() {
                stage.setScene(scene);
                stage.show();
            }

        });
    }

    private BufferedImage toBufferedImage(Container container, Bounds bounds) {
        BufferedImage bufferedImage = new BufferedImage((int) bounds.getWidth() - 16, (int) bounds.getHeight() - 38, BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = bufferedImage.getGraphics();
        graphics.translate((int) -bounds.getMinX(), (int) -bounds.getMinY()); // translating
        // to
        // upper-left
        // corner
        container.paint(graphics);
        graphics.dispose();
        return bufferedImage;
    }

    public void componentHidden(ComponentEvent e) {
        // TODO Auto-generated method stub

    }

    public void componentMoved(ComponentEvent e) {
        // TODO Auto-generated method stub

    }

    public void componentResized(ComponentEvent e) {
        if (e.getSource() == fxPanel) {
            ActionListener ac = new ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    save(fxPanel, boundbox, file);
                    timer.stop();
                    fxPanel.removeAll();
                    restore();
                    frame.dispose();

                }
            };
            timer = new Timer(TIME, ac);
            timer.start();
        }
    }

    public void componentShown(ComponentEvent e) {
        // TODO Auto-generated method stub

    }
}
