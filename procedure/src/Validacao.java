import java.util.InputMismatchException;

import javax.swing.JOptionPane;


public class Validacao {

	public boolean verificacaoNome(String a){
		
		boolean r = true;
		int r2 = 0;
		
		do{
		if (a.equals("")){
			r = false;
			JOptionPane.showMessageDialog(null, "Campo Nome Vazio");
			break;
		}
		if (a.length()<=3){
			r = false;
			JOptionPane.showMessageDialog(null, "Nome Incompleto");
			break;
		}
		}while(r2 == 2);
		
		return r;
		
	}
	
	public boolean verificacaoEmail(String e){
		
		String email = e ;
		boolean r = true;
		int r2 = 0;
		int i = email.indexOf("@");
		
		do{
			if(i < 3){
				
				JOptionPane.showMessageDialog(null, "Email Incompleto");
				r = false;
				
			}else if (e.length()-5 <= i ){
				
				JOptionPane.showMessageDialog(null, "Email Incompleto");
				r = false;
				
			}
			
			
		}while(r2 == 2);
		
		return r;
		
	}
	
	public boolean verificacaoData(String data){
		
		boolean r = true;
		int r2 = 0;
		
		do{
			if(data.length() == 0){
				
				JOptionPane.showMessageDialog(null, "Data de Nascimento Incompleto");
				r = false;
				
			}			
			
		}while(r2 == 2);
		
		return r;
		
	}
	
	/*public boolean verificacaoCpf(String cpf){
		
		boolean r = true;
		int r2 = 0;
		
		do{
			if(cpf.length() < 1){
				
				JOptionPane.showMessageDialog(null, "CPF Incompleto");
				r = false;
				
			}			
			
		}while(r2 == 2);
		
		return r;
		
	}*/
	
	
	  /* public static boolean verificacaoCpf(String CPF) {
		// considera-se erro CPF's formados por uma sequencia de numeros iguais
		    if (CPF.equals("00000000000") || CPF.equals("11111111111") ||
		        CPF.equals("22222222222") || CPF.equals("33333333333") ||
		        CPF.equals("44444444444") || CPF.equals("55555555555") ||
		        CPF.equals("66666666666") || CPF.equals("77777777777") ||
		        CPF.equals("88888888888") || CPF.equals("99999999999") ||
		       (CPF.length() != 11))
		       return(false);

		    char dig10, dig11;
		    int sm, i, r, num, peso;

		// "try" - protege o codigo para eventuais erros de conversao de tipo (int)
		    try {
		// Calculo do 1o. Digito Verificador
		      sm = 0;
		      peso = 10;
		      for (i=0; i<9; i++) {              
		// converte o i-esimo caractere do CPF em um numero:
		// por exemplo, transforma o caractere '0' no inteiro 0         
		// (48 eh a posicao de '0' na tabela ASCII)         
		        num = (int)(CPF.charAt(i) - 48); 
		        sm = sm + (num * peso);
		        peso = peso - 1;
		      }

		      r = 11 - (sm % 11);
		      if ((r == 10) || (r == 11))
		         dig10 = '0';
		      else dig10 = (char)(r + 48); // converte no respectivo caractere numerico

		// Calculo do 2o. Digito Verificador
		      sm = 0;
		      peso = 11;
		      for(i=0; i<10; i++) {
		        num = (int)(CPF.charAt(i) - 48);
		        sm = sm + (num * peso);
		        peso = peso - 1;
		      }

		      r = 11 - (sm % 11);
		      if ((r == 10) || (r == 11))
		         dig11 = '0';
		      else dig11 = (char)(r + 48);

		// Verifica se os digitos calculados conferem com os digitos informados.
		      if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
		         return(true);
		      else return(false);
		    } catch (InputMismatchException erro) {
		        return(false);
		    }
		    
		  }
	  
	  
	  public static String imprimeCPF(String cpf) {
		    return cpf;
		  } */
	
	
	/*public boolean verificacaoEmail(String email) {
	    // System.out.println("Metodo de validacao de email");
	    Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$"); 
	    Matcher m = p.matcher(email); 
	    if (m.find()){
	      JOptionPane.showMessageDialog(null, "O email "+email+" e valido");
	      return true;
	    }
	    else{
	      System.out.println("O E-mail "+email+" é inválido");
	      return false;
	    }  
	 }*/ 
	
	
}
