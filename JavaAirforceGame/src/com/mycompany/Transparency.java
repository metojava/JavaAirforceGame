package com.mycompany;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.io.File;

import javax.imageio.ImageIO;


public class Transparency {

	public static  BufferedImage makeTransparentBackground(String pathToImage)
	{
		BufferedImage bimage = null;
		try {
			bimage = ImageIO.read(new File(pathToImage));
		} catch (Exception e) {
			e.printStackTrace();
		}
		int color = bimage.getRGB(0, 0);
		
		final Color colore = new Color(color);
		
		int red = colore.getRed();
		int blue = colore.getBlue();
		int green = colore.getGreen();
		
		
		ImageFilter filter = new RGBImageFilter() {
			
			int trans = colore.getRGB() | 0xFF000000;
			
			@Override
			public int filterRGB(int x, int y, int rgb) {
				if((rgb | 0xFF000000)==trans)
					return rgb & 0x00FFFFFF;
				else
				return rgb;
			}
		};
		
		ImageProducer imageProducer = new FilteredImageSource(bimage.getSource(), filter);
		Image imageRes = Toolkit.getDefaultToolkit().createImage(imageProducer);
		return imageToBufferedImage(imageRes, bimage.getWidth(), bimage.getHeight());
	}
	
	public static  BufferedImage imageToBufferedImage(Image image, int width , int height)
	{
		BufferedImage bimage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = (Graphics2D)bimage.getGraphics();
		g2d.drawImage(image, 0, 0, null);
		g2d.dispose();
		return bimage;
		
	}
}
