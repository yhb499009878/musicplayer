package com.airlab.musicplayer.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.airlab.musicplayer.model.musicinfo.Kc;
import com.airlab.musicplayer.model.musicinfo.Ly;
import com.airlab.musicplayer.model.musicinfo.TLiuyan;

import javafx.scene.control.Pagination;

public class IndexAction {
	private int id;
	private String no;//
	private String jszno;
	private String jl;
	private String tel;
	private String realname;
	private String gzkno;
	private String keyword;
	private double jbgz;
	private String message;
	private String path;
	private int index = 1;
	private String descs;
	/**
	 * 主页显示
	 * @return
	 *//*
	public String index() {
		try {
			//查询音乐
			DBUtil util = new DBUtil();
			Connection conn = util.openConnection();

			String sql = " select * from kc where 1=1 ";
			if(keyword!=null&&!keyword.equals("")){
				sql+=" and ( names like '%"+keyword+"%' or zjr like '%"+keyword+"%' )";
			}
			PreparedStatement pstmt1 = conn.prepareStatement(sql);
			ResultSet rs = pstmt1.executeQuery();

			List<Kc> list = new ArrayList<Kc>();
			while (rs.next()) {
				Kc bean = new Kc();
				bean.setId(rs.getInt("id"));
				bean.setBfcs(rs.getInt("bfcs"));
				bean.setDescs(rs.getString("descs"));
				bean.setNames(rs.getString("names"));
				bean.setPlcs(rs.getInt("plcs"));
				bean.setSccs(rs.getInt("sccs"));
				bean.setUrl1(rs.getString("url1"));
				bean.setUrl2(rs.getString("url2"));
				bean.setXx(rs.getString("xx"));
				bean.setZjr(rs.getString("zjr"));
				bean.setTnames(rs.getString("tnames"));
				bean.setIshy(rs.getString("ishy"));
				list.add(bean);
			}
			//分页显示
			int pageSize = 10;
			int fromIndex = (index - 1) * pageSize;
			int toIndex = Math.min(fromIndex + pageSize, list.size());
			List adminListFenye = list.subList(fromIndex, toIndex);

			Pagination p = new Pagination();//
			p.setIndex(index);//
			p.setPageSize(pageSize);
			p.setTotle(list.size());//
			p.setData(adminListFenye);//
			p.setPath("index.action");//

			Map request = (Map) ServletActionContext.getContext()
					.get("request");
			request.put("page", p);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ActionSupport.SUCCESS;
	}
	public String types() {
		try {
			//查询音乐
			DBUtil util = new DBUtil();
			Connection conn = util.openConnection();

			String sql = " select * from kc where 1=1 and tid="+id;
			if(keyword!=null&&!keyword.equals("")){
				sql+=" and ( names like '%"+keyword+"%' or zjr like '%"+keyword+"%' )";
			}
			PreparedStatement pstmt1 = conn.prepareStatement(sql);
			ResultSet rs = pstmt1.executeQuery();

			List<Kc> list = new ArrayList<Kc>();
			while (rs.next()) {
				Kc bean = new Kc();
				bean.setId(rs.getInt("id"));
				bean.setBfcs(rs.getInt("bfcs"));
				bean.setDescs(rs.getString("descs"));
				bean.setNames(rs.getString("names"));
				bean.setPlcs(rs.getInt("plcs"));
				bean.setSccs(rs.getInt("sccs"));
				bean.setUrl1(rs.getString("url1"));
				bean.setUrl2(rs.getString("url2"));
				bean.setXx(rs.getString("xx"));
				bean.setZjr(rs.getString("zjr"));
				bean.setTnames(rs.getString("tnames"));
				bean.setIshy(rs.getString("ishy"));
				list.add(bean);
			}
			//分页显示
			int pageSize = 10;
			int fromIndex = (index - 1) * pageSize;
			int toIndex = Math.min(fromIndex + pageSize, list.size());
			List adminListFenye = list.subList(fromIndex, toIndex);

			Pagination p = new Pagination();//
			p.setIndex(index);//
			p.setPageSize(pageSize);
			p.setTotle(list.size());//
			p.setData(adminListFenye);//
			p.setPath("index.action");//

			Map request = (Map) ServletActionContext.getContext()
					.get("request");
			String tnames=ValueBean.getNamesId(id);
			request.put("tnames", tnames);
			request.put("page", p);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ActionSupport.SUCCESS;
	}
	public String sc() {
		try {
			//查询音乐
			DBUtil util = new DBUtil();
			Connection conn = util.openConnection();
			Map session = ServletActionContext.getContext().getSession();
			String uid=session.get("id").toString();
			String sql = " select * from kc where 1=1  and id in(select kid from sc where uid="+uid+")";
			PreparedStatement pstmt1 = conn.prepareStatement(sql);
			ResultSet rs = pstmt1.executeQuery();

			List<Kc> list = new ArrayList<Kc>();
			while (rs.next()) {
				Kc bean = new Kc();
				bean.setId(rs.getInt("id"));
				bean.setBfcs(rs.getInt("bfcs"));
				bean.setDescs(rs.getString("descs"));
				bean.setNames(rs.getString("names"));
				bean.setPlcs(rs.getInt("plcs"));
				bean.setSccs(rs.getInt("sccs"));
				bean.setUrl1(rs.getString("url1"));
				bean.setUrl2(rs.getString("url2"));
				bean.setXx(rs.getString("xx"));
				bean.setZjr(rs.getString("zjr"));
				bean.setTnames(rs.getString("tnames"));
				bean.setIshy(rs.getString("ishy"));
				list.add(bean);
			}
			//分页显示
			int pageSize = 10;
			int fromIndex = (index - 1) * pageSize;
			int toIndex = Math.min(fromIndex + pageSize, list.size());
			List adminListFenye = list.subList(fromIndex, toIndex);

			Pagination p = new Pagination();//
			p.setIndex(index);//
			p.setPageSize(pageSize);
			p.setTotle(list.size());//
			p.setData(adminListFenye);//
			p.setPath("index.action");//

			Map request = (Map) ServletActionContext.getContext()
					.get("request");
			request.put("page", p);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ActionSupport.SUCCESS;
	}
	*//**
	 * 留言列表
	 * @return
	 *//*
	public String lylist() {
		try {
			//查询音乐
			DBUtil util = new DBUtil();
			Connection conn = util.openConnection();

			String sql = " select * from liuyan where 1=1 ";
			PreparedStatement pstmt1 = conn.prepareStatement(sql);
			ResultSet rs = pstmt1.executeQuery();

			List<TLiuyan> list = new ArrayList<TLiuyan>();
			while (rs.next()) {
				TLiuyan bean = new TLiuyan();
				bean.setId(rs.getInt("id"));
				bean.setDescs(rs.getString("descs"));
				bean.setNames(rs.getString("names"));
				bean.setTimes(rs.getDate("times"));
				list.add(bean);
			}

			Map request = (Map) ServletActionContext.getContext()
					.get("request");
			request.put("list", list);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ActionSupport.SUCCESS;
	}
	
	public String lylist2() {
		try {
			//查询音乐
			DBUtil util = new DBUtil();
			Connection conn = util.openConnection();

			String sql = " select * from liuyan where 1=1 ";
			PreparedStatement pstmt1 = conn.prepareStatement(sql);
			ResultSet rs = pstmt1.executeQuery();

			List<TLiuyan> list = new ArrayList<TLiuyan>();
			while (rs.next()) {
				TLiuyan bean = new TLiuyan();
				bean.setId(rs.getInt("id"));
				bean.setDescs(rs.getString("descs"));
				bean.setNames(rs.getString("names"));
				bean.setTimes(rs.getDate("times"));
				list.add(bean);
			}

			Map request = (Map) ServletActionContext.getContext()
					.get("request");
			request.put("list", list);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ActionSupport.SUCCESS;
	}
	
	
	public String liuyanadd() {
		try {
			Map session = ServletActionContext.getContext().getSession();
			String names=session.get("username").toString();
			String sqlAdd = "insert into liuyan(descs,times,names) values('" + descs + "',now(),'"+names+"')";
			DBUtil util = new DBUtil();
			Connection conn = util.openConnection();
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sqlAdd);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		this.setMessage("留言成功");
		this.setPath("lylist.action");
		return "succeed";
	}
	
	
	public String search() {
		return ActionSupport.SUCCESS;
	}

	*//**
	 * 音乐详细
	 * @return
	 *//*
	public String detail() {
		try {
			DBUtil util = new DBUtil();
			Connection conn = util.openConnection();
			//根据id选择音乐
			String sql = " select * from kc where id="+id;
			PreparedStatement pstmt1 = conn.prepareStatement(sql);
			ResultSet rs = pstmt1.executeQuery();
			Kc bean = new Kc();
			while (rs.next()) {
				
				bean.setId(rs.getInt("id"));
				bean.setBfcs(rs.getInt("bfcs"));
				bean.setDescs(rs.getString("descs"));
				bean.setNames(rs.getString("names"));
				bean.setPlcs(rs.getInt("plcs"));
				bean.setSccs(rs.getInt("sccs"));
				bean.setUrl1(rs.getString("url1"));
				bean.setUrl2(rs.getString("url2"));
				bean.setXx(rs.getString("xx"));
				bean.setZjr(rs.getString("zjr"));
				bean.setTnames(rs.getString("tnames"));
				bean.setIshy(rs.getString("ishy"));
			}
			//查询当前音乐的留言信息
			String sql2 = " select * from ly  where kcid="+id;
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			ResultSet rs2 = pstmt2.executeQuery();

			List<Ly> list2 = new ArrayList<Ly>();
			while (rs2.next()) {
				Ly bean2 = new Ly();
				bean2.setId(rs2.getInt("id"));
				bean2.setDescs(rs2.getString("descs"));
				bean2.setNames(rs2.getString("names"));
				bean2.setTimes(rs2.getDate("times"));
				list2.add(bean2);
			}
			Map request = (Map) ServletActionContext.getContext()
					.get("request");
			request.put("bean", bean);
			request.put("list2", list2);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ActionSupport.SUCCESS;
	}
	
	
	
	public String getMessage() {
		return message;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}


	public String getNo() {
		return no;
	}


	public void setNo(String no) {
		this.no = no;
	}


	public String getJszno() {
		return jszno;
	}


	public void setJszno(String jszno) {
		this.jszno = jszno;
	}


	public String getJl() {
		return jl;
	}


	public void setJl(String jl) {
		this.jl = jl;
	}


	public String getGzkno() {
		return gzkno;
	}


	public void setGzkno(String gzkno) {
		this.gzkno = gzkno;
	}


	public double getJbgz() {
		return jbgz;
	}


	public void setJbgz(double jbgz) {
		this.jbgz = jbgz;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getDescs() {
		return descs;
	}

	public void setDescs(String descs) {
		this.descs = descs;
	}

*/

}
