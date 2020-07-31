package com.ntels.ccbs.batch.sample.tasklet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.sample.vo.SampleFile;

@Component
public class SampleFileReader implements ItemReader<SampleFile> {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	private FileReader<SampleFile> fileReader;
	private List<SampleFile> list;
	private boolean isFetchEnd;
	
	private interface DataParser<T> {
		T parse(String data) throws java.text.ParseException;
	}
	
	private class SampleFileParser implements DataParser<SampleFile> {

		private static final int INDEX_USER_ID = 0;
		private static final int INDEX_SEQ = 1;
		private static final int INDEX_PLATFORM = 2;
		private static final int INDEX_NATION_CODE = 3;
		private static final int INDEX_LOGIN_DATE = 4;
		private static final int INDEX_LOGIN_YYYYMMDD = 5;
		private static final int INDEX_LOGIN_HH = 6;
		
		@Override
		public SampleFile parse(String data) throws java.text.ParseException {
			String[] split = data.split(",");
			
			SampleFile file = new SampleFile();
			file.setUserId(split[INDEX_USER_ID]);
			file.setSeq(split[INDEX_SEQ]);
			file.setPlatform(split[INDEX_PLATFORM]);
			file.setNationCode(split[INDEX_NATION_CODE]);
			file.setLoginDate(split[INDEX_LOGIN_DATE]);
			file.setLoginYyyymmdd(split[INDEX_LOGIN_YYYYMMDD]);
			file.setLoginHh(split[INDEX_LOGIN_HH]);
			
			return file;
		}
		
	}
	
	private class FileReader<T> {
		
		Logger logger = LoggerFactory.getLogger(getClass());
		
		private File srcFile;
		private int fetchSize;
		private DataParser<T> dataParser;
		
		private boolean isOpen;
		
		private FileInputStream fis;
		private InputStreamReader isr;
		private BufferedReader br;
		
		public FileReader(File srcFile, int fetchSize, DataParser<T> dataParser) {
			this.srcFile = srcFile;
			this.fetchSize = fetchSize;
			this.dataParser = dataParser;
			
			isOpen = false;
		}
		
		public void open() throws FileNotFoundException {
			if (fis != null || isr != null || br != null) {
				logger.debug("InputStream already open!");
				return;
			}
			
			fis = new FileInputStream(srcFile);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			
			isOpen = true;
		}
		
		public List<T> fetch() throws IOException, java.text.ParseException {
			
			if (isOpen == false) {
				throw new RuntimeException("You must open file before fetch");
			}
			
			List<T> list = new ArrayList<>();
			
			String line = null;
			
			int i = 0;
			
			while (i < fetchSize && (line = br.readLine()) != null) {
				logger.info("line : {}", line);
				list.add(parseItem(line));
				i++;
			}
			
			return list;
		}

		public void close() throws IOException {
			if (br != null) {
				br.close();
			}
			
			if (isr != null) {
				isr.close();
			}
			
			if (fis != null) {
				fis.close();
			}
		}
		
		public T parseItem(String data) throws java.text.ParseException {
			return dataParser.parse(data);
		}
		
	}
	
	@Override
	public SampleFile read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		
		if (fileReader == null) {
			isFetchEnd = false;
			File csvFile = new File("/Users/Cashyalla/Documents/test.csv");
			
			fileReader = new FileReader<>(csvFile, 1000, new SampleFileParser());
			fileReader.open();	
		}
		
		if (list == null || list.isEmpty() == true) {
			if (isFetchEnd == false) {
				list = fileReader.fetch();

				if (list.size() < 1000) {
					fileReader.close();
					isFetchEnd = true;
				}
			}
		}

		if (list != null && list.isEmpty() == false) {
			return list.remove(0);
		}

		return null;
	}

}
