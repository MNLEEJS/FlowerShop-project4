import java.sql.ResultSet;

public class MembershipMapper implements IResultMapper<Membership> {

	@Override
	public Membership resultMapping(ResultSet rs) {
		try {
			int no = rs.getInt("no");
			String name = rs.getString("name");
			String phoneNumber = rs.getString("phoneNumber");
			String id = rs.getString("id");
			String pw= rs.getString("pw");
			String address = rs.getString("address");
		
			return Membership.builder()
					.no(no)
					.name(name)
					.phonenumber(phoneNumber)
					.id(id)
					.pw(pw)
					.address(address)
					.build();
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
