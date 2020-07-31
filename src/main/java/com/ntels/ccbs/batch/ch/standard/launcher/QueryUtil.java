package com.ntels.ccbs.batch.ch.standard.launcher;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;

import javax.sql.DataSource;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

//import com.mysql.jdbc.Connection;
import com.ntels.ccbs.batch.entity.CommonCode;

/**
 * 
* framework의 transaction과는 별도로
* 개별 connection 이 필요한 경우 이용
*
* <PRE>
* 1. ClassName: DBUtil
* 2. FileName : DBUtil.java
* 3. Package : com.skt.ssp.nisf.util
* 4. 작성자 : Administrator
* 5. 작성일 : 2015. 6. 2. 오후 3:45:37
* 6. 변경이력
* 이름 : 일자 : 변경내용
* ————————————————————————————————————————
* Administrator : 2015. 6. 2. : 신규 개발.
* </PRE>
 */
public class QueryUtil {
	
	/** logger. */
	static Logger logger = LoggerFactory.getLogger(QueryUtil.class);
@Autowired
	private static DriverManager dataSource;
	private static String url = "jdbc:mysql://localhost/bss";
	private static String user = "ccbs";
	private static String password = "ccbs2016!01";

	/** sql 저장소. */
	final static HashMap<String, String> sqls = new HashMap<String, String>();
	
	
	/**
	 * 
	* TODO ..
	*
	* @param path 'com/xxx/xxx'
	* @param mapperName xxxxMapper
	* @param type insert ? update ? sql ?
	* @param id attribute id
	* @return String
	 */
	public static String getSqlFromMybatisMapper(String path, String mapperName, String type, String id){
		
		String key = mapperName+"."+id;
		String mpath = path.endsWith("/") ? path : path+"/";
		
		if(sqls.containsKey(key));
		else{
			InputStream is = null;
			try {
				is = Thread.currentThread().getContextClassLoader().getResourceAsStream(mpath+mapperName+".xml");
				Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
	
				doc.getDocumentElement().normalize();
				
				NodeList nodeLst = doc.getElementsByTagName(type);
				for (int s = 0; s < nodeLst.getLength(); s++) {
					Node node = nodeLst.item(s);
					Element fstElmnt = (Element) node;
					if(fstElmnt.getAttribute("id").equals(id)){
						sqls.put(key, fstElmnt.getFirstChild().getNodeValue().replaceAll("#\\{.*?\\}", "?"));
						break;
					}
				}
			}catch(Exception e){
				logger.error("{}", e);
			}finally{
				if(is != null){
					try{
						is.close();
						is = null;
					}catch(Exception e){
						logger.error("{}", e);
					}
				}
			}
		}
		
		String sql = sqls.get(key);
		
		if(sql == null)
			logger.error("{}.{} {} SQL 정보가 없습니다.",path, mapperName, id);
		
		return sql == null ? "" : sql.trim();
	}
	
	/**
	 * ResultSet을 클래스 인스턴스를 생성하여, 
	 * 값 설정 후 리턴 함
	 * 
	 * @param rs ResultSet
	 * @param c Object 클래스
	 * @return T
	 */
	public static <T> T getResultSetToObject(ResultSet rs, Class<T> c) {
        try {
        	
        	Field[] fields = c.getDeclaredFields();
        	
        	//객체 인스턴스 생성
			T x = c.newInstance();
        	
			for(Field f : Arrays.asList(fields)){
						
				// 객체 파라미터 설정
				// setter 객체를 기준으로 하기 때문에 1개로 가정
				Class<?> parameterClass = f.getType();
				
				// setter 메소스 설정
				String methodName = "set"+Character.toUpperCase(f.getName().charAt(0)) + f.getName().substring(1);
				Method m = ReflectionUtils.findMethod(c, methodName, parameterClass);
				
				// camel case 형태의 property 명을 DB 칼럼명 규칙으로 변경
				// mybatis의 <setting name="mapUnderscoreToCamelCase" value="true" /> 기능과 유사
				// ex) userName => USER_NAME
				ReflectionUtils.invokeMethod(m, x, rs.getString(f.getName().replaceAll("([A-Z])", "_$1").toUpperCase()));
			}		
        	
        	return x;
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

	@SuppressWarnings("null")
	public static void main(String[] args) throws SQLException  {
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection conn = null;
		
		System.out.println(QueryUtil.getSqlFromMybatisMapper("com/ntels/ccbs/batch/sample/dao/mapper","CommonCodeMapper", "select","listCommonCodeOracle"));

		conn = DriverManager.getConnection(url, user, password);

		pstmt = conn.prepareStatement(QueryUtil.getSqlFromMybatisMapper("com/ntels/ccbs/batch/sample/dao/mapper"
				,"CommonCodeMapper"
				, "select"
				,"listCommonCodeOracle"));
		rs = pstmt.executeQuery();
		while(rs != null){
			rs.next();
			System.out.println(QueryUtil.getResultSetToObject(rs, CommonCode.class));
		}
	}
	
	
}
