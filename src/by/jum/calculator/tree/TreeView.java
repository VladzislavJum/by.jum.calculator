package by.jum.calculator.tree;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.Color;
import java.awt.Dimension;


public class TreeView {
    private JTree tree;

    public JTree getTree() {
        return tree;
    }

    public JScrollPane addTreePanel() {
        JPanel treePanel = new JPanel();
        tree = new JTree(new DefaultMutableTreeNode(""));
        tree.setRootVisible(false);
        tree.setBackground(new Color(217, 217, 217));
        tree.setDragEnabled(true);
        treePanel.add(tree);
        JScrollPane scrollPane = new JScrollPane(treePanel);
        scrollPane.setPreferredSize(new Dimension(130, 100));
        return scrollPane;
    }


}
