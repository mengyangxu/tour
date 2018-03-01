package com.jmm.drools.aspect;

import java.util.UUID;
import org.apache.logging.log4j.Logger;
import org.slf4j.MDC;

/**
 * 
 * 跟踪日志
 *
 * @author JMM 2017年5月4日
 */
public class TraceLog {
	protected Logger logger;
	protected String method;
	protected long threshold = 0L;
	protected String invokeNo;
	protected String exType = "N";

	protected String beyondThd = "N";
	protected long beginTime;
	protected long endTime;
	protected StringBuilder message = new StringBuilder();

	public TraceLog(Logger logger, String method, long threshold) {
		this.logger = logger;
		this.method = method;
		this.threshold = threshold;
		this.invokeNo = MDC.get("invokeNo");
	}

	public void begin() {
		this.beginTime = System.currentTimeMillis();

		if (this.invokeNo == null)
			MDC.put("invokeNo", UUID.randomUUID().toString().replace("-", ""));
	}

	public void end() {
		if (this.logger.isWarnEnabled()) {
			this.endTime = System.currentTimeMillis();
			long runTime = this.endTime - this.beginTime;

			if ((this.threshold > 0L) && (runTime > this.threshold)) {
				this.beyondThd = "Y";
			}

			this.message.append("ME:").append(this.method).append("|RT:").append(runTime).append("|BT:")
					.append(this.beyondThd).append("|ET:").append(this.exType);
		}
	}

	public void reset(String method, long threshold) {
		this.method = method;
		this.threshold = threshold;
		this.exType = "N";
		this.beyondThd = "N";
	}

	public void setExType(String exType) {
		this.exType = exType;
	}

	public void log() {
		try {
			if (this.logger.isWarnEnabled())
				this.logger.info(this.message.toString());
		} finally {
			if (this.invokeNo == null)
				MDC.remove("invokeNo");
		}
	}

	public String toString() {
		return this.message.toString();
	}
}
