// =============================================================================
//                Filename: VexTeam.java ~ November 23, 2019
// =============================================================================
// Jaime Bohorquez
// Created using Atom + Terminal on Mac OS
// This program stores the Team class, that is able to instantiate a team object
// that can store the teams score, name, etc.
// =============================================================================

class Team implements Comparable
{
     private int rank = -1;
     private String name;
     private int score;

     public Team(String name, int score)
     {
          this.name = name;
          this.score = score;
     }

     // Getter method(s).
     public String getName()
     {
          return this.name;
     }

     public int getScore()
     {
          return this.score;
     }

     public int getRank()
     {
          return this.rank;
     }

     // Setter method(s).
     public void setRank(int rank)
     {
          this.rank = rank;
     }

     // Implementation.
     public int compareTo(Object o)
     {
          return 0;
     }
}
