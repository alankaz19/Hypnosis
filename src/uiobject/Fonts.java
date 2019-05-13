/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uiobject;

import java.awt.Font;

/**
 *
 * @author alank
 */
public class Fonts {
        public static Font getBitFont(int size) {
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, Fonts.class.getResourceAsStream("/Art/UI/fonts/font2.ttf"));
            font = font.deriveFont(Font.TRUETYPE_FONT, size);
            return font;
        } catch (Exception ex) {
            System.out.println("font not found");
        }
        return new Font(Font.MONOSPACED, Font.BOLD, size);
    }
        public static Font getHorrorFont(int size) {
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, Fonts.class.getResourceAsStream("/Art/UI/fonts/font5.ttc"));
            font = font.deriveFont(Font.TRUETYPE_FONT, size);
            return font;
        } catch (Exception ex) {
            System.out.println("font not found");
        }
        return new Font(Font.MONOSPACED, Font.BOLD, size);
    }
        public static Font getCrazyFont(int size) {
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, Fonts.class.getResourceAsStream("/Art/UI/fonts/font4.ttf"));
            font = font.deriveFont(Font.TRUETYPE_FONT, size);
            return font;
        } catch (Exception ex) {
            System.out.println("font not found");
        }
        return new Font(Font.MONOSPACED, Font.BOLD, size);
    }
}
