package java_jdbc.java_jdbc;

import org.junit.Test;
import conexaojdbc.SingleConnection;

public class TesteBancoJDBC {
	@Test
	public void initBanco() {
		SingleConnection.getConnection();
	}
}