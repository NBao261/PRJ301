package converttest;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.imageio.ImageIO;

public class Converttest {
    private static final String ASCII_CHARS = "@%#*+=-:. ";

    public static void main(String[] args) {
        String userHome = System.getProperty("user.home");
        String imagePath = userHome + "\\Downloads\\anh.jpg"; // Đường dẫn ảnh đầu vào
        String outputPath = userHome + "\\Downloads\\ascii_image.html"; // Đường dẫn file HTML đầu ra
        int asciiWidth = 80; // 👈 Giảm độ rộng ASCII để dễ nhìn hơn

        try {
            BufferedImage image = ImageIO.read(new File(imagePath));
            BufferedImage resizedImage = resizeImage(image, asciiWidth);
            String asciiHtml = convertToHtml(resizedImage);

            // Xuất ra file HTML
            try (PrintWriter out = new PrintWriter(new FileWriter(outputPath))) {
                out.println(asciiHtml);
            }

            System.out.println("✅ ASCII Art đã được lưu vào: " + outputPath);
            System.out.println("Hãy mở file HTML bằng trình duyệt để xem.");
        } catch (IOException e) {
            System.out.println("❌ Không thể đọc ảnh. Vui lòng kiểm tra đường dẫn: " + imagePath);
            e.printStackTrace();
        }
    }

    // Thay đổi kích thước ảnh để giữ nguyên tỷ lệ khung hình
    private static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth) {
        double aspectRatio = (double) originalImage.getHeight() / originalImage.getWidth();
        int targetHeight = (int) (targetWidth * aspectRatio * 0.3); // 👈 Giảm tỷ lệ chiều cao xuống 0.3
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        resizedImage.getGraphics().drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        return resizedImage;
    }

    // Chuyển đổi ảnh thành ASCII và xuất ra HTML
    private static String convertToHtml(BufferedImage image) {
        StringBuilder html = new StringBuilder();
        html.append("<html><head><title>ASCII Art</title></head>");
        html.append("<body style='background-color:black; font-family:monospace; white-space:pre;'>");
        html.append("<pre style='color:white; font-size:7px; line-height:7px;'>\n"); // 👈 Chỉnh font-size nhỏ hơn

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int pixel = image.getRGB(x, y);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = pixel & 0xff;
                int gray = (red + green + blue) / 3; // Tính độ sáng trung bình
                char asciiChar = ASCII_CHARS.charAt(gray * (ASCII_CHARS.length() - 1) / 255);
                html.append(asciiChar);
            }
            html.append("<br>\n"); // Xuống dòng trong HTML
        }

        html.append("</pre></body></html>");
        return html.toString();
    }
}
