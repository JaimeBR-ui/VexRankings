// =============================================================================
//                Filename: URLWritter.java ~ November 21, 2019
// =============================================================================
// Jaime Bohorquez
// Created using Atom + Terminal on Mac OS
// This program extracts data from websites and writes it to a file.
// =============================================================================

// package vexutilities;

import java.util.*;
import java.io.*;
import java.net.*;

class URLWritter
{
     private URLWritter() {}

     protected static void writeToFile(String linkToWebPage, String fileName)
     {
          URL url;
          FileWriter fileWriter;
          PrintWriter printWriter;
          URLConnection con;
          InputStream is;
          Scanner sc;

          try
          {
               url = new URL(linkToWebPage);
          }
          catch(MalformedURLException e)
          {
               System.out.println("Invalid URL: " + linkToWebPage + "\n" + e);
               return;
          }

          try
          {
               fileWriter = new FileWriter(fileName);
               printWriter = new PrintWriter(fileWriter);

               // Get the input stream through URL Connection.
               con = url.openConnection();
               is = con.getInputStream();
          }
          catch(IOException e)
          {
               System.out.println(e);
               return;
          }

          sc = new Scanner(new InputStreamReader(is));

          String line = null;

          // Read each line and write to file.
          while (sc.hasNext())
          {
               line = sc.nextLine();
               printWriter.print(line);
          }
          printWriter.close();
          sc.close();
     }
}
