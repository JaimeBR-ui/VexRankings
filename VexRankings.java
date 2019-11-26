// =============================================================================
//                Filename: VexRankings.java ~ November 21, 2019
// =============================================================================
// Jaime Bohorquez
// Created using Atom + iTerm2 on Mac OS
// This program will get the top 50 top scores for the Vex Robotics Competition
// skills challenge. This program will only work for the VexU division for now.
// =============================================================================

// package vexutilities;

import java.util.*;

public class VexRankings
{
     private static final String gradeLevel = "College";
     private static final String season = "Tower Takeover";
     private static final int numberOfRankings = 15;

     private static String vexDBFile = "./data/vexdb_rankings.json";
     private static String robotEventsFile = "./data/robot_events_rankings.json";

     private VexRankings() {}

     public static void main(String [] args)
     {
          String vexdbURL = getVexdbURL(gradeLevel, season);
          String robotEventsURL = getRobotEventsURL(gradeLevel, season);

          System.out.println("     Writing data to files...");
          URLWritter.writeToFile(vexdbURL, vexDBFile);
          URLWritter.writeToFile(robotEventsURL, robotEventsFile);

          // Read json files and return head of linked list.
          Team robotEvents = JsonReader.getJsonContents(robotEventsFile);
          Team vexDB = JsonReader.getJsonContents(robotEventsFile);

          System.out.println("     Readng files...");
          Set<Team> teams = new TreeSet<>();

          while (robotEvents != null)
          {
               teams.add(robotEvents);
               robotEvents = robotEvents.next;
          }

          while (vexDB != null)
          {
               teams.add(vexDB);
               vexDB = vexDB.next;
          }

          int i = 1;
          for (Team t : teams)
          {
               t.setRank(i);
               System.out.println(t.rank() + ". " + t.plateNumber()
                                                            + " " + t.score());
               i++;
          }

          System.out.println("     [Complete]");
     }

     public static String getRobotEventsURL(String gradeLevel, String season)
     {
          int seasonNumber;

          if (season.equals("Tower Takeover"))
               seasonNumber = 131;
          else
               return null;

          int includePostSeason = 0;
          String URL = "https://www.robotevents.com/api/seasons/";
          URL += seasonNumber + "/skills";
          URL += "?post_season=" + includePostSeason;
          URL += "&grade_level=" + gradeLevel;
          return URL;
     }

     public static String getVexdbURL(String gradeLevel, String season)
     {
          String url = "https://api.vexdb.io/v1/get_skills?season=";

          String [] subNames = season.split(" ");

          int length = subNames.length - 1;

          for (String e : subNames)
               url += e + ((e.equals(subNames[length])) ? "" : "%20");

          if (gradeLevel.equals("College"))
               url += "&program=" + "VEXU&type=2";
          else
               return null;

          return url;
     }
}
