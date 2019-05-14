import java.awt.EventQueue;
import javafx.event.*;
import javafx.scene.*;
import javafx.application.*;
import javafx.stage.*;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Window.Type;

public class HuffmanGUI{
	Huffman obj=new Huffman();
	private JFrame frmHuffman;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HuffmanGUI window = new HuffmanGUI();
					window.frmHuffman.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HuffmanGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHuffman = new JFrame();
		frmHuffman.setTitle(" Huffman");
		frmHuffman.getContentPane().setBackground(Color.PINK);
		frmHuffman.getContentPane().setForeground(Color.PINK);
		frmHuffman.getContentPane().setLayout(null);
		
		JButton Compress = new JButton("Compress");
		Compress.setFont(new Font("Tahoma", Font.BOLD, 11));
		Compress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				obj.Compress();
				JOptionPane.showMessageDialog(null,"Done Compressing");
			}
		});
		Compress.setBounds(48, 112, 108, 37);
		frmHuffman.getContentPane().add(Compress);
		
		JButton Decompress = new JButton("DeCompress");
		Decompress.setFont(new Font("Tahoma", Font.BOLD, 11));
		Decompress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				obj.Decompress();
				JOptionPane.showMessageDialog(null,"Done DeCompressing");
			}
		});
		Decompress.setBounds(274, 112, 108, 37);
		frmHuffman.getContentPane().add(Decompress);
		
		JLabel label = new JLabel("_______________");
		label.setBounds(178, 218, 94, 14);
		frmHuffman.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("...");
		label_1.setBounds(214, 174, 46, 14);
		frmHuffman.getContentPane().add(label_1);
		frmHuffman.setBounds(100, 100, 450, 300);
		frmHuffman.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
