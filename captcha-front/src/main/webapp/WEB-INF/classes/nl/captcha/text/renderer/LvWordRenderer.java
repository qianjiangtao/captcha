package nl.captcha.text.renderer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.image.BufferedImage;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 
 * @Title: LvWordRenderer.java
 * @desc 针对lvmama定制的
 * @author wangwenming
 * @date 2016年12月7日
 * @version v1.0
 */
public class LvWordRenderer implements WordRenderer {

	private static final Random RAND = new SecureRandom();
	private static final List<Color> DEFAULT_COLORS = new ArrayList<Color>();
	private static final List<Font> DEFAULT_FONTS = new ArrayList<Font>();
	static {
		DEFAULT_COLORS.add(Color.BLACK);
		DEFAULT_FONTS.add(new Font("Arial", Font.BOLD, 40));
		DEFAULT_FONTS.add(new Font("Courier", Font.BOLD, 40));
	}

	private final List<Color> _colors = new ArrayList<Color>();
	private final List<Font> _fonts = new ArrayList<Font>();
	private boolean isRoute;
	private int overlap;

	/**
	 * Use the default color (black) and fonts (Arial and Courier).
	 */
	public LvWordRenderer() {
		this(DEFAULT_COLORS, DEFAULT_FONTS, false, 0);
	}

	/**
	 * Build a <code>WordRenderer</code> using the given <code>Color</code>s and
	 * <code>Font</code>s.
	 * 
	 * @param colors
	 * @param fonts
	 */
	public LvWordRenderer(List<Color> colors, List<Font> fonts, boolean isRoute, int overlap) {
		_colors.addAll(colors);
		_fonts.addAll(fonts);
		this.isRoute = isRoute;
		this.overlap = overlap;
	}

	/**
	 * Render a word onto a BufferedImage.
	 * 
	 * @param word
	 *            The word to be rendered.
	 * @param image
	 *            The BufferedImage onto which the word will be painted.
	 */
	@Override
	public void render(final String word, BufferedImage image) {
		Graphics2D g = image.createGraphics();

		RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		hints.add(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
		g.setRenderingHints(hints);

		FontRenderContext frc = g.getFontRenderContext();
		int index = 0;
		double totalHeight = 0;
		char wordChars[] = word.toCharArray();
		Font chosenFonts[] = new Font[wordChars.length];
		int charWidths[] = new int[wordChars.length];
		int widthNeeded = 0;
		for (int i = 0; i < wordChars.length; i++) {
			int choiceFont = RAND.nextInt(_fonts.size());
			chosenFonts[i] = _fonts.get(choiceFont);
			char charToDraw[] = { wordChars[i] };
			GlyphVector gv = chosenFonts[i].createGlyphVector(frc, charToDraw);
			charWidths[i] = (int) gv.getVisualBounds().getWidth();
			totalHeight += gv.getVisualBounds().getHeight();
			if (overlap > 0 && i < wordChars.length - 1) {
				if (overlap < 100) {
					charWidths[i] = (int) (charWidths[i] * (100 - overlap) / 100.0);
				} else {
					charWidths[i] = charWidths[i] + (int) (2 * overlap / 100.0);
				}
			}
			widthNeeded += charWidths[i];
		}
		int height = (int) totalHeight / wordChars.length;
		int startPosY = (image.getHeight() - height) / 2 + height;
		int startPosX = (image.getWidth() - widthNeeded) / 2;
		for (int i = 0; i < wordChars.length; i++) {
			g.setColor(_colors.get(RAND.nextInt(_colors.size())));
			g.setFont(chosenFonts[i]);
			char charToDraw[] = { wordChars[i] };
			if (isRoute) {
				index++;
				double re = Math.floor(Math.random() * 7);
				if (index % 2 == 0) {
					g.rotate(-re * Math.PI / 180, startPosX, startPosY);
				} else {
					g.rotate(re * Math.PI / 180, startPosX, startPosY);
				}
			}
			g.drawChars(charToDraw, 0, charToDraw.length, startPosX, startPosY);
			startPosX = startPosX + charWidths[i];
		}

	}
}
