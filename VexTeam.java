// =============================================================================
//                Filename: VexTeam.java ~ November 23, 2019
// =============================================================================
// Jaime Bohorquez
// Created using Atom + iTerm2 on Mac OS
// This program stores the VexTeam class, that is able to instantiate a VexTeam
//object that can store the teams score, name, etc.
// =============================================================================

class VexTeam extends Team implements Comparable
{
     public VexTeam(String name, String plateNumber, int score)
     {
          super(name, plateNumber, score);
     }
}
