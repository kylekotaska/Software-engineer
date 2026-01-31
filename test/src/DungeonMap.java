import java.util.LinkedList;
import java.util.List;

public class DungeonMap{
	
	
	public DungeonMap(int roomAmt) {
		dungeonRooms = new DungeonRoom[roomAmt];
	}
	public DungeonRoom[] dungeonRooms = new DungeonRoom[0];
	
	
	public void AddRoom(DungeonRoom room) {
		dungeonRooms[room.roomId - 1] = room;
	}
	
	public void ConnectRooms(int roomId1, int roomId2, int dangerLevel) {
		DungeonPath newPath = new DungeonPath(roomId2, dangerLevel);
		dungeonRooms[roomId1 - 1].possiblePaths.add(newPath);
	}
	
	
	private List<RoomNode> GetNeighboringNodes(RoomNode currentNode, List<RoomNode> allNodes) {
		
		DungeonRoom nodeRoom = currentNode.room;
		List<DungeonPath> roomPaths = nodeRoom.possiblePaths;
		List<RoomNode> neighboringNodes = new LinkedList<RoomNode>();
		
		for (int i = 0; i < roomPaths.size(); i++) {
			int destinationId = roomPaths.get(i).destinationRoomId;
			int dangerLevel = roomPaths.get(i).dangerLevel;
			
			RoomNode neighboringNode = allNodes.get(destinationId - 1);
			neighboringNode.danger = dangerLevel;
			neighboringNodes.add(neighboringNode);
					
		}
		
		
		return neighboringNodes;
	}
	
	private List<RoomNode> PathFind(List<RoomNode> roomNodes, int startId, int endId) {
		
		if (startId == endId) {
			return roomNodes;
		}
		
		RoomNode currentNode = roomNodes.get(startId - 1);
		int currentDanger = currentNode.danger;
		
	
		
		
		List<RoomNode> neighborNodes = GetNeighboringNodes(currentNode, roomNodes);
		
		currentNode.visited = true;
		RoomNode nextNode = null;
		for (int i = 0; i < neighborNodes.size(); i++) {
			
			RoomNode neighbor = neighborNodes.get(i);
			
			if (neighbor.visited == false) {
				
				neighbor.INF = false;
				neighbor.danger += currentDanger;
				neighbor.prevNode = currentNode;
			
			}
		}
		
		
		int minDanger = 0;
		boolean minDangerSet = false;
		for (int i = 0; i < roomNodes.size(); i++) {
			if (roomNodes.get(i).INF == false && roomNodes.get(i).visited == false)   {
				if (minDangerSet == false) {
					minDanger = roomNodes.get(i).danger;
					nextNode = roomNodes.get(i);
				
				}
				else {
					if (roomNodes.get(i).danger < minDanger) {
						minDanger = roomNodes.get(i).danger;
						nextNode = roomNodes.get(i);
					
					}
				}
			}
		}
		if (nextNode == null) {
			
			return null;
		}
		

		return PathFind(roomNodes, nextNode.room.roomId, endId);
		
		
		
		
	}
	
	private class RoomNode {
		DungeonRoom room;
		int danger;
		RoomNode prevNode = null;
		boolean visited = false;
		boolean INF = true;
	}
	
	public List<RoomNode> BackTrack(RoomNode current, List<RoomNode> path) {
		if (current.prevNode == null) {
			return path;
		}
		if (current.prevNode != null) {
			path.add(current.prevNode);
		}
		return BackTrack(current.prevNode, path);	
	}
	
	public List<DungeonRoom> FindSafestPath(int startId, int endId) {
		List<DungeonRoom> safestPath = new LinkedList<DungeonRoom>();
		List<RoomNode> roomNodes = new LinkedList<RoomNode>();
		
		
		for (int i = 0; i < dungeonRooms.length; i++) {
			RoomNode newNode = new RoomNode();
			
			newNode.room = dungeonRooms[i];
			
			if (i == 0) {
				newNode.danger = 0;
				newNode.INF = false;
			}
			
			roomNodes.add(newNode);
		}
		
		
		roomNodes = PathFind(roomNodes, startId, endId);
		
		if (roomNodes == null) {
			System.out.println("No safe path to the exit. You're trapped!");
		}
		else {
			List<RoomNode> path = new LinkedList<RoomNode>();
			
			path.add(roomNodes.get(endId - 1));
			BackTrack(roomNodes.get(endId - 1), path);
			
			int totalDanger = roomNodes.get(endId - 1).danger;
			
			for (int i = path.size() - 1; i > 0; i--) {
				DungeonRoom initialRoom = path.get(i).room;
				DungeonRoom finalRoom = path.get(i-1).room;
				int dangerLevel = 0;
				
				for (int k = 0; k < initialRoom.possiblePaths.size(); k++) {
					if (initialRoom.possiblePaths.get(k).destinationRoomId == finalRoom.roomId) {
						dangerLevel = initialRoom.possiblePaths.get(k).dangerLevel;
					}
				}
				
				System.out.print("Room " + path.get(i).room.roomId + " -> " + path.get(i-1).room.roomId);
				System.out.println(" (Danger Level: " + dangerLevel + ")");
				
			}
			System.out.println();
			System.out.println("Total Danger Level: " + totalDanger);
			
			return safestPath;
		}
		return null;
	}
	
	public void PrintDungeon() {
		for (int i = 0; i < dungeonRooms.length; i++) {
			DungeonRoom currentRoom = dungeonRooms[i];
			List<DungeonPath> roomPaths = currentRoom.possiblePaths;
			
			
			
			for (int k = 0; k < roomPaths.size(); k++) {
				DungeonPath currentPath = roomPaths.get(k);
				int destinationId = currentPath.destinationRoomId;
				int danger = currentPath.dangerLevel;
			
			
//				
			}
		
		}
	}
	
}