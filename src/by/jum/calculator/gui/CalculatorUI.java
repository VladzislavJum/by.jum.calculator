package by.jum.calculator.gui;

import by.jum.calculator.constants.Names;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

public class CalculatorUI {
    public List<JButton> numberList;
    public List<JButton> buttonList;
    private JFrame frame;
    private JPanel scoreboardPanel;
    private JPanel trigonometricPanel;
    private TreeView tree;
    private JCheckBoxMenuItem treeCheckBox;
    private JScrollPane scrollPane;

    public void createMainWindow() {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        frame = new JFrame("Calculator");
        // frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(260, 360);
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }


    public void createMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu viewMenu = new JMenu(Names.VIEW.getName());
        ButtonGroup viewGroup = new ButtonGroup();

        final JRadioButtonMenuItem trigonometricButtonItem = new JRadioButtonMenuItem(Names.TRIGONOMEETRIC.getName());
        final JRadioButtonMenuItem simpleButtonItem = new JRadioButtonMenuItem(Names.SIMPLE.getName());
        treeCheckBox = new JCheckBoxMenuItem("Tree");
        simpleButtonItem.setSelected(true);
        viewGroup.add(trigonometricButtonItem);
        viewGroup.add(simpleButtonItem);


        treeCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (treeCheckBox.isSelected()) {
                    scrollPane.setVisible(true);
                    buttonList.get(2).setEnabled(true);
                    buttonList.get(3).setEnabled(true);
                    frame.setSize(150 + frame.getWidth(), 360);

                } else {
                    scrollPane.setVisible(false);
                    buttonList.get(2).setEnabled(false);
                    buttonList.get(3).setEnabled(false);
                    frame.setSize(frame.getWidth() - 150, 360);
                }
            }
        });

        simpleButtonItem.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (simpleButtonItem.isSelected()) {
                    trigonometricPanel.setVisible(false);
                    frame.setSize(frame.getWidth() - 55, 360);

                }
            }
        });

        trigonometricButtonItem.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (trigonometricButtonItem.isSelected()) {
                    trigonometricPanel.setVisible(true);
                    frame.setSize(frame.getWidth() + 55, 360);
                }
            }
        });


        viewMenu.add(simpleButtonItem);
        viewMenu.add(trigonometricButtonItem);
        viewMenu.add(treeCheckBox);
        menuBar.add(viewMenu);
        frame.setJMenuBar(menuBar);
    }

    public void addScoreboard() {
        scoreboardPanel = new JPanel();
        scoreboardPanel.setSize(200, 264);
        scoreboardPanel.setLayout(new GridBagLayout());
        scoreboardPanel.updateUI();
        frame.add(scoreboardPanel, BorderLayout.CENTER);

        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(100, 75));
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setFont(new Font("Calibri", Font.BOLD, 30));
        textField.setOpaque(true);
        textField.setBackground(Color.lightGray);
        textField.setBorder(BorderFactory.createLineBorder(Color.RED));
        frame.add(textField, BorderLayout.NORTH);

        addButtonKit();
    }

    private void addButtonKit() {
        buttonList = new ArrayList<JButton>();

        buttonList.add(new JButton(Names.BRACKET_LEFT.getName()));
        buttonList.add(new JButton(Names.BRACKET_RIGHT.getName()));
        buttonList.add(new JButton(Names.FOLDING.getName()));
        buttonList.add(new JButton(Names.UNFOLDING.getName()));
        buttonList.add(new JButton(Names.DELETE.getName()));
        buttonList.add(new JButton(Names.CLEAR.getName()));
        buttonList.add(new JButton(Names.ADD.getName()));
        buttonList.add(new JButton(Names.DIFF.getName()));
        buttonList.add(new JButton(Names.MULT.getName()));
        buttonList.add(new JButton(Names.DIV.getName()));
        buttonList.add(new JButton(Names.SQRT.getName()));
        buttonList.add(new JButton(Names.POINT.getName()));
        buttonList.add(new JButton(Names.OPPOSITE.getName()));
        buttonList.add(new JButton(Names.PERCENT.getName()));
        buttonList.add(new JButton(Names.COUNT.getName()));


        for (JButton button : buttonList) {
            button.setFont(new Font("Calibri", Font.BOLD, 20));
        }
        locate();
        addNumberButton();
        scoreboardPanel.setSize(232, 264);

    }

    private void locate() {

        for (int number = 0, alignmentX = 1; number < 2; number++, alignmentX++) {
            scoreboardPanel.add(buttonList.get(number), new GridBagConstraints(alignmentX, 5, 1, 1, 1, 1, GridBagConstraints.NORTH,
                    GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        }

        for (int number = 2, alignmentX = 0; number < 6; number++, alignmentX++) {
            scoreboardPanel.add(buttonList.get(number), new GridBagConstraints(alignmentX, 1, 1, 1, 1, 1, GridBagConstraints.NORTH,
                    GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        }

        for (int number = 6, alignmentY = 2; number < 11; number++, alignmentY++) {
            scoreboardPanel.add(buttonList.get(number), new GridBagConstraints(3, alignmentY, 1, 1, 1, 1, GridBagConstraints.NORTH,
                    GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        }

        for (int number = 11, alignmentX = 0; number < 14; number++, alignmentX++) {
            scoreboardPanel.add(buttonList.get(number), new GridBagConstraints(alignmentX, 6, 1, 1, 1, 1, GridBagConstraints.NORTH,
                    GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        }

        scoreboardPanel.add(buttonList.get(14), new GridBagConstraints(0, 7, 4, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

    }

    private void addNumberButton() {
        numberList = new ArrayList<JButton>();
        for (int number = 0; number < 10; number++) {
            JButton button = new JButton(String.valueOf(number));
            button.setFont(new Font("Calibri", Font.BOLD, 20));
            numberList.add(button);
        }
        locateNumberButton();
    }

    private void locateNumberButton() {
        scoreboardPanel.add(numberList.get(0), new GridBagConstraints(0, 5, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));


        for (int number = 7, alignmentX = 0; number < 10; number++, alignmentX++) {
            scoreboardPanel.add(numberList.get(number), new GridBagConstraints(alignmentX, 2, 1, 1, 1, 1, GridBagConstraints.NORTH,
                    GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        }

        for (int number = 4, alignmentX = 0; number < 7; number++, alignmentX++) {
            scoreboardPanel.add(numberList.get(number), new GridBagConstraints(alignmentX, 3, 1, 1, 1, 1, GridBagConstraints.NORTH,
                    GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        }

        for (int number = 1, alignmentX = 0; number < 4; number++, alignmentX++) {
            scoreboardPanel.add(numberList.get(number), new GridBagConstraints(alignmentX, 4, 1, 1, 1, 1, GridBagConstraints.NORTH,
                    GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        }
    }

    public void addTree() {
        tree = new TreeView();
        scrollPane = tree.addTreePanel();
        scrollPane.setVisible(false);
        frame.add(scrollPane, BorderLayout.WEST);
        buttonList.get(2).setEnabled(false);
        buttonList.get(3).setEnabled(false);
    }

    public void addTrigonometric() {
        trigonometricPanel = new TrigonometricPanel().addTrigonometricPanel();
        frame.add(trigonometricPanel, BorderLayout.EAST);
    }

}
