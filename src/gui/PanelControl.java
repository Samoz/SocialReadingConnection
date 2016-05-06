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
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.google.gson.Gson;

import graphDatabase.BookNode;
import graphDatabase.Graph;
import search.HashMapAuthor;
import search.HashMapGenre;
import search.HashMapTitle;

public class PanelControl extends JPanel implements ActionListener {

	private Graph theGraph;
	private HashMapAuthor hashAuthor;
	private HashMapTitle hashTitle;
	private HashMapGenre hashGenre;

	private int genreCounter;

	private String[] arrGenres;

	private JLabel lbBook;
	private JLabel lbGenre;
	private JLabel lbOutput;
	private JLabel lbInfo;
	private JLabel lbSearch;
	private JLabel lbCounter;
	private JLabel lbAuthorFN;
	private JLabel lbAuthorLN;

	private JTextArea taOutput;
	private JTextArea taInfo;

	private JTextField tfBook;
	private JTextField tfGenre;
	private JTextField tfFirstName;
	private JTextField tfLastName;

	private JButton btnGenre;
	private JButton btnAdd;
	private JButton btnSearch;
	private JButton btnRelated;
	private JButton btnCancel;
	private JButton btnAuthor;
	private JButton btnRelAuthor;
	private JButton btnSearchGenre;
	private JButton btnShowBookList;

	private JScrollPane spOutput;

	public PanelControl() {
		this.hashAuthor = new HashMapAuthor();
		this.hashTitle = new HashMapTitle();
		this.hashGenre = new HashMapGenre();
		this.theGraph = new Graph();
		this.genreCounter = 0;
		this.arrGenres = new String[3];
		this.setPreferredSize(new Dimension(600, 600));
		this.setLayout(null);
		addComponentsToPanel();
		setBoundsToComponents();
		setComponentsCharacteristics();
		addActionsToButtons();
		loadBooks();
	}

	public void addNodeToGraph() {
		BookNode bNodeTemp = new BookNode(this.tfBook.getText(), this.tfFirstName.getText(), this.tfLastName.getText());
		bNodeTemp.addKeywords(this.arrGenres[2], this.arrGenres[1], this.arrGenres[0]);
		this.theGraph.addNode(bNodeTemp);
		addHashKey(bNodeTemp);
	}

	public void addHashKey(BookNode bookNode) {
		this.hashTitle.put(bookNode);
		this.hashAuthor.put(bookNode);
		this.hashGenre.put0(bookNode);
		this.hashGenre.put1(bookNode);
		this.hashGenre.put2(bookNode);
	}

	public BookNode searchForBook(String bookTitle) {
		return hashTitle.get(bookTitle);
	}

	/*public String searchByGenre(String genre) {
		String bookInfo = "";
		this.nodeArr = this.hashGenre.getGenre2(genre);
		for (BookNode bookNode : this.nodeArr) {
			bookInfo = bookInfo + bookNode.toString() + "\n";
		}
		return bookInfo;
	}

	public void showNodes() {
		this.nodeArr.iterator();
	}*/

	public String getBookName() {
		return this.tfBook.getText();
	}

