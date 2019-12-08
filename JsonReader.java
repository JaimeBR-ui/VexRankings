// =============================================================================
//               Filename: JsonReader.java ~ November 24, 2019
// =============================================================================
// Jaime Bohorquez
// Created using Atom + iTerm2 on Mac OS
// This program stores the Team class, that is able to instantiate a team object
// that can store the teams score, name, etc.
// =============================================================================

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

class JsonReader
{
     protected static Team getRobotEventsScores(String fileName)
     {
          JSONParser parser = new JSONParser();
          Reader reader;
          JSONArray array = null;
          try
          {
               // Lets get the array within the json file.
               reader = new FileReader(fileName);
               array = (JSONArray) parser.parse(reader);
          }
          catch (Exception e)
          {
               e.printStackTrace();
          }

          // lets get the team array
          Team head = null;
          Team current = null;

          for (int i = 0; i < array.size(); i++)
          {
               // Gets the team from the array.
               JSONObject jsonTeam = (JSONObject) array.get(i);

               JSONObject teamData = (JSONObject) jsonTeam.get("team");

               String teamName = (String) teamData.get("teamName");
               String plateNumber = (String) teamData.get("team");

               JSONObject scores = (JSONObject) jsonTeam.get("scores");
               long score = (long) scores.get("score");

               Team team = new Team(teamName, plateNumber, (int) score);

               if (head == null)
               {
                    current = team;
                    head = current;
               }
               else
               {
                    current.next = team;
                    current = current.next;
               }

          }
          return head;
     }

     protected static Team getVexDBScores(String fileName)
     {
          JSONParser parser = new JSONParser();
          Reader reader;
          JSONArray array = null;
          int size = 0;
          try
          {
               // Lets get the array within the json file.
               reader = new FileReader(fileName);
               JSONObject container = (JSONObject) parser.parse(reader);
               long length = (long) container.get("size");
               size = (int) length;
               array = (JSONArray) container.get("result");
          }
          catch (Exception e)
          {
               e.printStackTrace();
          }

          // lets get the team array
          Team head = null, current = null;

          for (int i = 0; i < size; i++)
          {
               // Gets the team from the array.
               JSONObject jsonTeam = (JSONObject) array.get(i);

               String plateNumber = (String) jsonTeam.get("team");
               String teamName = getTeamName(plateNumber);

               long score = (long) jsonTeam.get("score");

               Team team = new Team(teamName, plateNumber, (int) score);

               if (head == null)
               {
                    current = team;
                    head = current;
               }
               else
               {
                    current.next = team;
                    current = current.next;
               }
          }
          return head;
     }

     protected static String getTeamName(String plateNumber)
     {
          String fileName = "./data/team_data.json";
          String link = "https://api.vexdb.io/v1/get_teams?team=" + plateNumber;
          URLWritter.writeToFile(link, fileName);

          // Read JSON file.
          JSONParser parser = new JSONParser();
          Reader reader;
          JSONArray array = null;
          int size = 0;
          try
          {
               // Lets get the array within the json file.
               reader = new FileReader(fileName);
               JSONObject container = (JSONObject) parser.parse(reader);
               long length = (long) container.get("size");
               size = (int) length;
               array = (JSONArray) container.get("result");
          }
          catch (Exception e)
          {
               e.printStackTrace();
          }

          // lets get the team array
          Team head = null, current = null;

          // Gets the team from the array.
          JSONObject jsonTeam = (JSONObject) array.get(0);

          String name = (String) jsonTeam.get("team_name");

          return name;
     }
}
