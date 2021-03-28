package Shalazary;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.imageio.ImageIO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Test {
    public static void printXYChart(String filename, XYSeries[] serieses, String title, String xAxisLabel, String yAxisLabel) throws IOException {
        XYSeriesCollection dataset = new XYSeriesCollection();
        for (XYSeries series : serieses)
            dataset.addSeries(series);

        JFreeChart chart = ChartFactory.createXYLineChart(title, xAxisLabel, yAxisLabel, dataset);

        BufferedImage image = chart.createBufferedImage(3840, 2160);

        FileOutputStream file = new FileOutputStream(filename + ".png");
        ImageIO.write(image, "png", file);
        file.close();
    }

    public static void test(String testname, int datasetSize, int timeThreshold,
            Supplier<ArrayList<Integer>> arrayListSupplier, Supplier<LinkedList<Integer>> linkedListSupplier,
            Function<ArrayList<Integer>, Long> arrayListTest, Function<LinkedList<Integer>, Long> linkedListTest)
            throws IOException {
        ArrayList<Integer> arrayList = arrayListSupplier.get();
        LinkedList<Integer> linkedList = linkedListSupplier.get();

        XYSeries arrayListSeries = new XYSeries("ArrayList");
        XYSeries linkedListSeries = new XYSeries("LinkedList");

        long arrayListTotalTime = 0;
        long linkedListTotalTime = 0;

        for (int i = 0; i < datasetSize; i++) {
            // ArrayList
            long leadTime = arrayListTest.apply(arrayList);
            arrayListTotalTime += leadTime;
            if (leadTime <= timeThreshold)
                arrayListSeries.add(arrayList.size(), leadTime);

            // LinkedList
            leadTime = linkedListTest.apply(linkedList);
            linkedListTotalTime += leadTime;
            if (leadTime <= timeThreshold)
                linkedListSeries.add(linkedList.size(), leadTime);
        }

        String title = testname + "\n" + 
                       "ArrayList: Total " + arrayListTotalTime + "ns, " + "Average " + arrayListTotalTime / datasetSize + "ns\n" + 
                       "LinkedList: Total " + linkedListTotalTime + "ns, " + "Average " + linkedListTotalTime / datasetSize + "ns";

        printXYChart(testname, new XYSeries[] { arrayListSeries, linkedListSeries }, title, "List size", "Lead time");
    }

    public static void main(String[] args) throws IOException {
        int datasetSize = 5000;

        TestBuilder<ArrayList<Integer>> arrayListTestBuilder = new TestBuilder<ArrayList<Integer>>();
        TestBuilder<LinkedList<Integer>> linkedListTestBuilder = new TestBuilder<LinkedList<Integer>>();

        // Add
        test("AddFirstTest", datasetSize, 1500, () -> new ArrayList<Integer>(), () -> new LinkedList<Integer>(),
                arrayListTestBuilder.build((ArrayList<Integer> l) -> l.add(0, 0)),
                linkedListTestBuilder.build((LinkedList<Integer> l) -> l.addFirst(0)));

        test("AddMiddleTest", datasetSize, 10000, () -> new ArrayList<Integer>(), () -> new LinkedList<Integer>(),
                arrayListTestBuilder.build((ArrayList<Integer> l) -> l.add(l.size() / 2, 0)),
                linkedListTestBuilder.build((LinkedList<Integer> l) -> l.add(l.size() / 2, 0)));

        test("AddLastTest", datasetSize, 1500, () -> new ArrayList<Integer>(), () -> new LinkedList<Integer>(),
                arrayListTestBuilder.build((ArrayList<Integer> l) -> l.add(l.size(), 0)),
                linkedListTestBuilder.build((LinkedList<Integer> l) -> l.addLast(0)));
            
        // Get
        test("GetFirstTest", datasetSize, 500, () -> new ArrayList<Integer>(), () -> new LinkedList<Integer>(),
                arrayListTestBuilder.build((ArrayList<Integer> l) -> l.get(0), (ArrayList<Integer> l) -> l.add(0) , (ArrayList<Integer> l) -> {}),
                linkedListTestBuilder.build((LinkedList<Integer> l) -> l.getFirst(), (LinkedList<Integer> l) -> l.addLast(0), (LinkedList<Integer> l) -> {}));

        test("GetMiddleTest", datasetSize, 5000, () -> new ArrayList<Integer>(), () -> new LinkedList<Integer>(),
                arrayListTestBuilder.build((ArrayList<Integer> l) -> l.get(l.size() / 2), (ArrayList<Integer> l) -> l.add(0) , (ArrayList<Integer> l) -> {}),
                linkedListTestBuilder.build((LinkedList<Integer> l) -> l.get(l.size() / 2), (LinkedList<Integer> l) -> l.addLast(0), (LinkedList<Integer> l) -> {}));

        test("GetLastTest", datasetSize, 500, () -> new ArrayList<Integer>(), () -> new LinkedList<Integer>(),
                arrayListTestBuilder.build((ArrayList<Integer> l) -> l.get(l.size() - 1), (ArrayList<Integer> l) -> l.add(0) , (ArrayList<Integer> l) -> {}),
                linkedListTestBuilder.build((LinkedList<Integer> l) -> l.getLast(), (LinkedList<Integer> l) -> l.addLast(0), (LinkedList<Integer> l) -> {}));   
        
        // Remove
        test("RemoveFirstTest", datasetSize, 500, () -> new ArrayList<Integer>(), () -> new LinkedList<Integer>(),
                arrayListTestBuilder.build((ArrayList<Integer> l) -> l.remove(0), (ArrayList<Integer> l) -> l.add(0) , (ArrayList<Integer> l) -> l.add(0)),
                linkedListTestBuilder.build((LinkedList<Integer> l) -> l.removeFirst(), (LinkedList<Integer> l) -> l.addLast(0), (LinkedList<Integer> l) -> l.addLast(0)));
        
        test("RemoveMiddleTest", datasetSize, 5000, () -> new ArrayList<Integer>(), () -> new LinkedList<Integer>(),
                arrayListTestBuilder.build((ArrayList<Integer> l) -> l.remove(l.size() / 2), (ArrayList<Integer> l) -> l.add(0) , (ArrayList<Integer> l) -> l.add(0)),
                linkedListTestBuilder.build((LinkedList<Integer> l) -> l.remove(l.size() / 2), (LinkedList<Integer> l) -> l.addLast(0), (LinkedList<Integer> l) -> l.addLast(0)));

        test("RemoveLastTest", datasetSize, 500, () -> new ArrayList<Integer>(), () -> new LinkedList<Integer>(),
                arrayListTestBuilder.build((ArrayList<Integer> l) -> l.remove(l.size() - 1), (ArrayList<Integer> l) -> l.add(0) , (ArrayList<Integer> l) -> l.add(0)),
                linkedListTestBuilder.build((LinkedList<Integer> l) -> l.removeLast(), (LinkedList<Integer> l) -> l.addLast(0), (LinkedList<Integer> l) -> l.addLast(0)));
    }
}
