/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converttest;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author cbao
 */
public class Converttest {

    /**
     * @param args the command line arguments
     */
    private static final String ASCII_CHARS = "@%#*+=-:. ";

    public static void main(String[] args) {
        String userHome = System.getProperty("user.home");
        String imagePath = userHome + "\\Downloads\\anh.jpg";
        int asciiWidth = 100; // Độ rộng của ảnh ASCII (số ký tự)

        try {
            BufferedImage image = ImageIO.read(new File(imagePath));
            BufferedImage resizedImage = resizeImage(image, asciiWidth);
            String asciiArt = convertToAscii(resizedImage);
            System.out.println(asciiArt);
        } catch (IOException e) {
            System.out.println("Không thể đọc ảnh. Vui lòng kiểm tra đường dẫn: " + imagePath);
            e.printStackTrace();
        }
    }

    // Thay đổi kích thước ảnh để giữ nguyên tỷ lệ khung hình
    private static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth) {
        double aspectRatio = (double) originalImage.getHeight() / originalImage.getWidth();
        int targetHeight = (int) (targetWidth * aspectRatio * 0.55); // Nhân với 0.55 để điều chỉnh tỷ lệ khung hình
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        resizedImage.getGraphics().drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        return resizedImage;
    }

    // Chuyển đổi ảnh thành ASCII
    private static String convertToAscii(BufferedImage image) {
        StringBuilder asciiArt = new StringBuilder();
        int width = image.getWidth();
        int height = image.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = image.getRGB(x, y);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = pixel & 0xff;
                int gray = (red + green + blue) / 3; // Tính độ sáng trung bình
                char asciiChar = ASCII_CHARS.charAt(gray * (ASCII_CHARS.length() - 1) / 255);
                asciiArt.append(asciiChar);
            }
            asciiArt.append("\n");
        }

        return asciiArt.toString();
    }

}
