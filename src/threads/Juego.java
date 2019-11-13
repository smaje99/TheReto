package threads;
import arduino.Arduino;
import gui.Game;
public class Juego extends Temporizador {
    public static boolean lineGame;
    public static boolean stop;
    static {
        lineGame = false;
        stop = false;
    }
    public Juego(String clock, Game game) {
        super(clock, game);
    }
    @Override
    public void run() {
        lineGame = true;
        stop = false;
        updateTime(getGame().getTime());
        temporizador(getGame().getTime());
        lineGame = false;
        try {
            getGame().getContinuePlay().dispose();
        } catch (Exception e){//None
        }
        if (!stop){
            Arduino arduino = Arduino.getConexion();
            arduino.ledOffAll();
            getGame().estadisticas().setVisible(true);
        }
    }
    @Override
    public void action() {
        if (lineGame) super.action();
        else {
            setMinute(0);
            setSecond(0);
            updateTime(getGame().getTime());
        }
    }
}