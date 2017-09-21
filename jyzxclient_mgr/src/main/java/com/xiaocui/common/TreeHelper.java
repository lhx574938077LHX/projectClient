package com.xiaocui.common;

import java.util.ArrayList;
import java.util.List;

import com.xiaocui.platform.model.ext.NavTreeAuthEx;
import com.xiaocui.platform.model.ext.NavTreeEx;
import com.xiaocui.platform.model.perm.Authority;
import com.xiaocui.platform.model.perm.NavTree;


public class TreeHelper {

	//临时权限
	private Object[] perms ;
//	long[] perms = new long[]{4,31};

	//NavTree表整表信息
	private List<NavTree> nodes;
	
	public void ShowNowTree()
	{
		for(NavTree n:nodes){
			System.out.println(n.getId());
		}
		
	}
	
	public TreeHelper()
	{

	}
	
	public TreeHelper(List<NavTree> nodes,Object[] objects)
	{
		this.nodes = nodes;
		this.perms = objects;
	}
	
	public List<NavTreeEx> genUserPermTree(long fpid)
	{
		//第一步
		excludeNonExistNode();
		nodes = getNavTreeData(nodes,fpid);
		
		List<NavTreeEx> p_NavTreeExs = toNavTreeEx(nodes,fpid);
		
		return p_NavTreeExs;
	}
	
	//增加权限模块使用
	public List<NavTreeAuthEx> genUserPermTreeEx(long fpid)
	{
		//第一步
		excludeNonExistNode();
		nodes = getNavTreeData(nodes,fpid);
		
		List<NavTreeAuthEx> p_NavTreeExs = toNavTreeExAuth(nodes,fpid);
		
		return p_NavTreeExs;
	}
	
	//修改权限模块使用
	public List<NavTreeAuthEx> genUserPermTreeEditEx(long fpid,List<Authority> authorityList)
	{
		//第一步
		excludeNonExistNode();
		nodes = getNavTreeData(nodes,fpid);
		
		List<NavTreeAuthEx> p_NavTreeExs = toNavTreeExAuthEdit(nodes,fpid,authorityList);
		
		return p_NavTreeExs;
	}
	
	//增加权限模块使用
	public List<NavTreeAuthEx> toNavTreeExAuth(List<NavTree> p_NavTrees,long fpid)
	{
		List<NavTreeAuthEx> p_NavTreeExs  = new ArrayList<NavTreeAuthEx>();
		for(NavTree navTree : p_NavTrees)
		{
			if(navTree.getPid() == fpid)
			{
				NavTreeAuthEx navTreeEx = new NavTreeAuthEx();
				//标识列
				navTreeEx.setId(navTree.getId());
			    //父ID
				navTreeEx.setPid(navTree.getPid());
			    //标签ID
				navTreeEx.setOid(navTree.getOid());
			    //是否展开0.false 1.true
				navTreeEx.setExpanded(navTree.getExpanded()==1?true:false);
			    //标签名
				navTreeEx.setText(navTree.getText());
			    //节点类型：0.导航Tab.1导航折叠2.导航树形3.树形节点
				navTreeEx.setNodeType(navTree.getNodeType());
			    //图标css
				navTreeEx.setIconcls(navTree.getIconcls());
			    //排序字段
				navTreeEx.setSort(navTree.getSort());
			    //导航节点
				navTreeEx.setGuid(navTree.getGuid());
			    //功能视图UUID
				navTreeEx.setFuncViewUuid(navTree.getFuncViewUuid());
				
				if(hasChildNode(p_NavTrees,navTree.getId()))
				{
					navTreeEx.setChildren(toNavTreeExAuth(p_NavTrees,navTree.getId()));
				}else{
					navTreeEx.setLeaf(true);
					navTreeEx.setChecked(false);
				}
				
				p_NavTreeExs.add(navTreeEx);
			}
		}
		
		return p_NavTreeExs;
	}
	
	//修改权限模块使用
	public List<NavTreeAuthEx> toNavTreeExAuthEdit(List<NavTree> p_NavTrees,long fpid,List<Authority> authorityList)
	{
		List<NavTreeAuthEx> p_NavTreeExs  = new ArrayList<NavTreeAuthEx>();
		for(NavTree navTree : p_NavTrees)
		{
			if(navTree.getPid() == fpid)
			{
				NavTreeAuthEx navTreeEx = new NavTreeAuthEx();
				//标识列
				navTreeEx.setId(navTree.getId());
			    //父ID
				navTreeEx.setPid(navTree.getPid());
			    //标签ID
				navTreeEx.setOid(navTree.getOid());
			    //是否展开0.false 1.true
				navTreeEx.setExpanded(navTree.getExpanded()==1?true:false);
			    //标签名
				navTreeEx.setText(navTree.getText());
			    //节点类型：0.导航Tab.1导航折叠2.导航树形3.树形节点
				navTreeEx.setNodeType(navTree.getNodeType());
			    //图标css
				navTreeEx.setIconcls(navTree.getIconcls());
			    //排序字段
				navTreeEx.setSort(navTree.getSort());
			    //导航节点
				navTreeEx.setGuid(navTree.getGuid());
			    //功能视图UUID
				navTreeEx.setFuncViewUuid(navTree.getFuncViewUuid());
				
				if(hasChildNode(p_NavTrees,navTree.getId()))
				{
					navTreeEx.setChildren(toNavTreeExAuthEdit(p_NavTrees,navTree.getId(),authorityList));
				}else{
					navTreeEx.setLeaf(true);
					navTreeEx.setChecked(false);
					for(Authority authority:authorityList){
						if(authority.getCode().equals(navTreeEx.getGuid())){
							navTreeEx.setChecked(true);
						}
					}
				}
				
				p_NavTreeExs.add(navTreeEx);
			}
		}
		
		return p_NavTreeExs;
	}
	
