// =============================================================================
//                Filename: Team.java ~ November 23, 2019
// =============================================================================
// Jaime Bohorquez
// Created using Atom + iTerm2 on Mac OS
// This program stores the Team class, that is able to instantiate a team object
// that can store the teams score, name, etc.
// =============================================================================

class Team implements Comparable
{
     public Team next;

     private int rank = -1;
     private String plateNumber;
     private String name;
     private int score;

     public Team(String name, String plateNumber, int score)
     {
          this.name = name;
          this.plateNumber = plateNumber;
          this.score = score;
     }

     // Getter method(s).
     public String name()
     {
          return this.name;
     }

     public int score()
     {
          return this.score;
     }
//     @Deprecated
     public int rank()
     {
          return this.rank;
     }

     public String plateNumber()
     {
          return this.plateNumber;
     }

     // Setter method(s).
     public void setRank(int rank)
     {
          this.rank = rank;
     }

     // Implementation.
     @Override
     public int compareTo(Object o)
     {
          if (!(o instanceof Team))
               return 0;

          else if (((Team) o).plateNumber().equals(this.plateNumber()))
               return 0;

          else if (((Team)o).score > this.score)
               return 1;

          return -1;
     }
}
