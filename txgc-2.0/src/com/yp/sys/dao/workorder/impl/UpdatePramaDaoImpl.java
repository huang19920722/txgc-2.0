package com.yp.sys.dao.workorder.impl;


import org.springframework.stereotype.Repository;

import com.yp.sys.dao.common.impl.BaseDaoImpl;
import com.yp.sys.dao.workorder.IUpdatePramaDao;

import com.yp.sys.entity.workorder.WorkOrderUpdatePrama;

@Repository(value="updatePramaDao")
public class UpdatePramaDaoImpl extends BaseDaoImpl<WorkOrderUpdatePrama> implements
		IUpdatePramaDao {

	

}
