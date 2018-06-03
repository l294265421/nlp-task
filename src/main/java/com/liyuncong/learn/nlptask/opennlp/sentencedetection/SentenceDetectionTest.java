package com.liyuncong.learn.nlptask.opennlp.sentencedetection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

/**
 * 模型地址：http://opennlp.sourceforge.net/models-1.5/
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
}
