import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class ImageLoader {


    public static void main(String[] args) {
        String train_dir = "/content/drive/MyDrive/capstone_dataset/American Sign Language recognition Dataset/training_set";
        String eval_dir = "/content/drive/MyDrive/capstone_dataset/American Sign Language recognition Dataset/test_set";

        String directory = "/path/to/directory";
        List<Mat> images = new ArrayList<Mat>();
        List<Integer> labels = new ArrayList<Integer>();
        String[] uniq_labels = {"label1", "label2", "label3"};

        for (int idx = 0; idx < uniq_labels.length; idx++) {
            String label = uniq_labels[idx];
            String path = directory + "/" + label;
            File folder = new File(path);
            File[] files = folder.listFiles();

            for (File file : files) {
                String filepath = path + "/" + file.getName();
                Mat image = Imgcodecs.imread(filepath);
                Imgproc.resize(image, image, new Size(64, 64));
                images.add(image);
                labels.add(idx);
            }
        }

        Mat[] imagesArray = images.toArray(new Mat[0]);
        int[] labelsArray = labels.stream().mapToInt(i -> i).toArray();

        // use imagesArray and labelsArray for further processing


        List<String> uniq_labels = Arrays.asList(new File(train_dir).list());
        uniq_labels.sort(null);
        List<Image> images = loadImages(train_dir);
        List<String> labels = getLabels(train_dir);

        if (uniq_labels.equals(Arrays.asList(new File(eval_dir).list()))) {
            List<Image> X_eval = loadImages(eval_dir);
            List<String> y_eval = getLabels(eval_dir);
        }

    }
}
