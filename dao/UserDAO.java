package com.brainmentors.chatapp.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.brainmentors.chatapp.dto.UserDTO;
import com.brainmentors.chatapp.util.Encryption;

//user CRUD
public class UserDAO {
	public boolean isLogin(UserDTO userDTO) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		final String SQL="select userid from users where userid=? and password=?";
		try {
			con=CommonDAO.createConnection();
			pstmt=con.prepareStatement(SQL);
			pstmt.setString(1, userDTO.getUserid());
			String encryptpwd= Encryption.passwordEncrypt(new String(userDTO.getPassword()));
			pstmt.setString(2, encryptpwd);
			rs=pstmt.executeQuery();
			return rs.next();
//			if(rs.next()) {
//				return true;
//			}else {
//				return false;
//			}
		}
		finally {
			if(rs!=null) {
				rs.close();
			}
			if(pstmt!=null) {
				pstmt.close();
			}
			if(con!=null) {
				con.close();
			}
		}
	}
public int add(UserDTO userDTO) throws ClassNotFoundException, SQLException ,Exception{
	System.out.println("Recieved"+"userid"+userDTO.getUserid()+" password"+userDTO.getPassword());
	//now put data in a database
	Connection connection=null;
	Statement stmt=null;//query
	try {//guarded region-->exception may come
	connection=CommonDAO.createConnection();//connection is created
	//to do a query
	stmt=connection.createStatement();
	//insert into users(userid,password) values('Asif','asif123');
	//return 1 if added otherwise 0
	int record=stmt.executeUpdate("insert into users(userid,password) values('"+userDTO.getUserid()+"','"+Encryption.passwordEncrypt(new String(userDTO.getPassword()))+"')");
	return record;
	}
	//finally will rum return will wait finally only does not run when system.exit()
	//resource clean up
	finally {//Always execute whether exception comes or not
	if(stmt!=null)
	stmt.close();
	if(connection!=null)
	connection.close();
	}
}
}