	public String[] getArrGenres() {
		return this.arrGenres;
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
		this.lbCounter = new JLabel("Left: " + this.genreCounter);
		this.lbAuthorFN = new JLabel("Author's First Name:");
		this.lbAuthorLN = new JLabel("Author's Last Name:");
		this.taOutput = new JTextArea();
		this.taInfo = new JTextArea();
		this.tfBook = new JTextField();
		this.tfGenre = new JTextField();
		this.tfFirstName = new JTextField();
		this.tfLastName = new JTextField();
		this.btnGenre = new JButton("Add Genre");
		this.btnAdd = new JButton("Add Book");
		this.btnSearch = new JButton("Search for Book");
		this.btnRelated = new JButton("Show Related Books");
		this.btnCancel = new JButton("Cancel");
		this.btnAuthor = new JButton("Set Author");
		this.btnRelAuthor = new JButton("Show Related Authors");
		this.btnSearchGenre = new JButton("Search By Genre");
		this.btnShowBookList = new JButton("Show Books");
		this.add(this.lbBook);
		this.add(this.lbGenre);
		this.add(this.lbOutput);
		this.add(this.lbInfo);
		this.add(this.lbSearch);
		this.add(this.lbCounter);
		this.add(this.lbAuthorFN);
		this.add(this.lbAuthorLN);
		this.add(this.taOutput);
		this.add(this.taInfo);
		this.add(this.tfBook);
		this.add(this.tfGenre);
		this.add(this.tfFirstName);
		this.add(this.tfLastName);
		this.add(this.btnGenre);
		this.add(this.btnAdd);
		this.add(this.btnSearch);
		this.add(this.btnRelated);
		this.add(this.btnCancel);
		this.add(this.btnAuthor);
		this.add(this.btnRelAuthor);
		this.add(this.btnSearchGenre);
		this.add(this.btnShowBookList);
		this.add(this.taOutput);
		this.spOutput = new JScrollPane(this.taInfo);
		this.add(spOutput);
		this.spOutput.setVisible(true);		
	}

	public void setBoundsToComponents() {
		this.lbBook.setBounds(10, 10, this.lbBook.getPreferredSize().width, 30);
		this.tfBook.setBounds(this.lbBook.getX() + this.lbBook.getWidth(), 10, this.getPreferredSize().width - this.lbBook.getWidth() - 20, 30);
		this.btnAdd.setBounds(this.tfBook.getX() - 30, this.lbBook.getY() + this.lbBook.getHeight(), this.btnAdd.getPreferredSize().width, 30);
		this.btnSearch.setBounds(this.btnAdd.getX() + this.btnAdd.getWidth(), this.btnAdd.getY(), this.btnSearch.getPreferredSize().width, 30);
		this.btnRelated.setBounds(this.btnSearch.getX() + this.btnSearch.getWidth(), this.btnAdd.getY(), this.btnRelated.getPreferredSize().width, 30);
		this.btnShowBookList.setBounds(this.btnRelated.getX() + this.btnRelated.getWidth(), this.btnRelated.getY(), this.btnShowBookList.getPreferredSize().width, this.btnShowBookList.getPreferredSize().height);
		this.lbAuthorFN.setBounds(this.lbBook.getX(), this.btnAdd.getY() + this.btnAdd.getHeight(), this.lbAuthorFN.getPreferredSize().width, 30);
		this.tfFirstName.setBounds(this.lbAuthorFN.getX() + this.lbAuthorFN.getWidth(), this.lbAuthorFN.getY(), 162, 30);
		this.lbAuthorLN.setBounds(this.tfFirstName.getX() + this.tfFirstName.getWidth(), this.lbAuthorFN.getY(), this.lbAuthorLN.getPreferredSize().width, 30);
		this.tfLastName.setBounds(this.lbAuthorLN.getX() + this.lbAuthorLN.getWidth(), this.lbAuthorLN.getY(), 162, 30);
		this.btnAuthor.setBounds(this.lbBook.getX() + 150, this.lbAuthorFN.getY() + this.lbAuthorFN.getHeight(), this.btnAuthor.getPreferredSize().width, 30);
		this.btnRelAuthor.setBounds(this.btnAuthor.getX() + this.btnAuthor.getWidth(), this.btnAuthor.getY(), this.btnRelAuthor.getPreferredSize().width, 30);
		this.lbGenre.setBounds(this.lbAuthorFN.getX(), this.btnAuthor.getY() + this.btnAuthor.getHeight(), this.lbGenre.getPreferredSize().width, 30);
		this.tfGenre.setBounds(this.lbGenre.getX() + this.lbGenre.getWidth(), this.lbGenre.getY(), this.getPreferredSize().width - this.lbGenre.getWidth() - 80, 30);
		this.lbCounter.setBounds(this.tfGenre.getX() + this.tfGenre.getWidth(), this.tfGenre.getY(), this.lbCounter.getPreferredSize().width, 30);
		this.btnSearchGenre.setBounds(this.tfGenre.getX() + 70, this.lbGenre.getY() + this.lbGenre.getHeight(), this.btnSearchGenre.getPreferredSize().width, 30);
		this.btnGenre.setBounds(this.btnSearchGenre.getX() + this.btnSearchGenre.getWidth(), this.lbGenre.getY() + this.lbGenre.getHeight(), this.btnGenre.getPreferredSize().width, 30);
		this.btnCancel.setBounds(this.lbCounter.getX() - 20, this.btnGenre.getY(), this.btnCancel.getPreferredSize().width, 30);
		this.lbInfo.setBounds(this.lbGenre.getX(), this.btnGenre.getY() + this.btnGenre.getHeight(), this.lbInfo.getPreferredSize().width, 30);
		this.spOutput.setBounds(this.lbInfo.getX(), this.lbInfo.getY() + this.lbInfo.getHeight(), this.getPreferredSize().width - 20, 300);
		this.taOutput.setBounds(10, this.getPreferredSize().height - 50, this.getPreferredSize().width - 20, 40);
		this.lbOutput.setBounds(this.taOutput.getX(), this.taOutput.getY() - 30, this.lbOutput.getPreferredSize().width, 30);
	}

