package com.duongtai.sydiary.configs;

import java.util.Collections;
import java.util.List;

import com.duongtai.sydiary.entities.Diary;

public class SortDiary {
	public static List<Diary> sortByCondition(List<Diary> diaries, String sort){
		
		switch (sort) {
		case Snippets.LAST_EDITED:
			diaries.sort((o1, o2) -> o1.getLast_edited().compareToIgnoreCase(o2.getLast_edited()));
			Collections.reverse(diaries);	
			break;
		case Snippets.CREATED_AT:
			diaries.sort((o1, o2) -> o1.getCreated_at().compareToIgnoreCase(o2.getCreated_at()));
            Collections.reverse(diaries);		
			break;
		case Snippets.A_Z:
			diaries.sort((o1, o2) -> o1.getTitle().compareToIgnoreCase(o2.getTitle()));
			break;
		case Snippets.Z_A:
			diaries.sort((o1, o2) -> o1.getTitle().compareToIgnoreCase(o2.getTitle()));
			Collections.reverse(diaries);		
			break;
		default:
			break;
		}
		
		return diaries;
	}
}
