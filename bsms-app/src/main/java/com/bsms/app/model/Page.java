package com.bsms.app.model;

/**
 * @ClassName: Page.java
 * @Description: TODO
 * @author: 张凯
 * @Date: 2018年11月16日 上午9:23:53
 * @parma <T>
 */
public class Page {

	// 页码
	private int pageNum;

	// 每页显示条数
	private int pageSize;

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
