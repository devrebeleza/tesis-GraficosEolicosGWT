package com.paquete.graficos.eolicos.funcionescarga;

import java.math.BigDecimal;

import java.sql.Savepoint;


import org.hibernate.classic.Session;

public class FuncionesComunes {

	static BigDecimal convertirStrABigDecimal(String string) {
		// TODO Auto-generated method stub
		String str = string.trim();
		str = str.replaceAll(",", ".");
		return(new BigDecimal(str));
	}
	
	static Integer convertirStrAInteger(String string) {
		// TODO Auto-generated method stub
		String str = string.trim();
		str = str.replaceAll(",", ".");
		return(new Integer(str));
	}
	Session session = null;
	private Savepoint savepoint;

//	public Savepoint setSavepoint(final String savePoint)
//	{
//	   session.getSession(null).doWork(new Work()
//	   {
//	      public void execute(Connection connection) throws SQLException
//	      {
//	         savepoint = connection.setSavepoint(savePoint);
//	      }
//	   });
//	   return savepoint;
//	}

//	public void rollbackSavepoint(final Savepoint savepoint)
//	{
//	   getSession().doWork(new Work(){
//	      public void execute(Connection connection) throws SQLException
//	      {
//	         connection.rollback(savepoint);
//	      }
//	   });
//	}
}
