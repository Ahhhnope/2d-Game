package GameStates;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import main.GamePanel;
import utilz.LoadSave;

public class Shop extends State implements StateMethods {
	private BufferedImage background;
	private BufferedImage KiemFree, Kiemdevo, Kiemsat, Kiemthan, Kiemtoithuong;
	private BufferedImage Thuocsucmanh, Thuoctocdo, Thuochoimau;
	private String ChatAgis = "Agis: Xin chào, tôi là chủ cửa hàng này bạn có muốn mua đồ gì không?";
	
	Rectangle KIEMFREE, KIEMDEVO;
	int mouseX, mouseY;
	
	public Shop(GamePanel gamePanel) {
		super(gamePanel);
		loadStuff();
		initItemBounds();
	}
	
	public void loadStuff() {
		background = LoadSave.GetSpriteAtlas(LoadSave.BackgroundShop);
		
		KiemFree = LoadSave.GetSpriteAtlas(LoadSave.Kiemfree);
		Kiemdevo = LoadSave.GetSpriteAtlas(LoadSave.Kiemdevo);
		Kiemsat = LoadSave.GetSpriteAtlas(LoadSave.Kiemsat);
		Kiemthan = LoadSave.GetSpriteAtlas(LoadSave.Kiemthan);
		Kiemtoithuong = LoadSave.GetSpriteAtlas(LoadSave.Kiemtoithuong);
		
		Thuochoimau = LoadSave.GetSpriteAtlas(LoadSave.Thuochoimau);
		Thuocsucmanh = LoadSave.GetSpriteAtlas(LoadSave.Thuocsucmanh);
		Thuoctocdo = LoadSave.GetSpriteAtlas(LoadSave.Thuoctocdo);
	}
	
	public void initItemBounds() {
		KIEMFREE = new Rectangle(155, 170, KiemFree.getWidth(), KiemFree.getHeight());
		KIEMDEVO = new Rectangle(288, 175, Kiemdevo.getWidth(), Kiemdevo.getHeight());
	}

	@Override
	public void update() {

	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(background, 0, 0, GamePanel.game_width, GamePanel.game_height, null);
		
		g.drawImage(KiemFree, 155, 170,(int) (KiemFree.getWidth() / 9 * GamePanel.scale), (int) (KiemFree.getHeight() / 9 * GamePanel.scale), null);
		g.drawImage(Kiemdevo, 285, 175,(int) (Kiemdevo.getWidth() / 7 * GamePanel.scale), (int) (Kiemdevo.getHeight() / 7 * GamePanel.scale), null);
		g.drawImage(Kiemsat, 410, 172,(int) (Kiemsat.getWidth() / 7 * GamePanel.scale), (int) (Kiemsat.getHeight() / 7 * GamePanel.scale), null);
		g.drawImage(Kiemtoithuong, 530, 172,(int) (Kiemtoithuong.getWidth() / 12 * GamePanel.scale), (int) (Kiemtoithuong.getHeight() / 12 * GamePanel.scale), null);
		g.drawImage(Kiemthan, 165, 260,(int) (Kiemthan.getWidth() / 5 * GamePanel.scale), (int) (Kiemthan.getHeight() / 5 * GamePanel.scale), null);
		
		g.drawImage(Thuochoimau, 290, 255,(int) (Thuochoimau.getWidth() / 5 * GamePanel.scale), (int) (Thuochoimau.getHeight() / 5 * GamePanel.scale), null);
		g.drawImage(Thuocsucmanh, 410, 256,(int) (Thuocsucmanh.getWidth() / 5 * GamePanel.scale), (int) (Thuocsucmanh.getHeight() / 5 * GamePanel.scale), null);
		g.drawImage(Thuoctocdo, 530, 248,(int) (Thuoctocdo.getWidth() / 5 * GamePanel.scale), (int) (Thuoctocdo.getHeight() / 5 * GamePanel.scale), null);
		
	    g.setColor(java.awt.Color.WHITE);
	    g.setFont(new java.awt.Font("Press Start 2P", java.awt.Font.BOLD, 20));
	    g.drawString(ChatAgis, 25, 500);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (KIEMFREE.contains(e.getX(), e.getY())) {
			ChatAgis = "lmao thằng nghèo";
		}
		
		if (KIEMDEVO.contains(e.getX(), e.getY())) {
			ChatAgis = "lmao thằng giau";
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
