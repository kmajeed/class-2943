package com.control4.yamba.yambalib;

import com.control4.yamba.yambalib.YambaStatus;

interface IYambaService {
	boolean updateStatus( in String status );
	List<YambaStatus> getTimeline(int records);
}