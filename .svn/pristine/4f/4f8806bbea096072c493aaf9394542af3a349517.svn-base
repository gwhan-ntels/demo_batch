package com.ntels.ccbs.batch.common;

import java.io.IOException;

import com.ntels.ccbs.batch.common.entity.Common;

/**
 * 
 */

/**
 * @author ntels_shlee
 *
 */
public class CLog {

	public int nlen;
	public String stLogFileName;
	public CFile clsFile;
	public CUtil clsUtil;
	private static int BUFFER_LEN = 512;

	public CLog() {
		clsUtil = new CUtil();
		clsFile = new CFile();
	}

	public void CloseLog() {
		clsFile.fileClose();
	}

	public void WriteLog(String msg) throws Exception {

		StringBuffer sbuffer = null;
		String tbuffer = new String();

		int i = 0, nlength = 0, nWritelen = 0;

		nlength = msg.length();
		nWritelen = 512;

		// System.out.println("WriteLog :" + msg + ": LEN :" + nlength );

		for (i = 0; i < nlength; i++) {
			nWritelen = nlength - i;
			if (nWritelen > BUFFER_LEN)
				nWritelen = BUFFER_LEN;

			if (sbuffer == null)
				sbuffer = new StringBuffer();

			sbuffer.append("[" + clsUtil.utilGetDateTime(4) + "] ");
			sbuffer.append(msg.substring(i, i + nWritelen));

			// System.out.println("loop ["+i+"]:" + nWritelen + ":" + sbuffer);

			tbuffer = sbuffer.toString();
			clsFile.fileWriteLine(tbuffer);

			sbuffer = null;
			i = i + (nWritelen - 1);
		}
	}

	public boolean OpenLog() {
		boolean bret = true;

		try {
			bret = clsFile.fileOpen(stLogFileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bret;
	}

	public boolean OpenLog(String logFileName) {
		boolean bret = true;

		try {
			bret = clsFile.fileOpen(logFileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bret;
	}

	public String MakeLog(Common clsKey, String path) {
		StringBuffer sbuffer = new StringBuffer();

		// file_name : path /
		// [prgm_id]_[so_id]_[grp_id]_[batch_seq]_[p_seq]_[yyyyMMddHHmmss].log"
		sbuffer.append(path);
		sbuffer.append("\\");
		sbuffer.append(clsKey.getBatPgmId());
		sbuffer.append("_");
		sbuffer.append(clsKey.getSoId());
		sbuffer.append("_");
		sbuffer.append(clsKey.getGrpId());
		sbuffer.append("_");
		sbuffer.append(clsKey.getClcWrkNo());
		sbuffer.append("_");
		sbuffer.append(clsKey.getpSeq());
		sbuffer.append("_");
		sbuffer.append(CUtil.utilGetDateTime(1));
		sbuffer.append(".log");
		stLogFileName = sbuffer.toString();
 
		return stLogFileName;
	}

}