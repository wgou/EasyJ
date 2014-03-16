package com.dao;

import java.sql.SQLException;
import java.util.List;

import org.easyj.framework.core.annotation.Service;
import org.easyj.framework.core.annotation.Transcation;
import org.easyj.framework.core.data.BaseDao;
import org.easyj.framework.util.PagedList;

import com.bean.Org;

@Service("orgDao")
public class OrgDao extends BaseDao{

	//��ҳ��ѯ
	public PagedList<Org> getPagedList(int pageNum,int pageSize,Object[] params) throws Exception{
		return super.getPagedListData(Org.class, "select * from org order by id", pageNum, pageSize, params);
	}
	//��ѯ����
	public List<Org> getOrgByList() throws Exception{
		return super.queryDataList(Org.class, "select * from org order by id");
	} 
	//����
	@Transcation
	public String insertOrg(Org org) throws Exception{
		return (String) super.insert(org);
	}
	//ɾ��
	public int deleteOrg(int id) throws SQLException{
		return super.delete("delete  from org t where t.id=?", new Object[]{id});
	}
}
