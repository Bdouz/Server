package server.clip;

import java.util.logging.Logger;

public class ObjectDef {

	private static final Logger log = Logger.getLogger(ObjectDef.class
			.getName());

	public static ObjectDef class46;

	public static ObjectDef getObjectDef(int i) {
		if (i > streamIndices.length)
			i = streamIndices.length - 1;
		for (int j = 0; j < 20; j++)
			if (cache[j].type == i)
				return cache[j];
		cacheIndex = (cacheIndex + 1) % 20;
		ObjectDef class46 = cache[cacheIndex];
		stream.currentOffset = streamIndices[i];
		class46.type = i;
		class46.nullLoader();
		class46.readValues(stream);
		return class46;
	}


	public int[] solidObjects = { 1902, 1903, 1904, 1905, 1906, 1907, 1908,
			1909, 1910, 1911, 1912, 1536, 1535, 1537, 1538, 5139, 5140, 5141,
			5142, 5143, 5144, 5145, 5146, 5147, 5148, 5149, 5150, 1534, 1533,
			1532, 1531, 1530, 1631, 1624, 733, 1658, 1659, 1631, 1620, 14723,
			14724, 14726, 14622, 14625, 14627, 11668, 11667, 14543, 14749,
			14561, 14750, 14752, 14751, 1547, 1548, 1415, 1508, 1506, 1507,
			1509, 1510, 1511, 1512, 1513, 1514, 1515, 1516, 1517, 1518, 1519,
			1520, 1521, 1522, 1523, 1524, 1525, 1526, 1527, 1528, 1529, 1505,
			1504, 3155, 3154, 3152, 10748, 9153, 9154, 9473, 1602, 1603, 1601,
			1600, 9544, 9563, 9547, 2724, 6966, 6965, 9587, 9588, 9626, 9627,
			9596, 9598, 11712, 11713, 11773, 11776, 11652, 11818, 11716, 11721,
			14409, 11715, 11714, 11825, 11409, 11826, 11819, 14411, 14410,
			11719, 11717, 14402, 11828, 11772, 11775, 11686, 12278, 1853,
			11611, 11610, 11609, 11608, 11607, 11561, 11562, 11563, 11564,
			11558, 11616, 11617, 11625, 11624, 12990, 12991, 5634, 1769, 1770,
			135, 134, 11536, 11512, 11529, 11513, 11521, 11520, 11519, 11518,
			11517, 11516, 11514, 11509, 11538, 11537, 11470, 11471, 136, 11528,
			11529, 11530, 11531, 1854, 1000, 9265, 9264, 1591, 11708, 11709,
			11851 };

	public void setSolid(int type) {
		aBoolean779 = false;
		for (int i = 0; i < solidObjects.length; i++) {
			if (type == solidObjects[i]) {
				aBoolean767 = true;
				aBoolean779 = true;
				continue;
			}
		}

	}

	public void nullLoader() {
		anIntArray773 = null;
		anIntArray776 = null;
		name = null;
		description = null;
		modifiedModelColors = null;
		originalModelColors = null;
		anInt744 = 1;
		anInt761 = 1;
		aBoolean767 = true;
		aBoolean757 = true;
		hasActions = false;
		aBoolean762 = false;
		aBoolean769 = false;
		aBoolean764 = false;
		anInt781 = -1;
		anInt775 = 16;
		aByte737 = 0;
		aByte742 = 0;
		actions = null;
		anInt746 = -1;
		anInt758 = -1;
		aBoolean751 = false;
		aBoolean779 = true;
		anInt748 = 128;
		anInt772 = 128;
		anInt740 = 128;
		anInt768 = 0;
		anInt738 = 0;
		anInt745 = 0;
		anInt783 = 0;
		aBoolean736 = false;
		aBoolean766 = false;
		anInt760 = -1;
		anInt774 = -1;
		anInt749 = -1;
		childrenIDs = null;
	}

	private static Buffer stream;

	public static void loadConfig() {
		log.info("Unpacking object models...");
		stream = new Buffer(
				FileOperations.readFile("./Data/cache/world/object/loc.dat"));
		Buffer stream = new Buffer(
				FileOperations.readFile("./Data/cache/world/object/loc.idx"));
		int totalObjects = stream.readUnsignedWord();
		streamIndices = new int[totalObjects];
		int i = 2;
		for (int j = 0; j < totalObjects; j++) {
			streamIndices[j] = i;
			i += stream.readUnsignedWord();
		}
		cache = new ObjectDef[20];
		for (int k = 0; k < 20; k++) {
			cache[k] = new ObjectDef();
		}
		log.info("Loaded " + totalObjects + " Objects.");
	}

