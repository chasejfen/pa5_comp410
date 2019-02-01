
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static String[] vertices; // The sorted list of vertex names
	static AdjListNode[] adjList; // The adjacency list

	public static String[] topSort() {
		int[] inDegree = new int[vertices.length];
		String[] nulla = new String[vertices.length];
		for (int i = 0; i < nulla.length; i++)
			nulla[i] = null;
		for (int i = 0; i < inDegree.length; i++) {
			AdjListNode node = adjList[i];
			while (node != null) {
				int vertex = node.v;
				++inDegree[vertex];
				node = node.next;
			}
		}
		Queue<String> Q = new ArrayDeque<String>();
		// holding values with no
		// incoming vertexes
		for (int i = 0; i < inDegree.length; i++) {
			if (inDegree[i] == 0)
				Q.add(vertices[i]);
		}
		if (Q.isEmpty())
			return nulla;
		String[] sorted = new String[vertices.length];

		int count = 0;
		while (!Q.isEmpty()) {
			String vert = Q.remove();
			sorted[count] = vert;
			int in = Arrays.binarySearch(vertices, vert);
			AdjListNode node = adjList[in];
			while (node != null) {
				if (--inDegree[node.v] == 0)
					Q.add(vertices[node.v]);
				node = node.next;
			}
			count++;
		}
		if (count != vertices.length)
			return nulla;
		return sorted;
	}

	public static void main(String[] args) throws FileNotFoundException {
		if (args.length != 1) {
			System.err.println("Incorrect number of args passed");
			System.exit(-1);
		}
		Scanner fileIn = new Scanner(new File(args[0]));
		vertices = ReadGraph.readVertices(fileIn);
		adjList = ReadGraph.readEdgesAdjList(fileIn);
		String[] sorted = topSort();
	}
}
