package helpers;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;
import java.io.IOException;


public class ImageGenerator {


    public File createTempImage() throws IOException {
        BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < 100; y++) {
            for (int x = 0; x < 100; x++) {
                image.setRGB(x, y, Color.BLUE.getRGB());
            }
        }

;
        File imageFile = new File("/shared/profile.jpg");
        imageFile.getParentFile().mkdirs();
        ImageIO.write(image, "jpg", imageFile);
        return imageFile;
        // File tempImage = File.createTempFile("test-image-", ".jpg");
        //ImageIO.write(image, "jpg", tempImage)
//        tempImage.deleteOnExit();
//        return tempImage;
    }

}
