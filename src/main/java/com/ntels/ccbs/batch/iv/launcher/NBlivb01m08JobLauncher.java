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
 * 1. ClassName: NBlivb01m08JobLauncher
 * 2. FileName : NBlivb01m08JobLauncher.java
 * 3. Package  : com.ntels.ccbs.batch.iv.launcher
 * 4. 작성자   : gwhan
 * 5. 작성일   : 2020.07. 14. 오후 4:58:44
 * 6. 변경이력
 *		이름	:	일자	: 변경내용
 *     ————————————————————————————————————————
 *		smyun :	2020.07. 14.	: 신규 개발.
 *      </PRE>
 */
public class NBlivb01m08JobLauncher {

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
		
		CommonJobExecuter jobLauncher = new CommonJobExecuter("spring/batch-AplyAdjBill-tasklet.xml");
		jobLauncher.executeJob(args);

	}
}
