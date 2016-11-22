package com.AlgoFocus.SynonymAPP.DAO;

public interface SynonymDAO 
{
	
	SynonymJavaBean wordSearch(String word);
	boolean Save(String Word, String Synonyms);
}
