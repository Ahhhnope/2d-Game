package utilz;

import main.GamePanel;

public class Constants {
	
	public static class EnemyConstants {
		public static final int Skeleton = 1;
		
		public static final int Idle = 0;
		public static final int Running = 0;
		public static final int Attack = 0;
		public static final int Hit = 0;
		public static final int Dead = 0;
	}
	
	public static class UI {
		public static class Buttons {
			public static final int Button_Default_Width = 140;
			public static final int Button_Default_Height = 56;
			public static final int Button_Width = (int) (Button_Default_Width * GamePanel.scale);
			public static final int Button_Height = (int) (Button_Default_Height * GamePanel.scale);
			
		}
		
		public static class PauseButton {
			public static final int Sound_Button_Default_Size = 42;
			public static final int Sound_Button_Size = (int) (Sound_Button_Default_Size * GamePanel.scale);
		}
		
		public static class UtilButton {
			public static final int Util_Button_Default_Size = 56;
			public static final int Util_Button_Size = (int) (Util_Button_Default_Size * GamePanel.scale);
		}
		
		public static class VolumeButtons {
			public static final int Volume_Default_Width = 28;
			public static final int Volume_Default_Height = 44;
			public static final int Volume_Slider_Default_Width = 215;
//			Slider and Volume both have the same Height btw :)))
			
			public static final int Volume_Width = (int) (Volume_Default_Width * GamePanel.scale);
			public static final int Volume_Height = (int) (Volume_Default_Height * GamePanel.scale);
			public static final int Volume_Slider_Width = (int) (Volume_Slider_Default_Width * GamePanel.scale);

		}
	}
	
	
	public static class Directions {
		public static final int Up = 0;
		public static final int Right = 1;
		public static final int Down = 2;
		public static final int Left = 3;
	}
	
	
	
	public static class PlayerConstants {
		public static final int Idle = 0;
		public static final int Run = 3;
		public static final int Attack = 8;
		public static final int Jump = 5;
		public static final int Falling = 2;
		
		public static int GetAnimationIndex(int action) {
			
			switch (action) {
			case Idle:
				return 2;
			case Run:
				return 8;
			case Attack:
				return 5;
			case Jump:
				return 8;
			case Falling:
				return 2;
			default:
				return 1;
			}
		}
	}
}
