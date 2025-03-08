package utilz;

public class Constants {
	
	public static class Directions {
		public static final int Up = 0;
		public static final int Right = 1;
		public static final int Down = 2;
		public static final int Left = 3;
	}
	
	
	
	public static class PlayerConstants {
		public static final int Idle = 0;
		public static final int Run = 1;
		public static final int Jump = 2;
		public static final int Attack = 3;
		public static final int Attack2 = 4;
		
		public static int GetAnimationIndex(int action) {
			
			switch (action) {
			case Idle:
				return 10;
			case Run:
				return 10;
			case Jump:
				return 3;
			case Attack:
				return 4;
			case Attack2:
				return 6;
			default:
				return 1;
			}
		}
	}
}
