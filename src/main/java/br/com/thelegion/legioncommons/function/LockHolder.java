package br.com.thelegion.legioncommons.function;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Supplier;


public class LockHolder {

	private final Lock readLock;
	private final Lock writeLock;

	public LockHolder() {
		this(new ReentrantReadWriteLock());
	}

	public LockHolder(ReadWriteLock readLock) {
		this.readLock = readLock.readLock();
		this.writeLock = readLock.writeLock();
	}

	public <T> T read(Supplier<T> block) {
		readLock.lock();
		try {
			return block.get();
		} finally {
			readLock.unlock();
		}
	}

	public void read(Runnable block) {
		readLock.lock();
		try {
			block.run();
		} finally {
			readLock.unlock();
		}
	}

	public <T> T write(Supplier<T> block) {
		writeLock.lock();
		try {
			return block.get();
		} finally {
			writeLock.unlock();
		}
	}

	public void write(Runnable block) {
		writeLock.lock();
		try {
			block.run();
		} finally {
			writeLock.unlock();
		}
	}

}