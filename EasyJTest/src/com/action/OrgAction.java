package com.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.easyj.framework.core.annotation.Controller;
import org.easyj.framework.core.annotation.Model;
import org.easyj.framework.core.annotation.RequestMapping;
import org.easyj.framework.core.annotation.Resource;
import org.easyj.framework.util.PagedList;
import org.easyj.framework.util.ResponseView;

import com.bean.Org;
import com.service.OrgService;
@Controller("/org")
public class OrgAction {
	@Resource(name="orgService")
	private OrgService orgService;
	//分页查询
	@RequestMapping("/pagedList")
	public void getPagedList(HttpServletRequest request,HttpServletResponse response) throws Exception{
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		String params = request.getParameter("params");
		Object[] objs = null;
		if(params != null && params.length() > 0){
			objs = new Object[params.length()];
			int i = 0;
			for(String param :  params.split(",")){
				objs[i++] = param;
			}
		}
		PagedList<Org> p = orgService.getPagedList(pageNum, pageSize, objs);
		request.setAttribute("pagedList",  p);
		ResponseView.responseView("/pagedlist.jsp");
	}
	//查询集合   
	@RequestMapping("/orgByList")
	public void getOrgByList(HttpServletRequest request,HttpServletResponse response) throws Exception{
		request.setAttribute("list", orgService.getOrgByList());
		ResponseView.responseView("/list.jsp");
	} 
	//插入
	@RequestMapping("/insertOrg")
	public void insertOrg(@Model Org org,HttpServletRequest request,HttpServletResponse response) throws Exception{
		System.out.println(org.getId() +  "-"+ org.getName() + "-"+ org.getOrderNum() + "-"+ org.getPid());
		orgService.insertOrg(org);
		getOrgByList(request,response);
	}
	
	//删除
	@RequestMapping("/deleteOrg")
	public void deleteOrg(HttpServletRequest request,HttpServletResponse response) throws Exception{
		int id = Integer.parseInt(request.getParameter("id"));
		 orgService.deleteOrg(id);
		 getOrgByList(request,response);
	}
}
