# Jaime Bohorquez

# File removes all the compiled java files in the directory. I could have used
# the command directly but one wrong move and I could delete my whole project.

# Start of script:
make:
	@echo "Building project..."
	@javac VexRankings.java
clean :
	@echo "Cleaning project"
	@-$Drm -rf rm *.class
run:
	@echo "Starting up:"
	@java VexRankings
all:
	@make clean
	@make

# Some things:
# @makes the cmd not print the output of the command
