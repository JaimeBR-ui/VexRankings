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
     private static final int numberOfRankings = 50;

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
          Team robotEvents = JsonReader.getRobotEventsScores(robotEventsFile);
          Team vexDB = JsonReader.getVexDBScores(vexDBFile);

          System.out.println("     Reading files...");
          Map<String, Team> teams = new TreeMap<>();

          while (robotEvents != null)
          {
               Team t = teams.get(robotEvents.plateNumber());

               if (t == null || t.score() < robotEvents.score())
                    teams.put(robotEvents.plateNumber(), robotEvents);

               robotEvents = robotEvents.next;
          }

          while (vexDB != null)
          {
               Team t = teams.get(vexDB.plateNumber());

               if (t == null || t.score() < vexDB.score())
                    teams.put(vexDB.plateNumber(), vexDB);
               vexDB = vexDB.next;
          }

          printRankings(teams);

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

          System.out.println(url);
          return url;
     }

     protected static void printRankings(Map<String, Team> teams)
     {
          int i = 1;
          Set<String> set = teams.keySet();

          List<Team> list = new ArrayList<>();

          for (String s : set)
               list.add(teams.get(s));

          Comparator<Team> scoreOrder =  new Comparator<Team>()
          {
               public int compare(Team t1, Team t2)
               {
                    return t2.score() - t1.score();
               }
          };

          Collections.sort(list, scoreOrder);

          for (Team t : list)
          {
               t.setRank(i);

               if (i < 10)
                    System.out.print(" ");

               System.out.print(t.rank() + ". " + t.plateNumber());

               for (int j = 0; j < 6 - t.plateNumber().length(); j++)
                    System.out.print(" ");

               System.out.println("\t" + t.score() + "\t" + t.name());
               if (++i > numberOfRankings)
                    break;
          }
     }
}
