package br.com.thelegion.legioncommons.time;

public class TimeCounter {

	private long startTime;

	private TimeCounter(long startTime) {
		this.startTime = startTime;
	}

	public static TimeCounter fromNow() {
		return new TimeCounter(System.currentTimeMillis());
	}

	public void reset() {
		this.startTime = System.currentTimeMillis();
	}

	public long elapsedTimeInMillis() {
		return System.currentTimeMillis() - startTime;
	}

	public double elapsedTime() {
		long now = System.currentTimeMillis();
		return (now - startTime) / 1000.0;
	}
}