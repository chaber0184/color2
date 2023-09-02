/**
 * 
 */
package project2;

/**
 * class that contains list of color objects
 * inherits from OrderedLinkedList<E>
 * @author Dimitri Chaber
 * @version 9/25/2017
 */
public class ColorList extends OrderedLinkedList<Color> {

	public ColorList() {

	}

	/**
	 * Finds color object with name matching argument
	 * 
	 * @param colorName
	 * @return color object with matching name if it exists
	 */
	public Color getColorByName(String colorName) {
		for (int i = 0; i < super.size(); i++) {
			if (super.get(i).getName().equalsIgnoreCase(colorName))
				return super.get(i);
		}
		//if no match is found returns null
		return null;
	}

	/**
	 * Find color object with hex matching argument
	 * 
	 * @param colorHexValue
	 * @return color object with matching hex if it exists
	 */
	public Color getColorByHexValue(String colorHexValue) {
		for (int i = 0; i < super.size(); i++) {
			if (super.get(i).getHexValue().equalsIgnoreCase(colorHexValue))
				return super.get(i);
		}
		//if no match is found returns null
		return null;
	}

}