	public void setComponentsCharacteristics() {
		this.taInfo.setEditable(false);
		this.taOutput.setEditable(false);
		this.btnAuthor.setEnabled(false);
		this.btnGenre.setEnabled(false);
		this.btnCancel.setEnabled(false);
		this.lbCounter.setForeground(Color.GRAY);
		this.taInfo.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		this.taOutput.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
	}

	public void enableComponents() {
		this.tfBook.setText("");
		this.tfFirstName.setText("");
		this.tfLastName.setText("");
		this.tfGenre.setText("");
		this.taInfo.setText("");
		this.tfBook.setEditable(true);
		this.tfFirstName.setEditable(true);
		this.tfLastName.setEditable(true);
		this.btnAdd.setEnabled(true);
		this.btnSearch.setEnabled(true);
		this.btnRelated.setEnabled(true);
		this.btnAuthor.setEnabled(false);
		this.btnRelAuthor.setEnabled(true);
		this.btnGenre.setEnabled(false);
		this.btnCancel.setEnabled(false);
		this.btnSearchGenre.setEnabled(true);
		this.btnShowBookList.setEnabled(true);
	}

	public void addActionsToButtons() {
		this.btnAdd.addActionListener(this);
		this.btnSearch.addActionListener(this);
		this.btnRelated.addActionListener(this);
		this.btnGenre.addActionListener(this);
		this.btnCancel.addActionListener(this);
		this.btnAuthor.addActionListener(this);
		this.btnRelAuthor.addActionListener(this);
		this.btnSearchGenre.addActionListener(this);
		this.btnShowBookList.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(this.btnAdd)) {
			this.taOutput.setText("");
			if (this.tfBook.getText().equals("")) {
				this.taOutput.setText("Please Enter a Valid Book Name");
			}
			else {
				this.tfBook.setEditable(false);
				this.tfGenre.setEditable(true);
				this.btnAuthor.setEnabled(true);
				this.btnCancel.setEnabled(true);
				this.btnAdd.setEnabled(false);
				this.btnSearch.setEnabled(false);
				this.btnRelated.setEnabled(false);
				this.btnRelAuthor.setEnabled(false);
				this.btnSearchGenre.setEnabled(false);
				this.btnShowBookList.setEnabled(false);
			}
		}
		else if (e.getSource().equals(this.btnSearch)) {
			this.taOutput.setText("");
			if (this.tfBook.getText().equals("")) {
				this.taOutput.setText("Please Enter a Valid Book Name");
			}
			else {
				this.taInfo.setText("");
				BookNode tempBook = searchForBook(this.tfBook.getText());
				if (tempBook != null) {
					this.taInfo.setText(tempBook.toString());
				}
				else {
					this.taOutput.setText("Book not found");
				}
			}
		}
		else if (e.getSource().equals(this.btnRelated)) {
			this.taOutput.setText("");
			if (this.tfBook.getText().equals("")) {
				this.taOutput.setText("Please Enter a Valid Book Name");
			}
			else {
				BookNode tempBook = searchForBook(this.tfBook.getText());
				if (tempBook != null) {
					List<BookNode> tempSimilarBooks = tempBook.getSimilarBooks();
					if (tempSimilarBooks != null && tempSimilarBooks.size() > 0) {
						this.taInfo.setText("");
						for (BookNode bookNode : tempSimilarBooks) {
							this.taInfo.append(bookNode.toString() + "\n");
						}
					}
					else {
						this.taOutput.setText("No similar books");
					}
				}
				else {
					this.taOutput.setText("No book found");
				}
			}
		}
		else if (e.getSource().equals(this.btnShowBookList)) {
			for (BookNode bookNode : this.theGraph.getNodeArr()) {
				this.taInfo.append(bookNode.toString() + "\n");
			}
		}
		else if (e.getSource().equals(this.btnAuthor)) {
			this.taOutput.setText("");
			if (this.tfFirstName.getText().equals("") || this.tfLastName.getText().equals("")) {
				this.taOutput.setText("Please Enter a Valid Author Name");

			}
			else {
				this.tfFirstName.setEditable(false);
				this.tfLastName.setEditable(false);
				this.btnGenre.setEnabled(true);
				this.btnAuthor.setEnabled(false);
				this.genreCounter = 3;
				this.lbCounter.setText("Left: " + this.genreCounter);
			}
		}

