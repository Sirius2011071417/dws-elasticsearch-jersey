package cn.domain;

import java.util.List;

public class Page {
	private long count;
	private int page;
	private int page_size = 15;
	private List<String> list;
	private int offset;

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPage_size() {
		return page_size;
	}

	public void setPage_size(int page_size) {
		this.page_size = page_size;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

}
