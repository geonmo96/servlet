package board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BoardDAO {
	private String url="jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "jsp";
	private String pw = "1234";
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public BoardDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	private void replyShape(BoardDTO dto) {
		String sql = "update test_board set step = step+1 where idgroup=? and step >?";
		try {
			con = DriverManager.getConnection(url,id,pw);
			ps = con.prepareStatement(sql);
			ps.setInt(1, dto.getIdgroup());
			ps.setInt(2, dto.getStep());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void reply(BoardDTO dto) {
		replyShape(dto);
		String sql = "insert into test_board(id,name,title,content,idgroup,step,indent)"
				+ " values(test_board_seq.nextval, ?, ?, ?, ?, ?, ?)";
		try {
			con = DriverManager.getConnection(url, id, pw);
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getName());
			ps.setString(2, dto.getTitle());
			ps.setString(3, dto.getContent());
			ps.setInt(4, dto.getIdgroup());
			ps.setInt(5, dto.getStep() + 1);
			ps.setInt(6, dto.getIndent() + 1);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public BoardDTO reply_view(String id) {
		BoardDTO dto = new BoardDTO();
		try {
			con = DriverManager.getConnection(url, this.id,pw);
			String sql = "select * from test_board where id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				dto.setId(rs.getInt("id"));
				dto.setHit(rs.getInt("hit"));
				dto.setIdgroup(rs.getInt("idgroup"));
				dto.setStep(rs.getInt("step"));
				dto.setIndent(rs.getInt("indent"));
				dto.setName(rs.getString("name"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setSavedate(rs.getTimestamp("savedate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	public void delete(String id) {
		try {
			con = DriverManager.getConnection(url,this.id, pw);
			String sql = "delete from test_board where id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void modify(BoardDTO dto) {
		try {
			con = DriverManager.getConnection(url,id,pw);
			String sql = "update test_board set name=?, title=?, content=? where id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getName());
			ps.setString(2, dto.getTitle());
			ps.setString(3, dto.getContent());
			ps.setInt(4, dto.getId());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	private void upHit(String id) {
		try {
			con = DriverManager.getConnection(url, this.id, pw);
			String sql = "update test_board set hit = hit + 1 where id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public BoardDTO contentView(String id) {
		upHit(id);
		String sql = "select * from test_board where id = ?";
		BoardDTO dto = new BoardDTO();
		try {
			con = DriverManager.getConnection(url,this.id,pw);
			ps = con.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(id));
			rs = ps.executeQuery();
			while(rs.next()) {
				dto.setId(rs.getInt("id"));
				dto.setHit(rs.getInt("hit"));
				dto.setIdgroup(rs.getInt("idgroup"));
				dto.setStep(rs.getInt("step"));
				dto.setIndent(rs.getInt("indent"));
				dto.setName(rs.getString("name"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setSavedate(rs.getTimestamp("savedate"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	public void write_save(String name, String title, String content) {
		String sql = "insert into test_board(id,name,title,content,idgroup,step,indent)"
				+ " values(test_board_seq.nextval, ?, ?, ?, test_board_seq.currval, 0, 0)";
		try {
			con = DriverManager.getConnection(url, id, pw);
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, title);
			ps.setString(3, content);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<BoardDTO> list(){
		String sql = "select * from test_board order by idgroup desc, step asc";
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		try {
			con = DriverManager.getConnection(url, id, pw);
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setId(rs.getInt("id"));
				dto.setHit(rs.getInt("hit"));
				dto.setIdgroup(rs.getInt("idgroup"));
				dto.setStep(rs.getInt("step"));
				dto.setIndent(rs.getInt("indent"));
				dto.setName(rs.getString("name"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setSavedate(rs.getTimestamp("savedate"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
