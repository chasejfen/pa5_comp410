
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class ReadGraph {
	static String[] vertices;

	public static String[] readVertices(Scanner fileIn) throws FileNotFoundException {
		int verNum = fileIn.nextInt();
		String[] outString = new String[verNum];
		for (int i = 0; i < verNum; i++) {
			outString[i] = fileIn.next();
		}
		Arrays.sort(outString);
		vertices = outString;
		return outString;
	}

	public static AdjListNode[] readEdgesAdjList(Scanner fileIn) {
		AdjListNode[] adjOut = new AdjListNode[vertices.length];
		fileIn.nextLine();
		fileIn.nextLine();

		while (fileIn.hasNext()) {
			String inVert = fileIn.next();
			String destVert = fileIn.next();
			int weight = fileIn.nextInt();
			int inIndx = Arrays.binarySearch(vertices, inVert);
			int desIndx = Arrays.binarySearch(vertices, destVert);
			if (adjOut[inIndx] == null)
				adjOut[inIndx] = new AdjListNode(desIndx, weight, null);
			else {
				AdjListNode existingNode = adjOut[inIndx];
				adjOut[inIndx] = new AdjListNode(desIndx, weight, existingNode);
			}
		}
		return adjOut;
	}

}
