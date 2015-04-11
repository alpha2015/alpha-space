package net.alpha.support;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface PreparedStatementSetter {
	public void setParameters(PreparedStatement pstmt) throws SQLException;
}
