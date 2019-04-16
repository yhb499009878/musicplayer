package com.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.Types;

public class ValueBean {
	
	public static List getTypesList(){
		
		DBUtil util= new DBUtil();
		Connection conn = util.openConnection();
		ResultSet rs = null;
		String sql="select * from types ";
		List<Types> list = new ArrayList<Types>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Types bean = new Types();
				bean.setId(rs.getInt("id"));
				bean.setNames(rs.getString("names"));
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static String getNamesId(int id){
		
		DBUtil util= new DBUtil();
		Connection conn = util.openConnection();
		ResultSet rs = null;
		String sql="select names from types where id="+id;
		String result="";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			result=rs.getString("names");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
}
