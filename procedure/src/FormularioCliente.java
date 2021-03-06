import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.MaskFormatter;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JRDesignViewer;
import net.sf.jasperreports.view.JasperViewer;



public class FormularioCliente extends JDialog{
	private String ver, email, data, cpf;
	private int r;
	private JLabel lblPesquisa, lblNome, lblDtnasc, lblSexo, lblCpf, lblEstCivil, lblEnd, lblNum, lblCep, lblBairro, lblCidade, lblTel, lblEMail, lblRg;
	private JRadioButton rbMasc, rbFem;
	private JTextField txtPesquisa, txtNome, txtDtnasc, txtCpf, txtEstCivil, txtEnd , txtNum, txtCep, txtBairro, txtCidade, txtTel, txtEMail, txtRg;
	private JButton btnCadastrar, btnPesquisar, btnLimpar, btnAtualizar, btnDeletar,btnRelatorio;
	private JComboBox cbPesquisar;
	Validacao v = new Validacao();
	MaskFormatter mskData, mskCpf;
	Conexao conex = new Conexao();
	int resposta,codigo,contador=20;
	String verificar = "";
	Color Fundo = new Color(200, 225, 241);  
	Color Erro = new Color(200, 0, 0);  
	
	   //***JTable****
	   
	   static String sql = "select  nome,rg,cpf,email,endereco,tel,cidade,cep,bairro,estado,estadocivil,dtNasc    from tbCliente";	      
	   private JTable tabelaCliente;
	   private JScrollPane scrollPane;   
	   //no construtor passar a uma String sql para ser exibida na JTable
	   private ModeloDeTabela mt = new ModeloDeTabela(sql);

	
	
	
	ButtonGroup bg; 
	public FormularioCliente() throws ParseException{
		this.setTitle("Formul?rio de Cadastro de Clientes");
		this.setModal(true);
		this.setSize(800,500);
		this.setResizable(false);
		FormularioCliente.this.dispose();
		this.getContentPane().setBackground(Fundo);
		//
		//Add
		//
		Container form = this.getContentPane();
		setLocationRelativeTo(form);
		form.setLayout(null);
		//
		//Label Text NomeCli
		//
		lblNome = new JLabel("Nome :");
		lblNome.setBounds(15,(50+contador),40,22);
		form.add(lblNome);
		//
		txtNome = new JTextField("");
		txtNome.setBounds(110,(50+contador),200,22);
		form.add(txtNome);
		//
		//Label TEXT PESQUISANDO - E COMBO BOX
		//
		lblPesquisa = new JLabel("Pesquisar :");
		lblPesquisa.setBounds(160,4,80,22);
		form.add(lblPesquisa);
		//
		txtPesquisa = new JTextField("");
		txtPesquisa.setBounds(240,30,200,22);
		form.add(txtPesquisa);
		//
		cbPesquisar = new JComboBox();
		cbPesquisar.setBounds(240, 4, 200, 22);
		cbPesquisar.addItem("CPF");
		cbPesquisar.addItem("Nome");
		form.add(cbPesquisar);
		//
		//Label Text DataNascCli
		//
		lblDtnasc = new JLabel("Data de Nascimento :");
		lblDtnasc.setBounds(360,(50+contador),150,22);
		form.add(lblDtnasc);
		//
		mskData = new MaskFormatter ("####/##/##");
		mskData.setPlaceholderCharacter('_');
		txtDtnasc = new JFormattedTextField(mskData);
		txtDtnasc.setBounds(500,(50+contador),60,22);
		form.add(txtDtnasc);
		//
		//Label Text EstCivil
		//
		lblEstCivil = new JLabel("Estado Civil :");
		lblEstCivil.setBounds(15,(80+contador),150,22);
		form.add(lblEstCivil);
		//
		txtEstCivil = new JTextField("");
		txtEstCivil.setBounds(110,(80+contador),200,22);
		form.add(txtEstCivil);
		//
		//Label Text EndCli
		//
		lblEnd = new JLabel("Endere?o :");
		lblEnd.setBounds(360,(80+contador),150,22);
		form.add(lblEnd);
		//
		txtEnd = new JTextField("");
		txtEnd.setBounds(500,80+(contador),200,22);
		form.add(txtEnd);
		//
		//Label Text NumCli
		//
		lblNum = new JLabel("N? :");
		lblNum.setBounds(360,(110+contador),150,22);
		form.add(lblNum);
		//
		txtNum = new JTextField("");
		txtNum.setBounds(500,(110+contador),200,22);
		form.add(txtNum);
		//
		//Label Text CepCli
		//
		lblCep = new JLabel("CEP :");
		lblCep.setBounds(360,(140+contador),150,22);
		form.add(lblCep);
		//
		txtCep = new JTextField("");
		txtCep.setBounds(500,(140+contador),200,22);
		form.add(txtCep);
		//
		//Label Text BairroCli
		//
		lblBairro = new JLabel("Bairro :");
		lblBairro.setBounds(360,(170+contador),150,22);
		form.add(lblBairro);
		//
		txtBairro = new JTextField("");
		txtBairro.setBounds(500,(170+contador),200,22);
		form.add(txtBairro);
		//
		//Label Text CidadeCli
		//
		lblCidade = new JLabel("Cidade :");
		lblCidade.setBounds(360,(200+contador),150,22);
		form.add(lblCidade);
		//
		txtCidade = new JTextField("");
		txtCidade.setBounds(500,(200+contador),200,22);
		form.add(txtCidade);
		//
		//Label Text TelCli
		//
		lblTel = new JLabel("Telefone :");
		lblTel.setBounds(15,(110+contador),150,22);
		form.add(lblTel);
		//
		txtTel = new JTextField("");
		txtTel.setBounds(110,(110+contador),200,22);
		form.add(txtTel);
		//
		//Label Text EMailCli
		//
		lblEMail = new JLabel("E-Mail :");
		lblEMail.setBounds(15,(140+contador),150,22);
		form.add(lblEMail);
		//
		txtEMail = new JTextField("");
		txtEMail.setBounds(110,(140+contador),200,22);
		form.add(txtEMail);
		//
		//Label Text RgCli
		//
		lblRg = new JLabel("RG :");
		lblRg.setBounds(15,(170+contador),150,22);
		form.add(lblRg);
		//
		txtRg = new JTextField("");
		txtRg.setBounds(110,(170+contador),200,22);
		form.add(txtRg);
		
		//
		//Label Radio SexoCli
		//
		lblSexo = new JLabel("Sexo :");
		lblSexo.setBounds(15,(200+contador),150,22);
		form.add(lblSexo);
		//
		bg = new ButtonGroup ();
		rbMasc = new JRadioButton ("Masculino");
		bg.add(rbMasc);
		rbMasc.setBounds(110,(200+contador),90,22);
		form.add(rbMasc);
		//
		rbFem = new JRadioButton ("Feminino");
		rbFem.setBounds(200,(200+contador),100,22);
		bg.add(rbFem);
		form.add(rbFem);
		//
		//Label Text CpfCli
		//
		lblCpf = new JLabel("Cpf :");
		lblCpf.setBounds(15,(230+contador),150,22);
		form.add(lblCpf);
		//
		mskCpf = new MaskFormatter ("###.###.###-##");
		mskCpf.setPlaceholderCharacter('_');
		txtCpf = new JFormattedTextField(mskCpf);
		txtCpf.setBounds(110,(230+contador),200,22);
		form.add(txtCpf);
		
		
		
		//JTable 
	     tabelaCliente = new JTable( mt ); 
	     scrollPane = new JScrollPane();
	     //para ajustar o tamanho da coluna
	     //tabelaCliente.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);     
	     tabelaCliente.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	     scrollPane.setViewportView(tabelaCliente); 
	     scrollPane.setBounds(15,275,520,100);
	     form.add(scrollPane);

		//bot?o relat?rio
	     
	        btnRelatorio  = new JButton();
	        btnRelatorio.setBounds(675,360,95,22);
	        btnRelatorio.setText("Relatorio");
	        btnRelatorio.setToolTipText("Ir? realizar o cadastro");
			form.add(btnRelatorio);
	     //
		
			btnRelatorio.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				
				
				//Controla e executa uma  instru??o sql

				Statement meustate;

				//conjunto de dados retornando retornado por uma consulta sql
				ResultSet resultset;

				//instancia o objeto conex da classe Conexao
				Conexao conex = new Conexao();

				String sql = "SELECT * FROM TbProduto";
				conex.Conectar();
				
				try{

				meustate = Conexao.con.createStatement();
				resultset = meustate.executeQuery(sql);

				//para compilar o relat?rio
				JasperReport r =JasperCompileManager.compileReport("relatorios\\RelatorioProduto.jrxml");

				//objeto para colocar no jasper report o resultado retornado
				JRResultSetDataSource jrresultado = new JRResultSetDataSource(resultset);

				//para passar par?metros para o relat?rio
				HashMap parametermap = new HashMap();
				

				//chama o fill report
				JasperPrint jp = JasperFillManager.fillReport(r,parametermap,jrresultado);

				//para gerar relat?rio em pdf
				JasperExportManager.exportReportToPdfFile(jp,"relatorios\\RelatorioProduto.pdf");

				//para exibi??o do relat?rio
				JasperViewer js = new JasperViewer (jp, false);

				int janelaRelat = js.getExtendedState();

				janelaRelat = js.MAXIMIZED_BOTH;
			
				js.setExtendedState(janelaRelat);
				js.setVisible(true);
			
				}catch (SQLException erro){
					
				System.out.println("Relat?rio n?o encontrado");
				
				}
			
				catch (JRException erro){
					
					conex.Fechar();
					erro.printStackTrace();
			
				}
			
				conex.Fechar();
			
		
		
		
				}
				});
				
		
		
		
		
		
		
		//
		/////////////////////////	 BOTOES E SUAS A??ES .... /////////////////
		//
		//
		///////////////////////// 	BOTAO CADASTRAR / A??O /////////////////////////
		//
		btnCadastrar = new JButton();
		btnCadastrar.setBounds(675,250,95,22);
		btnCadastrar.setText("Cadastrar");
		btnCadastrar.setToolTipText("Ir? realizar o cadastro");
		form.add(btnCadastrar);
		//
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// PEGANDO VALOR DAS TXT DATA, EMAIL, NOME E CPF ...			
				ver = txtNome.getText().trim();
				email = txtEMail.getText().trim();
				data = txtDtnasc.getText().trim();
				cpf = txtCpf.getText().trim();
				
				 // VERIFICANDO SE OS CAMPOS FORAM PREENCHIDOS E EXIBINDO RESULTADOS
				
				if(v.verificacaoNome(ver)==true){ // VERIFICANDO NOME
					
					if (v.verificacaoData(data)==true){ // VERIFICANDO DATA DE NASCIMENTO
						
						if (v.verificacaoEmail(email)==true){ // VERIFICANDO EMAIL
									
							conex.Conectar();
							
							// PEGANDO CONTEUDO DAS TEXT'S PARA PODER GUARDAR NO BANCO
							
							String nome = txtNome.getText();
							String civil = txtEstCivil.getText();
							String endereco = txtEnd.getText();
							String numEnd = txtNum.getText();
							String cep = txtCep.getText();
							String bairro = txtBairro.getText();
							String cidade = txtCidade.getText();
							String telefone = txtTel.getText();
							String email = txtEMail.getText();
							String rg = txtRg.getText();
							String sexo = "" ;
							String cpf = txtCpf.getText();
							
							// AQUI DIVIDO A DATA E DENTRO DA 'dataCli' EU MONTO NO FORMATO AMERICANO
							String dia = txtDtnasc.getText().substring(0,2);
							String mes = txtDtnasc.getText().substring(3,5);
							String ano = txtDtnasc.getText().substring(6);
							String dataNasc = ano + "/" +mes+ "/" +dia;	
							
							// STRING COM O COMANDO SQL
							
							if(rbMasc.isSelected() == true){
								
								sexo = rbMasc.getText();
															
							}else if (rbFem.isSelected() == true) {
								
								sexo = rbFem.getText();

									
							}
							
							String comando = ("INSERT INTO tbCliente VALUES (null, '"+nome+"', '"+sexo+"', '"+cpf+"', '"+rg+"', '"+civil+"', '"+dataNasc+"', '"+telefone+"', '"+email+"', '"+endereco+"', '"+numEnd+"', '"+cep+"', '"+bairro+"', '"+cidade+"', '"+cidade+"') ");						
							
							try {				
								
								// INSERINDO NO BANCO E FECHANDO !
										
								java.sql.Statement stmt = conex.con.createStatement();
								stmt.executeUpdate(comando);
								stmt.close();
								conex.con.close();
								
								JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso.");
								
								// LIMPANDO AS TEXT'S
								
								for (int i=0; i < getContentPane().getComponentCount(); i++) {  
				                    Component c = getContentPane().getComponent(i);  
				                    if (c instanceof JTextField) {  
				                        JTextField field = (JTextField) c;  
				                        field.setText("");  
				                        // field.enable(false);
				                        
				                    } // FIM DO IF  
				                    
				                } // FIM DO LA?O 

								
							}catch (SQLException | HeadlessException e1){
								
								System.out.println("ERRO" + e1.getMessage());
								txtEMail.setText("loool");
								
							} // FIM CATCH
										
							
						} // FIM VERIFICA??O EMAIL
						
					} // FIM VERIFICA??O DATA NASCIMENTO				
					
				} // FIM VERIFICA??O NOME
				
				conex.Fechar();
				
				for (int i=0; i < getContentPane().getComponentCount(); i++) { 
		            Component c = getContentPane().getComponent(i);  
		            
		            if (c instanceof JTextField) {  
		            	
		                JTextField field = (JTextField) c;    
		                field.enable(false);
		                field.setText("");
		                
		            }  // FIM DO IF
		            
		        }  // FIM DO LA?O
				
	            txtPesquisa.requestFocus();
	            txtPesquisa.setEnabled(true);
	            btnCadastrar.setEnabled(true);
	            btnPesquisar.setEnabled(true);
	            rbFem.setEnabled(true);
	            rbMasc.setEnabled(true);
	            cbPesquisar.setEnabled(true);
				
	            
			} // FIM BOTAO CADASTRAR
			
		}); // FIM BOTAO 2 E PRINCIPAL
		//
		///////////////////////// 	BOTAO PESQUISAR / A??O	 /////////////////////////
		//
		btnPesquisar = new JButton();
		btnPesquisar.setBounds(444,30,100,22);
		btnPesquisar.setText("Pesquisar");
		btnPesquisar.setToolTipText("Pesquisa todos os cadastros");
		form.add(btnPesquisar);
		//
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Statement meustate;
				
				Statement meustate2;
				
				ResultSet resultset;
				
				ResultSet resultset2;
				
				conex.Conectar();
				
				String sql = "";

				String sql2 = "SELECT DATE_FORMAT(dtNasc, '%d/%m/%Y') from tbCliente where nome = '"+txtPesquisa.getText()+"' ";
				
				
				if(cbPesquisar.getSelectedItem() == "Nome"){
					
					sql = "call p_nome('"+txtPesquisa.getText()+"') " ;
					
					
		
							
					
				}else if (cbPesquisar.getSelectedItem() == "CPF"){
					
					sql = "call p_cpf'"+txtPesquisa.getText()+"' " ; 
					
				} // FIM DO IF/ELSE
				
				try{
					
					meustate = Conexao.con.createStatement();
					resultset = meustate.executeQuery(sql);
					
					while(resultset.next()){
						
						verificar = resultset.getString(2);
						System.out.println(resultset.getString(2));
						
					} // FIM DO WHILE DO VERIFICAR
					
					resultset.first(); // joga o resultset para o primeiro resultado novamente ... porque acima ele rodou todos ! (eu acho)
					
					if(verificar == ""){
						
						System.out.println(" banco vazio");
						
						resposta = JOptionPane.showConfirmDialog( null," Cliente N?o Cadastrado \n Deseja Cadastra-lo?", "N?o cadastrado",JOptionPane.YES_NO_OPTION);
						
						if(resposta == 0){
							
							System.out.println("Voc? clicou no sim!");
							
							String a = txtPesquisa.getText();
							
							for (int i=0; i < getContentPane().getComponentCount(); i++) { 
					            Component c = getContentPane().getComponent(i);  
					            
					            if (c instanceof JTextField) {  
					            	
					                JTextField field = (JTextField) c;    
					                field.enable(true);
					                field.setText("");
					                
					            }  // FIM DO IF
					            
					        }  // FIM DO LA?O
							
				            txtNome.setText(a);
				            txtPesquisa.setText("");
				            txtPesquisa.setEnabled(false);
				            txtNome.requestFocus();
				            btnCadastrar.setEnabled(true);
				            btnPesquisar.setEnabled(false);
				            rbFem.setEnabled(true);
				            rbMasc.setEnabled(true);
				            cbPesquisar.setEnabled(false);
							
						}else{ // ELSE RESPOSTA
							
							System.out.println("Voc? clicou em n?o");
							txtPesquisa.requestFocus();
							txtPesquisa.setText("");
							
						} // FIM ELSE/IF DA RESPOSTA (IF)
						
					}else{ // ELSE DO VERIFICAR
						
						System.out.println("Encontramos registros no banco");
						
						meustate = Conexao.con.createStatement();
						resultset = meustate.executeQuery(sql);
						
						while(resultset.next()){
							
							System.out.println(resultset.getString(1));
							codigo = resultset.getInt(1);
							
							txtNome.setText((resultset.getString(2)));
							
							txtCpf.setText((resultset.getString(4)));
							
							txtRg.setText((resultset.getString(5)));
							
							txtEstCivil.setText((resultset.getString(6)));	
							
							txtTel.setText((resultset.getString(8)));
							
							txtEMail.setText((resultset.getString(9)));
							
							txtEnd.setText((resultset.getString(10)));
							
							txtNum.setText((resultset.getString(11)));
							
							txtCep.setText((resultset.getString(12)));
							
							txtBairro.setText((resultset.getString(13)));
							
							txtCidade.setText((resultset.getString(14)));
							
							// txtEstado.setText((resultset.getString(15))); TEXT DE ESTADO ... CRIAR ... CRIAR
							
							if(resultset.getString(3).equalsIgnoreCase("MASCULINO")){
								
								rbMasc.setSelected(true);
								
							}else if(resultset.getString(3).equalsIgnoreCase("FEMININO")){
								
								rbFem.setSelected(true);
								
							} // FIM DA VERIFICACAO SEXO
							
						} // FIM DO WHILE PARA DADOS GERAIS
						
						meustate2 = Conexao.con.createStatement();
						resultset2 = meustate2.executeQuery(sql2);
						
						while(resultset2.next()){
							
							txtDtnasc.setText((resultset2.getString(1)));
		
						} // FIM DO WHILE PARA DATA
						
						txtPesquisa.requestFocus();
						
						resposta = JOptionPane.showConfirmDialog( null," Cliente Encontrado \n Deseja Fazer Altera??o?", "Alterar Cadastro ?",JOptionPane.YES_NO_OPTION);

						if(resposta == 0){
							
							for (int i=0; i < getContentPane().getComponentCount(); i++) { 
					            Component c = getContentPane().getComponent(i);  
					            
					            if (c instanceof JTextField) {  
					            	
					                JTextField field = (JTextField) c;    
					                field.enable(true);
					                
					            }  // FIM DO IF
					            
					        }  // FIM DO LA?O
							
							txtPesquisa.setText("");
							txtPesquisa.setEnabled(false);
				           	txtNome.requestFocus();
				           	btnPesquisar.setEnabled(false);
				           	btnAtualizar.setEnabled(true);
				          	rbFem.setEnabled(true);
				          	rbMasc.setEnabled(true);
				       	 	cbPesquisar.setEnabled(false);
						
						}
						
					} // FIM ELSE/IF DA VERIFICA??O (IF)
					
				}catch (SQLException erro){
					
					System.out.println("Erro: " + erro.getMessage());
					
				} // FIM DO TRY/CATCH
				
				conex.Fechar();
				verificar = "";
				
			} // FIM BOTAO PESQUISAR
		}); // FIM BOTAO 2 E PRINCIPAL
		//
		///////////////////////// 	BOTAO ATUALIZAR / A??O /////////////////////////
		//
		btnAtualizar = new JButton();
		btnAtualizar.setBounds(675,303,95,22);
		btnAtualizar.setText("Atualizar");
		btnAtualizar.setToolTipText("ATUALIZA");
		form.add(btnAtualizar);
		//
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				conex.Conectar();
				
				Statement meustate;
				
				String sexo = "";
				
				if(rbMasc.isSelected() == true){
					
					sexo = rbMasc.getText();
												
				}else if (rbFem.isSelected() == true) {
					
					sexo = rbFem.getText();
	
				} // FIM DO SEXO ... VARIAVEL QUE PEGA O TEXTO
				
				// AQUI DIVIDO A DATA E DENTRO DA 'dataCli' EU MONTO NO FORMATO AMERICANO
				String dia = txtDtnasc.getText().substring(0,2);
				String mes = txtDtnasc.getText().substring(3,5);
				String ano = txtDtnasc.getText().substring(6);
				String dataNasc = ano + "/" +mes+ "/" +dia;	
				
				String sql = ("update tbCliente set nome = '"+txtNome.getText()+"', sexo = '"+sexo+"', cpf = '"+txtCpf.getText()+"', rg = '"+txtRg.getText()+"', estadoCivil = '"+txtEstCivil.getText()+"', dtNasc = '"+dataNasc+"', tel = '"+txtTel.getText()+"', email = '"+txtEMail.getText()+"', endereco = '"+txtEnd.getText()+"', numero = '"+txtNum.getText()+"', cep = '"+txtCep.getText()+"', bairro = '"+txtBairro.getText()+"', cidade = '"+txtCidade.getText()+"', estado = '"+txtCidade.getText()+"' where codCliente = '"+codigo+"' ");						
				
				resposta = JOptionPane.showConfirmDialog( null," Confirmar Altera??o ?", "Alterar Cadastro ?",JOptionPane.YES_NO_OPTION);
				
				if(resposta == 0){
					
					System.out.println("atualizar");
					
					try{
						
						meustate = Conexao.con.createStatement();
						meustate.executeUpdate(sql);
						
						JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
						
						for (int i=0; i < getContentPane().getComponentCount(); i++) { 
				            Component c = getContentPane().getComponent(i);  
				            
				            if (c instanceof JTextField) {  
				            	
				                JTextField field = (JTextField) c;    
				                field.enable(false);
				                
				            }  // FIM DO IF
				            
				        }  // FIM DO LA?O
						
						txtPesquisa.setText("");
						txtPesquisa.setEnabled(true);
			           	txtPesquisa.requestFocus();
			           	btnPesquisar.setEnabled(true);
			           	btnAtualizar.setEnabled(false);
			          	rbFem.setEnabled(false);
			          	rbMasc.setEnabled(false);
			       	 	cbPesquisar.setEnabled(true);
						
					}catch (SQLException error){
						
						System.out.println("erro:" + error.getMessage());
						
					}
					
				}else{
					
					System.out.println("nao quer atualizar");
					
				} // FIM DO ELSE/IF PERGUNTA SE QUER ATUALIZAR
				
				
			} // FIM BOTAO ATUALIZAR
			
		}); // FIM BOTAO 2 E PRINCIPAL
		//
		///////////////////////// 	BOTAO DELETAR / A??O /////////////////////////
		//
		btnDeletar = new JButton();
		btnDeletar.setBounds(675,330,95,22);
		btnDeletar.setText("Deletar");
		btnDeletar.setToolTipText("DELETA");
		form.add(btnDeletar);
		//
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event){
				
				System.out.println("DELETANDO");
				
				resposta = JOptionPane.showConfirmDialog( null," Confirmar Delete ?", "Deletar Usuario ?",JOptionPane.YES_NO_OPTION);
				
				conex.Conectar();
				
				Statement meustate;
				
				if(resposta == 0){
					
					System.out.println("SIM");
					
					String sql = ("DELETE FROM tbCliente where codCliente = '"+codigo+"'");
					
					try {				
						
						// INSERINDO NO BANCO E FECHANDO !
								
						meustate = Conexao.con.createStatement();
						meustate.executeUpdate(sql);
						
						JOptionPane.showMessageDialog(null, "DELETADO COM SUCESSO");
						
						// LIMPANDO AS TEXT'S
						
						for (int i=0; i < getContentPane().getComponentCount(); i++) {  
		                    Component c = getContentPane().getComponent(i);  
		                    if (c instanceof JTextField) {  
		                        JTextField field = (JTextField) c; 
		                        field.setEnabled(false);
		                        field.setText("");  
		                        // field.enable(false);
		                        
		                    } // FIM DO IF  
		                    
		                } // FIM DO LA?O 
						
				        txtPesquisa.setEnabled(true);
				        txtPesquisa.requestFocus();
				        btnPesquisar.setEnabled(true);
				        btnCadastrar.setEnabled(false);
				        //btAtualizar.setEnabled(false);
				        btnDeletar.setEnabled(false);
				        //btLimpar.setEnabled(false);
				        rbMasc.setEnabled(false);
				        rbFem.setEnabled(false);
				        cbPesquisar.setEnabled(true);
						
						
					}catch (SQLException e1){
						
						System.out.println("ERRO" + e1.getMessage());
						
					} // FIM CATCH
					
				}else{
					
					System.out.println("NAO");
					
				}
				
				conex.Fechar();
				
			} // FIM BOTAO
			
		});
		//
		///////////////////////// 	BOTAO Limpar / A??O /////////////////////////
		//
		btnLimpar = new JButton();
		btnLimpar.setBounds(675,277,95,22);
		btnLimpar.setText("Limpar");
		btnLimpar.setToolTipText(" LIMPAR ");
		form.add(btnLimpar);
		//
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtCep.setText("");
				txtPesquisa.setText("");
				txtPesquisa.setText("");
	           	txtNome.setText("");
	           	txtEstCivil.setText("");
	           	txtDtnasc.setText("");
	           	txtEnd.setText("");
	           	txtCep.setText("");
	           	txtBairro.setText("");
	           	txtCidade.setText("");
	           	txtTel.setText("");
	           	txtEMail.setText("");
	           	txtRg.setText("");
	           	txtCpf.setText("");
	           	txtNum.setText("");
	           		           	
	          	rbFem.setEnabled (true);
	          	rbMasc.setEnabled (true);
	       	 	cbPesquisar.setEnabled (true);
				
				
				
			}
		});
		//
		//para selecionar apenas uma linha   
	     tabelaCliente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	       
		        txtPesquisa.getDocument().addDocumentListener(new DocumentListener(){
		     	public void changedUpdate(DocumentEvent e){
		     		System.out.println("changed Update");
		     		
		     		txtNome.setText(tabelaCliente.getModel().getValueAt(tabelaCliente.getSelectedRow(),1).toString());
		     		txtEstCivil.setText(tabelaCliente.getModel().getValueAt(tabelaCliente.getSelectedRow(),5).toString());
		     		txtCpf.setText(tabelaCliente.getModel().getValueAt(tabelaCliente.getSelectedRow(),3).toString());
		     		txtBairro.setText(tabelaCliente.getModel().getValueAt(tabelaCliente.getSelectedRow(),4).toString());
		     		txtEMail.setText(tabelaCliente.getModel().getValueAt(tabelaCliente.getSelectedRow(),5).toString());
		     		txtDtnasc.setText(tabelaCliente.getModel().getValueAt(tabelaCliente.getSelectedRow(),6).toString());
		     		txtCep.setText(tabelaCliente.getModel().getValueAt(tabelaCliente.getSelectedRow(),7).toString());
		     		txtNum.setText(tabelaCliente.getModel().getValueAt(tabelaCliente.getSelectedRow(),8).toString());
		     		txtEnd.setText(tabelaCliente.getModel().getValueAt(tabelaCliente.getSelectedRow(),9).toString());
		     		txtRg.setText(tabelaCliente.getModel().getValueAt(tabelaCliente.getSelectedRow(),5).toString());
		     		txtTel.setText(tabelaCliente.getModel().getValueAt(tabelaCliente.getSelectedRow(),4).toString());
		     		
		     	}	     	
		     	
		     	public void removeUpdate(DocumentEvent e){
		     		System.out.println("remove Update");	     		
		     	
		     		String sql = "select * from tbCliente where nome like '"+txtPesquisa.getText()+"%'";
		     		
		     		try{
		        		mt.setQuery(sql);
		        		
		        	}
		        	catch(SQLException sqle){
		        		JOptionPane.showMessageDialog(null,"N?o foi poss?vel executar a consulta");
		        	}
		     		
		     	}
		     	
		     	public void insertUpdate(DocumentEvent e){
		     		System.out.println("insert Update");
		     		
	                String sql = "select * from tbCliente where nome like '"+txtPesquisa.getText()+"%'";
		     		
		     		try{
		        		mt.setQuery(sql);

		        	}
		        	catch(SQLException sqle){
		        		JOptionPane.showMessageDialog(null,"N?o foi poss?vel executar a consulta");
		        	}
		     	
		     	}
		     });     
		        
		        tabelaCliente.getSelectionModel().addListSelectionListener(new ListSelectionListener() {  
		        	     
		        	   public void valueChanged(ListSelectionEvent e) {  
		        	       	        		   	        		   
		        	       txtNome.setText(tabelaCliente.getValueAt(tabelaCliente.getSelectedRow(), 0).toString());		        	       
				     	   txtEstCivil.setText(tabelaCliente.getModel().getValueAt(tabelaCliente.getSelectedRow(),5).toString());
				     	   txtCpf.setText(tabelaCliente.getModel().getValueAt(tabelaCliente.getSelectedRow(),2).toString());
				     	   txtCidade.setText(tabelaCliente.getModel().getValueAt(tabelaCliente.getSelectedRow(),6).toString());
				     	   txtEMail.setText(tabelaCliente.getModel().getValueAt(tabelaCliente.getSelectedRow(),3).toString());
				     	   txtRg.setText(tabelaCliente.getModel().getValueAt(tabelaCliente.getSelectedRow(),1).toString());
				     	   txtTel.setText(tabelaCliente.getModel().getValueAt(tabelaCliente.getSelectedRow(),5).toString());
				     	   txtEnd.setText(tabelaCliente.getModel().getValueAt(tabelaCliente.getSelectedRow(),8).toString());
				     	   txtCep.setText(tabelaCliente.getModel().getValueAt(tabelaCliente.getSelectedRow(),7).toString());
				     	   txtBairro.setText(tabelaCliente.getModel().getValueAt(tabelaCliente.getSelectedRow(),8).toString());
				     	   txtDtnasc.setText(tabelaCliente.getModel().getValueAt(tabelaCliente.getSelectedRow(),11).toString());
				     	   txtNum.setText(tabelaCliente.getModel().getValueAt(tabelaCliente.getSelectedRow(),7).toString());
				     	   //controla e executa uma instru?ao sql
		             	   Statement meustate;
		             	   
		             	   //conjunto de dados retornado por uma consulta sql
		             	   ResultSet resultset;

		             	   //instancia o objeto conex da classe Conexao
		             	   Conexao conex = new Conexao(); 	   
		             	   
		             	   conex.Conectar();	             	   
		             	  
		            	   String sqlSelect = "select * from tbCliente where nome like '"+txtPesquisa.getText()+"%'";
		            	   int r = 0;
		            	    try{
		                    meustate = Conexao.con.createStatement();
		                    resultset = meustate.executeQuery(sqlSelect);
		                   
		                    
		                    while(resultset.next()){
		                    	r = Integer.parseInt(resultset.getString(1));      
		                    }
		                     
		            	   }
		                  catch(SQLException erro){
		                   System.out.println("Consulta sem resultado");
		                  }     	 
		            	            	      
		        	   }  
		        	} );


		
		
		
		//
		///////////////////////// 	BLOQUEANDO TEXT'S E AJEITANDO BOTOES E TALZ	 /////////////////////////
		//
		for (int i=0; i < getContentPane().getComponentCount(); i++) { 
            Component c = getContentPane().getComponent(i);  
            
            if (c instanceof JTextField) {  
            	
                JTextField field = (JTextField) c;    
                field.enable(false);
                field.setText("");
                
            }  // FIM DO IF
            
            txtPesquisa.setEnabled(true);
            txtPesquisa.requestFocus();
            btnPesquisar.setEnabled(true);
            btnCadastrar.setEnabled(false);
            rbFem.setEnabled(false);
            rbMasc.setEnabled(false);
            cbPesquisar.setEnabled(true);
            
        }  // FIM DO LA?O
		
	}// FIM DO METODO ....
				
} // FIM CLASSE
