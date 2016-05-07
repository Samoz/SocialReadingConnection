package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.google.gson.Gson;

import graphDatabase.BookNode;
import graphDatabase.BookNodeComparator;
import graphDatabase.Graph;
import search.HashMapAuthor;
import search.HashMapGenre;
import search.HashMapTitle;

public class PanelControl extends JPanel implements ActionListener {

	private Graph theGraph;
	private HashMapAuthor hashAuthor;
	private HashMapTitle hashTitle;
	private HashMapGenre hashGenre;
	private BookNode actualBook;
	
	private boolean relatedFunction;
	private boolean allBooksFunction;
	private boolean addGenreFunction;
	private boolean addAuthorFunction;
	private boolean searchForGenreFunction;

	private JLabel lbBook;
	private JLabel lbGenre;
	private JLabel lbOutput;
	private JLabel lbInfo;
	private JLabel lbSearch;
	private JLabel lbAuthorFN;
	private JLabel lbAuthorLN;
	private JLabel lbRelatedBooks;

	private JTextArea taOutput;
	private JTextArea taInfo;

	private JTextField tfBook;
	private JTextField tfGenre;
	private JTextField tfFirstName;
	private JTextField tfLastName;

	private JButton btnGenre;
	private JButton btnAdd;
	private JButton btnSearch;
	private JButton btnClear;
	private JButton btnAuthor;
	private JButton btnAllAuthors;
	private JButton btnSearchGenre;
	private JButton btnShowBookList;

	private JList jListRelatedBooks;

	private JScrollPane spInfo;
	private JScrollPane spOutput;
	private JScrollPane spRelatedBooks;

	private DefaultListModel bookNodeModel = new DefaultListModel();
	private DefaultListModel bookNodeModelGenre = new DefaultListModel();
	private DefaultListModel authorModel = new DefaultListModel();

	public PanelControl() {
		this.hashAuthor = new HashMapAuthor();
		this.hashTitle = new HashMapTitle();
		this.hashGenre = new HashMapGenre();
		this.theGraph = new Graph();
		this.setPreferredSize(new Dimension(800, 600));
		this.setLayout(null);
		addComponentsToPanel();
		setBoundsToComponents();
		setComponentsCharacteristics();
		addActionsToButtons();
		loadBooks();
	}

	public BookNode prepareBookToAdd() {
		BookNode bNodeTemp = new BookNode(this.tfBook.getText(), this.tfFirstName.getText(), this.tfLastName.getText());
		return bNodeTemp;
	}

	public void addHashKey(BookNode bookNode) {
		this.hashTitle.put(bookNode);
		this.hashAuthor.put(bookNode);
	}

	public BookNode searchForBook(String bookTitle) {
		return hashTitle.get(bookTitle);
	}

	public void showRelatedBooks(BookNode bookNode) {
		BookNode tempBook = bookNode;
		if (tempBook != null) {
			this.taInfo.setText(tempBook.toString());
			List<BookNode> tempSimilarBooks = tempBook.getSimilarBooks();
			if (tempSimilarBooks != null && tempSimilarBooks.size() > 0) {
				this.jListRelatedBooks.setModel(this.bookNodeModel);
				for (BookNode bookNodeList : tempSimilarBooks) {
					this.bookNodeModel.addElement(bookNodeList.getBookTitle());	
				}
			}
			else {
				this.taOutput.setText("No Similar Books");
			}
		}
		else {
			this.taOutput.setText("Book Not found");
		}
	}

	public List<String> showAllAuthors() {
		List<String> tempList = new ArrayList<String>(new LinkedHashSet<String>());
		List<BookNode> tempBookNodeList = this.theGraph.getNodeArr();
		for (BookNode bookNode : tempBookNodeList) {
			tempList.add(bookNode.getAuthor());
		}
		Set<String> noDuplicateAuthorList = new LinkedHashSet<String>(tempList);
		tempList.clear();
		tempList.addAll(noDuplicateAuthorList);
		Collections.sort(tempList);
		return tempList;
	}

