import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.util.Arrays;

/**
 * Created by Ian on 22-2-2017.
 */
public class AlphaTester {

    public static boolean testAlhpa(BufferedImage image1, BufferedImage image2) {
        int[] image1Pixels;
        int[] image2Pixels;

//        PixelGrabber grabber1 = new PixelGrabber(image1, 0, 0, image1.getWidth(), image1.getHeight(), false);
//        PixelGrabber grabber2 = new PixelGrabber(image2, 0, 0, image2.getWidth(), image2.getHeight(), false);
//        try {
//            if(grabber1.grabPixels()) {
//                image1Pixels = (int[]) grabber1.getPixels();
//            }
//            if(grabber2.grabPixels()) {
//                image2Pixels = (int[]) grabber2.getPixels();
//            }
//
//            if(Arrays.equals(image1Pixels, image2Pixels)) {
//                return true;
//            }
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }

        image1Pixels = image1.getAlphaRaster().getPixels(0, 0, image1.getWidth(), image1.getHeight(), new int[16 * 16]);
        image2Pixels = image2.getAlphaRaster().getPixels(0, 0, image2.getWidth(), image2.getHeight(), new int[16 * 16]);

        if(Arrays.equals(image1Pixels, image2Pixels)) {
            return true;
        }
        return false;
    }
}
