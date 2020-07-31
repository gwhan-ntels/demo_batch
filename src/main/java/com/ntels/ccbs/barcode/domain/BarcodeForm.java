package com.ntels.ccbs.barcode.domain;

import java.io.File;

import org.krysalis.barcode4j.tools.MimeTypes;

public class BarcodeForm {

	public enum BarcodeType {
		CODABAR("codabar"), CODE39("code39"), POSTNET("postnet"), INTL2OF5("intl2of5")
		, EAN_128("ean-128"), ROYAL_MAIL_CBC("royal-mail-cbc"), EAN_13("ean-13")
		, ITF_14("itf-14"), DATAMATRIX("datamatrix"), CODE128("code128"), PDF417("pdf417")
		, UPC_A("upc-a"), UPC_E("upc-e"), USPS4CB("usps4cb"), EAN_8("ean-8");

		private String type;

		BarcodeType(String type) {
			this.type = type;
		}

		public String getType() {
			return type;
		}
	}

	public enum ImageFormat {
		SVG("svg"), EPS("eps"), TIFF("tiff"), JPEG("jpg"), PNG("png"), GIF("gpf"), BMP("bmp");

		private String format;

		ImageFormat(String format) {
			this.format = format;
		}

		public String getFormat() {
			return format;
		}

		public String getMimeType() {
			return MimeTypes.expandFormat(format);
		}
	}

	private BarcodeType barcodeType;

	private ImageFormat imageFormat;

	private String barcodeData;
	
	private File outputFile;

	public BarcodeType getBarcodeType() {
		return barcodeType;
	}

	public void setBarcodeType(BarcodeType barcodeType) {
		this.barcodeType = barcodeType;
	}

	public ImageFormat getImageFormat() {
		return imageFormat;
	}

	public void setImageFormat(ImageFormat imageFormat) {
		this.imageFormat = imageFormat;
	}

	public String getBarcodeData() {
		return barcodeData;
	}

	public void setBarcodeData(String barcodeData) {
		this.barcodeData = barcodeData;
	}

	public File getOutputFile() {
		return outputFile;
	}

	public void setOutputFile(File outputFile) {
		this.outputFile = outputFile;
	}

}