	public List<String> removeDuplicateGenresInList() {
		List<String> tempList = new ArrayList<String>(new LinkedHashSet<String>());
		List<BookNode> tempBookNodeList = this.theGraph.getNodeArr();
		for (BookNode bookNode : tempBookNodeList) {
			tempList.addAll(bookNode.getKeywords());
		}
		Set<String> noDuplicateGenreList = new LinkedHashSet<String>(tempList);
		tempList.clear();
		tempList.addAll(noDuplicateGenreList);
		Collections.sort(tempList);
		return tempList;
	}

	public List<String> addGenresToBook(List<String> genreList) {
		List<String> tempList = new ArrayList<String>(new LinkedHashSet<String>());
		tempList.addAll(genreList);
		Set<String> noDuplicateGenreForBook = new LinkedHashSet<String>(tempList);
		tempList.clear();
		tempList.addAll(noDuplicateGenreForBook);
		Collections.sort(tempList);
		return tempList;
	}

	public List<BookNode> searchByGenre(String genre) {
		List<BookNode> tempList = new ArrayList<BookNode>();
		List<BookNode> tempBookList = theGraph.getNodeArr();
		for (BookNode bookNode : tempBookList) {
			if (bookNode.getKeywords().contains(genre)) {
				tempList.add(bookNode);
			}
		}
		return tempList;
	}

	public String getBookName() {
		return this.tfBook.getText();
	}

