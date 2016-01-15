package rs.etf.sv153036m;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JList;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUIFinder {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private CoffeeFinder coffeeFinder;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIFinder window = new GUIFinder();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUIFinder() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		coffeeFinder = new CoffeeFinder();
		
		DefaultListModel listModel;
		String[] elements;
		
		frame = new JFrame();
		frame.setBounds(100, 100, 729, 475);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 177, 436);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		listModel = new DefaultListModel();
		elements = coffeeFinder.getKinds();
		for (String elem : elements)
		{
			listModel.addElement(elem);
		}
		final JList list = new JList(listModel);
		list.setBounds(22, 22, 130, 55);
		panel.add(list);
		
		listModel = new DefaultListModel();
		elements = coffeeFinder.getBases();
		for (String elem : elements)
		{
			listModel.addElement(elem);
		}
		final JList list_1 = new JList(listModel);
		list_1.setBounds(22, 105, 130, 77);
		panel.add(list_1);
		
		final String[] treeElements = coffeeFinder.getIngredients();
		final JTree tree = new JTree();
		tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("Ingredients") {
				{
					for (String elem : treeElements)
					{
						AddElemToTree(elem, this, coffeeFinder);
					}
				}
			}
		));
		tree.setBounds(22, 193, 130, 232);
		JScrollPane listScroller = new JScrollPane();
		listScroller.setViewportView(tree);
		listScroller.setBounds(22, 193, 130, 232);
		panel.add(listScroller);
		//panel.add(tree);
		
		JLabel lblNewLabel = new JLabel("Base");
		lblNewLabel.setBounds(22, 88, 46, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Kind");
		lblNewLabel_1.setBounds(22, 0, 46, 14);
		panel.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(187, 120, 516, 186);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		final DefaultListModel listModel_included = new DefaultListModel();
		final JList textArea = new JList(listModel_included);
		textArea.setBounds(10, 24, 358, 60);
		panel_1.add(textArea);
		
		final DefaultListModel listModel_excluded = new DefaultListModel();
		final JList textArea_1 = new JList(listModel_excluded);
		textArea_1.setBounds(10, 115, 358, 60);
		panel_1.add(textArea_1);
		
		JLabel lblIncludedIngredients = new JLabel("Included Ingredients");
		lblIncludedIngredients.setBounds(10, 0, 101, 14);
		panel_1.add(lblIncludedIngredients);
		
		JLabel lblExcludedIngredients = new JLabel("Excluded Ingredients");
		lblExcludedIngredients.setBounds(10, 90, 101, 14);
		panel_1.add(lblExcludedIngredients);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String selected = tree.getSelectionPath().getLastPathComponent().toString();
				if (selected.equals("Ingredients"))
				{
					return;
				}
				listModel_included.addElement(selected);
			}
		});
		btnNewButton.setBounds(392, 25, 89, 23);
		panel_1.add(btnNewButton);
		
		JButton btnRemove_1 = new JButton("Remove");
		btnRemove_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object selected = textArea.getSelectedValue();
				if (selected == null)
				{
					return;
				}
				listModel_included.removeElement(selected.toString());
			}
		});
		btnRemove_1.setBounds(392, 59, 89, 23);
		panel_1.add(btnRemove_1);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String selected = tree.getSelectionPath().getLastPathComponent().toString();
				if (selected.equals("Ingredients"))
				{
					return;
				}
				listModel_excluded.addElement(selected);
			}
		});
		btnAdd.setBounds(392, 116, 89, 23);
		panel_1.add(btnAdd);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object selected = textArea.getSelectedValue();
				if (selected == null)
				{
					return;
				}
				listModel_included.removeElement(selected.toString());
			}
		});
		btnRemove.setBounds(392, 150, 89, 23);
		panel_1.add(btnRemove);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(187, 317, 516, 108);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		final DefaultListModel listModel_get = new DefaultListModel();
		final JList list_2 = new JList(listModel_get);
		JScrollPane listScroller2 = new JScrollPane();
		listScroller2.setViewportView(list_2);
		listScroller2.setBounds(110, 11, 396, 77);
		panel_2.add(listScroller2);
		
		JButton btnGet = new JButton("Get");
		btnGet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listModel_get.removeAllElements();
				String base = textField_1.getText();
				String kind = textField.getText();
				int size;
				size = listModel_included.size();
				String[] includedIngr = null; 
				if (size > 0)
				{
					includedIngr = new String[size];
				}
				for (int i=0; i<size; i++)
				{
					includedIngr[i] = (String) listModel_included.getElementAt(i);
				}
				
				size = listModel_excluded.size();
				String[] excludedIngr = null; 
				if (size > 0)
				{
					excludedIngr = new String[size];
				}
				for (int i=0; i<size; i++)
				{
					excludedIngr[i] = (String) listModel_excluded.getElementAt(i);
				}
				
				String[] result = coffeeFinder.get(base, kind, includedIngr, excludedIngr);
				
				for (String elem : result)
				{
					listModel_get.addElement(elem);
				}
			}
		});
		btnGet.setBounds(10, 44, 89, 23);
		panel_2.add(btnGet);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(187, 11, 243, 99);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(10, 34, 112, 20);
		panel_3.add(textField);
		textField.setColumns(10);
		
		JButton button = new JButton("Add");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText(list.getSelectedValue().toString());
			}
		});
		button.setBounds(10, 65, 89, 23);
		panel_3.add(button);
		
		JButton button_2 = new JButton("Remove");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText("");
			}
		});
		button_2.setBounds(109, 65, 89, 23);
		panel_3.add(button_2);
		
		JLabel lblChosenKind = new JLabel("Chosen kind");
		lblChosenKind.setBounds(59, 9, 101, 14);
		panel_3.add(lblChosenKind);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(440, 11, 263, 99);
		frame.getContentPane().add(panel_4);
		panel_4.setLayout(null);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(10, 35, 112, 20);
		panel_4.add(textField_1);
		
		JButton button_1 = new JButton("Add");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField_1.setText(list_1.getSelectedValue().toString());
			}
		});
		button_1.setBounds(10, 65, 89, 23);
		panel_4.add(button_1);
		
		JButton button_3 = new JButton("Remove");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField_1.setText("");
			}
		});
		button_3.setBounds(113, 65, 89, 23);
		panel_4.add(button_3);
		
		JLabel lblChosenBase = new JLabel("Chosen base");
		lblChosenBase.setBounds(75, 10, 101, 14);
		panel_4.add(lblChosenBase);
	}

	protected void AddElemToTree(String elem, DefaultMutableTreeNode node, CoffeeFinder coffeeFinder) {
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(elem);
		node.add(newNode);
		
		String[] elements = coffeeFinder.getSubClassOf(elem);
		for (String newElem : elements)
		{
			AddElemToTree(newElem, newNode, coffeeFinder);
		}
	}
}
