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
	//��ҳ��ѯ
	public PagedList<Org> getPagedList(int pageNum,int pageSize,Object[] params) throws Exception{
		return orgDao.getPagedList(pageNum, pageSize, params);
	}
	//��ѯ����
	public List<Org> getOrgByList() throws Exception{
		return orgDao.getOrgByList();
	} 
	//����
	
	public String insertOrg(Org org) throws Exception{
		return orgDao.insertOrg(org);
	}
	//ɾ��
	public int deleteOrg(int id) throws SQLException{
		return orgDao.deleteOrg(id);
	}
}
