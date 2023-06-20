package koreait.jdbc.day4;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import koreait.jdbc.day4.JBuy;
import koreait.jdbc.day2.OracleUtility;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



public class JBuyDao {		
	//구매와 관련된 CRUD 실행 SQL. DAO : JCustomerDao, JProductDao
	//메소드 이름은 insert, update, delete, select, selectbyPname 등으로 이름 작성
	
	//트랜잭션을 처리하는 예시 : AUTO COMMIT을 해제하고 직접 COMMIT을 함
	//try catch 직접하기
	public int insertMany(List<JBuy> carts) {
			Connection con = OracleUtility.getConnection();
			//5. 상품 구매(결제)하기 - 장바구니의 데이터를 `구매` 테이블에 입력하기 (여러개 insert)	
			String sql = "INSERT INTO J_BUY values (jbuy_seq.nextval , ?, ?, ?, sysdate)";
			int count = 0;
			try {
			con.setAutoCommit(false);
			for(JBuy b : carts) {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1,b.getCustomid());
				ps.setString(2,b.getPcode());
				ps.setInt(3,b.getQuantity());
				count+=ps.executeUpdate();
				}
				con.commit();			//커밋
			} catch (SQLException e) {
				System.out.println("장바구니 상품 구매하기 예외 : " + e.getMessage());
				System.out.println("장바구니 상품 구매를 취소합니다.");
				try {
					con.rollback();		//롤백
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			return count;
		}
	
	
	//총 금액
	//함수 조회하는 SELECT는 항상 결과행이 1개, 컬럼도 1개
	public long selectprice(String customid) throws SQLException{
		Connection con = OracleUtility.getConnection();
		String sql="SELECT jb.customid,jb.PCODE, jp.PRICE, jb.QUANTITY FROM J_PRODUCT jp JOIN J_BUY jb ON JP.PCODE = JB.PCODE AND jb.CUSTOMID = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, customid);
		ResultSet rs = ps.executeQuery();
		int result= 0;
		while(rs.next()) {
			result += rs.getInt(3) * rs.getInt(4);
		}
		ps.close();
		con.close();
		return result;
	}

	public List<JBuyHis> buymany(String customid) throws SQLException{
		Connection con = OracleUtility.getConnection();
		String sql="SELECT jp.PNAME, jp.PRICE, jb.QUANTITY, jb.BUY_DATE FROM J_PRODUCT jp JOIN J_BUY jb ON JP.PCODE = JB.PCODE AND jb.CUSTOMID = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, customid);
		ResultSet rs = ps.executeQuery();
		List<JBuyHis> results = new ArrayList<>();
		while(rs.next()) {
			results.add(new JBuyHis(rs.getString(1),rs.getInt(2),rs.getInt(3),rs.getDate(4)));
		}
		ps.close();
		con.close();
		return results;
	}
	
	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	public class JBuyHis {
		private String pname;
		private int price;
		private int quantity;
		private Date buy_date;
		
		@Override
		public String toString() {
			return "상품 : " + pname + ", 가격 : " + price + ", 수량 : " + quantity + ", 구매 날짜: "+ buy_date +"\n";
		}

	}

	public int insert(JBuy buy) {
		return 1;
	}


	public JBuy selectOne(int buyseq) throws SQLException {
		Connection con = OracleUtility.getConnection();
		String sql = "SELECT * FROM J_BUY WHERE BUY_SEQ = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, buyseq);
		ResultSet rs = ps.executeQuery();
		JBuy result = null;
		if(rs.next()) {
			result = new JBuy(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getDate(5));
		}
		
		return result;
	}
}
