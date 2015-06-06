package by.jum.calculator.gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.Dimension;


public class TreeView {
    private JPanel treePanel;

    public JScrollPane addTreePanel() {
        treePanel = new JPanel();
        treePanel.setPreferredSize(new Dimension(150, 200));

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("root");
        DefaultMutableTreeNode child = new DefaultMutableTreeNode("child");
        DefaultMutableTreeNode child1 = new DefaultMutableTreeNode("child1");
        DefaultMutableTreeNode child2 = new DefaultMutableTreeNode("child2");
        root.add(new DefaultMutableTreeNode("cds"));
        root.add(new DefaultMutableTreeNode("cds"));
        root.add(new DefaultMutableTreeNode("cds"));
        root.add(new DefaultMutableTreeNode("cds"));
        root.add(new DefaultMutableTreeNode("cds"));
        root.add(new DefaultMutableTreeNode("cds"));
        root.add(new DefaultMutableTreeNode("cds"));
        root.add(new DefaultMutableTreeNode("cds"));
        root.add(new DefaultMutableTreeNode("cds"));
        root.add(new DefaultMutableTreeNode("cds"));
        root.add(new DefaultMutableTreeNode("cds"));
        root.add(new DefaultMutableTreeNode("cds"));
        root.add(new DefaultMutableTreeNode("cds"));
        root.add(new DefaultMutableTreeNode("cds"));
        root.add(new DefaultMutableTreeNode("cds"));
        root.add(new DefaultMutableTreeNode("cds"));
        root.add(new DefaultMutableTreeNode("cds"));
        root.add(new DefaultMutableTreeNode("cds"));
        root.add(new DefaultMutableTreeNode("cds"));
        root.add(new DefaultMutableTreeNode("cds"));
        root.add(new DefaultMutableTreeNode("cds"));
        root.add(new DefaultMutableTreeNode("cds"));
        root.add(new DefaultMutableTreeNode("cds"));
        root.add(new DefaultMutableTreeNode("cds"));
        root.add(new DefaultMutableTreeNode("cds"));
        root.add(new DefaultMutableTreeNode("cds"));
        root.add(new DefaultMutableTreeNode("cds"));
        root.add(new DefaultMutableTreeNode("cds"));
        root.add(new DefaultMutableTreeNode("cds"));
        root.add(new DefaultMutableTreeNode("cds"));
        root.add(new DefaultMutableTreeNode("cds"));
        root.add(new DefaultMutableTreeNode("cds"));
        child1.add(child2);
        root.add(child);

        JTree tree = new JTree(root);
        treePanel.add(tree);
        JScrollPane scrollPane = new JScrollPane(treePanel);
        return scrollPane;
    }

    public JPanel getTreePanel() {
        return treePanel;
    }
}
