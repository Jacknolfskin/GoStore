package cn.go.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.zhx.core.bean.TestTb;
import cn.zhx.core.dao.TestTbDao;

/**
 * 测试事务
 * @author lx
 *
 */
@Service("testTbService")
@Transactional
public class TestTbServiceImpl implements TestTbService{

	@Autowired
	private TestTbDao testTbDao;
	//保存
	public void insertTestTb(TestTb testTb){
		testTbDao.insertTestTb(testTb);
		
		//throw new RuntimeException();
	}
}