	public List<NavTreeEx> toNavTreeEx(List<NavTree> p_NavTrees,long fpid)
	{
		List<NavTreeEx> p_NavTreeExs  = new ArrayList<NavTreeEx>();
		for(NavTree navTree : p_NavTrees)
		{
			if(navTree.getPid() == fpid)
			{
				NavTreeEx navTreeEx = new NavTreeEx();
				//标识列
				navTreeEx.setId(navTree.getId());
			    //父ID
				navTreeEx.setPid(navTree.getPid());
			    //标签ID
				navTreeEx.setOid(navTree.getOid());
			    //是否展开0.false 1.true
				navTreeEx.setExpanded(navTree.getExpanded()==1?true:false);
			    //标签名
				navTreeEx.setText(navTree.getText());
			    //节点类型：0.导航Tab.1导航折叠2.导航树形3.树形节点
				navTreeEx.setNodeType(navTree.getNodeType());
			    //图标css
				navTreeEx.setIconcls(navTree.getIconcls());
			    //排序字段
				navTreeEx.setSort(navTree.getSort());
			    //导航节点
				navTreeEx.setGuid(navTree.getGuid());
			    //功能视图UUID
				navTreeEx.setFuncViewUuid(navTree.getFuncViewUuid());
				
				if(hasChildNode(p_NavTrees,navTree.getId()))
				{
					navTreeEx.setChildren(toNavTreeEx(p_NavTrees,navTree.getId()));
				}else{
					navTreeEx.setLeaf(true);
				}
				
				p_NavTreeExs.add(navTreeEx);
			}
		}
		
		return p_NavTreeExs;
	}
	
	private boolean hasChildNode(List<NavTree> p_NavTrees,Long fpid)
	{
		int count = 0;
		for(NavTree navTree : p_NavTrees)
		{
			if(navTree.getPid() == fpid)
			{
				count ++;
			}
		}
		return count > 0? true:false;
	}
	
	//净化节点
	private void excludeNonExistNode()
	{
		List<NavTree> result = new ArrayList<NavTree>();
		for(int i=0;i<nodes.size();i++)
		{
			if(nodes.get(i).getNodeType() == 3) //3
			{
				if(hasLeafPerm(nodes.get(i).getId()))
				{
					result.add(nodes.get(i));
				}
			}
			else
			{
				result.add(nodes.get(i));
			}
		}
		nodes = result;
	}
	
	//判断是否有权限
	private boolean hasLeafPerm(long id)
	{
		for(int i=0;i<perms.length;i++)
		{
			if(id == (Long)perms[i])
			{
				return true;
			}
		}
		return false;
	}
		
	 //获取指定节点下的所有子节点
	public List<NavTree> getNavTreeData(List<NavTree> p_NavTrees, long fpid)
    {
        List<NavTree> results = new ArrayList<NavTree>();
        results = getAllChildNode(p_NavTrees, results, fpid);
        results = navTreeExcludeAllNullNode(results);
        return results;
    }

    //获取全部子节点
    private List<NavTree> getAllChildNode(List<NavTree> p_NavTrees, List<NavTree> p_results, long fpid)
    {
        List<NavTree> p_Childs = getChildNode(p_NavTrees, fpid);
        if (p_Childs.size() > 0)
        {
            for(NavTree p_Child:p_Childs)
            {
                p_results.add(p_Child);

                if (p_Child.getNodeType() < 3)//  ！=3
                {
                    List<NavTree> p_DeepChilds = getChildNode(p_NavTrees, p_Child.getId());
                    if (p_DeepChilds.size() > 0)
                    {
                    	p_results = getAllChildNode(p_NavTrees, p_results, p_Child.getId());
                    }
                }
            }
        }
        
        return p_results;
    }

    //获取子节点
    private List<NavTree> getChildNode(List<NavTree> p_NavTrees, long fpid)
    {
        List<NavTree> results = new ArrayList<NavTree>();
        for (int i = 0; i < p_NavTrees.size(); i++)
        {
            if (fpid == p_NavTrees.get(i).getPid())
            {
                results.add(p_NavTrees.get(i));
            }
        }
        return results;
    }

    //排除掉树形中的空展开节点
    private List<NavTree> navTreeExcludeAllNullNode(List<NavTree> p_results)
    {
        while (getNullExpandedNodeCount(p_results) > 0)
        {
            for (int i = 0; i < p_results.size(); i++)
            {
                if (p_results.get(i).getNodeType() < 3) //!=3 !=0
                {
                    List<NavTree> p_Childs = getChildNode(p_results, p_results.get(i).getId());
                    if (p_Childs.size() == 0)
                    {
                        p_results.remove(p_results.get(i));
                    }
                }
            }
        }
        return p_results;
    }

    //判断是否存在为空的展开节点
    private int getNullExpandedNodeCount(List<NavTree> p_NavTrees)
    {
        int result = 0;
        for (int i = 0; i < p_NavTrees.size(); i++)
        {
            if (p_NavTrees.get(i).getNodeType() < 3)// ！=3
            {
                List<NavTree> p_Childs = getChildNode(p_NavTrees, p_NavTrees.get(i).getId());
                if (p_Childs.size() == 0)
                {
                    result++;
                }
            }
        }
        return result;
    }
}
