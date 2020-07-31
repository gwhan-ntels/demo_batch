package com.ntels.ccbs.batch.iv.launcher;

import com.ntels.ccbs.batch.common.launcher.CommonJobExecuter;

/**
 *
 * job launcher 샘플.
 * 
 * tasket 예제
 *
 * @see [Optional] 관련정보
 * 
 *      <PRE>
 * 1. ClassName: NBliv03m01JobLauncher
 * 2. FileName : NBliv03m01JobLauncher.java
 * 3. Package  : com.ntels.ccbs.batch.sample.launcher
 * 4. 작성자   : smyun
 * 5. 작성일   : 2015. 3. 18. 오후 1:17:44
 * 6. 변경이력
 *		이름	:	일자	: 변경내용
 *     ————————————————————————————————————————
 *		smyun :	2015. 3. 18.	: 신규 개발.
 *      </PRE>
 */
public class NBlivb01m03JobLauncher {

	/**
	 *
	 * entry method.
	 *
	 * @warning [Optional]함수의 제약사항이나 주의해야 할 점
	 * @param args
	 *            String[]
	 * @throws Exception
	 * @see [Optional]관련 정보(관련 함수, 관련 모듈)
	 */
	public static void main(String[] args) throws Exception {
		
		CommonJobExecuter jobLauncher = new CommonJobExecuter("spring/batch-nBlivb01m03-reader.xml");
		jobLauncher.executeJob(args);

	}
}
