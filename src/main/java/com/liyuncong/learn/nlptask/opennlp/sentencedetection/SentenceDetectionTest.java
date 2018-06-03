package com.liyuncong.learn.nlptask.opennlp.sentencedetection;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.sentdetect.SentenceSample;
import opennlp.tools.sentdetect.SentenceSampleStream;
import opennlp.tools.util.InputStreamFactory;
import opennlp.tools.util.MarkableFileInputStreamFactory;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;

/**
 * 模型地址：http://opennlp.sourceforge.net/models-1.5/
 * 
 * @author liyuncong
 *
 */
public class SentenceDetectionTest {
	public static void main(String[] args) {
		try (InputStream modelIn = new FileInputStream("opennlp/model/en-sent.bin")) {
			SentenceModel model = new SentenceModel(modelIn);
			SentenceDetectorME sentenceDetector = new SentenceDetectorME(model);
			String sentences[] = sentenceDetector.sentDetect("  First sentence. Second sentence. ");
			for (String string : sentences) {
				System.out.println(string);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void train(String dataFilepath, String saveModelPath) throws IOException {
		InputStreamFactory inputStreamFactory = new MarkableFileInputStreamFactory(new File(dataFilepath));
		ObjectStream<String> lineStream = new PlainTextByLineStream( inputStreamFactory,
				StandardCharsets.UTF_8);

		SentenceModel model;

		try (ObjectStream< SentenceSample>sampleStream = new SentenceSampleStream(lineStream)) {
			model = SentenceDetectorME.train("en", sampleStream, true, null, TrainingParameters.defaultParams());
		}

		try (OutputStream modelOut = new BufferedOutputStream(new FileOutputStream(saveModelPath))) {
			model.serialize(modelOut);
		}
	}
}
