package com.debaura.db_automation_ptes.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.debaura.db_automation_ptes.page.LoginPage;

public class JdbcConexion {

    private static final String HOST = "localhost";
    private static final String PORT = "3306";
    private static final String DATABASE = "demo";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "********"; 

    private static final String SQL_QUERY = "SELECT * FROM credentials WHERE scenario = 'zerobalancecard'";
    private static final String LOGIN_URL = "https://login.app.com";

    private static final String USERNAME_CONSTANT = "username";
    private static final String PASSWORD_CONSTANT = "passwd";

    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE, DB_USERNAME, DB_PASSWORD);
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(SQL_QUERY);

            while (rs.next()) {
                WebDriver driver = new FirefoxDriver();
                driver.get(LOGIN_URL);
                LoginPage loginPage = new LoginPage(driver);
                String username = rs.getString(USERNAME_CONSTANT);
                String password = rs.getString(PASSWORD_CONSTANT);
                loginPage.login(username, password);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
        }
    }
}