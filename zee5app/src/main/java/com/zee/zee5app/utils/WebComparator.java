package com.zee.zee5app.utils;

import java.util.Comparator;

import com.zee.zee5app.dto.WebSeries;

public class WebComparator implements Comparator<WebSeries> {

	@Override
	public int compare(WebSeries o1, WebSeries o2) {
		// TODO Auto-generated method stub
		return o2.getWebSeriesName().compareTo(o1.getWebSeriesName());
	}


	
}
