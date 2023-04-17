import org.tensorflow.keras.applications.resnet50.ResNet50;
import org.tensorflow.keras.applications.vgg16.VGG16;
import org.tensorflow.keras.applications.vgg19.VGG19;
import org.tensorflow.keras.layers.*;
import org.tensorflow.keras.models.Model;
import org.tensorflow.keras.preprocessing.image.ImageDataGenerator;
import org.tensorflow.keras.preprocessing.image.ImagePreprocessing;/
import org.tensorflow.keras.preprocessing.image.ImagePreprocessingFactory;
import org.tensorflow.keras.preprocessing.image.InputType;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Example {
    public static void main(String[] args) throws IOException {
        ResNet50 resNet50 = new ResNet50();
        VGG16 vgg16 = new VGG16();
        VGG19 vgg19 = new VGG19();

        Model model = new Model(vgg16.inputs(), vgg16.layers().get(17).output());

        ImagePreprocessing imagePreprocessing = ImagePreprocessingFactory
                .resize(224, 224)
                .preprocessInput(InputType.FLOAT32);

        Input inputLayer = new Input(shape=new int[]{224, 224, 3});
        Lambda lambdaLayer = new Lambda((x) -> x / 255.0f);
        Dense denseLayer = new Dense(128, activation="relu");
        Flatten flattenLayer = new Flatten();
        Dropout dropoutLayer = new Dropout(0.5);

        Model newModel = new Model(inputs=inputLayer,
                outputs=dropoutLayer.apply(
                        flattenLayer.apply(
                                denseLayer.apply(
                                        lambdaLayer.apply(inputLayer)))));

        ImageDataGenerator imageDataGenerator = new ImageDataGenerator(rescale=1.0/255.0,
                shearRange=0.2,
                zoomRange=0.2,
                horizontalFlip=true);

        File file = new File("example.jpg");
        BufferedImage bufferedImage = ImageIO.read(file);
        bufferedImage = imagePreprocessing.preprocess(bufferedImage);

        float[][][] imageArray = new float[1][224][224][3];
        for (int i = 0; i < 224; i++) {
            for (int j = 0; j < 224; j++) {
                int rgb = bufferedImage.getRGB(i, j);
                imageArray[0][i][j][0] = (rgb >> 16) & 0xFF;
                imageArray[0][i][j][1] = (rgb >> 8) & 0xFF;
                imageArray[0][i][j][2] = rgb & 0xFF;
            }
        }

        float[] prediction = newModel.predict(imageArray)[0];
        System.out.println("Prediction: " + Arrays.toString(prediction));
    }
}