	public static byte[] getBuffer(String s) {
		try {
			java.io.File f = new java.io.File("Data/cache/world/object/" + s);
			if (!f.exists())
				return null;
			byte[] buffer = new byte[(int) f.length()];
			java.io.DataInputStream dis = new java.io.DataInputStream(
					new java.io.FileInputStream(f));
			dis.readFully(buffer);
			dis.close();
			return buffer;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void readValues(Buffer stream) {
		int flag = -1;
		do {
			int type = stream.readUnsignedByte();
			if (type == 0)
				break;
			if (type == 1) {
				int len = stream.readUnsignedByte();
				if (len > 0) {
					if (anIntArray773 == null || lowMem) {
						anIntArray776 = new int[len];
						anIntArray773 = new int[len];
						for (int k1 = 0; k1 < len; k1++) {
							anIntArray773[k1] = stream.readUnsignedWord();
							anIntArray776[k1] = stream.readUnsignedByte();
						}
					} else {
						stream.currentOffset += len * 3;
					}
				}
			} else if (type == 2)
				name = stream.readString();
			else if (type == 3)
				description = stream.readBytes();
			else if (type == 5) {
				int len = stream.readUnsignedByte();
				if (len > 0) {
					if (anIntArray773 == null || lowMem) {
						anIntArray776 = null;
						anIntArray773 = new int[len];
						for (int l1 = 0; l1 < len; l1++)
							anIntArray773[l1] = stream.readUnsignedWord();
					} else {
						stream.currentOffset += len * 2;
					}
				}
			} else if (type == 14)
				anInt744 = stream.readUnsignedByte();
			else if (type == 15)
				anInt761 = stream.readUnsignedByte();
			else if (type == 17)
				aBoolean767 = false;
			else if (type == 18)
				aBoolean757 = false;
			else if (type == 19)
				hasActions = (stream.readUnsignedByte() == 1);
			else if (type == 21)
				aBoolean762 = true;
			else if (type == 22)
				aBoolean769 = false;
			else if (type == 23)
				aBoolean764 = true;
			else if (type == 24) {
				anInt781 = stream.readUnsignedWord();
				if (anInt781 == 65535)
					anInt781 = -1;
			} else if (type == 28)
				anInt775 = stream.readUnsignedByte();
			else if (type == 29)
				aByte737 = stream.readSignedByte();
			else if (type == 39)
				aByte742 = stream.readSignedByte();
			else if (type >= 30 && type < 39) {
				if (actions == null)
					actions = new String[5];
				actions[type - 30] = stream.readString();
				if (actions[type - 30].equalsIgnoreCase("hidden"))
					actions[type - 30] = null;
			} else if (type == 40) {
				int i1 = stream.readUnsignedByte();
				modifiedModelColors = new int[i1];
				originalModelColors = new int[i1];
				for (int i2 = 0; i2 < i1; i2++) {
					modifiedModelColors[i2] = stream.readUnsignedWord();
					originalModelColors[i2] = stream.readUnsignedWord();
				}

			} else if (type == 60)
				anInt746 = stream.readUnsignedWord();
			else if (type == 62)
				aBoolean751 = true;
			else if (type == 64)
				aBoolean779 = false;
			else if (type == 65)
				anInt748 = stream.readUnsignedWord();
			else if (type == 66)
				anInt772 = stream.readUnsignedWord();
			else if (type == 67)
				anInt740 = stream.readUnsignedWord();
			else if (type == 68)
				anInt758 = stream.readUnsignedWord();
			else if (type == 69)
				anInt768 = stream.readUnsignedByte();
			else if (type == 70)
				anInt738 = stream.readSignedWord();
			else if (type == 71)
				anInt745 = stream.readSignedWord();
			else if (type == 72)
				anInt783 = stream.readSignedWord();
			else if (type == 73)
				aBoolean736 = true;
			else if (type == 74)
				aBoolean766 = true;
			else if (type == 75)
				anInt760 = stream.readUnsignedByte();
			else if (type == 77) {
				anInt774 = stream.readUnsignedWord();
				if (anInt774 == 65535)
					anInt774 = -1;
				anInt749 = stream.readUnsignedWord();
				if (anInt749 == 65535)
					anInt749 = -1;
				int j1 = stream.readUnsignedByte();
				childrenIDs = new int[j1 + 1];
				for (int j2 = 0; j2 <= j1; j2++) {
					childrenIDs[j2] = stream.readUnsignedWord();
					if (childrenIDs[j2] == 65535)
						childrenIDs[j2] = -1;
				}
			}
		} while (true);
		if (flag == -1 && name != "null" && name != null) {
			hasActions = anIntArray773 != null
					&& (anIntArray776 == null || anIntArray776[0] == 10);
			if (actions != null)
				hasActions = true;
		}
		if (aBoolean766) {
			aBoolean767 = false;
			aBoolean757 = false;
		}
		if (anInt760 == -1)
			anInt760 = aBoolean767 ? 1 : 0;
	}

	public ObjectDef() {
		type = -1;
	}

	public boolean hasActions() {
		return hasActions;
	}

	public boolean hasName() {
		return name != null && name.length() > 1;
	}

	public boolean solid() {
		return aBoolean779;
	}

	public int xLength() {
		return anInt744;
	}

	public int yLength() {
		return anInt761;
	}

	public boolean aBoolean767() {
		return aBoolean767;
	}

	public boolean aBoolean736;
	public byte aByte737;
	public int anInt738;
	public String name;
	public int anInt740;
	public byte aByte742;
	public int anInt744;
	public int anInt745;
	public int anInt746;
	public int[] originalModelColors;
	public int anInt748;
	public int anInt749;
	public boolean aBoolean751;
	public static boolean lowMem;
	public int type;
	public static int[] streamIndices;
	public boolean aBoolean757;
	public int anInt758;
	public int childrenIDs[];
	public int anInt760;
	public int anInt761;
	public boolean aBoolean762;
	public boolean aBoolean764;
	public boolean aBoolean766;
	public boolean aBoolean767;
	public int anInt768;
	public boolean aBoolean769;
	public static int cacheIndex;
	public int anInt772;
	public int[] anIntArray773;
	public int anInt774;
	public int anInt775;
	public int[] anIntArray776;
	public byte description[];
	public boolean hasActions;
	public boolean aBoolean779;
	public int anInt781;
	public static ObjectDef[] cache;
	public int anInt783;
	public int[] modifiedModelColors;
	public String actions[];

	public int actionCount() {
		return hasActions ? 1 : 0;
	}
	
}
