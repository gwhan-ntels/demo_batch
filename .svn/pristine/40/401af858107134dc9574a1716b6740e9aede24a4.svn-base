package com.ntels.ccbs.barcode;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.krysalis.barcode4j.BarcodeClassResolver;
import org.krysalis.barcode4j.DefaultBarcodeClassResolver;
import org.krysalis.barcode4j.HumanReadablePlacement;
import org.krysalis.barcode4j.impl.AbstractBarcodeBean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ntels.ccbs.barcode.domain.BarcodeForm;

/**
 * Barcode4j를 사용한 바코드 생성 클래스
 * BarcodeGenerator = new Barcode4jGenerator();
 * @author Cashyalla
 *
 */
@Component
public class Barcode4jGenerator implements BarcodeGenerator {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 바코드 파일 생성
	 * @param barcodeForm
	 */
	@Override
	public void ganerateToFile(BarcodeForm barcodeForm) {
		AbstractBarcodeBean bean = null;
		
		BarcodeClassResolver resolver = new DefaultBarcodeClassResolver();
		try {
			bean = (AbstractBarcodeBean) resolver.resolveBean(barcodeForm.getBarcodeType().getType()).newInstance();
			bean.setMsgPosition(HumanReadablePlacement.HRP_NONE);

			OutputStream os = null;

			try {
				logger.debug("barcode output file : {}", barcodeForm.getOutputFile().getPath());
				
				if (barcodeForm.getOutputFile().getParentFile().exists() == false) {
					barcodeForm.getOutputFile().getParentFile().mkdirs();
				}

				os = new FileOutputStream(barcodeForm.getOutputFile());
				
				BitmapCanvasProvider canvas = new BitmapCanvasProvider(os 
						, barcodeForm.getImageFormat().getMimeType(), 350
						, BufferedImage.TYPE_BYTE_BINARY, true, 0);

				bean.generateBarcode(canvas, barcodeForm.getBarcodeData());
				
				canvas.finish();
			} finally {
				if (os != null) {
					os.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
