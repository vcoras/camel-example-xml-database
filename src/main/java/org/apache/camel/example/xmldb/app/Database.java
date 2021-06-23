/**	
 * This class should only be able to handle 	
 * reading and writing of an .xml file	
 * 	
 * TODO: Include some form of security	
 */	

package org.apache.camel.example.xmldb.app;	

import java.io.File;	

 public class Database {	

    private String filePath = "";	

    // Constructors	
    public Database() { super(); this.filePath = "database.xml"; }	
    public Database(String filePath) { super(); this.filePath = filePath; }	

    public File read() {	
        File inputFile = new File(this.filePath);	

        return inputFile;	
    }	

    // For debugging purposes	
    public static void main(String[] args) {	

    }	
 }