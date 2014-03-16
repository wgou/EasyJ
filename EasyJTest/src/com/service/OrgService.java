package com.service;

import java.sql.SQLException;
import java.util.List;

import org.easyj.framework.core.annotation.Resource;
import org.easyj.framework.core.annotation.Service;
import org.easyj.framework.util.PagedList;

import com.bean.Org;
import com.dao.OrgDao;

@Service("orgService")
public class OrgService {

	@Resource(name="orgDao")
	private OrgDao orgDao;
	//分页查询
	public PagedList<Org> getPagedList(int pageNum,int pageSize,Object[] params) throws Exception{
		return orgDao.getPagedList(pageNum, pageSize, params);
	}
	//查询集合
	public List<Org> getOrgByList() throws Exception{
		return orgDao.getOrgByList();
	} 
	//插入
	
	public String insertOrg(Org org) throws Exception{
		return orgDao.insertOrg(org);
	}
	//删除
	public int deleteOrg(int id) throws SQLException{
		return orgDao.deleteOrg(id);
	}
}
