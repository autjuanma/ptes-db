package com.debaura.db_automation_ptes.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class JdbcConexion {

	public static void main(String[] args) {

		String host = "localhost";
		String port = "3306";

		try {

			Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/demo", "root",
					"********");
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery("select * from credentials where scenario = 'zerobalancecard'");

			while (rs.next()) {

				WebDriver driver = new FirefoxDriver();
				driver.get("https://login.salesforce.com");
				driver.findElement(By.xpath(".//*[@id='username']")).sendKeys(rs.getString("username"));
				driver.findElement(By.xpath("//input[@id='password']")).sendKeys(rs.getString("passwd"));
				driver.findElement(By.xpath("//input[@id='Login']")).click();

			}
		} catch (Exception e) {
		}

	}

}
