package com.ntels.ccbs.batch.iv.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.ntels.ccbs.batch.common.LazyLoader;
import com.ntels.ccbs.batch.common.dao.LazyLoadingDao;
import com.ntels.ccbs.batch.common.entity.Common;
import com.ntels.ccbs.batch.iv.common.entity.CBillComm;
import com.ntels.ccbs.batch.iv.dao.mapper.NBlivb01m32Mapper;

/**
 * NBliv32m01Dao 클래스
 * 
 * mybatis만을 사용한다면 QueryUtil 상속할 필요 없음.
 * 
 * <PRE>
 * 1. ClassName: NBliv32m01Dao
 * 2. FileName : NBliv32m01Dao.java
 * 3. Package  : com.ntels.ccbs.batch.sample.dao
 * 4. Comment  :
 * 5. 작성자   : Administrator
 * 6. 작성일   : 2016. 3. 7. 오전 9:03:06
 * 7. 변경이력
 *		이름	:	일자	: 변경내용
 *     ———————————————————————————————————
 *		Administrator :	2016. 3. 7.	: 신규 개발.
 * </PRE>
 */
@Repository
public class NBlivb01m32DaoImpl extends LazyLoadingDao implements NBlivb01m32Dao {

	/** NBliv32m01Mapper Autowired.  */
	@Autowired
	private NBlivb01m32Mapper clsMapper;

	/* (non-Javadoc)
	 * @see com.ntels.ccbs.batch.common.dao.JdbcDao#getMapperPath()
	 */
	@Override
	protected String getMapperPath() {
		// TODO Auto-generated method stub
		return "com/ntels/ccbs/batch/iv/dao/mapper";
	}

	/* (non-Javadoc)
	 * @see com.ntels.ccbs.batch.common.dao.JdbcDao#getMapperName()
	 */
	@Override
	protected String getMapperName() {
		// TODO Auto-generated method stub
		return "NBlivb01m32Mapper";
	}
	@Value("${dbms.kind}")
	String dbKind;	
	
	@Override
	public LazyLoader<CBillComm> listInfoDirect( Common comm) 
	{
		return getLazyLoader(dbKind,"listInfo", CBillComm.class, comm);
	}
	
	@Override
    public int saveInfoDirect(List<Object> obj)
	{
		
		return insert(dbKind,"saveInfo", CBillComm.class, obj);
	}


	@Override
	public LazyLoader<CBillComm> listMastInfoDirect( Common comm) 
	{
		return getLazyLoader(dbKind,"listMastInfo", CBillComm.class, comm);
	}

	@Override
    public int saveMastInfoDirect( List<Object> obj)
	{
		return insert(dbKind,"saveMastInfo", CBillComm.class, obj);
	}

	

	@Override
	public LazyLoader<CBillComm> listHotBillInfo(Common comm)
	{
		return getLazyLoader(dbKind,"listHotBillInfo", CBillComm.class, comm);
	}
	
	@Override
	public LazyLoader<CBillComm> listHotBillMastInfo(Common comm)
	{
		return getLazyLoader(dbKind,"listHotBillMastInfo", CBillComm.class, comm);
	}
	
	@Override
	public int saveHotBillInfo( List<Object> obj)
	{
		return insert(dbKind,"saveHotBillInfo", CBillComm.class, obj);
	};

	@Override
	public List<CBillComm> listHotBillAllInfo(Common comm)
	{
		return getList(dbKind,"listHotBillInfo", CBillComm.class, comm);
		
	}
	@Override
	public List<CBillComm> listHotBillMastAllInfo(Common comm)
	{
		return getList(dbKind,"listHotBillMastInfo", CBillComm.class, comm);
		
	}





	
}
