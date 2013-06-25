package br.com.infoliver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.infoliver.sah.negocio.entity.MovimentacaoProcedimento;

public class Main {

	static Connection connection;
	
	public static void main(String[] args) {
		
//		try {
//			Class.forName("org.postgresql.Driver");
//			
//			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/sauth", "postgres", "123456");
//			
//			Statement stmt = connection.createStatement();
//			
//			ResultSet rs = stmt.executeQuery("select * from sigtap.tb_procedimento");
//			
//			
//			
//			List<MovimentacaoProcedimento> list = new ArrayList<MovimentacaoProcedimento>();
//			
//			
//			while (rs.next()) {
//				MovimentacaoProcedimento procedimento = new MovimentacaoProcedimento();
//				procedimento.setSigtap_co_procedimento(rs.getString("co_procedimento"));
//				procedimento.setSigtap_vl_sa(rs.getDouble("vl_sa"));
//				procedimento.setSigtap_vl_sh(rs.getDouble("vl_sh"));
//				procedimento.setSigtap_vl_sp(rs.getDouble("vl_sp"));
//				
//				list.add(procedimento);
//				
//			}
//			
//			for (MovimentacaoProcedimento movimentacaoProcedimento : list) {
//				stmt.execute("update sigtap.tb_procedimento " +
//								"set vl_sp = " + movimentacaoProcedimento.getSigtap_vl_sp().intValue() / 100 + "." + movimentacaoProcedimento.getSigtap_vl_sp().intValue() % 100 + " " +
//								"where co_procedimento = '" + movimentacaoProcedimento.getSigtap_co_procedimento() + "' ");
//			}
//			
//			System.out.println("Sucesso");
//			
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		
	}
}
