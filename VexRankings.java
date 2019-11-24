// =============================================================================
//                Filename: VexRankings.java ~ November 21, 2019
// =============================================================================
// Jaime Bohorquez
// Created using Atom + Terminal on Mac OS
// This program will get the top 50 top scores for the Vex Robotics Competition
// skills challenge. This program will only work for the VexU division for now.
// =============================================================================

// package vexutilities;

import java.util.*;

public class VexRankings
{
     private static final String gradeLevel = "College";
     private static final String season = "Tower Takeover";

     private VexRankings() {}

     public static void main(String [] args)
     {
          String vexdbURL = getVexdbURL(gradeLevel, season);
          String robotEventsURL = getRobotEventsURL(gradeLevel, season);

          System.out.println("     Writing data to files...");
          URLWritter.writeToFile(vexdbURL, "./data/vexdb_rankings.json");
          URLWritter.writeToFile(robotEventsURL, "./data/robot_events_rankings.json");
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
          url += (season.split(" "))[0] + "%20";
          url += (season.split(" "))[1];

          if (gradeLevel.equals("College"))
               url += "&program=" + "VEXU&type=2";
          else
               return null;

          return url;
     }
}
