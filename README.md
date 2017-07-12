# Page-Replacing-Simulator:
A simulator which uses the 3 page replacement algorithms LRU , Optimal and Second Chance and calculates page fault for them in a two mods!

This simulator is written in java.

# Algorithms Used:

There are 3 supported algorithms for simulation:

		* LRU(Least Recently Used)
		* Optimal
		* Second Chance

# Modes:

There are 2 mods defined in this program 
		
	Default: 
		in this mode we use a set of 5000 selections from memory and between 1 and 10 frames and the number in each member of set is between 1 and 50 and is created using Uniform Distribution.
	
	User Defined:
		in this mode the user defines the lenght of set and also the sets members are created by Normal Distribution which the mean and variance will be defined by user and also user can specify the range of the frames for example you can test and see if we start rising pages from 10 to 20 what will happen about page faults .

# Statistical diagram:

after you press the run button the program starts calculating and after that it gives the data to a LineChart created in a new fx form.


