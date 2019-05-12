package scene;

import java.awt.*;

import javax.swing.*;

/** Some static methods for some common painting functions.
 *
 * @version 1.0
 * @author Jeremy Wood
 **/
public class PaintUtil {
    private float alpha = 0;
    public static Color getFocusRingColor() {
        Object obj = UIManager.getColor("focusRing");
        if(obj instanceof Color)
            return (Color)obj;
        return new Color(255, 153, 0);


    }

    public static void paintFocus(Graphics2D g,Shape shape,int biggestStroke) {
        Color focusColor = getFocusRingColor();
        Color[] focusArray = new Color[] {
                new Color(focusColor.getRed(), focusColor.getGreen(), focusColor.getBlue(),255),
                new Color(focusColor.getRed(), focusColor.getGreen(), focusColor.getBlue(),170),
                new Color(focusColor.getRed(), focusColor.getGreen(), focusColor.getBlue(),110)
        };
        g.setStroke(new BasicStroke(biggestStroke));
        g.setColor(focusArray[2]);
        g.draw(shape);
        g.setStroke(new BasicStroke(biggestStroke-1));
        g.setColor(focusArray[1]);
        g.draw(shape);
        g.setStroke(new BasicStroke(biggestStroke-2));
        g.setColor(focusArray[0]);
        g.draw(shape);
        g.setStroke(new BasicStroke(1));
    }

}
