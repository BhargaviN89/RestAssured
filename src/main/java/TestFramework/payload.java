package TestFramework;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import resources.dataDriven;

public class payload {
	
	public static String payloadData()
	{
		String payload = "{"+

			    "\"location\":{"+
			        "\"lat\" : -38.383494,"+
			        "\"lng\" : 33.427362"+
			    "},"+
			    "\"accuracy\":50,"+
			    "\"name\":\"Frontline house\","+
			    "\"phone_number\":\"(+91) 983 893 3937\","+
			    "\"address\" : \"29, side layout, cohen 09\","+
			    "\"types\": [\"shoe park\",\"shop\"],"+
			    "\"website\" : \"http://google.com\","+
			   "\"language\" : \"French-IN\""+
			"}";
		return payload;
	}
	
	public static String addBookPayload(String isbn, String aisle)
	{
		String addBook = "{\r\n" + 
				"\"name\":\"Learn Appium Automation with Java\",\r\n" + 
				"\"isbn\":\""+isbn+"\",\r\n" + 
				"\"aisle\":\""+aisle+"\",\r\n" + 
				"\"author\":\"John foe\"\r\n" + 
				"}\r\n" + 
				"";
		return addBook;
	}
	
	public static String jiraSessionPayload()
	{
		String jiraSession = "{ \"username\": \"bhargavi\", \"password\": \"rajamani22\" }";
		return jiraSession;
	}
	

	public static String createJiraIssuePayload()
	{
		String createJiraIssue = "{" + 
				"\"fields\": {" + 
				"\"project\": {" + 
				"\"key\": \"RES\"" + 
				"}," + 
				"\"summary\": \"New Debit Card Issue\"," + 
				"\"description\": \"JIRA with RestAssured\"," + 
				"\"issuetype\" : {" + 
				"\"name\" : \"Bug\"" + 
				"}" + 
				"}" + 
				"}";
		return createJiraIssue;
	}
	
	public static String createJiraCommentPayload()
	{
		String createJiraComment = "{" + 
				"\"body\": \"Adding comment to issue\"," + 
				"\"visibility\": {" + 
				"\"type\": \"role\"," + 
				"\"value\": \"Administrators\"" + 
				"}" + 
				"}";
		return createJiraComment;
	}
	
	public static String updateJiraCommentPayload()
	{
		String updateJiraComment = "{" + 
				"\"body\": \"Updating comment to issue\"," + 
				"\"visibility\": {" + 
				"\"type\": \"role\"," + 
				"\"value\": \"Administrators\"" + 
				"}" + 
				"}";
		return updateJiraComment;
	}
	
	public static HashMap<String, Object> addBookExcelPayload() throws IOException
	{
		dataDriven data = new dataDriven();
		ArrayList<String> a = data.dataDrivenExcel("RestAssured","RestAssuredBook");
		HashMap<String,Object> map = new HashMap<String, Object> ();
		map.put("name", a.get(1));
		map.put("isbn", a.get(2));
		map.put("aisle", a.get(3));
		map.put("author", a.get(4));
		
		return map;
		
	}
	
}
