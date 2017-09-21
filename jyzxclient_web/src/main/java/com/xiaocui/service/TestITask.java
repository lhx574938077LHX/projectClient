package com.xiaocui.service;

import com.xiaocui.entity.PkgHeader;

public class TestITask implements ITask {
	
	protected ITask itask;
	
	@Override
	public PkgHeader doTask(PkgHeader reqPkg, PkgHeader rspPkg) {
		return null;
	}

	TestITask testITask = (TestITask)this.itask;
	

	public ITask getItask() {
		return itask;
	}

	public void setItask(ITask itask) {
		this.itask = itask;
	}
	
	
}
