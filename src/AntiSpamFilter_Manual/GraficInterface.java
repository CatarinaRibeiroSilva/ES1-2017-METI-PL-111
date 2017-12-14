package AntiSpamFilter_Manual;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class GraficInterface {
	
		//Manual Mode
		private JButton buttonValidar;
		private JButton buttonGravar;
		private JButton buttonValidarAuto;
		private JButton buttonGravarAuto;
		private JTextArea areaManual;
		private JTextArea weightManual;
		private JTextArea areaAuto;
		private JTextArea weigthtAuto;
		
		//Paths
		public JButton pathToSpamButton;
		public JButton pathToHamButton;
		public JButton pathToRulesButton;
		public JTextField spamTextField;
		public JTextField hamTextField;
		public JTextField rulesTextField;
		public LogicClass logic = new LogicClass();
		

		public GraficInterface() {
			JFrame janela = new JFrame("Spam Filter Manual");

			//Textfield To show Path
			spamTextField = new JTextField();
			hamTextField = new JTextField();
			rulesTextField = new JTextField();
			
			//Panel show Path
			JPanel gridPanelForTexts = new JPanel(new GridLayout(3, 1));
			gridPanelForTexts.add(rulesTextField);
			gridPanelForTexts.add(hamTextField);
			gridPanelForTexts.add(spamTextField);

			
			//Button to selectFiles and Path
			pathToSpamButton = new JButton("Select Spam file");
			pathToHamButton = new JButton("Select Ham file");
			pathToRulesButton = new JButton("Select Rules file");
			
			//Panel to select Path and Files
			JPanel gridPanelForButtons = new JPanel(new GridLayout(3, 1));
			gridPanelForButtons.add(pathToRulesButton);
			gridPanelForButtons.add(pathToHamButton);
			gridPanelForButtons.add(pathToSpamButton);

			
			//Panel For TextField and Buttons - Path
			JPanel pathAndButton = new JPanel(new BorderLayout());
			pathAndButton.add(gridPanelForTexts, BorderLayout.CENTER);
			pathAndButton.add(gridPanelForButtons, BorderLayout.EAST);

			
			//ManualMode
			areaManual = new JTextArea();
			areaManual.setEditable(false);
			areaManual.setBackground(Color.WHITE);
			
			weightManual = new JTextArea();
			weightManual.setEditable(false);
			weightManual.setBackground(Color.WHITE);
			
			JSplitPane pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
			pane.setDividerSize(1);
			pane.setDividerLocation(200);
			pane.setLeftComponent(areaManual);
			pane.setRightComponent(weightManual);

			JLabel FP = new JLabel("Faldos Positivos:");
			JTextField FPresult = new JTextField();
			JLabel FN = new JLabel("Falsos Negativos:");
			JTextField FNresult = new JTextField();	
			
			JPanel FResultes = new JPanel(new GridLayout(2,2));
			FResultes.add(FP);
			FResultes.add(FPresult);
			FResultes.add(FN);
			FResultes.add(FNresult);
			
			
			JScrollPane scroll2 = new JScrollPane(pane);
			scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scroll2.setPreferredSize(new Dimension(300, 300));
			 
			JPanel panelForTextArea = new JPanel();
			panelForTextArea.setSize(200, 200);
			panelForTextArea.add(scroll2, BorderLayout.CENTER);
			 
			buttonValidar = new JButton("Validar");
			buttonGravar = new JButton("Gravar");
			JPanel buttonsPanel = new JPanel(new GridLayout(1,2));
			buttonsPanel.add(buttonValidar);
			buttonsPanel.add(buttonGravar);
			
			 			
			 JPanel PanelManualAll = new JPanel(new BorderLayout());
			 PanelManualAll.add(panelForTextArea, BorderLayout.NORTH);
			 PanelManualAll.add(buttonsPanel, BorderLayout.SOUTH);
			 PanelManualAll.add(FResultes, BorderLayout.CENTER);
			 			
			 			
			//ManualMode
			 areaAuto = new JTextArea();
			 areaAuto.setEditable(false);
			 areaAuto.setBackground(Color.WHITE);
				
			 weigthtAuto = new JTextArea();
				weigthtAuto.setEditable(false);
				weigthtAuto.setBackground(Color.WHITE);
				
				JSplitPane paneAuto = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
				paneAuto.setDividerSize(1);
				paneAuto.setDividerLocation(200);
				paneAuto.setLeftComponent(areaAuto);
				paneAuto.setRightComponent(weigthtAuto);

				JLabel FPAuto = new JLabel("Faldos Positivos:");
				JTextField FPresultAuto = new JTextField();
				JLabel FNAuto = new JLabel("Falsos Negativos:");
				JTextField FNresultAuto = new JTextField();	
				
				JPanel FResultesAuto = new JPanel(new GridLayout(2,2));
				FResultesAuto.add(FPAuto);
				FResultesAuto.add(FPresultAuto);
				FResultesAuto.add(FNAuto);
				FResultesAuto.add(FNresultAuto);
				
				
				JScrollPane scrollAuto = new JScrollPane(paneAuto);
				scrollAuto.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				scrollAuto.setPreferredSize(new Dimension(300, 300));
				 
				JPanel panelForTextAreaAuto = new JPanel();
				panelForTextAreaAuto.setSize(200, 200);
				panelForTextAreaAuto.add(scrollAuto, BorderLayout.CENTER);
				 
				buttonValidarAuto = new JButton("Validar");
				buttonGravarAuto = new JButton("Gravar");
				JPanel buttonsPanelAuto = new JPanel(new GridLayout(1,2));
				buttonsPanelAuto.add(buttonValidarAuto);
				buttonsPanelAuto.add(buttonGravarAuto);
				
				 			
				 JPanel PanelAutoAll = new JPanel(new BorderLayout());
				 PanelAutoAll.add(panelForTextAreaAuto, BorderLayout.NORTH);
				 PanelAutoAll.add(buttonsPanelAuto, BorderLayout.SOUTH);
				 PanelAutoAll.add(FResultesAuto, BorderLayout.CENTER);
				 			

			janela.setSize(650, 500);
			janela.setResizable(false);
			janela.setLocation(100, 100);
			janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			janela.add(pathAndButton, BorderLayout.NORTH);
			janela.add( PanelManualAll, BorderLayout.EAST);
			janela.add(PanelAutoAll, BorderLayout.WEST);
			janela.setVisible(true);

		
		pathToSpamButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					spamTextField.setEditable(true);
					spamTextField.setText(logic.getFile().getAbsolutePath());
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		});
		
		pathToHamButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {hamTextField.setEditable(true);
				hamTextField.setText(logic.getFile().getAbsolutePath());
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		});

		pathToRulesButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					rulesTextField.setEditable(true);
					rulesTextField.setText(logic.getFile().getAbsolutePath());
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		});
		
		}
}	