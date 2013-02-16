package test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import util.Utility;

public class Test {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
	/*	
		String line = "wc -l " + "/home/hathitrust/solr/ToVM_Solr_related/test/apache-tomcat-6.0.35/bin/proxy_logs/logfile";

		String out = Utility.exec(line);
		
		PrintWriter pw = new PrintWriter("stdout");
		
		pw.println(out);
		pw.flush();
		pw.close();
		System.out.println(out.contains(" "));
		System.out.println(out.contains("	"));*/
		
		System.out.println(Utility.convertDate2UTC("2012-11-04"));
		System.out.println(Utility.convertDate2UTC("2013-02-21"));
		
	}
}
