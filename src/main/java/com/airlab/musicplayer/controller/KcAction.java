package com.airlab.musicplayer.controller;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.io.FileUtils;

import com.airlab.musicplayer.model.musicinfo.Kc;
import com.airlab.musicplayer.model.musicinfo.Types;
import com.airlab.musicplayer.model.musicinfo.Users;

import javafx.scene.control.Pagination;

public class KcAction {
	/*private int id;

	private String names;
	private int tid;
	private String ishy;
	private String zjr;
	private String xx;
	private String descs;
	private int bfcs;
	private int plcs;
	private int sccs;
	private String url1;// --
	private String url2;// --
	private String message;
	private String path;
	private List<File> image; // 上传的文件

	private List<String> imageFileName; // 文件名称 p
	private List<String> imageContentType; // 文件类型
	private int index = 1;

	public String addbefore() {
		return ActionSupport.SUCCESS;
	}
	
	public String kcaddbefore() {
		
		
		try {
			DBUtil util = new DBUtil();
			Connection conn = util.openConnection();
			String sql = " select * from types ";
			PreparedStatement pstmt1 = conn.prepareStatement(sql);
			ResultSet rs = pstmt1.executeQuery();

			List<Types> list = new ArrayList<Types>();
			while (rs.next()) {
				Types bean = new Types();
				bean.setId(rs.getInt("id"));
				bean.setNames(rs.getString("names"));
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

	*//**
	 * 音乐添加
	 * 
	 * @return
	 *//*
	public String add() {
		// 文件保存路径
		String realpath = ServletActionContext.getServletContext().getRealPath(
				"/files");
		String url1 = "";
		String url2 = "";
		String picfileName = "";
		if (image != null) {
			// 随机数+日期 重命名上传文件名称
			Random rnd = new Random();
			int r = rnd.nextInt(100);
			Date date2 = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
			String strDate2 = formatter.format(date2);
			picfileName = strDate2 + r;
			// 遍历上传文件
			for (int i = 0; i < image.size(); i++) {
				File oneFile = image.get(i);
				String onewimageFileName = imageFileName.get(i);
				String type = onewimageFileName.substring(onewimageFileName
						.lastIndexOf("."));
				String fileName = strDate2 + r + type;
				if (i == 0) {
					url1 = "files/" + fileName;
				} else {
					url2 = "files/" + fileName;
				}
				try {
					if (oneFile != null) {
						// 保存文件
						File savefile = new File(new File(realpath), fileName);
						if (!savefile.getParentFile().exists())
							savefile.getParentFile().mkdirs();
						FileUtils.copyFile(oneFile, savefile);

					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}

		}
		try {
			String tnames="";
			DBUtil util = new DBUtil();
			Connection conn = util.openConnection();
			String sql = " select * from types where id="+tid;
			PreparedStatement pstmt1 = conn.prepareStatement(sql);
			ResultSet rs = pstmt1.executeQuery();
			while (rs.next()) {
				tnames=rs.getString("names");
			}
			
			// 执行sql插入音乐信息到数据库 默认 播放 顶 评论次数为0
			String sqlAdd = "insert into kc(names,zjr,xx,descs,bfcs,plcs,sccs,url1,url2,tid,tnames,ishy) values('"
					+ names
					+ "','"
					+ zjr
					+ "','"
					+ xx
					+ "','"
					+ descs
					+ "',0,0,0,'" + url1 + "','" + url2 + "',"+tid+",'"+tnames+"','"+ishy+"')";
			
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sqlAdd);
		} catch (Exception e) {
			// TODO: handle exception
		}

		this.setMessage("添加成功");
		this.setPath("kcmana.action");
		return "succeed";
	}

	*//**
	 * 音乐列表
	 * 
	 * @return
	 *//*
	public String mana() {

		try {
			DBUtil util = new DBUtil();
			Connection conn = util.openConnection();

			String sql = " select * from kc ";
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

			int pageSize = 10;
			int fromIndex = (index - 1) * pageSize;
			int toIndex = Math.min(fromIndex + pageSize, list.size());
			List adminListFenye = list.subList(fromIndex, toIndex);

			Pagination p = new Pagination();//
			p.setIndex(index);//
			p.setPageSize(pageSize);
			p.setTotle(list.size());//
			p.setData(adminListFenye);//
			p.setPath("kcmana.action");//

			Map request = (Map) ServletActionContext.getContext()
					.get("request");
			request.put("page", p);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ActionSupport.SUCCESS;
	}
	
	public String usersmana() {

		try {
			DBUtil util = new DBUtil();
			Connection conn = util.openConnection();
			String sql = " select * from users ";
			PreparedStatement pstmt1 = conn.prepareStatement(sql);
			ResultSet rs = pstmt1.executeQuery();

			List<Users> list = new ArrayList<Users>();
			while (rs.next()) {
				Users bean = new Users();
				bean.setId(rs.getInt("id"));
				bean.setUsername(rs.getString("username"));
				bean.setPassword(rs.getString("password"));
				bean.setTel(rs.getString("tel"));
				bean.setSex(rs.getString("sex"));
				bean.setEmail(rs.getString("email"));
				bean.setIsvip(rs.getString("isvip"));
				list.add(bean);
			}

			int pageSize = 10;
			int fromIndex = (index - 1) * pageSize;
			int toIndex = Math.min(fromIndex + pageSize, list.size());
			List adminListFenye = list.subList(fromIndex, toIndex);
			Pagination p = new Pagination();//
			p.setIndex(index);//
			p.setPageSize(pageSize);
			p.setTotle(list.size());//
			p.setData(adminListFenye);//
			p.setPath("usersmana.action");//

			Map request = (Map) ServletActionContext.getContext()
					.get("request");
			request.put("page", p);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ActionSupport.SUCCESS;
	}

	*//**
	 * 删除音乐
	 * 
	 * @return
	 *//*
	public String del() {

		try {
			DBUtil util = new DBUtil();
			Connection conn = util.openConnection();
			String sqlD = "delete from  kc where id=" + id;
			System.out.println("================  " + sqlD);
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sqlD);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		this.setMessage("删除成功");
		this.setPath("kcmana.action");
		return "succeed";
	}
	public String liuyandel() {
		try {
			String sqlAdd = "delete from  liuyan where id="+id;
			DBUtil util = new DBUtil();
			Connection conn = util.openConnection();
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sqlAdd);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		this.setMessage("删除成功");
		this.setPath("lylist2.action");
		return "succeed";
	}
	public String usersdel() {

		try {
			DBUtil util = new DBUtil();
			Connection conn = util.openConnection();
			String sqlD = "delete from  users where id=" + id;
			System.out.println("================  " + sqlD);
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sqlD);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		this.setMessage("删除成功");
		this.setPath("usersmana.action");
		return "succeed";
	}
	public String usersvip() {

		try {
			DBUtil util = new DBUtil();
			Connection conn = util.openConnection();
			String sqlD = "update  users set isvip='是' where id=" + id;
			System.out.println("================  " + sqlD);
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sqlD);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		this.setMessage("设置成功");
		this.setPath("usersmana.action");
		return "succeed";
	}
	public String kcmb() {

		try {
			DBUtil util = new DBUtil();
			Connection conn = util.openConnection();

			String sql = " select * from kc where id=" + id;
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
				break;
			}
			Map request = (Map) ServletActionContext.getContext()
					.get("request");
			request.put("bean", bean);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return ActionSupport.SUCCESS;
	}
	
	*//**
	 * 修改音乐
	 * 
	 * @return
	 *//*
	public String kcm() {

		try {
			DBUtil util = new DBUtil();
			Connection conn = util.openConnection();
			String sqlD = "update  kc set zjr='"+zjr+"',names='"+names+"',xx='"+xx+"',descs='"+descs+"' where id=" + id;
			System.out.println("================  " + sqlD);
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sqlD);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		this.setMessage("修改成功");
		this.setPath("kcmana.action");
		return "succeed";
	}

	*//**
	 * 播放次数添加
	 * 
	 * @return
	 *//*
	public String bfadd() {
		try {
			String sqlAdd = "update kc set bfcs=bfcs+1 where id=" + id;
			DBUtil util = new DBUtil();
			Connection conn = util.openConnection();
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sqlAdd);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return "succeed";
	}

	*//**
	 * 顶次数添加
	 * 
	 * @return
	 *//*
	public String dadd() {
		try {
			String sqlAdd = "update kc set sccs=sccs+1 where id=" + id;
			DBUtil util = new DBUtil();
			Connection conn = util.openConnection();
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sqlAdd);
			//加入我喜欢的
			Map session = ServletActionContext.getContext().getSession();
			String uid=session.get("id").toString();
			String sqlAdd2 = "insert into sc(uid,kid) values ("+uid+","+id+")";
			Statement stmt2 = conn.createStatement();
			stmt2.executeUpdate(sqlAdd2);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return "succeed";
	}

	*//**
	 * 留言信息添加
	 * 
	 * @return
	 *//*
	public String lyadd() {
		try {
			Map session = ServletActionContext.getContext().getSession();
			String names=session.get("username").toString();
			String sqlAdd = "insert into ly(kcid,descs,times,names) values(" + id
					+ ",'" + descs + "',now(),'"+names+"')";
			DBUtil util = new DBUtil();
			Connection conn = util.openConnection();
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sqlAdd);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		this.setMessage("评论成功");
		this.setPath("detail.action?id=" + id);
		return "succeed";
	}
	
	public String typesadd() {
		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
		try {
			
			String sql = "insert into types(names) values('"
				+ names
				+ "')";

		System.out.println(sql);

		Statement stmt = conn.createStatement();
		stmt.executeUpdate(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}

		this.setMessage("添加成功");
		this.setPath("typesmana.action");
		return "succeed";
	}
	
	public String typesmana() {

		try {
			DBUtil util = new DBUtil();
			Connection conn = util.openConnection();
			String sql = " select * from types ";
			PreparedStatement pstmt1 = conn.prepareStatement(sql);
			ResultSet rs = pstmt1.executeQuery();

			List<Types> list = new ArrayList<Types>();
			while (rs.next()) {
				Types bean = new Types();
				bean.setId(rs.getInt("id"));
				bean.setNames(rs.getString("names"));
				list.add(bean);
			}

			int pageSize = 10;
			int fromIndex = (index - 1) * pageSize;
			int toIndex = Math.min(fromIndex + pageSize, list.size());
			List adminListFenye = list.subList(fromIndex, toIndex);

			Pagination p = new Pagination();//
			p.setIndex(index);//
			p.setPageSize(pageSize);
			p.setTotle(list.size());//
			p.setData(adminListFenye);//
			p.setPath("typessmana.action");//

			Map request = (Map) ServletActionContext.getContext()
					.get("request");
			request.put("page", p);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ActionSupport.SUCCESS;
	}

	
	public String typesdel() {

		try {
			DBUtil util = new DBUtil();
			Connection conn = util.openConnection();
			String sqlD = "delete from  types where id=" + id;
			System.out.println("================  " + sqlD);
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sqlD);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		this.setMessage("删除成功");
		this.setPath("typesmana.action");
		return "succeed";
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

	public String getDescs() {
		return descs;
	}

	public void setDescs(String descs) {
		this.descs = descs;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public List<File> getImage() {
		return image;
	}

	public void setImage(List<File> image) {
		this.image = image;
	}

	public List<String> getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(List<String> imageFileName) {
		this.imageFileName = imageFileName;
	}

	public List<String> getImageContentType() {
		return imageContentType;
	}

	public void setImageContentType(List<String> imageContentType) {
		this.imageContentType = imageContentType;
	}

	public String getZjr() {
		return zjr;
	}

	public void setZjr(String zjr) {
		this.zjr = zjr;
	}

	public String getXx() {
		return xx;
	}

	public void setXx(String xx) {
		this.xx = xx;
	}

	public int getBfcs() {
		return bfcs;
	}

	public void setBfcs(int bfcs) {
		this.bfcs = bfcs;
	}

	public int getPlcs() {
		return plcs;
	}

	public void setPlcs(int plcs) {
		this.plcs = plcs;
	}

	public int getSccs() {
		return sccs;
	}

	public void setSccs(int sccs) {
		this.sccs = sccs;
	}

	public String getUrl1() {
		return url1;
	}

	public void setUrl1(String url1) {
		this.url1 = url1;
	}

	public String getUrl2() {
		return url2;
	}

	public void setUrl2(String url2) {
		this.url2 = url2;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getIshy() {
		return ishy;
	}

	public void setIshy(String ishy) {
		this.ishy = ishy;
	}*/

}
