package BoatSearch;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		final Set<Person> people = new HashSet<Person>();
		
		final Scanner s = new Scanner(new File("input.txt"));

		if(s.hasNextInt()) {
			int length = s.nextInt();
			while (length > 0 && s.hasNextInt()) {
				people.add(new Person(s.nextInt()));
				length--;
			}
		}
		
		s.close();
		
		final State initialState = new State(people, new HashSet<Person>(), false);
		final FileWriter output = new FileWriter("output.txt");
		
		final GraphSearch bfsSearcher = new GraphSearch(initialState, new BreadthFrontier());
		output.write("BFS " + bfsSearcher.findSolution() + "\n\n");
		
		final GraphSearch dfsSearcher = new GraphSearch(initialState, new DepthFrontier());
		output.write("DFS " + dfsSearcher.findSolution() + "\n\n");
		
		final GraphSearch aStarSearcher = new GraphSearch(initialState, new AStarFrontier());
		output.write("A* " + aStarSearcher.findSolution());
		
		output.close();
		
		//so looking at output is less painful
		//http://www.adam-bien.com/roller/abien/entry/reading_a_file_into_a2
		final String check = new Scanner(new File("output.txt")).useDelimiter("\\Z").next();
        System.out.println("" + check);
	}

}
