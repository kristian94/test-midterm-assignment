package net.sf.javaanpr.test;

import net.sf.javaanpr.imageanalysis.CarSnapshot;
import net.sf.javaanpr.intelligence.Intelligence;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.*;

//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertThat;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by Kristian Nielsen on 28-03-2018.
 */
@RunWith(Parameterized.class)
public class RecognitionAllIt {

    private static final Logger logger = LoggerFactory.getLogger(RecognitionAllIt.class);

    private String path;
    private String expectedPlate;

    @Parameterized.Parameters
    public static Collection platesAndPaths() throws IOException {
        // results (correct plates)
        String resultsPath = "src/test/resources/results.properties";
        InputStream resultsStream = new FileInputStream(new File(resultsPath));
        Properties properties = new Properties();
        properties.load(resultsStream);
        resultsStream.close();

        // snapshots
        String snapshotDirPath = "src/test/resources/snapshots";
        File snapshotDir = new File(snapshotDirPath);
        File[] snapshotFiles = snapshotDir.listFiles();

        List<String[]> paramList = new ArrayList<String[]>();

        for(int i = 0; i < snapshotFiles.length; i++){
            String path = snapshotFiles[i].getPath();
            String plate = properties.getProperty(snapshotFiles[i].getName());

            // 0 = path, 1 = expectedPlate
            paramList.add(new String[]{path, plate});
        }

        return paramList;
    }

    public RecognitionAllIt(String path, String expectedPlate) {
        this.path = path;
        this.expectedPlate = expectedPlate;
    }

    @Test
    public void intelligenceSingleTest() throws IOException, ParserConfigurationException, SAXException {
        logger.info("#######  RUNNING: intelligenceSingleTest  ######");

        CarSnapshot carSnap = new CarSnapshot(path);
        assertThat("carSnap is null", carSnap, notNullValue());
        assertThat("carSnap.image is null", carSnap.getImage(), notNullValue());

        Intelligence intel = new Intelligence();
        assertThat("intel is null", intel, notNullValue());

        String spz = intel.recognize(carSnap);
        assertThat("The licence plate is null - are you sure the image has the correct color space?", spz, notNullValue());
        assertThat("Plate incorrectly parsed", spz, equalTo(expectedPlate));

        carSnap.close();
    }
}
