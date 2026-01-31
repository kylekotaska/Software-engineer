import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Assignment6 {
	
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.println("Type in dungeon name to start.");
		
		String dungeonName = input.next();
		
		
		File dungeon = new File(dungeonName);
		
		try {
			Scanner fileReader = new Scanner(dungeon);
			
			
			
			int numRooms = fileReader.nextInt();
			String[] rooms = new String[numRooms];
			
			DungeonMap dungeonMap = new DungeonMap(numRooms);
			
			fileReader.nextLine();
			
			for (int i = 0; i < numRooms; i++) {
				rooms[i] = fileReader.nextLine();
			}
			
		
			
			for (int i = 0; i < rooms.length; i++) {
				String currentRoom = rooms[i];
				Scanner roomReader = new Scanner(currentRoom);
				
				int roomId = roomReader.nextInt();
				int roomConnections = roomReader.nextInt();
				
				DungeonRoom newRoom = new DungeonRoom(roomId);
				
				for (int k = 0; k < roomConnections; k++) {
					int nextRoomId = roomReader.nextInt();
					int nextRoomDanger = roomReader.nextInt();
					
					DungeonPath newPath = new DungeonPath(nextRoomId, nextRoomDanger);
					newRoom.possiblePaths.add(newPath);
				}
				
				dungeonMap.AddRoom(newRoom);
			}
			
			dungeonMap.PrintDungeon();
			
			dungeonMap.FindSafestPath(1, numRooms);
			
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			System.out.print(e);
		}
	}
}