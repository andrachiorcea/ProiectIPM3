import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Combine {

    public static void main (String[] argc) throws IOException {
        File path = new File("C:\\Users\\hriscu ilie\\Desktop\\INFO\\JAVA\\IP\\Combine\\src");


        BufferedImage image1 = ImageIO.read(new File(path, "1.png"));
        BufferedImage image2 = ImageIO.read(new File(path, "2.png"));

        // create the new image, canvas size is the max. of both image sizes
        int w = image1.getWidth() + image2.getWidth();
        int h =  Math.max(image1.getHeight(), image2.getHeight());
        BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        // paint both images, preserving the alpha channels
        Graphics g = combined.getGraphics();
        g.drawImage(image1, 0, 0, null);
        g.drawImage(image2, image1.getWidth(), 0, null);

        File outputfile = new File(path +"\\saved.png");
        // Save as new image
        ImageIO.write(combined, "png", outputfile);
    }

}
