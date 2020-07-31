package com.ntels.ccbs.batch.sample.tasklet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.batch.sample.vo.SampleFile;

@Component
public class SampleFileWriter implements ItemWriter<SampleFile>, StepExecutionListener {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private FileWriter<SampleFile> fileWriter;
	
	private interface DataGenerator<T> {
		String generate(T item);
	}
	
	private class SampleFileGenerator implements DataGenerator<SampleFile> {

		@Override
		public String generate(SampleFile item) {
			
			StringBuffer data = new StringBuffer();
			data.append(item.getLoginHh());
			data.append(",");
			data.append(item.getLoginYyyymmdd());
			data.append(",");
			data.append(item.getLoginDate().toString());
			data.append(",");
			data.append(item.getNationCode());
			data.append(",");
			data.append(item.getPlatform());
			data.append(",");
			data.append(item.getSeq());
			data.append(",");
			data.append(item.getUserId());
			
			return data.toString();
		}
		
	}
	
	private class FileWriter<T> {
		
		private File outFile;
		private DataGenerator<T> dataGenerator;
		
		private boolean isOpen;
		
		private FileOutputStream fos;
		private OutputStreamWriter osw;
		private BufferedWriter bw;
		
		public FileWriter(File outFile, DataGenerator<T> dataGenerator) {
			this.outFile = outFile;
			this.dataGenerator = dataGenerator;
			
			isOpen = false;
		}
		
		public void open() throws FileNotFoundException {
			if (fos != null || osw != null || bw != null) {
				logger.debug("OutputStream already open!");
				return;
			}
			
			fos = new FileOutputStream(outFile);
			osw = new OutputStreamWriter(fos);
			bw = new BufferedWriter(osw);
			
			isOpen = true;
		}
		
		public void writeToFile(List<? extends T> list) throws IOException {
			if (isOpen == false) {
				throw new RuntimeException("You must open stream before write to file");
			}
			
			for (T item : list) {
				bw.write(getLine(item));
				bw.write("\n");
			}
		}
		
		public void close() throws IOException {
			
			if (bw != null) {
				bw.flush();
				bw.close();
			}
			
			if (osw != null) {
				osw.close();
			}
			
			if (fos != null) {
				fos.close();
			}
			
		}
		
		public String getLine(T item) {
			return dataGenerator.generate(item);
		}
		
	}
	
	@Override
	public void write(List<? extends SampleFile> items) throws Exception {
		
		if (fileWriter == null) {
			File outFile = new File("/Users/Cashyalla/Documents/test2.csv");
			fileWriter = new FileWriter<>(outFile, new SampleFileGenerator());
			fileWriter.open();
		}
		
		fileWriter.writeToFile(items);
		
	}

	@Override
	public ExitStatus afterStep(StepExecution arg0) {
		try {
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void beforeStep(StepExecution arg0) {
		
	}

}