	public void loadBooks() {
		String jsonText = "";
		Path file = Paths.get("text/Books.json");
		try {
			jsonText = new String(Files.readAllBytes(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			Gson gson = new Gson();
			List<BookNode> bookList = new ArrayList<BookNode>(Arrays.asList(gson.fromJson(jsonText, BookNode[].class)));
			for (BookNode bookNode : bookList) {
				bookNode.setSimilarBooks(new ArrayList<BookNode>());
				this.theGraph.addNode(bookNode);
				addHashKey(bookNode);
			}
		} catch (Exception e) {
		}
	}

	public void addComponentsToPanel() {
		this.lbBook = new JLabel("Book Name:");
		this.lbGenre = new JLabel("Genres of the Book:");
		this.lbOutput = new JLabel("Output:");
		this.lbInfo = new JLabel("Info:");
		this.lbSearch = new JLabel("Search for Book:");
		this.lbAuthorFN = new JLabel("Author's First Name:");
		this.lbAuthorLN = new JLabel("Author's Last Name:");
		this.lbRelatedBooks = new JLabel("Selector:");
		this.taOutput = new JTextArea();
		this.taInfo = new JTextArea();
		this.tfBook = new JTextField();
		this.tfGenre = new JTextField();
		this.tfFirstName = new JTextField();
		this.tfLastName = new JTextField();
		this.btnGenre = new JButton("Add Genre");
		this.btnAdd = new JButton("Add Book");
		this.btnSearch = new JButton("Search for Book");
		this.btnClear = new JButton("Clear");
		this.btnAuthor = new JButton("Set Author");
		this.btnAllAuthors = new JButton("Show Authors");
		this.btnSearchGenre = new JButton("Search By Genre");
		this.btnShowBookList = new JButton("Show Books");
		this.jListRelatedBooks = new JList<String>();
		this.add(this.lbBook);
		this.add(this.lbGenre);
		this.add(this.lbOutput);
		this.add(this.lbInfo);
		this.add(this.lbSearch);
		this.add(this.lbAuthorFN);
		this.add(this.lbAuthorLN);
		this.add(this.lbRelatedBooks);
		this.add(this.taOutput);
		this.add(this.taInfo);
		this.add(this.tfBook);
		this.add(this.tfGenre);
		this.add(this.tfFirstName);
		this.add(this.tfLastName);
		this.add(this.btnGenre);
		this.add(this.btnAdd);
		this.add(this.btnSearch);
		this.add(this.btnClear);
		this.add(this.btnAuthor);
		this.add(this.btnAllAuthors);
		this.add(this.btnSearchGenre);
		this.add(this.btnShowBookList);
		this.add(this.taOutput);
		this.spInfo = new JScrollPane(this.taInfo);
		this.spOutput = new JScrollPane(this.taOutput);
		this.spRelatedBooks = new JScrollPane(this.jListRelatedBooks);
		this.add(this.spInfo);
		this.add(this.spRelatedBooks);
		this.add(this.spOutput);
		this.jListRelatedBooks.setVisible(true);
		this.spInfo.setVisible(true);
		this.spRelatedBooks.setVisible(true);
		this.spOutput.setVisible(true);
	}

	public void setBoundsToComponents() {
		this.lbBook.setBounds(10, 10, this.lbBook.getPreferredSize().width, 30);
		this.tfBook.setBounds(this.lbBook.getX() + this.lbBook.getWidth(), 10, this.getPreferredSize().width - this.lbBook.getWidth() - 220, 30);
		this.lbRelatedBooks.setBounds(this.tfBook.getX() + this.tfBook.getWidth() + 10, this.lbBook.getY(), 190, this.lbBook.getHeight());
		this.spRelatedBooks.setBounds(this.lbRelatedBooks.getX(), this.lbRelatedBooks.getY() + this.lbRelatedBooks.getHeight(), 190, 550);
		this.btnAdd.setBounds(this.tfBook.getX() + 60, this.lbBook.getY() + this.lbBook.getHeight(), this.btnAdd.getPreferredSize().width, 30);
		this.btnSearch.setBounds(this.btnAdd.getX() + this.btnAdd.getWidth(), this.btnAdd.getY(), this.btnSearch.getPreferredSize().width, 30);
		this.btnShowBookList.setBounds(this.btnSearch.getX() + this.btnSearch.getWidth(), this.btnAdd.getY(), this.btnShowBookList.getPreferredSize().width, 30);
		this.lbAuthorFN.setBounds(this.lbBook.getX(), this.btnAdd.getY() + this.btnAdd.getHeight(), this.lbAuthorFN.getPreferredSize().width, 30);
		this.tfFirstName.setBounds(this.lbAuthorFN.getX() + this.lbAuthorFN.getWidth(), this.lbAuthorFN.getY(), 162, 30);
		this.lbAuthorLN.setBounds(this.tfFirstName.getX() + this.tfFirstName.getWidth(), this.lbAuthorFN.getY(), this.lbAuthorLN.getPreferredSize().width, 30);
		this.tfLastName.setBounds(this.lbAuthorLN.getX() + this.lbAuthorLN.getWidth(), this.lbAuthorLN.getY(), 162, 30);
		this.btnAuthor.setBounds(this.lbBook.getX() + 150, this.lbAuthorFN.getY() + this.lbAuthorFN.getHeight(), this.btnAuthor.getPreferredSize().width, 30);
		this.btnAllAuthors.setBounds(this.btnAuthor.getX() + this.btnAuthor.getWidth(), this.btnAuthor.getY(), this.btnAllAuthors.getPreferredSize().width, 30);
		this.lbGenre.setBounds(this.lbAuthorFN.getX(), this.btnAuthor.getY() + this.btnAuthor.getHeight(), this.lbGenre.getPreferredSize().width, 30);
		this.tfGenre.setBounds(this.lbGenre.getX() + this.lbGenre.getWidth(), this.lbGenre.getY(), this.getPreferredSize().width - this.lbGenre.getWidth() - 220, 30);
		this.btnSearchGenre.setBounds(this.tfGenre.getX() + 70, this.lbGenre.getY() + this.lbGenre.getHeight(), this.btnSearchGenre.getPreferredSize().width, 30);
		this.btnGenre.setBounds(this.btnSearchGenre.getX() + this.btnSearchGenre.getWidth(), this.lbGenre.getY() + this.lbGenre.getHeight(), this.btnGenre.getPreferredSize().width, 30);
		this.btnClear.setBounds(this.tfLastName.getX() + 80, this.btnGenre.getY(), this.btnClear.getPreferredSize().width, 30);
		this.lbInfo.setBounds(this.lbGenre.getX(), this.btnGenre.getY() + this.btnGenre.getHeight(), this.lbInfo.getPreferredSize().width, 30);
		this.spInfo.setBounds(this.lbInfo.getX(), this.lbInfo.getY() + this.lbInfo.getHeight(), this.getPreferredSize().width - 220, 300);
		this.spOutput.setBounds(10, this.getPreferredSize().height - 50, this.getPreferredSize().width - 220, 40);
		this.lbOutput.setBounds(this.taOutput.getX(), this.taOutput.getY() - 30, this.lbOutput.getPreferredSize().width, 30);
	}

	public void setComponentsCharacteristics() {
		this.tfGenre.setText("Genre1, Genre2, Genre3, . . ., GenreN");
		this.tfGenre.setEditable(false);
		this.taInfo.setEditable(false);
		this.taOutput.setEditable(false);
		this.btnAuthor.setEnabled(false);
		this.btnGenre.setEnabled(false);
		this.btnClear.setEnabled(false);
		this.taInfo.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		this.taOutput.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		this.jListRelatedBooks.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
	}

	public void enableComponents() {
		this.tfBook.setText("");
		this.tfFirstName.setText("");
		this.tfLastName.setText("");
		this.tfGenre.setText("Genre1, Genre2, Genre3, . . ., GenreN");
		this.taInfo.setText("");
		this.tfBook.setEditable(true);
		this.tfFirstName.setEditable(true);
		this.tfLastName.setEditable(true);
		this.btnAdd.setEnabled(true);
		this.btnSearch.setEnabled(true);
		this.btnAuthor.setEnabled(false);
		this.btnAllAuthors.setEnabled(true);
		this.btnGenre.setEnabled(false);
		this.btnClear.setEnabled(false);
		this.btnSearchGenre.setEnabled(true);
		this.btnShowBookList.setEnabled(true);
		this.lbRelatedBooks.setText("");
		this.addGenreFunction = false;
		this.allBooksFunction = false;
		this.relatedFunction = false;
		this.addAuthorFunction = false;
		this.searchForGenreFunction = false;
		this.bookNodeModel.clear();
		this.bookNodeModelGenre.clear();
		this.authorModel.clear();
	}

	public void addActionsToButtons() {
		this.btnAdd.addActionListener(this);
		this.btnSearch.addActionListener(this);
		this.btnGenre.addActionListener(this);
		this.btnClear.addActionListener(this);
		this.btnAuthor.addActionListener(this);
		this.btnAllAuthors.addActionListener(this);
		this.btnSearchGenre.addActionListener(this);
		this.btnShowBookList.addActionListener(this);
		this.jListRelatedBooks.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (relatedFunction) {
					Object selectedValue = jListRelatedBooks.getSelectedValue();
					if (!e.getValueIsAdjusting()) {
						if (selectedValue != null) {
							actualBook = searchForBook(selectedValue.toString());
							bookNodeModel.clear();
							tfBook.setText("");
							showRelatedBooks(actualBook);
						}
						if (actualBook != null) {
							taInfo.setText(actualBook.toString());
						}
					}
				}
				else if (allBooksFunction) {
					Object selectedValue = jListRelatedBooks.getSelectedValue();
					if (!e.getValueIsAdjusting()) {
						if (selectedValue != null) {
							actualBook = searchForBook(selectedValue.toString());
							taInfo.setText(actualBook.toString());
							tfBook.setText(actualBook.getBookTitle());
						}
					}
				}
				else if(addGenreFunction) {
					Object selectedValue = jListRelatedBooks.getSelectedValue();
					if (!e.getValueIsAdjusting()) {
						if (selectedValue != null && tfGenre.getText().equals("")) {
							tfGenre.setText(selectedValue.toString());
						}
						else {
							tfGenre.setText(tfGenre.getText() + ", " + selectedValue.toString());
						}
					}
				}
				else if (addAuthorFunction) {
					Object selectedValue = jListRelatedBooks.getSelectedValue();
					if (!e.getValueIsAdjusting()) {
						if (selectedValue != null) {
							if (btnAuthor.isEnabled()) {
								String[] author = selectedValue.toString().split(" ");
								tfFirstName.setText(author[0]);
								tfLastName.setText(author[1]);
							}
						}
					}
				}
				else if (searchForGenreFunction) {
					Object selectionValue = jListRelatedBooks.getSelectedValue();
					if (!e.getValueIsAdjusting()) {
						if (selectionValue != null) {
							taInfo.setText("");
							for (BookNode bookNode : searchByGenre(selectionValue.toString())) {
								taInfo.append(bookNode.toString() + "\n");
							}
						}
					}
				}
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(this.btnAdd)) {
			this.bookNodeModelGenre = bookNodeModel;
			if (this.allBooksFunction || this.relatedFunction) {
				this.tfBook.setText("");
			}
			this.taOutput.setText("");
			this.lbRelatedBooks.setText("");
			this.bookNodeModel.clear();
			this.allBooksFunction = false;
			this.relatedFunction = false;
			this.taInfo.setText("");
			if (this.tfBook.getText().equals("")) {
				this.lbOutput.setText("Please Enter a Valid Book Name");
			}
			else {
				this.tfBook.setEditable(false);
				this.btnAuthor.setEnabled(true);
				this.btnClear.setEnabled(true);
				this.btnAdd.setEnabled(false);
				this.btnSearch.setEnabled(false);
				this.btnAllAuthors.setEnabled(false);
				this.btnSearchGenre.setEnabled(false);
				this.btnShowBookList.setEnabled(false);
			}
		}
		else if (e.getSource().equals(this.btnSearch)) {
			this.btnAdd.setEnabled(false);
			this.relatedFunction = true;
			this.allBooksFunction = false;
			this.addGenreFunction = false;
			this.bookNodeModel.clear();
			this.jListRelatedBooks.removeAll();
			this.spRelatedBooks.repaint();
			this.taInfo.setText("");
			this.taOutput.setText("");
			this.btnClear.setEnabled(true);
			if (this.tfBook.getText().equals("")) {
				this.taOutput.setText("Please Enter a Valid Book Name");
			}
			else {
				this.lbRelatedBooks.setText("Recommended Books:");
				BookNode tempBook = searchForBook(this.tfBook.getText());
				showRelatedBooks(tempBook);
				this.taInfo.setText(tempBook.toString());
			}
		}	
		else if (e.getSource().equals(this.btnShowBookList)) {
			this.btnAdd.setEnabled(false);
			this.relatedFunction = false;
			this.allBooksFunction = true;
			this.addGenreFunction = false;
			this.searchForGenreFunction = false;
			this.addAuthorFunction = false;
			this.bookNodeModel.clear();
			this.bookNodeModelGenre.clear();
			this.authorModel.clear();
			this.lbRelatedBooks.setText("");
			this.taOutput.setText("");
			this.taInfo.setText("");
			this.btnClear.setEnabled(true);
			this.lbRelatedBooks.setText("Books Stored:");
			this.jListRelatedBooks.setModel(bookNodeModel);
			Collections.sort(this.theGraph.getNodeArr(), new BookNodeComparator());
			for (BookNode bookNode : this.theGraph.getNodeArr()) {
				this.bookNodeModel.addElement(bookNode.getBookTitle());
			}
		}
		else if (e.getSource().equals(this.btnAuthor)) {
			this.taOutput.setText("");
			if (this.tfFirstName.getText().equals("") || this.tfLastName.getText().equals("")) {
				this.taOutput.setText("Please Enter a Valid Author Name");
			}
			else {
				this.authorModel.clear();
				this.bookNodeModel.clear();
				this.tfGenre.setEditable(true);
				this.tfGenre.setText("");
				this.addAuthorFunction = false;
				this.addGenreFunction = true;
				this.searchForGenreFunction = false;
				this.allBooksFunction = false;
				this.searchForGenreFunction = false;
				this.lbRelatedBooks.setText("Genres:");
				this.jListRelatedBooks.setModel(this.bookNodeModelGenre);
				for (String string : removeDuplicateGenresInList()) {
					this.bookNodeModelGenre.addElement(string);
				}
				this.tfFirstName.setEditable(false);
				this.tfLastName.setEditable(false);
				this.btnGenre.setEnabled(true);
				this.btnAuthor.setEnabled(false);
			}
		}
		else if (e.getSource().equals(this.btnAllAuthors)) {
			this.taInfo.setText("");
			this.taOutput.setText("");
			this.bookNodeModel.clear();
			this.bookNodeModelGenre.clear();
			this.btnAdd.setEnabled(false);
			this.btnClear.setEnabled(true);
			this.addAuthorFunction = true;
			this.addGenreFunction = false;
			this.allBooksFunction = false;
			this.relatedFunction = false;
			this.searchForGenreFunction = false;
			this.lbRelatedBooks.setText("Authors");
			this.jListRelatedBooks.setModel(this.authorModel);
			for (String string : this.showAllAuthors()) {
				this.authorModel.addElement(string);
			}
		}
		else if (e.getSource().equals(this.btnSearchGenre)) {
			this.lbRelatedBooks.setText("Available Genres");
			this.btnSearchGenre.setEnabled(false);
			this.tfGenre.setText("");
			this.tfGenre.setEditable(true);
			this.taInfo.setText("");
			this.taOutput.setText("");
			this.tfBook.setText("");
			this.tfFirstName.setText("");
			this.tfLastName.setText("");
			this.tfBook.setEditable(false);
			this.tfFirstName.setEditable(false);
			this.tfLastName.setEditable(false);
			this.btnAdd.setEnabled(false);
			this.btnAuthor.setEnabled(false);
			this.btnGenre.setEnabled(false);
			this.btnAllAuthors.setEnabled(false);
			this.btnClear.setEnabled(true);
			if (this.tfGenre.equals("")) {
				this.taOutput.setText("Please enter a valid genre");
			}
			else {
				this.authorModel.clear();
				this.bookNodeModel.clear();
				this.searchForGenreFunction = true;
				this.allBooksFunction = false;
				this.addGenreFunction = false;
				this.relatedFunction = false;
				this.addAuthorFunction = false;
				this.lbRelatedBooks.setText("Genres:");
				this.jListRelatedBooks.setModel(this.bookNodeModelGenre);
				for (String string : removeDuplicateGenresInList()) {
					this.bookNodeModelGenre.addElement(string);

				}
			}
		}
		else if (e.getSource().equals(this.btnGenre)) {
			this.taOutput.setText("");
			if (this.tfGenre.getText().equals("")) {
				this.taOutput.setText("Please Enter a Valid Genre");
			}
			else {
				String[] tempGenre = this.tfGenre.getText().split(", ");
				List<String> tempGenresList = new ArrayList<String>();
				tempGenre = this.tfGenre.getText().split(", ");
				for (int i = 0; i < tempGenre.length; i++) {
					if (!tempGenre[i].equals("\\s+")) {
						tempGenresList.add(tempGenre[i]);
					}
				}
				BookNode bookToAdd = prepareBookToAdd();
				bookToAdd.setKeywords(addGenresToBook(tempGenresList));
				for (BookNode bookNode : this.theGraph.getNodeArr()) {
					bookNode.addSimilarNode(bookToAdd);
				}
				this.theGraph.getNodeArr().add(bookToAdd);
				addHashKey(bookToAdd);
				enableComponents();
				this.taOutput.setText("Book: " + bookToAdd.getBookTitle() + 
						" Author " + bookToAdd.getAuthor() + 
						" Genres: " + bookToAdd.getKeywords() + " has been added.");
			}
		}
		else if (e.getSource().equals(this.btnClear)) {
			enableComponents();
			this.taOutput.setText("");
			this.taInfo.setText("");
		}
	}	

}
