import java.util.LinkedList;
import java.util.List;

public class DungeonRoom {
	public int roomId;
	
	List<DungeonPath> possiblePaths = new LinkedList<DungeonPath>();
	
	public DungeonRoom(int roomId) {
		this.roomId = roomId;
	}
}