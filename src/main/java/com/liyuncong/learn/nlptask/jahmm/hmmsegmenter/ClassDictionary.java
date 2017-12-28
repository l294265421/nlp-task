package com.liyuncong.learn.nlptask.jahmm.hmmsegmenter;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.liyuncong.learn.nlptask.segmenter.common.HiddenStates;

/**
 * 类别/序号键值对集合。用于把转移矩阵的行列号或者发射矩阵中的行序号对应上类别。
 * @author yuncong
 *
 */
public class ClassDictionary {
	private Map<String, Integer> dictionary;
	
	private ClassDictionary() {
		dictionary = new HashMap<String, Integer>();
		// 词首
		dictionary.put(HiddenStates.BEGIN.getAlias(), 0);
		// 词中
		dictionary.put(HiddenStates.MIDDLE.getAlias(), 1);
		// 词尾
		dictionary.put(HiddenStates.END.getAlias(), 2);
		// 单字成词
		dictionary.put(HiddenStates.SINGLE.getAlias(), 3);
	}
	
	private static class SingletonHolder {
		private static ClassDictionary classDictionary = new ClassDictionary();
	}
	
	public static ClassDictionary getInstance() {
		return SingletonHolder.classDictionary;
	}
	
	public int size() {
		return dictionary.size();
	}
	
	public Set<String> classs() {
		return dictionary.keySet();
	}
	

	public Integer value(String key) {
		return dictionary.get(key);
	}
	
	public String key(Integer value) {
		for (Entry<String, Integer> entry : dictionary.entrySet()) {
			if (entry.getValue() == value) {
				return entry.getKey();
			}
		}
		return null;
	}
}
