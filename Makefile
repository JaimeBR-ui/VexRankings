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
cmr:
	@make clean
	@make
	@make run
mr:
	@make
	@make run

# Some things:
# @ makes the file not print the command running into the cmd
# -$Drm -rf makes the output of the command not print to the cmd
