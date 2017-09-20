package cn.domain;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Plan {
	private long info_pk;
	private String info_uuid;
	private String location;
	private List<Map<String, String>> custom_tags;
	private String license_number;
	private String construction_company;
	private Date op_time;
	private String info_url;
	private Date create_time;
	private Date release_date;
	private int state;
	private String address;
	private String source;
	private String info_type;
	private String project_name;
	private String title;
	private List<Map<String, String>> related_orgs;
	private String content;

	public long getInfo_pk() {
		return info_pk;
	}

	public void setInfo_pk(long info_pk) {
		this.info_pk = info_pk;
	}

	public String getInfo_uuid() {
		return info_uuid;
	}

	public void setInfo_uuid(String info_uuid) {
		this.info_uuid = info_uuid;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<Map<String, String>> getCustom_tags() {
		return custom_tags;
	}

	public void setCustom_tags(List<Map<String, String>> custom_tags) {
		this.custom_tags = custom_tags;
	}

	public String getLicense_number() {
		return license_number;
	}

	public void setLicense_number(String license_number) {
		this.license_number = license_number;
	}

	public String getConstruction_company() {
		return construction_company;
	}

	public void setConstruction_company(String construction_company) {
		this.construction_company = construction_company;
	}

	public Date getOp_time() {
		return op_time;
	}

	public void setOp_time(Date op_time) {
		this.op_time = op_time;
	}

	public String getInfo_url() {
		return info_url;
	}

	public void setInfo_url(String info_url) {
		this.info_url = info_url;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getRelease_date() {
		return release_date;
	}

	public void setRelease_date(Date release_date) {
		this.release_date = release_date;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getInfo_type() {
		return info_type;
	}

	public void setInfo_type(String info_type) {
		this.info_type = info_type;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Map<String, String>> getRelated_orgs() {
		return related_orgs;
	}

	public void setRelated_orgs(List<Map<String, String>> related_orgs) {
		this.related_orgs = related_orgs;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
