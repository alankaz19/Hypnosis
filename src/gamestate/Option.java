package gamestate;
import scene.AudioManager;
import scene.Texture;
import java.awt.*;
import java.awt.image.BufferedImage;
import uiobject.Button;
import uiobject.HintBox;

public class Option extends GameState {
    private BufferedImage base;
    private Button resume;
    private Button help;
    private Button home;
    private Button exit;
    private HintBox hint;
    

    protected Option(GameStateManager gsm) {
        super(gsm);
        this.base = Texture.getInstance().ui[2];
    }

    @Override
    public Option getInstance() {
        return null;
    }

    @Override
    public void init() {
        resume = new Button(805,225,200,100,Texture.getInstance().button[7],Texture.getInstance().button[6],1);
        help = new Button(805,325,200,100,Texture.getInstance().button[3],Texture.getInstance().button[2],1);
        home = new Button(805,425,200,100,Texture.getInstance().button[5],Texture.getInstance().button[4],1);
        exit = new Button(955,523,200,100,Texture.getInstance().button[1],Texture.getInstance().button[0],1);
        hint = new HintBox(8,200, 80, 500, 583);
    }

    @Override
    public void tick() {
        event();
    }

    @Override
    public void event() {
        setHovered(resume);
        setHovered(help);
        setHovered(home);
        setHovered(exit);
    }

    @Override
    public void render(Graphics g) {
        this.fadeIn(g);
        g.drawImage(base,700,80,400,600,null);
        g.drawImage(Texture.getInstance().background[11], 0, 0,1280,720, null);
        resume.render(g);
        help.render(g);
        home.render(g);
        if(help.isClicked()){
            hint.fadeIn(g, 0.02f);
//            exit.render(g);
        }
    }

    @Override
    public void keyPressed(int k) {
    }

    @Override
    public void keyReleased(int k) {

    }

    @Override
    public void mousePressed(int x, int y) {
        AudioManager.getInstance().getPlayList()[AudioManager.LEVEL_ONE_CLICK].play();
        //返回遊戲
        if(resume.isHovered()){
            gsm.setState(gsm.getPrevState());
        }
        //遊戲說明
        if(help.isHovered()){
            help.setClicked(true);
        }
        
        if(exit.isHovered()){
            help.setClicked(false);
        }
        
        //返回主選單
        if(home.isHovered()){
            gsm.setState(GameStateManager.MENU_STATE);
            AudioManager.getInstance().getPlayList()[AudioManager.LEVEL_TWO_BACKGROUND].stop();
            AudioManager.getInstance().getPlayList()[AudioManager.LEVEL_THREE_BACKGROUND].stop();
            AudioManager.getInstance().getPlayList()[AudioManager.LEVEL_ONE_BACKGROUND].stop();
            AudioManager.getInstance().getPlayList()[AudioManager.END_SCENE_BACKGROUND].stop();
        }
    }

    @Override
    public void mouseDragged(int x, int y) {
    }

    @Override
    public void mouseReleased(int x, int y) {
    }

    @Override
    public void mouseMoved(int x, int y) {
        this.setMousePos(x, y);
    }
    
    private void setHovered(Button button) {
        if (this.mouseX >= button.getX() && this.mouseX <= button.getX() + button.getWidth() && this.mouseY >= button.getY() + 25 && this.mouseY <= button.getY() + button.getHeight() - 20) {
            button.setHovered(true);
        }else{
            button.setHovered(false);
        }
    }
    
}
