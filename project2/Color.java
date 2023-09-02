package project2;

/**
 * class representing different colors  
 * @author Dimitri Chaber
 * @version 9/11/2017
 */
//class uses Comparable interface
/**
 * @author Dimitri Chaber
 *
 */
public class Color implements Comparable<Color> {
	// date fields
	private String hexValue;
	private String name = "";
	private int r;
	private int g;
	private int b;

	// Color constructors

	// constructor using a hexidecimal value of format #XXXXXX
	public Color(String colorHexValue) {
		// checks if hexvalue is valid and throws exception if they are not
		if (colorHexValue.charAt(0) == '#' && colorHexValue.length() == 7) {
			hexValue = colorHexValue;
			// convert hex string into RGB values
			int[] array = new int[3];
			for (int i = 0; i < array.length; i++) {
				// cut hex string into smaller string and convert to decimal
				// integer
				array[i] = Integer.parseInt(colorHexValue.substring((i * 2) + 1, (i * 2) + 3), 16);
				// assign rgb values
				r = array[0];
				g = array[1];
				b = array[2];
			}
		} else
			throw new IllegalArgumentException("Invalid hexadecimal value, value must be in format of #XXXXXX");

	}

	// constructor using hexadecimal value and name of the color
	public Color(String colorHexValue, String colorName) {
		// call hex constructor
		this(colorHexValue);
		name = colorName;
	}

	// constructor using values of red green and blue
	public Color(int red, int green, int blue) {
		// check if rgb values are valid and throws exception if they are not
		if ((red >= 0 && red <= 255) && (blue >= 0 && blue <= 255) && (green >= 0 && green <= 255)) {
			r = red;
			b = blue;
			g = green;
		} else
			throw new IllegalArgumentException("RGB values are invalid");
		// convert RGB to hex
		hexValue = String.format("#%02X%02X%02X", r, g, b);
	}

	// data field setters and getters
	/**
	 * returns red value
	 * @return red value
	 */
	int getRed() {
		return r;
	}

	/**
	 * returns green value
	 * @return green value
	 */
	int getGreen() {
		return g;
	}

	/**
	 * returns blue value
	 * @return blue value
	 */
	int getBlue() {
		return b;
	}

	/**
	 * returns name
	 * @return name
	 */
	String getName() {
		return name;
	}

	/**
	 * returns hex value
	 * @return hex value
	 */
	String getHexValue() {
		return hexValue;

	}

	/**
	 * determines if 2 color objects are equal based on their hex values
	 * 
	 * @param color
	 *            object
	 * @return true or false
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Color)
			return hexValue.equalsIgnoreCase((((Color) obj).getHexValue()));
		else
			return false;

	}

	/**
	 * compares 2 color objects based on their hex value
	 * 
	 * @param color
	 *            object
	 * @return integer representing greater than, less than or equal
	 * 
	 */
	@Override
	public int compareTo(Color o) {
		return this.getHexValue().compareToIgnoreCase(o.getHexValue());

	}

	/**
	 * creates a string with information about color object
	 * 
	 * @return string in format (hexvalue, r , g ,b , name)
	 */
	@Override
	public String toString() {
		// check to see if name exists
		if (name != null) {
			return (String.format("%S, (%3d,%3d,%3d), %s", hexValue, r, g, b, name));

		} else
			return (String.format("%S, (%3d,%3d,%3d)", hexValue, r, g, b));
	}

}
