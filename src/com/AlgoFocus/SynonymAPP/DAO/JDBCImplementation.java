package com.AlgoFocus.SynonymAPP.DAO;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;


public class JDBCImplementation implements SynonymDAO {
	Connection con = null;
	PreparedStatement pstmt =  null;
	ResultSet res = null;

	public SynonymJavaBean wordSearch(String word) {

		try {

			// 1. Load the Driver

			Class.forName("com.mysql.jdbc.Driver");

			// 2. Get the DB Connection via Driver

			String url = "jdbc:mysql://localhost:3306/WordSynonym";
			FileReader reader = new FileReader("E:\\My Workspace\\SynonymSearchApp\\WebContent\\credenti.properties");
			Properties prop = new Properties();
			prop.load(reader);
			
			con = DriverManager.getConnection(url, prop);
			
			
			// 3. Issue SQL Queries Via Connection
			String query = " select * from words_synonyms word_tbl, posts post_tbl "+ 
							" where word_tbl.word=post_tbl.word and post_tbl.word = ? ";
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, word);
			
			res = pstmt.executeQuery();
										
			
			// 4. Process the Results
			if (res.next()) 
			{
				SynonymJavaBean jbean = new SynonymJavaBean();
				
				System.out.println("SynonymJavaBean-----"+jbean);
				
				//data.setRegno(rs.getInt("si.regno"));

				jbean.setSynonym(res.getString("word_tbl.synonym"));
				jbean.setWord(res.getString("word_tbl.word"));
				jbean.setPost_content(res.getString("post_tbl.post_content"));
				
				
				return jbean;
				
			} else 
			{
				System.out.println("No data");
				
				return null;
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {

			try {
				if (con != null) {
					con.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public boolean Save(String Word, String Synonyms) 
	{
		Connection con = null;
		PreparedStatement pstmt = null;
					try {
							// 1. Load the Driver
							Class.forName("com.mysql.jdbc.Driver");
	
							// 2. Get the DB Connection via Driver
	
							String url = "jdbc:mysql://localhost:3306/wordsynonyminsert";
							FileReader reader = new FileReader("E:\\My Workspace\\SynonymSearchApp\\WebContent\\credenti.properties");
							Properties prop = new Properties();
							prop.load(reader);
							
							con = DriverManager.getConnection(url, prop);
							
							
							// 3. Issue SQL Queries Via Connection
							String query = "insert into words_synonyms values(?,?)";
							pstmt = con.prepareStatement(query);
							pstmt.setString(1, Word);
							pstmt.setString(2, Synonyms);
							
							int count = pstmt.executeUpdate();
							
							if(count > 0)
							{
								return true;
							}
							else
							{
								return false;
							}
							
						
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return false;
						}
		
		
		return false;
	}

}
