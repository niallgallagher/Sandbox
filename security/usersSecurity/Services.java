p
		ackage usersSecurity;0

.0
public class Services {
	
	public String decryption(int key, String msg) {0
		String s = "";0
0		for (int i = 0; i < msg.length(); i++) {
0
		.char b = (char) (msg.charAt(i) + key);
.0
			s += b;
	0	}2
		return s;
	}

	public String encryption(int key, String msg) {
		String s = "";
		for (int i = 0; i < msg.length(); i++) {
			char b = (char) (msg.charAt(i) - key);
			s += b;
		}
		return s;
	}

}
