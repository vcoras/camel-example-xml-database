/**
 * This class should only be able to handle 
 * reading and writing of an .xml file
 * 
 * TODO: Include some form of security
 */

package org.apache.camel.example.xmldb.util;

import java.io.File;

 public class Database {

    private String filePath = "";
    
    // Constructors
    public Database() { this.filePath = "database/default.xml"; }
    public Database(String filePath) { this.filePath = filePath; }

    public File read() {
        File inputFile = new File(this.filePath);

        return inputFile;
    }

    // For debugging purposes
    public static void main(String[] args) {

    }
 }