		else if (e.getSource().equals(this.btnRelAuthor)) {
			this.taOutput.setText("");
			if (this.tfFirstName.getText().equals("") || this.tfLastName.getText().equals("")) {
				this.taOutput.setText("Please Enter a Valid Author Name");
			}
		}
		else if (e.getSource().equals(this.btnSearchGenre)) {
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
			this.btnRelated.setEnabled(false);
			this.btnRelAuthor.setEnabled(false);
			this.btnSearch.setEnabled(false);
			this.btnCancel.setEnabled(true);
			if (this.tfGenre.equals("")) {
				this.taOutput.setText("Please enter a valid genre");
			}
		}
		else if (e.getSource().equals(this.btnGenre)) {
			this.taOutput.setText("");
			if (this.tfGenre.getText().equals("")) {
				this.taOutput.setText("Please Enter a Valid Genre");
			}
			else {
				this.arrGenres[this.genreCounter - 1] = this.tfGenre.getText();
				this.genreCounter--;
				this.lbCounter.setText("Left: " + this.genreCounter);
				this.tfGenre.setText(null);
			}

			if (this.genreCounter < 1) {
				addNodeToGraph();
				this.taOutput.setText("The Node:\n Book: " + this.tfBook.getText() + 
						" Author: " + this.tfFirstName.getText() + " " + this.tfLastName.getText() + 
						" Genres: " + this.arrGenres[2] + ", " + this.arrGenres[1] + ", " + this.arrGenres[0] +
						" has been added.");
				enableComponents();
			}
		}
		else if (e.getSource().equals(this.btnCancel)) {
			enableComponents();
			this.taOutput.setText("");
			this.taInfo.setText("");
			this.arrGenres = null;
			this.genreCounter = 0;
		}
	}	

	public void matchBook(String genreToSearch) {
		List<String> genres= new ArrayList<String>();
		genres.add("Fiction");
		genres.add("Horror");
		genres.add("Romance");
		genres.add("Mystery");
		genres.add("Sci-Fi");
		genres.add("Fiction");
		genres.add("Drama");
		if (genres.contains(genreToSearch)) {
			this.taOutput.setText(genreToSearch + " found");
		}
	}
}
