import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.table.AbstractTableModel;


public class ModeloDeTabela extends AbstractTableModel{
	
	ResultSet rs;    
    ResultSetMetaData metaData;
    PreparedStatement stmt;
    int totalColsConsulta;
    String[] pegaNomeColunas;
    
    int numLinhas;
    
    Conexao conex = new Conexao();
	
    //construtor com par�metro da String SQL
	public ModeloDeTabela(String sql){
		
		try{    					
			conex.Conectar();
			
			//executa a instru��o sql
			setQuery(sql);
			
     	   //preparando a instru��o
     	    stmt = Conexao.con.prepareStatement(sql);
     	    
     	    
		}catch(SQLException erro){
			System.out.println("nao executou o try do construtor");			
		}       	
		
	}// fim do construtor
	
	public int getColumnCount() throws IllegalStateException{
		try{
			return metaData.getColumnCount();
		}
		catch (SQLException e) {
			
		}		
		
		return 0;		
	}
	
	public String getColumnName(int col) throws IllegalStateException{
		
		try{
			return metaData.getColumnName(col+1);	
		}
		catch(SQLException e){
			
		}	    
		return "";
	}
	
	public int getRowCount() {	
		return numLinhas;
	}	
	
	public Object getValueAt(int linha, int coluna) {
		try{			
			rs.absolute(linha+1);			
            return rs.getObject(coluna+1);            
     	    
		}
		catch (SQLException e) {
			System.out.println("nao executou try getValueAT");
		}
		
		return "";		
	}	
	
	public void setQuery(String query)throws SQLException,IllegalStateException{
	
     	    stmt = Conexao.con.prepareStatement(query);
     	    rs = stmt.executeQuery();
     	    System.out.println("executou setQuery");
     	    
     	    metaData = rs.getMetaData();
     	    
     	    rs.last(); // move para a �ltima linha
     	    
     	    numLinhas = rs.getRow(); //obt�m o n�mero de linha
     	    
     	    System.out.println("N�mero de linhas: "+numLinhas);
     	    fireTableStructureChanged();
	}	

}
