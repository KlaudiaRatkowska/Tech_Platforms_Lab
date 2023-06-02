import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ImageProcessing
{
    private List<Path> files;

    public ImageProcessing(String folderName){
        Path source = Path.of(folderName);
        try (Stream<Path> stream = Files.list(source)){
            files = stream.collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void processImages() {
        for (Path filePath : files) {
            try {
                BufferedImage original = loadImage(filePath.toFile());
                BufferedImage processed = processImage(original);

                String outputFileName = getOutputFileName(filePath.getFileName().toString());
                saveImage(processed, outputFileName);

                System.out.println("Processed image: " + outputFileName);
            } catch (IOException e) {
                System.out.println("Error while processing image " + filePath + ": " + e.getMessage());
            }
        }
    }



    private BufferedImage loadImage(Object image) throws IOException {
        return ImageIO.read((File) image);
    }

    private BufferedImage processImage(BufferedImage original) {
        int height = original.getHeight();
        int width = original.getWidth();
        BufferedImage processedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int rgb = original.getRGB(i, j);
                Color color = new Color(rgb);
                int red = color.getRed();
                int blue = color.getBlue();
                int green = color.getGreen();
                Color outColor = new Color(red, blue, green);
                int outRgb = outColor.getRGB();
                processedImage.setRGB(i, j, outRgb);
            }
        }
        return processedImage;
    }


    private void saveImage(BufferedImage image, String fileName) throws IOException {
        Path outputFolder = Path.of("processedImages");
        if (!Files.exists(outputFolder)) {
            Files.createDirectory(outputFolder);
        }

        Path outputPath = outputFolder.resolve(fileName);
        File outputFile = outputPath.toFile();

        ImageIO.write(image, "img", outputFile);
    }

    private String getOutputFileName(String originalFileName) {
        int index = originalFileName.lastIndexOf(".");
        String name = originalFileName.substring(0, index);
        String extension = originalFileName.substring(index);
        return name + "_processed" + extension;
    }
}