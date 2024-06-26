package server.tick;


import java.util.LinkedList;
import java.util.List;

/**
 * A class that manages <code>Tickable</code>s for a specific
 * <code>GameEngine</code>.
 * @author Michael Bull
 *
 */
public class TickableManager {
	
	/**
	 * The list of tickables.
	 */
	private List<Tickable> tickables = new LinkedList<Tickable>();
	
	/**
	 * @return The tickables.
	 */
	public List<Tickable> getTickables() {
		return tickables;
	}
	
	/**
	 * Submits a new tickable to the <code>GameEngine</code>.
	 * @param tickable The tickable to submit.
	 */
	public void submit(final Tickable tickable) {
		tickables.add(tickable);
	}	
	
}
