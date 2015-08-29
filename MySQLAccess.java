package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class MySQLAccess {
  private Connection connect = null;
  private Statement statement = null;
  private PreparedStatement preparedStatement = null;
  private ResultSet resultSet = null;

	public void readDataBase() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(
					"jdbc:mysql://us-cdbr-iron-east-02.cleardb.net/ad_0235f762bbc6a49?user=b5e54b8d9b8c40&password=b2a198fc");
			statement = connect.createStatement();
			resultSet = statement.executeQuery("select * FROM jira_module");
			writeResultSet(resultSet);
			
		      preparedStatement = connect
		          .prepareStatement("INSERT INTO customer_commands values (?, ?, ?, ?, ? , ?, ?, ?)");
		      preparedStatement.setString(1, "title");
		      preparedStatement.setString(2, "commands");
		      preparedStatement.setString(3,  "category");
		      preparedStatement.setString(4, "module_name");
		      preparedStatement.setString(5, "issensitive");
		      preparedStatement.setString(6,  "user_mail_id");
		      preparedStatement.setString(7, "dev_group_id");
		      preparedStatement.setDate(4, (java.sql.Date) new Date());
		      preparedStatement.executeUpdate();

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}
	}

	private void writeResultSet(ResultSet resultSet) throws SQLException {
		while (resultSet.next()) {
			String moduleName = resultSet.getString("module_name");
			String mailId = resultSet.getString("mail_id");
			System.out.println("moduleName: " + moduleName);
			System.out.println("mailId: " + mailId);
		}
	}

	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {

		}
	}

}
