/*
#
# Copyright 2013 The Trustees of Indiana University
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
# http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either expressed or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#
# -----------------------------------------------------------------
#
# Project: HTRC-UI-AuditAnalyzer
# File:  TailInputStream.java
# Description: TODO
#
# -----------------------------------------------------------------
# 
*/
package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class TailInputStream extends InputStream {
	File file;
	InputStream in;

	public TailInputStream(File file) throws FileNotFoundException {
		super();
		in = new FileInputStream(file);
	}

	@Override
	public int available() throws IOException {
		return in.available();
	}

	public int read() throws IOException {
		int readByte = -1;
		do {
			if (in.available() > 0) {
				if ((readByte = in.read()) != -1) {
					break;
				}
			}
			try {
				Thread.sleep(9);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (readByte == -1);

		return readByte;
	}

	public int read(byte b[], int off, int len) throws IOException {
		if (b == null) {
			throw new NullPointerException();
		} else if (off < 0 || len < 0 || len > b.length - off) {
			throw new IndexOutOfBoundsException();
		} else if (len == 0) {
			return 0;
		}

		int c = read();
		if (c == -1) {
			return -1;
		}
		b[off] = (byte) c;

		return 1;
	}

	public void close() throws IOException {
		in.close();
	}
	
	
	
}