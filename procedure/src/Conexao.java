import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	
	static Connection con;
	
	public void Conectar(){
		
		try{
			
			Class.forName("com.mysql.jdbc.Driver");			
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdMedico","root","123456");
			
			System.out.println("Conexa� realizada com sucesso");
		}
		catch(SQLException ex){
			
			System.out.println("Problemas na conex�o com o banco de dados." + ex.getMessage());
			
		}
		catch ( ClassNotFoundException ex) {
			// TODO: handle exception
			System.out.println("Driver JDBC-ODBC n�o foi encontrado");
		} 
	}
	
	public void Fechar(){
		try{
			con.close();
			System.out.println("Conex�o finalizada com sucesso");
		}
		catch(SQLException ex){
			System.out.println("Problemas na conex�o");
		}

	}

}