import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class FrmPrincipal extends JFrame{
	private JButton btr;
	Color Fundo = new Color(225, 228, 241); 
	
	public FrmPrincipal(){
		
		// TELA PRINCIPAL
		
		 
		
		super ("Principal");
		this.setSize(1024, 800);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(Fundo);
		
		Container janelacMenu = this.getContentPane();
		setLocationRelativeTo(janelacMenu);
		janelacMenu.setLayout(null);

		btr = new JButton();
		btr.setBounds (300,200,400,300);
		btr.setText("R Passa !");
		janelacMenu.add(btr);
		
		
		// BOTOES DO MENU //
		
		JMenu arq = new JMenu ("Arquivo");
		JMenu cad = new JMenu ("Cadastrar");
		
		JMenuItem sair = new JMenuItem ("Sair");
		JMenuItem med = new JMenuItem ("Médico");
		JMenuItem cli = new JMenuItem ("Cliente");
		
		arq.add(sair);
		cad.add(med);
		cad.add(cli);
		
		JMenuBar bar = new JMenuBar ();
		setJMenuBar(bar);
		
		bar.add(arq);
		bar.add(cad);
		
		// AÇOES DOS BOTOES '-'
		
		sair.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
						FrmPrincipal.this.dispose();
						System.exit(0);
					}
				}	
		);
		
		
		cli.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
						FormularioCliente fc;
						try {
							fc = new FormularioCliente();
							fc.setVisible(true);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				}	
		);
	}
	
	
}
