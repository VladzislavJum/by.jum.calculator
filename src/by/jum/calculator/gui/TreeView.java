package by.jum.calculator.gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.Color;
import java.awt.Dimension;


public class TreeView {
    public JScrollPane addTreePanel() {
       JPanel treePanel = new JPanel();

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


        DefaultMutableTreeNode child21 = new DefaultMutableTreeNode("child2");
        DefaultMutableTreeNode child22 = new DefaultMutableTreeNode("child2");
        DefaultMutableTreeNode child23 = new DefaultMutableTreeNode("child2");
        DefaultMutableTreeNode child25 = new DefaultMutableTreeNode("child2");
        DefaultMutableTreeNode child20 = new DefaultMutableTreeNode("child2");

        child.add(child1);
        child1.add(child2);
        child2.add(child20);
        child20.add(child21);
        child21.add(child22);
        child22.add(child23);
        child23.add(child25);
        root.add(child);

        JTree tree = new JTree(root);
        tree.setBackground(Color.LIGHT_GRAY);
        treePanel.add(tree);
        JScrollPane scrollPane = new JScrollPane(treePanel);
        scrollPane.setPreferredSize(new Dimension(130, 100));
        return scrollPane;
    }

}
