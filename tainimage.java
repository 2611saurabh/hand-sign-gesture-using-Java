import java.util.Collections;
import java.util.List;

public class tainimage {


    List<Image> X_train, X_test, X_eval;
    List<String> y_train, y_test, y_eval;

Collections.shuffle(images); // randomly shuffle the images
    int split_index = (int) Math.round(images.size() * 0.8); // 80-20 train-test split
    X_train = images.subList(0, split_index);
    X_test = images.subList(split_index, images.size());
    y_train = labels.subList(0, split_index);
    y_test = labels.subList(split_index, labels.size());

    int n = uniq_labels.size();
    int train_n = X_train.size();
    int test_n = X_test.size();
System.out.println("Total number of symbols: " + n);
System.out.println("Number of training images: " + train_n);
System.out.println("Number of testing images: " + test_n);

if (X_eval != null) {
        int eval_n = X_eval.size();
        System.out.println("Number of evaluation images: " + eval_n);
    }

}
